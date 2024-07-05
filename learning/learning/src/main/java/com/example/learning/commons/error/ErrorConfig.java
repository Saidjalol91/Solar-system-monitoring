package com.example.learning.commons.error;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class ErrorConfig {
	@Bean
	public ConfigurableWebServerFactory webServerFactory(){
		TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory ();


		// TO DO please create error React Component or pages customizing in future. IT not create !!!!!!!!
		factory.addErrorPages (new ErrorPage (HttpStatus.NOT_FOUND, "/error"));
		return factory;
	}
}
