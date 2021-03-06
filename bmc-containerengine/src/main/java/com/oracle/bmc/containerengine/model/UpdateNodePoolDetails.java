/**
 * Copyright (c) 2016, 2018, Oracle and/or its affiliates. All rights reserved.
 */
package com.oracle.bmc.containerengine.model;

/**
 * The properties that define a request to update a node pool.
 * <br/>
 * Note: Objects should always be created or deserialized using the {@link Builder}. This model distinguishes fields
 * that are {@code null} because they are unset from fields that are explicitly set to {@code null}. This is done in
 * the setter methods of the {@link Builder}, which maintain a set of all explicitly set fields called
 * {@link #__explicitlySet__}. The {@link #hashCode()} and {@link #equals(Object)} methods are implemented to take
 * {@link #__explicitlySet__} into account. The constructor, on the other hand, does not set {@link #__explicitlySet__}
 * (since the constructor cannot distinguish explicit {@code null} from unset {@code null}).
 **/
@javax.annotation.Generated(value = "OracleSDKGenerator", comments = "API Version: 20180222")
@lombok.AllArgsConstructor(onConstructor = @__({@Deprecated}))
@lombok.Value
@com.fasterxml.jackson.databind.annotation.JsonDeserialize(
    builder = UpdateNodePoolDetails.Builder.class
)
@com.fasterxml.jackson.annotation.JsonFilter(com.oracle.bmc.http.internal.ExplicitlySetFilter.NAME)
public class UpdateNodePoolDetails {
    @com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    @lombok.experimental.Accessors(fluent = true)
    public static class Builder {
        @com.fasterxml.jackson.annotation.JsonProperty("name")
        private String name;

        public Builder name(String name) {
            this.name = name;
            this.__explicitlySet__.add("name");
            return this;
        }

        @com.fasterxml.jackson.annotation.JsonProperty("kubernetesVersion")
        private String kubernetesVersion;

        public Builder kubernetesVersion(String kubernetesVersion) {
            this.kubernetesVersion = kubernetesVersion;
            this.__explicitlySet__.add("kubernetesVersion");
            return this;
        }

        @com.fasterxml.jackson.annotation.JsonProperty("quantityPerSubnet")
        private Integer quantityPerSubnet;

        public Builder quantityPerSubnet(Integer quantityPerSubnet) {
            this.quantityPerSubnet = quantityPerSubnet;
            this.__explicitlySet__.add("quantityPerSubnet");
            return this;
        }

        @com.fasterxml.jackson.annotation.JsonProperty("initialNodeLabels")
        private java.util.List<KeyValue> initialNodeLabels;

        public Builder initialNodeLabels(java.util.List<KeyValue> initialNodeLabels) {
            this.initialNodeLabels = initialNodeLabels;
            this.__explicitlySet__.add("initialNodeLabels");
            return this;
        }

        @com.fasterxml.jackson.annotation.JsonProperty("subnetIds")
        private java.util.List<String> subnetIds;

        public Builder subnetIds(java.util.List<String> subnetIds) {
            this.subnetIds = subnetIds;
            this.__explicitlySet__.add("subnetIds");
            return this;
        }

        @com.fasterxml.jackson.annotation.JsonIgnore
        private final java.util.Set<String> __explicitlySet__ = new java.util.HashSet<String>();

        public UpdateNodePoolDetails build() {
            UpdateNodePoolDetails __instance__ =
                    new UpdateNodePoolDetails(
                            name,
                            kubernetesVersion,
                            quantityPerSubnet,
                            initialNodeLabels,
                            subnetIds);
            __instance__.__explicitlySet__.addAll(__explicitlySet__);
            return __instance__;
        }

        @com.fasterxml.jackson.annotation.JsonIgnore
        public Builder copy(UpdateNodePoolDetails o) {
            Builder copiedBuilder =
                    name(o.getName())
                            .kubernetesVersion(o.getKubernetesVersion())
                            .quantityPerSubnet(o.getQuantityPerSubnet())
                            .initialNodeLabels(o.getInitialNodeLabels())
                            .subnetIds(o.getSubnetIds());

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

    /**
     * The new name for the cluster. Avoid entering confidential information.
     **/
    @com.fasterxml.jackson.annotation.JsonProperty("name")
    String name;

    /**
     * The version of Kubernetes to which the nodes in the node pool should be upgraded.
     **/
    @com.fasterxml.jackson.annotation.JsonProperty("kubernetesVersion")
    String kubernetesVersion;

    /**
     * The number of nodes to ensure in each subnet.
     **/
    @com.fasterxml.jackson.annotation.JsonProperty("quantityPerSubnet")
    Integer quantityPerSubnet;

    /**
     * A list of key/value pairs to add to nodes after they join the Kubernetes cluster.
     **/
    @com.fasterxml.jackson.annotation.JsonProperty("initialNodeLabels")
    java.util.List<KeyValue> initialNodeLabels;

    /**
     * The OCIDs of the subnets in which to place nodes for this node pool.
     **/
    @com.fasterxml.jackson.annotation.JsonProperty("subnetIds")
    java.util.List<String> subnetIds;

    @com.fasterxml.jackson.annotation.JsonIgnore
    private final java.util.Set<String> __explicitlySet__ = new java.util.HashSet<String>();
}
