package com.myretail.productapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myretail.productapi.dao.ProductDao;
import com.myretail.productapi.model.ProductInfo;

@Service
public class ProductService {

	@Autowired
	ProductDao productDao;

	public ProductInfo fetchProductInfo() {
		return productDao.fetchProductInfo();
	}
}
