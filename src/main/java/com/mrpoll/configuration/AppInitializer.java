package com.mrpoll.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
        //http://websystique.com/springmvc/spring-mvc-4-and-spring-security-4-integration-example/
        //https://dzone.com/articles/springmvc4-spring-data-jpa
        //http://www.programming-free.com/2016/01/spring-security-spring-data-jpa.html
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AppConfig.class };
	}
 
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}
 
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}
