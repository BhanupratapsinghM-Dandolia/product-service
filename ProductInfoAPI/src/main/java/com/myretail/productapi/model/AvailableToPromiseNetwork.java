
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
    "product_id",
    "id_type",
    "available_to_promise_quantity",
    "street_date",
    "availability",
    "online_available_to_promise_quantity",
    "stores_available_to_promise_quantity",
    "availability_status",
    "multichannel_options",
    "is_infinite_inventory",
    "loyalty_availability_status",
    "loyalty_purchase_start_date_time",
    "is_loyalty_purchase_enabled",
    "is_out_of_stock_in_all_store_locations",
    "is_out_of_stock_in_all_online_locations"
})
public class AvailableToPromiseNetwork {

    @JsonProperty("product_id")
    private String productId;
    @JsonProperty("id_type")
    private String idType;
    @JsonProperty("available_to_promise_quantity")
    private Float availableToPromiseQuantity;
    @JsonProperty("street_date")
    private String streetDate;
    @JsonProperty("availability")
    private String availability;
    @JsonProperty("online_available_to_promise_quantity")
    private Float onlineAvailableToPromiseQuantity;
    @JsonProperty("stores_available_to_promise_quantity")
    private Float storesAvailableToPromiseQuantity;
    @JsonProperty("availability_status")
    private String availabilityStatus;
    @JsonProperty("multichannel_options")
    private List<Object> multichannelOptions = null;
    @JsonProperty("is_infinite_inventory")
    private Boolean isInfiniteInventory;
    @JsonProperty("loyalty_availability_status")
    private String loyaltyAvailabilityStatus;
    @JsonProperty("loyalty_purchase_start_date_time")
    private String loyaltyPurchaseStartDateTime;
    @JsonProperty("is_loyalty_purchase_enabled")
    private Boolean isLoyaltyPurchaseEnabled;
    @JsonProperty("is_out_of_stock_in_all_store_locations")
    private Boolean isOutOfStockInAllStoreLocations;
    @JsonProperty("is_out_of_stock_in_all_online_locations")
    private Boolean isOutOfStockInAllOnlineLocations;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("product_id")
    public String getProductId() {
        return productId;
    }

    @JsonProperty("product_id")
    public void setProductId(String productId) {
        this.productId = productId;
    }

    @JsonProperty("id_type")
    public String getIdType() {
        return idType;
    }

    @JsonProperty("id_type")
    public void setIdType(String idType) {
        this.idType = idType;
    }

    @JsonProperty("available_to_promise_quantity")
    public Float getAvailableToPromiseQuantity() {
        return availableToPromiseQuantity;
    }

    @JsonProperty("available_to_promise_quantity")
    public void setAvailableToPromiseQuantity(Float availableToPromiseQuantity) {
        this.availableToPromiseQuantity = availableToPromiseQuantity;
    }

    @JsonProperty("street_date")
    public String getStreetDate() {
        return streetDate;
    }

    @JsonProperty("street_date")
    public void setStreetDate(String streetDate) {
        this.streetDate = streetDate;
    }

    @JsonProperty("availability")
    public String getAvailability() {
        return availability;
    }

    @JsonProperty("availability")
    public void setAvailability(String availability) {
        this.availability = availability;
    }

    @JsonProperty("online_available_to_promise_quantity")
    public Float getOnlineAvailableToPromiseQuantity() {
        return onlineAvailableToPromiseQuantity;
    }

    @JsonProperty("online_available_to_promise_quantity")
    public void setOnlineAvailableToPromiseQuantity(Float onlineAvailableToPromiseQuantity) {
        this.onlineAvailableToPromiseQuantity = onlineAvailableToPromiseQuantity;
    }

    @JsonProperty("stores_available_to_promise_quantity")
    public Float getStoresAvailableToPromiseQuantity() {
        return storesAvailableToPromiseQuantity;
    }

    @JsonProperty("stores_available_to_promise_quantity")
    public void setStoresAvailableToPromiseQuantity(Float storesAvailableToPromiseQuantity) {
        this.storesAvailableToPromiseQuantity = storesAvailableToPromiseQuantity;
    }

    @JsonProperty("availability_status")
    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    @JsonProperty("availability_status")
    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    @JsonProperty("multichannel_options")
    public List<Object> getMultichannelOptions() {
        return multichannelOptions;
    }

    @JsonProperty("multichannel_options")
    public void setMultichannelOptions(List<Object> multichannelOptions) {
        this.multichannelOptions = multichannelOptions;
    }

    @JsonProperty("is_infinite_inventory")
    public Boolean getIsInfiniteInventory() {
        return isInfiniteInventory;
    }

    @JsonProperty("is_infinite_inventory")
    public void setIsInfiniteInventory(Boolean isInfiniteInventory) {
        this.isInfiniteInventory = isInfiniteInventory;
    }

    @JsonProperty("loyalty_availability_status")
    public String getLoyaltyAvailabilityStatus() {
        return loyaltyAvailabilityStatus;
    }

    @JsonProperty("loyalty_availability_status")
    public void setLoyaltyAvailabilityStatus(String loyaltyAvailabilityStatus) {
        this.loyaltyAvailabilityStatus = loyaltyAvailabilityStatus;
    }

    @JsonProperty("loyalty_purchase_start_date_time")
    public String getLoyaltyPurchaseStartDateTime() {
        return loyaltyPurchaseStartDateTime;
    }

    @JsonProperty("loyalty_purchase_start_date_time")
    public void setLoyaltyPurchaseStartDateTime(String loyaltyPurchaseStartDateTime) {
        this.loyaltyPurchaseStartDateTime = loyaltyPurchaseStartDateTime;
    }

    @JsonProperty("is_loyalty_purchase_enabled")
    public Boolean getIsLoyaltyPurchaseEnabled() {
        return isLoyaltyPurchaseEnabled;
    }

    @JsonProperty("is_loyalty_purchase_enabled")
    public void setIsLoyaltyPurchaseEnabled(Boolean isLoyaltyPurchaseEnabled) {
        this.isLoyaltyPurchaseEnabled = isLoyaltyPurchaseEnabled;
    }

    @JsonProperty("is_out_of_stock_in_all_store_locations")
    public Boolean getIsOutOfStockInAllStoreLocations() {
        return isOutOfStockInAllStoreLocations;
    }

    @JsonProperty("is_out_of_stock_in_all_store_locations")
    public void setIsOutOfStockInAllStoreLocations(Boolean isOutOfStockInAllStoreLocations) {
        this.isOutOfStockInAllStoreLocations = isOutOfStockInAllStoreLocations;
    }

    @JsonProperty("is_out_of_stock_in_all_online_locations")
    public Boolean getIsOutOfStockInAllOnlineLocations() {
        return isOutOfStockInAllOnlineLocations;
    }

    @JsonProperty("is_out_of_stock_in_all_online_locations")
    public void setIsOutOfStockInAllOnlineLocations(Boolean isOutOfStockInAllOnlineLocations) {
        this.isOutOfStockInAllOnlineLocations = isOutOfStockInAllOnlineLocations;
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
