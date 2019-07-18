package com.myretail.productapi.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.myretail.productapi.configuration.CassandraConfiguration;
import com.myretail.productapi.exception.GenericException;
import com.myretail.productapi.model.ErrorInfo;
import com.myretail.productapi.model.ProductPrice;
import com.myretail.productapi.model.Response;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class ProductPriceDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductPriceDao.class);

	@Autowired
	private CassandraConfiguration cassandra;

	@Value("${cassandra.query.to.fetch.price}")
	String query;

	@Value("${cassandra.query.to.update.price}")
	String updateQuery;

	@HystrixCommand(commandKey = "ProductPriceCommand", fallbackMethod = "fetchProductPriceFallbackMethod")
	public ProductPrice fetchProductPrice(String productId) {

		ProductPrice producPrice = new ProductPrice();
		LOGGER.debug("query: {}", query);

		Session session = cassandra.getSession();
		PreparedStatement preparedStatement = session.prepare(query);
		ResultSet results = session.execute(preparedStatement.bind(productId));
		if (results.isExhausted()) {
			return null;
		}
		Row row = results.one();
		producPrice.setProductId(productId);
		producPrice.setCurrentPrice(row.getDouble("CURRENT_PRICE"));
		producPrice.setCurrencyCode(row.getString("CURRENCY"));
		LOGGER.debug("Resposne producPrice:{}", producPrice);
		return producPrice;
	}

	public ProductPrice fetchProductPriceFallbackMethod(String productId) {
		LOGGER.error("productId:{}", productId);
		throw get500Exception();
	}

	@HystrixCommand(commandKey = "ProductUpdatePriceCommand", fallbackMethod = "updateProductPriceFallbackMethod")
	public Response updateProductPrice(ProductPrice updatePayload) {
		LOGGER.debug("updateQuery: {}", updateQuery);
		Session session = cassandra.getSession();
		PreparedStatement preparedStatement = session.prepare(updateQuery);
		session.execute(preparedStatement.bind(updatePayload.getCurrentPrice(), updatePayload.getCurrencyCode(),
				updatePayload.getProductId()));
		LOGGER.debug("Product updated successfully :{}", updatePayload.getProductId());
		Response response = new Response();
		response.setStatus("SUCCESS");
		return response;
	}

	public Response updateProductPriceFallbackMethod(ProductPrice updatePayload) {
		throw get500Exception();
	}

	private GenericException get500Exception() {
		return new GenericException(new ErrorInfo("ERR-500", "Unable to connect to data store."),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
