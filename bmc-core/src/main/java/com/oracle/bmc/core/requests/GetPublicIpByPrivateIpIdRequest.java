/**
 * Copyright (c) 2016, 2018, Oracle and/or its affiliates. All rights reserved.
 */
package com.oracle.bmc.core.requests;

import com.oracle.bmc.core.model.*;

@javax.annotation.Generated(value = "OracleSDKGenerator", comments = "API Version: 20160918")
@lombok.Builder(builderClassName = "Builder", buildMethodName = "buildWithoutInvocationCallback")
@lombok.Getter
public class GetPublicIpByPrivateIpIdRequest extends com.oracle.bmc.requests.BmcRequest {

    /**
     * Private IP details for fetching the public IP.
     */
    private GetPublicIpByPrivateIpIdDetails getPublicIpByPrivateIpIdDetails;

    public static class Builder {
        private com.oracle.bmc.util.internal.Consumer<javax.ws.rs.client.Invocation.Builder>
                invocationCallback = null;

        /**
         * Set the invocation callback for the request to be built.
         * @param invocationCallback the invocation callback to be set for the request
         * @return this builder instance
         */
        public Builder invocationCallback(
                com.oracle.bmc.util.internal.Consumer<javax.ws.rs.client.Invocation.Builder>
                        invocationCallback) {
            this.invocationCallback = invocationCallback;
            return this;
        }

        /**
         * Copy method to populate the builder with values from the given instance.
         * @return this builder instance
         */
        public Builder copy(GetPublicIpByPrivateIpIdRequest o) {
            getPublicIpByPrivateIpIdDetails(o.getGetPublicIpByPrivateIpIdDetails());
            invocationCallback(o.getInvocationCallback());
            return this;
        }

        /**
         * Build the instance of GetPublicIpByPrivateIpIdRequest as configured by this builder
         *
         * Note that this method takes calls to {@link Builder#invocationCallback(com.oracle.bmc.util.internal.Consumer)} into account,
         * while the method {@link Builder#buildWithoutInvocationCallback} does not.
         *
         * This is the preferred method to build an instance.
         *
         * @return instance of GetPublicIpByPrivateIpIdRequest
         */
        public GetPublicIpByPrivateIpIdRequest build() {
            GetPublicIpByPrivateIpIdRequest request = buildWithoutInvocationCallback();
            request.setInvocationCallback(invocationCallback);
            return request;
        }
    }
}
