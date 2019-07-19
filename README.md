# product-service


**Project Setup & Installation Guide**

Prerequisites:

Your machine should have JDK1.8 installed or configured in your IDE
You will need a Gradle and JDK1.8 enabled IDE like STS, Eclipse or Intellij to see projects structure.
I used Cassandra for noSQL data store, so either you should have Cassandra in your machine or any remote server.
Please find keyspace, table creating and insert scripts in ProductPriceTable.cql to setup data in Cassandra. Complete database setup before setting up projects in IDE.
Step 1: Clone github repository https://github.com/BhanupratapsinghM-Dandolia/product-service This repository has following spring-boot (with gradle) projects

MyRetailEurekaServer
MyRetailZuulService
ProductCompositeService
ProductInfoAPI
ProductPriceAPI
Step 2: Import all projects in your IDE as ‘Exiting Gradle Projects’

Step 3: Modify server.port property in application.properties file of each project, if port mentioned is already in use. If you modify port number in MyRetailEurekaServer project, please update property “eureka.client.serviceUrl.defaultZone” with correct eureka server port in all other projects.

Step 4: Run service via IDE Run all projects as “Spring Boot App” throw IDE or build all projects using ‘gradle build’ in following sequence a.	MyRetailEurekaServer b.	MyRetailZuulService c. ProductCompositeService d.	ProductInfoAPI e.	ProductPriceAPI Run service directly Build all project through ‘gradle build’ and start project through java command. ‘java -jar <>’.

Note:-

Logs: You can find all application logs at location /tmp/logs/<>.log (in window location will be c:/tmp/logs).
You can change log location by updating <>/src/main/resources/logback.xml file .
