/**
 * Copyright (c) 2016, 2018, Oracle and/or its affiliates. All rights reserved.
 */
package com.oracle.bmc.loadbalancer.model;

/**
 * The configuration details for a load balancer backend set.
 * For more information on backend set configuration, see
 * [Managing Backend Sets](https://docs.us-phoenix-1.oraclecloud.com/Content/Balance/tasks/managingbackendsets.htm).
 *
 * <br/>
 * Note: Objects should always be created or deserialized using the {@link Builder}. This model distinguishes fields
 * that are {@code null} because they are unset from fields that are explicitly set to {@code null}. This is done in
 * the setter methods of the {@link Builder}, which maintain a set of all explicitly set fields called
 * {@link #__explicitlySet__}. The {@link #hashCode()} and {@link #equals(Object)} methods are implemented to take
 * {@link #__explicitlySet__} into account. The constructor, on the other hand, does not set {@link #__explicitlySet__}
 * (since the constructor cannot distinguish explicit {@code null} from unset {@code null}).
 **/
@javax.annotation.Generated(value = "OracleSDKGenerator", comments = "API Version: 20170115")
@lombok.AllArgsConstructor(onConstructor = @__({@Deprecated}))
@lombok.Value
@com.fasterxml.jackson.databind.annotation.JsonDeserialize(
    builder = BackendSetDetails.Builder.class
)
@com.fasterxml.jackson.annotation.JsonFilter(com.oracle.bmc.http.internal.ExplicitlySetFilter.NAME)
public class BackendSetDetails {
    @com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    @lombok.experimental.Accessors(fluent = true)
    public static class Builder {
        @com.fasterxml.jackson.annotation.JsonProperty("backends")
        private java.util.List<BackendDetails> backends;

        public Builder backends(java.util.List<BackendDetails> backends) {
            this.backends = backends;
            this.__explicitlySet__.add("backends");
            return this;
        }

        @com.fasterxml.jackson.annotation.JsonProperty("healthChecker")
        private HealthCheckerDetails healthChecker;

        public Builder healthChecker(HealthCheckerDetails healthChecker) {
            this.healthChecker = healthChecker;
            this.__explicitlySet__.add("healthChecker");
            return this;
        }

        @com.fasterxml.jackson.annotation.JsonProperty("policy")
        private String policy;

        public Builder policy(String policy) {
            this.policy = policy;
            this.__explicitlySet__.add("policy");
            return this;
        }

        @com.fasterxml.jackson.annotation.JsonProperty("sessionPersistenceConfiguration")
        private SessionPersistenceConfigurationDetails sessionPersistenceConfiguration;

        public Builder sessionPersistenceConfiguration(
                SessionPersistenceConfigurationDetails sessionPersistenceConfiguration) {
            this.sessionPersistenceConfiguration = sessionPersistenceConfiguration;
            this.__explicitlySet__.add("sessionPersistenceConfiguration");
            return this;
        }

        @com.fasterxml.jackson.annotation.JsonProperty("sslConfiguration")
        private SSLConfigurationDetails sslConfiguration;

        public Builder sslConfiguration(SSLConfigurationDetails sslConfiguration) {
            this.sslConfiguration = sslConfiguration;
            this.__explicitlySet__.add("sslConfiguration");
            return this;
        }

        @com.fasterxml.jackson.annotation.JsonIgnore
        private final java.util.Set<String> __explicitlySet__ = new java.util.HashSet<String>();

        public BackendSetDetails build() {
            BackendSetDetails __instance__ =
                    new BackendSetDetails(
                            backends,
                            healthChecker,
                            policy,
                            sessionPersistenceConfiguration,
                            sslConfiguration);
            __instance__.__explicitlySet__.addAll(__explicitlySet__);
            return __instance__;
        }

        @com.fasterxml.jackson.annotation.JsonIgnore
        public Builder copy(BackendSetDetails o) {
            Builder copiedBuilder =
                    backends(o.getBackends())
                            .healthChecker(o.getHealthChecker())
                            .policy(o.getPolicy())
                            .sessionPersistenceConfiguration(o.getSessionPersistenceConfiguration())
                            .sslConfiguration(o.getSslConfiguration());

            copiedBuilder.__explicitlySet__.retainAll(o.__explicitlySet__);
            return copiedBuilder;
        }
    }

    /**
     * Create a new builder.
     */
    public static Builder builder() {
        return new Builder();
    }

    @com.fasterxml.jackson.annotation.JsonProperty("backends")
    java.util.List<BackendDetails> backends;

    @com.fasterxml.jackson.annotation.JsonProperty("healthChecker")
    HealthCheckerDetails healthChecker;

    /**
     * The load balancer policy for the backend set. To get a list of available policies, use the
     * {@link #listPolicies(ListPoliciesRequest) listPolicies} operation.
     * <p>
     * Example: `LEAST_CONNECTIONS`
     *
     **/
    @com.fasterxml.jackson.annotation.JsonProperty("policy")
    String policy;

    @com.fasterxml.jackson.annotation.JsonProperty("sessionPersistenceConfiguration")
    SessionPersistenceConfigurationDetails sessionPersistenceConfiguration;

    @com.fasterxml.jackson.annotation.JsonProperty("sslConfiguration")
    SSLConfigurationDetails sslConfiguration;

    @com.fasterxml.jackson.annotation.JsonIgnore
    private final java.util.Set<String> __explicitlySet__ = new java.util.HashSet<String>();
}
