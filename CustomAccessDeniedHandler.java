package com.bss.csr.config;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.bss.service.interfaces.EditCustomerService;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	private Logger logger = Logger.getLogger(CustomAccessDeniedHandler.class);
	@Autowired
	private EditCustomerService editCustomer;
	
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exc) throws IOException, ServletException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();	
		String currentDate = dateFormat.format(date);
        if (auth != null) {
        	logger.warn("User: " + auth.getName() + " attempted to access the protected URL: " + request.getRequestURI());
        	editCustomer.setSysAudit(auth.getName(), currentDate," attempted to access the protected URL: " + request.getRequestURI() , "User Manager");
        }
        
        response.sendRedirect(request.getContextPath() + "/Access_Denied");
    }

	

}