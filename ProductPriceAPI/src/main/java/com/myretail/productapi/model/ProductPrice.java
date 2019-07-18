package com.myretail.productapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(value = Include.NON_EMPTY)
@Getter
@Setter
@ToString
public class ProductPrice {
	String errorCode;
	String errorMessage;
	String productId;
	Double currentPrice;
	String currencyCode;
}
