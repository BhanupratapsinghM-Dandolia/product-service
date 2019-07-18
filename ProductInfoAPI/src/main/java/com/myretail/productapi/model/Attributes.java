
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
    "gift_wrapable",
    "has_prop65",
    "is_hazmat",
    "manufacturing_brand",
    "max_order_qty",
    "street_date",
    "media_format",
    "merch_class",
    "merch_classid",
    "merch_subclass",
    "return_method"
})
public class Attributes {

    @JsonProperty("gift_wrapable")
    private String giftWrapable;
    @JsonProperty("has_prop65")
    private String hasProp65;
    @JsonProperty("is_hazmat")
    private String isHazmat;
    @JsonProperty("manufacturing_brand")
    private String manufacturingBrand;
    @JsonProperty("max_order_qty")
    private Integer maxOrderQty;
    @JsonProperty("street_date")
    private String streetDate;
    @JsonProperty("media_format")
    private String mediaFormat;
    @JsonProperty("merch_class")
    private String merchClass;
    @JsonProperty("merch_classid")
    private Integer merchClassid;
    @JsonProperty("merch_subclass")
    private Integer merchSubclass;
    @JsonProperty("return_method")
    private String returnMethod;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("gift_wrapable")
    public String getGiftWrapable() {
        return giftWrapable;
    }

    @JsonProperty("gift_wrapable")
    public void setGiftWrapable(String giftWrapable) {
        this.giftWrapable = giftWrapable;
    }

    @JsonProperty("has_prop65")
    public String getHasProp65() {
        return hasProp65;
    }

    @JsonProperty("has_prop65")
    public void setHasProp65(String hasProp65) {
        this.hasProp65 = hasProp65;
    }

    @JsonProperty("is_hazmat")
    public String getIsHazmat() {
        return isHazmat;
    }

    @JsonProperty("is_hazmat")
    public void setIsHazmat(String isHazmat) {
        this.isHazmat = isHazmat;
    }

    @JsonProperty("manufacturing_brand")
    public String getManufacturingBrand() {
        return manufacturingBrand;
    }

    @JsonProperty("manufacturing_brand")
    public void setManufacturingBrand(String manufacturingBrand) {
        this.manufacturingBrand = manufacturingBrand;
    }

    @JsonProperty("max_order_qty")
    public Integer getMaxOrderQty() {
        return maxOrderQty;
    }

    @JsonProperty("max_order_qty")
    public void setMaxOrderQty(Integer maxOrderQty) {
        this.maxOrderQty = maxOrderQty;
    }

    @JsonProperty("street_date")
    public String getStreetDate() {
        return streetDate;
    }

    @JsonProperty("street_date")
    public void setStreetDate(String streetDate) {
        this.streetDate = streetDate;
    }

    @JsonProperty("media_format")
    public String getMediaFormat() {
        return mediaFormat;
    }

    @JsonProperty("media_format")
    public void setMediaFormat(String mediaFormat) {
        this.mediaFormat = mediaFormat;
    }

    @JsonProperty("merch_class")
    public String getMerchClass() {
        return merchClass;
    }

    @JsonProperty("merch_class")
    public void setMerchClass(String merchClass) {
        this.merchClass = merchClass;
    }

    @JsonProperty("merch_classid")
    public Integer getMerchClassid() {
        return merchClassid;
    }

    @JsonProperty("merch_classid")
    public void setMerchClassid(Integer merchClassid) {
        this.merchClassid = merchClassid;
    }

    @JsonProperty("merch_subclass")
    public Integer getMerchSubclass() {
        return merchSubclass;
    }

    @JsonProperty("merch_subclass")
    public void setMerchSubclass(Integer merchSubclass) {
        this.merchSubclass = merchSubclass;
    }

    @JsonProperty("return_method")
    public String getReturnMethod() {
        return returnMethod;
    }

    @JsonProperty("return_method")
    public void setReturnMethod(String returnMethod) {
        this.returnMethod = returnMethod;
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
