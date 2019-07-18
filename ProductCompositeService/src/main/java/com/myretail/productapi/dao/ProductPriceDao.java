package com.myretail.productapi.dao;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myretail.productapi.exception.GenericException;
import com.myretail.productapi.model.ErrorInfo;
import com.myretail.productapi.model.PriceUpdateResponse;
import com.myretail.productapi.model.ProductPrice;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import rx.Observable;

@Component
public class ProductPriceDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductPriceDao.class);

	@Value("${rest.end.point.product.price}")
	String restEndPoint;

	@Value("${rest.end.point.product.price.update}")
	String restUpdateEndPoint;

	@Autowired
	RestTemplate restTemplate;

	@HystrixCommand(commandKey = "ProductPriceCommand")
	public Observable<ProductPrice> fetchProductPrice(String id) {
		Map<String, String> variableMap = new HashMap<>();
		variableMap.put("product_id", id);
		ProductPrice productPrice;
		try {
			productPrice = restTemplate.getForObject(restEndPoint, ProductPrice.class, variableMap);
		} catch (RestClientResponseException exp) {
			LOGGER.error(exp.getMessage(),exp);
			String errString = exp.getResponseBodyAsString();
			ObjectMapper objectMapper = new ObjectMapper();
			ErrorInfo errorInfo;
			try {
				LOGGER.error("ErrorString:{}",errString);
				errorInfo = objectMapper.readValue(errString, ErrorInfo.class);
			} catch (Exception e) {
				LOGGER.error(exp.getMessage(),e);
				throw get500Exception();
			}
			throw new GenericException(errorInfo, HttpStatus.resolve(exp.getRawStatusCode()));
			
		} catch (Exception exp) {
			LOGGER.error(exp.getMessage(),exp);
			throw get500Exception();
		}

		LOGGER.debug("Resposne from Product Price API:{}", productPrice);
		return Observable.create(asyncOnSubscribe -> {
			asyncOnSubscribe.onNext(productPrice);
			asyncOnSubscribe.onCompleted();
		});
	}

	@HystrixCommand(commandKey = "ProductUpdatePriceCommand")
	public PriceUpdateResponse updateProductPrice(ProductPrice productPrice) {
		try {
			restTemplate.put(restUpdateEndPoint, productPrice);
			PriceUpdateResponse response = new PriceUpdateResponse();
			response.setStatus("SUCCESS");
			return response;
		} catch (Exception exp) {
			LOGGER.error(exp.getMessage(),exp);
			throw get500Exception();
		}
	}

	private GenericException get500Exception() {
		return new GenericException(new ErrorInfo("ERR-500", "Product price service is down."),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
