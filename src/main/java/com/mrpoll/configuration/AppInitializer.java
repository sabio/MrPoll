package com.mrpoll.configuration;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer{}
/*
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppConfig.class};
    }
    
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }
    
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
    
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        //return new Filter[]{new Filerx()};
        return new Filter[]{characterEncodingFilter, new ConfigurableSiteMeshFilter()};
    }
    
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        //servletContext.setInitParameter("spring.profiles.active", "dev");
    }
    
    class Filerx implements Filter {
        
        @Override
        public void init(FilterConfig fc) throws ServletException {
            
        }
        
        @Override
        public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
            HttpServletResponse res = (HttpServletResponse) sr1;            
            HttpServletRequest req = (HttpServletRequest) sr;            
            System.out.println("INICIO=============================================================");
            System.out.println("req.getCharacterEncoding = " + req.getCharacterEncoding());
            System.out.println("res.getCharacterEncoding = " + res.getCharacterEncoding());
            System.out.println("req.getContentType = " + req.getContentType());
            System.out.println("res.getContentType = " + res.getContentType());
            
            req.setCharacterEncoding("ISO-8859-1");
            res.setCharacterEncoding("ISO-8859-1");
            System.out.println("req.getCharacterEncoding 2 = "+req.getCharacterEncoding());
            System.out.println("res.getCharacterEncoding 2 = "+res.getCharacterEncoding());
            
            System.out.println("nombre = " + req.getParameter("name"));
            
            System.out.println("FIN=============================================================");
            fc.doFilter(sr, sr1);
        }
        
        @Override
        public void destroy() {
            
        }
        
    }
    
}
*/