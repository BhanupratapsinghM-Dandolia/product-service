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
public class CurrentPrice {
	private Double value;
	@JsonProperty("currency_code")
	private String currencyCode;
}
