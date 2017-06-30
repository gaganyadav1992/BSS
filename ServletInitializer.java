package com.bss.csr.config;

import javax.annotation.PostConstruct;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Configuration
@ComponentScan({ "com.bss.csr.config" })
@PropertySource(value = { "classpath:bulkupload.properties" })
public class ServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	
	/*private BulkUploadProp bulkUploadProp;*/
	
	@Autowired
    private Environment environment;
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[0];
	}
 
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[]{AppConfig.class};
	}
 
	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}
	
	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
		registration.setMultipartConfig(getMultipartConfigElement());
	}
	
	private MultipartConfigElement getMultipartConfigElement() {
		MultipartConfigElement multipartConfigElement = new MultipartConfigElement(	LOCATION, MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
		return multipartConfigElement;
	}

	@PostConstruct
	private void init(){
		LOCATION = environment.getProperty("bulkupload.LOCATION");
		//MAX_FILE_SIZE = Long.parseLong(environment.getProperty("bulkupload.MAX_FILE_SIZE"));
		//MAX_REQUEST_SIZE = Long.parseLong(environment.getProperty("bulkupload.MAX_REQUEST_SIZE"));
		//FILE_SIZE_THRESHOLD = Integer.parseInt(environment.getProperty("bulkupload.FILE_SIZE_THRESHOLD"));
		//System.out.println(">>>>>>>>Lion>>>>>>"+LOCATION+">>>>>>>>>MAX_FILE_SIZE>>>>>>>>"+MAX_FILE_SIZE+">>>>>>>>MAX_REQUEST_SIZE>>>>>>"+MAX_REQUEST_SIZE);
	}
	
	private  String LOCATION; // Temporary location where files will be stored

	private  long MAX_FILE_SIZE = 5242880; // 5MB : Max file size.
														// Beyond that size spring will throw exception.
	private  long MAX_REQUEST_SIZE = 20971520; // 20MB : Total request size containing Multi part.
	
	private  int FILE_SIZE_THRESHOLD = 0; // Size thresh*/
}