
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
    "brand",
    "manufacturer_brand",
    "facet_id"
})
public class ProductBrand {

    @JsonProperty("brand")
    private String brand;
    @JsonProperty("manufacturer_brand")
    private String manufacturerBrand;
    @JsonProperty("facet_id")
    private String facetId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("brand")
    public String getBrand() {
        return brand;
    }

    @JsonProperty("brand")
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @JsonProperty("manufacturer_brand")
    public String getManufacturerBrand() {
        return manufacturerBrand;
    }

    @JsonProperty("manufacturer_brand")
    public void setManufacturerBrand(String manufacturerBrand) {
        this.manufacturerBrand = manufacturerBrand;
    }

    @JsonProperty("facet_id")
    public String getFacetId() {
        return facetId;
    }

    @JsonProperty("facet_id")
    public void setFacetId(String facetId) {
        this.facetId = facetId;
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
