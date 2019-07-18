package com.myretail.productapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.myretail.productapi.dao.ProductPriceDao;
import com.myretail.productapi.exception.GenericException;
import com.myretail.productapi.model.ErrorInfo;
import com.myretail.productapi.model.ProductPrice;
import com.myretail.productapi.model.Response;

@Service
public class ProductPriceService {

	@Autowired
	ProductPriceDao productPriceDao;

	public ProductPrice fetchProductPrice(String productId) {
		ProductPrice productPrice = productPriceDao.fetchProductPrice(productId);
		if (productPrice == null) {
			throw new GenericException(new ErrorInfo("ERR-404", "Product id not found in data store."),
					HttpStatus.NOT_FOUND);
		} else {
			return productPrice;
		}
	}

	public Response updateProductPrice(ProductPrice updatePayload) {
		return productPriceDao.updateProductPrice(updatePayload);
	}
}
