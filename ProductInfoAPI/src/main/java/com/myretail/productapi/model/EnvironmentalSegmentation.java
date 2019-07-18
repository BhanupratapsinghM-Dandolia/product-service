
package com.myretail.productapi.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "is_lead_disclosure"
})
public class EnvironmentalSegmentation {

    @JsonProperty("is_lead_disclosure")
    private Boolean isLeadDisclosure;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("is_lead_disclosure")
    public Boolean getIsLeadDisclosure() {
        return isLeadDisclosure;
    }

    @JsonProperty("is_lead_disclosure")
    public void setIsLeadDisclosure(Boolean isLeadDisclosure) {
        this.isLeadDisclosure = isLeadDisclosure;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
