package com.mrpoll.configuration;

import javax.servlet.Filter;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    //http://websystique.com/springmvc/spring-mvc-4-and-spring-security-4-integration-example/
    //https://dzone.com/articles/springmvc4-spring-data-jpa
    //http://www.programming-free.com/2016/01/spring-security-spring-data-jpa.html
    //http://springinpractice.com/2012/05/11/pagination-and-sorting-with-spring-data-jpa
    //http://www.mkyong.com/spring-mvc/spring-mvc-form-handling-example/
    //http://www.journaldev.com/2668/spring-validation-example-mvc-validator
    //http://stackoverflow.com/questions/1972933/cross-field-validation-with-hibernate-validator-jsr-303
    //http://outbottle.com/spring-3-mvc-adding-objects-to-a-list-element-on-the-fly-at-form-submit-generic-method/
    //http://viralpatel.net/blogs/spring-mvc-multi-row-submit-java-list/

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


    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        return new Filter[]{characterEncodingFilter, new ConfigurableSiteMeshFilter()};
    }

}
