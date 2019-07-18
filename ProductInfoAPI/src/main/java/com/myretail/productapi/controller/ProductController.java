package com.myretail.productapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myretail.productapi.model.ProductInfo;
import com.myretail.productapi.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;

	@GetMapping(value = "/product-info", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ProductInfo fetchProductInfo() {
		return productService.fetchProductInfo();
	}
}
