/**
 * Copyright (c) 2016, 2018, Oracle and/or its affiliates. All rights reserved.
 */
package com.oracle.bmc.auth;

import com.oracle.bmc.auth.internal.AuthUtils;
import com.oracle.bmc.auth.internal.FederationClient;
import com.oracle.bmc.auth.internal.X509FederationClient;
import com.oracle.bmc.http.ClientConfigurator;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import java.util.Set;

/**
 * Base class for authentication details providers that make remote requests.
 */
public class AbstractRequestingAuthenticationDetailsProvider
        implements BasicAuthenticationDetailsProvider {
    protected final FederationClient federationClient;
    protected final CachingSessionKeySupplier sessionKeySupplier;

    public AbstractRequestingAuthenticationDetailsProvider(
            FederationClient federationClient, SessionKeySupplier sessionKeySupplier) {
        this.federationClient = federationClient;
        this.sessionKeySupplier = new CachingSessionKeySupplier(sessionKeySupplier);
    }

    /**
     * Base class for builders.
     * @param <B> builder class
     */
    protected abstract static class Builder<B extends Builder<B>> {
        protected Set<X509CertificateSupplier> intermediateCertificateSuppliers;
        protected SessionKeySupplier sessionKeySupplier;
        protected ClientConfigurator federationClientConfigurator;
        protected FederationClient federationClient;

        /**
         * Configures the custom SessionKeySupplier to use.
         */
        public B sessionKeySupplier(SessionKeySupplier sessionKeySupplier) {
            this.sessionKeySupplier = sessionKeySupplier;
            return (B) this;
        }

        /**
         * Configures the set of intermediate certificate suppliers to use, if any.
         */
        public B intermediateCertificateSuppliers(
                Set<X509CertificateSupplier> intermediateCertificateSuppliers) {
            this.intermediateCertificateSuppliers = intermediateCertificateSuppliers;
            return (B) this;
        }

        /**
         * Configures the ClientConfigurator to set on the REST client used by the
         * federation client, if any.
         */
        public B federationClientConfigurator(ClientConfigurator clientConfigurator) {
            this.federationClientConfigurator = clientConfigurator;
            return (B) this;
        }

        protected SessionKeySupplier getSessionKeySupplier() {
            return sessionKeySupplier != null ? sessionKeySupplier : new SessionKeySupplierImpl();
        }

        protected void buildFederationClient(
                String federationEndpoint,
                String tenancyId,
                X509CertificateSupplier leafCertificateSupplier) {
            federationClient =
                    new X509FederationClient(
                            federationEndpoint,
                            tenancyId,
                            leafCertificateSupplier,
                            getSessionKeySupplier(),
                            intermediateCertificateSuppliers,
                            federationClientConfigurator);
        }
    }

    @Override
    public String getKeyId() {
        return "ST$" + federationClient.getSecurityToken();
    }

    @Override
    public InputStream getPrivateKey() {
        return new ByteArrayInputStream(sessionKeySupplier.getPrivateKeyBytes());
    }

    @Deprecated
    @Override
    public String getPassPhrase() {
        return null;
    }
    /**
     * Returns the optional pass phrase for the (encrypted) private key, as a character array.
     *
     * @return The pass phrase as character array, or null if not applicable
     */
    @Override
    public char[] getPassphraseCharacters() {
        return null;
    }

    /**
     * Helper class to cache the private key as bytes so we don't have to parse it every time.
     * The key only changes during calls to refresh.
     * <p>
     * All methods in this class that are called outside of this class should be synchronized.
     */
    protected static class CachingSessionKeySupplier implements SessionKeySupplier {
        private final SessionKeySupplier delegate;
        private RSAPrivateKey lastPrivateKey = null;
        private byte[] privateKeyBytes = null;

        protected CachingSessionKeySupplier(final SessionKeySupplier delegate) {
            this.delegate = delegate;
            this.setPrivateKeyBytes(delegate.getPrivateKey());
        }

        @Override
        public synchronized RSAPublicKey getPublicKey() {
            return delegate.getPublicKey();
        }

        @Override
        public synchronized RSAPrivateKey getPrivateKey() {
            return delegate.getPrivateKey();
        }

        @Override
        public synchronized void refreshKeys() {
            delegate.refreshKeys();
        }

        // private keys can be refreshed asynchronously, always update first
        protected synchronized byte[] getPrivateKeyBytes() {
            setPrivateKeyBytes(this.getPrivateKey());
            return this.privateKeyBytes;
        }

        private void setPrivateKeyBytes(RSAPrivateKey privateKey) {
            // quick shallow ref check only
            if (privateKey != null && privateKey != lastPrivateKey) {
                lastPrivateKey = privateKey;
                this.privateKeyBytes = AuthUtils.toByteArrayFromRSAPrivateKey(privateKey);
            }
        }
    }

    /**
     * This is a helper class to generate in-memory temporary session keys.
     * <p>
     * The thread safety of this class is ensured through the Caching class above
     * which synchronizes on all methods.
     */
    protected static class SessionKeySupplierImpl implements SessionKeySupplier {
        private final static KeyPairGenerator GENERATOR;
        private KeyPair keyPair = null;

        static {
            try {
                GENERATOR = KeyPairGenerator.getInstance("RSA");
                GENERATOR.initialize(2048);
            } catch (NoSuchAlgorithmException e) {
                throw new Error(e.getMessage());
            }
        }

        protected SessionKeySupplierImpl() {
            this.keyPair = GENERATOR.generateKeyPair();
        }

        /**
         * Gets the public key
         * @return the public key, not null
         */
        @Override
        public RSAPublicKey getPublicKey() {
            return (RSAPublicKey) keyPair.getPublic();
        }

        /**
         * Gets the private key
         * @return the private key, not null
         */
        @Override
        public RSAPrivateKey getPrivateKey() {
            return (RSAPrivateKey) keyPair.getPrivate();
        }

        @Override
        public void refreshKeys() {
            this.keyPair = GENERATOR.generateKeyPair();
        }
    }
}
