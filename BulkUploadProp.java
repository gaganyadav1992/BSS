package com.bss.csr.config;



import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;



@Configuration
@PropertySource(value = { "classpath:applicationdata.properties" })
public class BulkUploadProp {

	
	@Autowired
    private Environment environment;
	

	/*public void getMultipartConfigElement() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        try {
            ctx.register(BulkUploadProp.class);
            ctx.refresh();
            Environment env = ctx.getEnvironment();
            System.out.println("Topic: " + env.getProperty("bulkupload.LOCATION"));
        } finally {
            ctx.close();
        }
	}*/
	
	
	
}
