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

import com.myretail.productapi.dao.ProductDao;
import com.myretail.productapi.model.*;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.spring.commons.config.AsyncConfig;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ProductApiApplicationTests {

	@MockBean
	RestTemplate restTemplate;

	@InjectMocks
	ProductDao productDao;

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

		Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(ProductInfo.class)))
				.thenReturn(getDummyProductInfo());

		ProductInfo productInfo = given().accept(ContentType.JSON).when().get("/product-info").then().statusCode(200)
				.extract().as(ProductInfo.class);

		Assert.assertNotNull(productInfo);
		Assert.assertNotNull(productInfo.getProduct());
		Assert.assertNotNull(productInfo.getProduct().getItem());
		Assert.assertNotNull(productInfo.getProduct().getItem().getProductDescription());
		Assert.assertNotNull(productInfo.getProduct().getItem().getProductDescription().getTitle());
		Assert.assertEquals("TestName", productInfo.getProduct().getItem().getProductDescription().getTitle());
	}

	@Test
	public void testError() {

		Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(ProductInfo.class)))
				.thenThrow(new RuntimeException());

		ProductInfo productInfo = given().accept(ContentType.JSON).when().get("/product-info").then().statusCode(200)
				.extract().as(ProductInfo.class);

		Assert.assertNotNull(productInfo);
		Assert.assertNotNull(productInfo.getProduct());
		Assert.assertNotNull(productInfo.getProduct().getItem());
		Assert.assertNotNull(productInfo.getProduct().getItem().getProductDescription());
		Assert.assertNotNull(productInfo.getProduct().getItem().getProductDescription().getTitle());
		Assert.assertEquals("Dummy Product Name.",
				productInfo.getProduct().getItem().getProductDescription().getTitle());
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

	@Test
    public void testGettersSetters() throws Exception {
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
