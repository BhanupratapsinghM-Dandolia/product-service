package com.myretail.productapi.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ErrorInfo implements Serializable{
	private static final long serialVersionUID = -4475017203237890407L;
	private String errorCode;
	private String errorMessage;
}
