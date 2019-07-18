package com.myretail.productapi.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.myretail.productapi.model.Item;
import com.myretail.productapi.model.Product;
import com.myretail.productapi.model.ProductDescription;
import com.myretail.productapi.model.ProductInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class ProductDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductDao.class);

	@Value("${rest.end.point}")
	String restEndPoint;

	@Autowired
	RestTemplate restTemplate;

	@HystrixCommand(commandKey = "ProductInfoCommand", fallbackMethod = "fetchProductInfoFallBack")
	public ProductInfo fetchProductInfo() {
		ProductInfo productInfo = restTemplate.getForObject(restEndPoint, ProductInfo.class);
		LOGGER.debug("Resposne from Product Info API:{}", productInfo);
		return productInfo;
	}

	public ProductInfo fetchProductInfoFallBack() {
		LOGGER.error("Calling fallback service to fetch alternate date.");
		ProductInfo productInfo = new ProductInfo();
		Product product = new Product();
		Item item = new Item();
		ProductDescription productDescription = new ProductDescription();
		productDescription.setTitle("Dummy Product Name.");
		item.setProductDescription(productDescription);
		product.setItem(item);
		productInfo.setProduct(product);
		return productInfo;
	}
}
