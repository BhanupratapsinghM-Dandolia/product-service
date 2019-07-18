package com.myretail.productapi.configuration;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

@Component
public class CassandraConfiguration {

	private static final Logger LOGGER = LoggerFactory.getLogger(CassandraConfiguration.class);

	@Value("${cassandra.contact.points}")
	String contactPoints;

	@Value("${cassandra.port}")
	Integer port;

	@Value("${cassandra.keyspace}")
	String keySpace;

	@Value("${cassandra.username}")
	String userName;

	@Value("${cassandra.password}")
	String password;

	private Session session;

	@PostConstruct
	public void init() {
		try {
			LOGGER.info("contactPoints:{}", contactPoints);
			LOGGER.info("port:{}", port);
			LOGGER.info("keySpace:{}", keySpace);
			LOGGER.info("userName:{}", userName);
			LOGGER.info("password:{}", password);

			session = Cluster.builder().addContactPoints(contactPoints).withPort(port)
					.withCredentials(userName, password).build().connect("PRODUCT_PRICE_KS");
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	public Session getSession() {
		return session;
	}
}
