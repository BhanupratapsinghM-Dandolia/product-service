package com.myretail.productapi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

import com.myretail.productapi.dao.ProductPriceDao;
import com.myretail.productapi.exception.GenericException;
import com.myretail.productapi.model.ErrorInfo;
import com.myretail.productapi.model.ProductPrice;
import com.myretail.productapi.model.Response;
import com.myretail.productapi.service.ProductPriceService;

@RestController
public class ProductController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductPriceDao.class);

	@Autowired
	ProductPriceService productPriceService;

	@GetMapping(value = "/product-price/{productId}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ProductPrice fetchProductPrice(@PathVariable String productId) {
		return productPriceService.fetchProductPrice(productId);
	}

	@PutMapping(value = "/product-price", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public Response updateProductPrice(@RequestBody @Valid ProductPrice updatePayload) {
		return productPriceService.updateProductPrice(updatePayload);
	}

	@ExceptionHandler(GenericException.class)
	@ResponseBody
	public ResponseEntity<ErrorInfo> genericException(HttpServletRequest req, GenericException ex) {
		String errorMessage = ex.getClass().getName() + ": " + ex.getMessage();
		LOGGER.error(errorMessage, ex);
		return new ResponseEntity<>(ex.getErrorInfo(), ex.getHttpStatus());
	}	
}
