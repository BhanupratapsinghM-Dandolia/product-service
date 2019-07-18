package com.myretail.productapi.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Price {
	
	@NotNull
	private Double value;
	
	@NotNull
	@JsonProperty("currency_code")
	private String currencyCode;

}
