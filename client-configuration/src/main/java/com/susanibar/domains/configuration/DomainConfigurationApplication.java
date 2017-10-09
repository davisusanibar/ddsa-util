package com.susanibar.domains.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class DomainConfigurationApplication {

	public static void main(String[] args) {
		SpringApplication.run(DomainConfigurationApplication.class, args);
	}

	@PostConstruct
	public void initSsl(){
		System.setProperty("javax.net.ssl.keyStore", Thread.currentThread().getContextClassLoader().getResource("client-keystore.jks").getPath());
		System.setProperty("javax.net.ssl.keyStorePassword", "secret");
		System.setProperty("javax.net.ssl.trustStore", Thread.currentThread().getContextClassLoader().getResource("client-truststore.jks").getPath());
		System.setProperty("javax.net.ssl.trustStorePassword", "secret");
	}
}
