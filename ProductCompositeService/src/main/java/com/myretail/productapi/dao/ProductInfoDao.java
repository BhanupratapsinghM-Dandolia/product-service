package com.myretail.productapi.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.myretail.productapi.model.Item;
import com.myretail.productapi.model.Product;
import com.myretail.productapi.model.ProductDescription;
import com.myretail.productapi.model.ProductInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import rx.Observable;

@Component
public class ProductInfoDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductInfoDao.class);

	@Value("${rest.end.point.product.info}")
	String restEndPoint;

	@Autowired
	RestTemplate restTemplate;

	@HystrixCommand(commandKey = "ProductInfoCommand", fallbackMethod = "fetchProductInfoFallBack")
	public Observable<ProductInfo> fetchProductInfo() {
		ProductInfo productInfo = restTemplate.getForObject(restEndPoint, ProductInfo.class);
		LOGGER.debug("Resposne from Product Info API:{}", productInfo);
		return Observable.create(asyncOnSubscribe -> {
			asyncOnSubscribe.onNext(productInfo);
			asyncOnSubscribe.onCompleted();
		});		
	}

	public Observable<ProductInfo> fetchProductInfoFallBack() {
		LOGGER.error("Calling fallback service to fetch alternate date.");
		ProductInfo productInfo = new ProductInfo();
		Product product = new Product();
		Item item = new Item();
		ProductDescription productDescription = new ProductDescription();
		productDescription.setTitle("Dummy Product Name.");
		item.setProductDescription(productDescription);
		product.setItem(item);
		productInfo.setProduct(product);
		return Observable.create(asyncOnSubscribe -> {
			asyncOnSubscribe.onNext(productInfo);
			asyncOnSubscribe.onCompleted();
		});
	}
}
