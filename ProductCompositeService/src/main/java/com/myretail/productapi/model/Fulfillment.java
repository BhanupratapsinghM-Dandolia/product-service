
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
    "is_po_box_prohibited"
})
public class Fulfillment {

    @JsonProperty("is_po_box_prohibited")
    private Boolean isPoBoxProhibited;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("is_po_box_prohibited")
    public Boolean getIsPoBoxProhibited() {
        return isPoBoxProhibited;
    }

    @JsonProperty("is_po_box_prohibited")
    public void setIsPoBoxProhibited(Boolean isPoBoxProhibited) {
        this.isPoBoxProhibited = isPoBoxProhibited;
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
