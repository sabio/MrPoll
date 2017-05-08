package com.mrpoll.configuration;

import com.mrpoll.converter.RoleIdToRoleConverter;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan(
    basePackages = 
        {
            "com.mrpoll.controller",
            "com.mrpoll.service",
            "com.mrpoll.dao",
            "com.mrpoll.converter",
            "com.mrpoll.configuration.hibernate",
            "com.mrpoll.configuration.security",
            "com.mrpoll.configuration.exception",
            "com.mrpoll.validator",
            "com.mrpoll.restcontroller"
        }
)
public class AppConfig extends WebMvcConfigurerAdapter {
    
    @Autowired
    RoleIdToRoleConverter roleIdToRoleConverter;
    
    
    /**
     * Configure ViewResolvers.
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        registry.viewResolver(viewResolver);
    }

    /**
     * Configure ResourceHandlers to serve static resources.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }

    /**
     * Configure MessageSource to lookup messages in
     * internationalized property files.
     */
    @Bean
    public MessageSource messageSource() {
        //ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("/i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
    
    /**
     * Configure LocaleResolver.
     */
    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setDefaultLocale(new Locale("es"));
        resolver.setCookieName("myLocaleCookie");
        resolver.setCookieMaxAge(4800); //4,800 seconds
        return resolver;
    }
    
    /**
     * Configure LocaleChangeInterceptor to check if Locale has changed. 
     * Of course you can add more interceptors if you wish
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("mylocale");
        registry.addInterceptor(interceptor);
    }
    
    /**
     * Configure formatters.
     */
    @Override
    public void addFormatters (FormatterRegistry registry) {
        registry.addConverter(roleIdToRoleConverter);
    }
    
    
}