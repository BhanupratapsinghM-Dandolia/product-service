package com.myretail.productapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(value = Include.NON_EMPTY)
@Getter
@Setter
@ToString
public class PriceUpdatePayload {
	private String id;
	private String name;
	@JsonProperty("current_price")
	private CurrentPrice currentPrice;
}
