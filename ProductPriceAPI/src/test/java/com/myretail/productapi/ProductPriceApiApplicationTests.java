package com.myretail.productapi;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.myretail.productapi.configuration.CassandraConfiguration;
import com.myretail.productapi.dao.ProductPriceDao;
import com.myretail.productapi.model.ErrorInfo;
import com.myretail.productapi.model.ProductPrice;
import com.myretail.productapi.model.Response;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.spring.commons.config.AsyncConfig;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ProductPriceApiApplicationTests {

	@MockBean
	CassandraConfiguration cassandraConfiguration;

	@InjectMocks
	ProductPriceDao productPriceDao;

	@Autowired
	private WebApplicationContext context;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		RestAssuredMockMvc.webAppContextSetup(context);
		RestAssuredMockMvc.config().asyncConfig(new AsyncConfig(15));
	}

	@Test
	public void testHappyPath() {
		Row row = Mockito.mock(Row.class);
		Mockito.when(row.getDouble(Mockito.eq("CURRENT_PRICE"))).thenReturn(99.9D);
		Mockito.when(row.getString(Mockito.eq("CURRENCY"))).thenReturn("INR");
		ResultSet resultSet = Mockito.mock(ResultSet.class);
		Mockito.when(resultSet.one()).thenReturn(row);
		Session session = Mockito.mock(Session.class);
		PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
		BoundStatement statement = Mockito.mock(BoundStatement.class);
		Mockito.when(preparedStatement.bind(Mockito.anyString())).thenReturn(statement);
		Mockito.when(session.prepare(Mockito.anyString())).thenReturn(preparedStatement);
		Mockito.when(session.execute(Mockito.any(Statement.class))).thenReturn(resultSet);
		Mockito.when(cassandraConfiguration.getSession()).thenReturn(session);

		ProductPrice productPrice = given().accept(ContentType.JSON).when().get("/product-price/900").then()
				.statusCode(200).extract().as(ProductPrice.class);

		Assert.assertNotNull(productPrice);
		Assert.assertEquals(null, productPrice.getErrorCode());
		Assert.assertEquals(null, productPrice.getErrorMessage());
		Assert.assertEquals("900", productPrice.getProductId());
		Assert.assertEquals("INR", productPrice.getCurrencyCode());
		Assert.assertEquals(new Double(99.9D), productPrice.getCurrentPrice());
	}

	@Test
	public void testErrorCase() {
		Mockito.when(cassandraConfiguration.getSession())
				.thenThrow(new RuntimeException("Unable to connect to cassandra."));
		ErrorInfo errorInfo = given().accept(ContentType.JSON).when().get("/product-price/900").then().statusCode(500)
				.extract().as(ErrorInfo.class);
		
		Assert.assertNotNull(errorInfo);
		Assert.assertEquals("ERR-500", errorInfo.getErrorCode());
		Assert.assertEquals("Unable to connect to data store.", errorInfo.getErrorMessage());
	}

	@Test
	public void testUpdatePriceHappyPath() {

		String request = "{\"productId\":\"13860428\",\"currentPrice\":13.56,\"currencyCode\":\"INR\"}";
		Session session = Mockito.mock(Session.class);
		PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
		BoundStatement statement = Mockito.mock(BoundStatement.class);
		Mockito.when(preparedStatement.bind(Mockito.anyString())).thenReturn(statement);
		Mockito.when(session.prepare(Mockito.anyString())).thenReturn(preparedStatement);
		Mockito.when(session.execute(Mockito.any(Statement.class))).thenReturn(null);
		Mockito.when(cassandraConfiguration.getSession()).thenReturn(session);

		Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(request).when()
				.put("/product-price").then().statusCode(200).extract().as(Response.class);
		Assert.assertNotNull(response);
		Assert.assertEquals("SUCCESS", response.getStatus());
	}

	@Test
	public void testUpdatePriceErrorCase() {

		String request = "{\"productId\":\"13860428\",\"currentPrice\":13.56,\"currencyCode\":\"INR\"}";

		Mockito.when(cassandraConfiguration.getSession()).thenThrow(new RuntimeException("Update failed."));

		ErrorInfo errorInfo = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(request).when()
				.put("/product-price").then().statusCode(500).extract().as(ErrorInfo.class);

		Assert.assertNotNull(errorInfo);
		Assert.assertEquals("ERR-500", errorInfo.getErrorCode());
		Assert.assertEquals("Unable to connect to data store.", errorInfo.getErrorMessage());
	}
}
