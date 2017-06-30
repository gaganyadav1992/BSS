package com.bss.csr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.bss.converter.DeptIDtoDeptConverter;
import com.bss.util.ConfigFileData;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.bss"})
@PropertySource(value = { "classpath:applicationdata.properties" })
public class AppConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
	DeptIDtoDeptConverter deptIDtoDeptConverter;
	
	@Autowired
    private Environment environment;
	
	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	@Bean(name = "multipartResolver")
	public StandardServletMultipartResolver resolver() {
		StandardServletMultipartResolver scl = new StandardServletMultipartResolver();
		scl.setResolveLazily(true);
		return scl;
	}


	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	/**
     * Configure Converter to be used.
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(deptIDtoDeptConverter);
    }
    
    @Bean
	public ConfigFileData getConfigData(){
		
		ConfigFileData config = new ConfigFileData();	
		//System.out.println(">>>>>>>>>>>>>>>>>>GAGAGAGAGAGAG>>>>>>>>>>>>>>"+environment.getProperty("prefix.PrePaid"));
		config.setPrepaidPlanPrefix(environment.getProperty("prefix.PrePaid"));
		config.setDataPlanPrefix(environment.getProperty("prefix.data"));
		config.setHybridPlanPrefix(environment.getProperty("prefix.hybrids"));
		config.setPostpaidPlanPrefix(environment.getProperty("prefix.postpaid"));
		config.setDeviceInstPercent(environment.getProperty("deviceinstallment.percentage"));
		config.setBulkUploadArticlePath(environment.getProperty("upload.articlePath"));
		config.setCustomerIdUploadPath(environment.getProperty("upload.customerIdPath"));
		config.setoCS(environment.getProperty("ocs.ocs"));
		config.setCommandNeedToSendOnOC(environment.getProperty("ocs.commandneedtosend"));
		config.setOcsIp(environment.getProperty("ocs.ip"));
		config.setOcsPort(environment.getProperty("ocs.port"));
		config.setBroadbandPlanPrefix(environment.getProperty("prefix.broadband"));
		config.setOcsuser(environment.getProperty("ocs.user"));
		config.setOcsPassword(environment.getProperty("ocs.password"));
		config.setSmppUrl(environment.getProperty("smpp.url"));
		config.setSenderShortCode(environment.getProperty("smpp.sender.shortcode"));
		return config;
	}
}
