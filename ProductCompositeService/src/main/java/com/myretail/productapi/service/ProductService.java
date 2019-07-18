package com.myretail.productapi.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myretail.productapi.dao.ProductInfoDao;
import com.myretail.productapi.dao.ProductPriceDao;
import com.myretail.productapi.model.CurrentPrice;
import com.myretail.productapi.model.PriceUpdatePayload;
import com.myretail.productapi.model.PriceUpdateResponse;
import com.myretail.productapi.model.ProductInfo;
import com.myretail.productapi.model.ProductPrice;
import com.myretail.productapi.model.Response;

import rx.Observable;

@Service
public class ProductService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductInfoDao.class);

	@Autowired
	ProductInfoDao productInfoDao;

	@Autowired
	ProductPriceDao productPriceDao;

	public Response fetchProductByID(String id) {
		return Observable.zip(productInfoDao.fetchProductInfo(), productPriceDao.fetchProductPrice(id),
				(productInfo, productPrice) -> {
					return getFinalResposne(productInfo, productPrice);
				}).toBlocking().first();
	}

	private Response getFinalResposne(ProductInfo productInfo, ProductPrice productPrice) {
		LOGGER.debug("productInfo:{}", productInfo);
		LOGGER.debug("productPrice:{}", productPrice);

		Response response = new Response();
		if (productPrice.getErrorCode() == null) {
			response.setId(productPrice.getProductId());
			response.setName(productInfo.getProduct().getItem().getProductDescription().getTitle());
			CurrentPrice currentPrice = new CurrentPrice();
			currentPrice.setCurrencyCode(productPrice.getCurrencyCode());
			currentPrice.setValue(productPrice.getCurrentPrice());
			response.setCurrentPrice(currentPrice);
		} else {
			response.setErrorCode(productPrice.getErrorCode());
			response.setErrorMessage(productPrice.getErrorMessage());
		}
		LOGGER.debug("Final Response:{}", response);
		return response;
	}

	public PriceUpdateResponse updateProductBy(PriceUpdatePayload payload) {
		if (payload == null || StringUtils.isEmpty(payload.getId()) || payload.getCurrentPrice() == null
				|| payload.getCurrentPrice().getValue() == null
				|| StringUtils.isEmpty(payload.getCurrentPrice().getCurrencyCode())) {
			throw new RuntimeException("Invalid Request.");
		}
		ProductPrice productPrice = new ProductPrice();
		productPrice.setCurrencyCode(payload.getCurrentPrice().getCurrencyCode());
		productPrice.setCurrentPrice(payload.getCurrentPrice().getValue());
		productPrice.setProductId(payload.getId());
		return productPriceDao.updateProductPrice(productPrice);
	}
}
