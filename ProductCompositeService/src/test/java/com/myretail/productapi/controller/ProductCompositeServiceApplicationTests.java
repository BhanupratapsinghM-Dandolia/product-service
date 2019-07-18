package com.myretail.productapi.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

import java.lang.reflect.Method;

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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.myretail.productapi.dao.ProductInfoDao;
import com.myretail.productapi.dao.ProductPriceDao;
import com.myretail.productapi.model.Attributes;
import com.myretail.productapi.model.AvailableToPromiseNetwork;
import com.myretail.productapi.model.BundleComponents;
import com.myretail.productapi.model.ContentLabel;
import com.myretail.productapi.model.CurrentPrice;
import com.myretail.productapi.model.DeepRedLabels;
import com.myretail.productapi.model.DisplayOption;
import com.myretail.productapi.model.Enrichment;
import com.myretail.productapi.model.EnvironmentalSegmentation;
import com.myretail.productapi.model.ErrorInfo;
import com.myretail.productapi.model.Fulfillment;
import com.myretail.productapi.model.Handling;
import com.myretail.productapi.model.Image;
import com.myretail.productapi.model.Item;
import com.myretail.productapi.model.ItemType;
import com.myretail.productapi.model.Label;
import com.myretail.productapi.model.Manufacturer;
import com.myretail.productapi.model.PackageDimensions;
import com.myretail.productapi.model.PriceUpdateResponse;
import com.myretail.productapi.model.Product;
import com.myretail.productapi.model.ProductBrand;
import com.myretail.productapi.model.ProductClassification;
import com.myretail.productapi.model.ProductDescription;
import com.myretail.productapi.model.ProductInfo;
import com.myretail.productapi.model.ProductPrice;
import com.myretail.productapi.model.ProductVendor;
import com.myretail.productapi.model.RecallCompliance;
import com.myretail.productapi.model.Response;
import com.myretail.productapi.model.ReturnPolicies;
import com.myretail.productapi.model.SalesClassificationNode;
import com.myretail.productapi.model.TaxCategory;
import com.myretail.productapi.model.Variation;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.spring.commons.config.AsyncConfig;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ProductCompositeServiceApplicationTests {

	@MockBean
	RestTemplate restTemplate;

	@Autowired
	@InjectMocks
	ProductPriceDao productPriceDao;

	@Autowired
	@InjectMocks
	ProductInfoDao productInfoDao;

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
		Mockito.when(restTemplate.getForObject(Mockito.contains("product-info"), Mockito.eq(ProductInfo.class)))
				.thenReturn(getDummyProductInfo());
		Mockito.when(restTemplate.getForObject(Mockito.contains("product-price"), Mockito.eq(ProductPrice.class),
				Mockito.anyMap())).thenReturn(getDummyProductPrice());

		Response response = given().accept(ContentType.JSON).when().get("/products/999").then().statusCode(200)
				.extract().as(Response.class);

		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getCurrentPrice());

		Assert.assertEquals("999", response.getId());
		Assert.assertEquals("TestName", response.getName());
		Assert.assertEquals("INR", response.getCurrentPrice().getCurrencyCode());
		Assert.assertEquals(new Double(99.99), response.getCurrentPrice().getValue());

	}

	@Test
	public void testProductInfoFailCase() {

		Mockito.when(restTemplate.getForObject(Mockito.contains("product-info"), Mockito.eq(ProductInfo.class)))
				.thenThrow(new RuntimeException("Product Info Service is down"));
		Mockito.when(restTemplate.getForObject(Mockito.contains("product-price"), Mockito.eq(ProductPrice.class),
				Mockito.anyMap())).thenReturn(getDummyProductPrice());

		Response response = given().accept(ContentType.JSON).when().get("/products/999").then().statusCode(200)
				.extract().as(Response.class);

		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getCurrentPrice());
		Assert.assertEquals("999", response.getId());
		Assert.assertEquals("Dummy Product Name.", response.getName());
		Assert.assertEquals("INR", response.getCurrentPrice().getCurrencyCode());
		Assert.assertEquals(new Double(99.99), response.getCurrentPrice().getValue());
	}

	@Test
	public void testBothServiceFailCase() {

		Mockito.when(restTemplate.getForObject(Mockito.contains("product-info"), Mockito.eq(ProductInfo.class)))
				.thenThrow(new RuntimeException("Product Info Service is down"));
		Mockito.when(restTemplate.getForObject(Mockito.contains("product-price"), Mockito.eq(ProductPrice.class),
				Mockito.anyMap())).thenThrow(new RuntimeException("Product Info Service is down"));

		ErrorInfo errorInfo= given().accept(ContentType.JSON).when().get("/products/999").then().statusCode(500).extract().as(ErrorInfo.class);
		Assert.assertNotNull(errorInfo);
		Assert.assertEquals("ERR-500", errorInfo.getErrorCode());
		Assert.assertEquals("Product price service is down.", errorInfo.getErrorMessage());
	}

	@Test
	public void testUpdatePriceHappyPath() {

		String request = "{\"productId\": \"15117729\",\"name\": \"The Big Lebowski (Blu-ray)\",\"current_price\":    {\"value\": 1230.5,\"currency_code\": \"INR\"}}";
		PriceUpdateResponse response = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(request)
				.when().put("/products/15117729").then().statusCode(200).extract().as(PriceUpdateResponse.class);
		Assert.assertNotNull(response);
		Assert.assertEquals("SUCCESS", response.getStatus());
	}

	@Test
	public void testUpdatePriceErrorCase() {

		String request = "{\"productId\": \"15117729\",\"name\": \"The Big Lebowski (Blu-ray)\",\"current_price\":    {\"value\": 1230.5,\"currency_code\": \"INR\"}}";

		Mockito
			.doThrow(new RuntimeException("Product price service is down."))
			.when(restTemplate).put(Mockito.contains("product-price"), Mockito.any(ProductPrice.class));
		
		ErrorInfo errorInfo = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(request)
				.when().put("/products/15117729").then().statusCode(500).extract().as(ErrorInfo.class);
		
		Assert.assertNotNull(errorInfo);
		Assert.assertEquals("ERR-500", errorInfo.getErrorCode());
		Assert.assertEquals("Product price service is down.", errorInfo.getErrorMessage());
	}

	private ProductInfo getDummyProductInfo() {
		ProductInfo productInfo = new ProductInfo();
		Product product = new Product();
		Item item = new Item();
		ProductDescription productDescription = new ProductDescription();
		productDescription.setTitle("TestName");
		item.setProductDescription(productDescription);
		product.setItem(item);
		productInfo.setProduct(product);
		return productInfo;
	}

	private ProductPrice getDummyProductPrice() {
		ProductPrice productPrice = new ProductPrice();
		productPrice.setCurrencyCode("INR");
		productPrice.setCurrentPrice(99.99);
		productPrice.setProductId("999");
		return productPrice;
	}

	@Test
	public void testGettersSetters() throws Exception {
		testGettersSetters(new PriceUpdateResponse(), PriceUpdateResponse.class);
		testGettersSetters(new Response(), Response.class);
		testGettersSetters(new CurrentPrice(), CurrentPrice.class);
		testGettersSetters(new Attributes(), Attributes.class);
		testGettersSetters(new AvailableToPromiseNetwork(), AvailableToPromiseNetwork.class);
		testGettersSetters(new BundleComponents(), BundleComponents.class);
		testGettersSetters(new ContentLabel(), ContentLabel.class);
		testGettersSetters(new DeepRedLabels(), DeepRedLabels.class);
		testGettersSetters(new DisplayOption(), DisplayOption.class);
		testGettersSetters(new Enrichment(), Enrichment.class);
		testGettersSetters(new EnvironmentalSegmentation(), EnvironmentalSegmentation.class);
		testGettersSetters(new Fulfillment(), Fulfillment.class);
		testGettersSetters(new Handling(), Handling.class);
		testGettersSetters(new Image(), Image.class);
		testGettersSetters(new Item(), Item.class);
		testGettersSetters(new ItemType(), ItemType.class);
		testGettersSetters(new Label(), Label.class);
		testGettersSetters(new Manufacturer(), Manufacturer.class);
		testGettersSetters(new PackageDimensions(), PackageDimensions.class);
		testGettersSetters(new Product(), Product.class);
		testGettersSetters(new ProductBrand(), ProductBrand.class);
		testGettersSetters(new ProductClassification(), ProductClassification.class);
		testGettersSetters(new ProductDescription(), ProductDescription.class);
		testGettersSetters(new ProductInfo(), ProductInfo.class);
		testGettersSetters(new ProductVendor(), ProductVendor.class);
		testGettersSetters(new RecallCompliance(), RecallCompliance.class);
		testGettersSetters(new ReturnPolicies(), ReturnPolicies.class);
		testGettersSetters(new SalesClassificationNode(), SalesClassificationNode.class);
		testGettersSetters(new TaxCategory(), TaxCategory.class);
		testGettersSetters(new Variation(), Variation.class);

	}

	public void testGettersSetters(Object obj, Class clas) throws Exception {
		Method[] methods = clas.getMethods();
		for (Method method : methods) {
			if (isGetter(method)) {
				method.invoke(obj, new Object[] {});
			}
			if (isSetter(method)) {
				method.invoke(obj, new Object[] { null });
			}
		}
		obj.toString();
	}

	public static boolean isGetter(Method method) {
		if (!method.getName().startsWith("get"))
			return false;
		if (method.getParameterTypes().length != 0)
			return false;
		if (void.class.equals(method.getReturnType()))
			return false;
		return true;
	}

	public static boolean isSetter(Method method) {
		if (!method.getName().startsWith("set"))
			return false;
		if (method.getParameterTypes().length != 1)
			return false;
		return true;
	}

}
