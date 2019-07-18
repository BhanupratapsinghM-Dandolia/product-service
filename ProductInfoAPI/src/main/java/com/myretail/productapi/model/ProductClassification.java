
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
    "product_type",
    "product_type_name",
    "item_type_name",
    "item_type"
})
public class ProductClassification {

    @JsonProperty("product_type")
    private String productType;
    @JsonProperty("product_type_name")
    private String productTypeName;
    @JsonProperty("item_type_name")
    private String itemTypeName;
    @JsonProperty("item_type")
    private ItemType itemType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("product_type")
    public String getProductType() {
        return productType;
    }

    @JsonProperty("product_type")
    public void setProductType(String productType) {
        this.productType = productType;
    }

    @JsonProperty("product_type_name")
    public String getProductTypeName() {
        return productTypeName;
    }

    @JsonProperty("product_type_name")
    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    @JsonProperty("item_type_name")
    public String getItemTypeName() {
        return itemTypeName;
    }

    @JsonProperty("item_type_name")
    public void setItemTypeName(String itemTypeName) {
        this.itemTypeName = itemTypeName;
    }

    @JsonProperty("item_type")
    public ItemType getItemType() {
        return itemType;
    }

    @JsonProperty("item_type")
    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
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
