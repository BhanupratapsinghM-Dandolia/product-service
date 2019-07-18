package com.myretail.productapi.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myretail.productapi.exception.GenericException;
import com.myretail.productapi.model.ErrorInfo;
import com.myretail.productapi.model.PriceUpdatePayload;
import com.myretail.productapi.model.PriceUpdateResponse;
import com.myretail.productapi.model.Response;
import com.myretail.productapi.service.ProductService;

@RestController
public class ProductController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	ProductService productService;

	@GetMapping(value = "/products/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public Response fetchProductInfo(@PathVariable String id) {
		return productService.fetchProductByID(id);
	}

	@PutMapping(value = "/products/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public PriceUpdateResponse updateProductInfo(@RequestBody PriceUpdatePayload payload, @PathVariable String id) {
		if (StringUtils.isEmpty(payload.getId()) || !payload.getId().equals(id)) {
			payload.setId(id);
		}
		return productService.updateProductBy(payload);
	}

	@ExceptionHandler(GenericException.class)
	@ResponseBody
	public ResponseEntity<ErrorInfo> genericException(HttpServletRequest req, GenericException ex) {
		String errorMessage = ex.getClass().getName() + ": " + ex.getMessage();
		LOGGER.error(errorMessage, ex);
		return new ResponseEntity<>(ex.getErrorInfo(), ex.getHttpStatus());
	}
}
