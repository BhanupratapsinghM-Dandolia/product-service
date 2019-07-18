
package com.myretail.productapi.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "images",
    "sales_classification_nodes"
})
public class Enrichment {

    @JsonProperty("images")
    private List<Image> images = null;
    @JsonProperty("sales_classification_nodes")
    private List<SalesClassificationNode> salesClassificationNodes = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("images")
    public List<Image> getImages() {
        return images;
    }

    @JsonProperty("images")
    public void setImages(List<Image> images) {
        this.images = images;
    }

    @JsonProperty("sales_classification_nodes")
    public List<SalesClassificationNode> getSalesClassificationNodes() {
        return salesClassificationNodes;
    }

    @JsonProperty("sales_classification_nodes")
    public void setSalesClassificationNodes(List<SalesClassificationNode> salesClassificationNodes) {
        this.salesClassificationNodes = salesClassificationNodes;
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
