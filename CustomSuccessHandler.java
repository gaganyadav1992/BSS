package com.bss.csr.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.bss.service.interfaces.UserService;
 
@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
 
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
 
    @Autowired
	private UserService userService;
    
    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)throws IOException {
    	System.out.println("Request:-"+request.toString());
        String targetUrl = determineTargetUrl(authentication,request);
        System.out.println("Url:-----"+targetUrl);
        if (response.isCommitted()) {
            System.out.println("Can't redirect");
            return;
        }
 
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }
 
    /*
     * This method extracts the roles of currently logged-in user and returns
     * appropriate URL according to his/her role.
     */
    protected String determineTargetUrl(Authentication authentication, HttpServletRequest request) {
        String url = "";
       
        request.getSession().setAttribute("username", authentication.getName());
        request.getSession().setAttribute("user_obj", userService.getUser(authentication.getName()));
        System.out.println("Name-:"+authentication.getName());
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
 
        List<String> roles = new ArrayList<String>();
 
        for (GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
        }
 
        if (isInv(roles)) {
            url = "/INVDashboard";
        } else if (isAdmin(roles)) {
            url = "/AdminDashboard";
        } else if (isCSR(roles)) {
            url = "/CsrDashboard";
        } else if (isTicket(roles)) {
            url = "/TicketDashBoard";
       } else if (isPOS(roles)) {
            url = "/POSDashboard";
        } else if (isSuperUser(roles)) {
            url = "/SuperUserDashboard";
        } else if (isBilling(roles)) {
            url = "/BillingDashboard";
        } else if (isReport(roles)) {
            url = "/ReportDashboard";
        } else {
            url = "/Home";
        }
 
        return url;
    }
 
    private boolean isCSR(List<String> roles) {
        if (roles.contains("ROLE_CSR")) {
            return true;
        }
        return false;
    }
 
    private boolean isAdmin(List<String> roles) {
        if (roles.contains("ROLE_ADMIN")) {
            return true;
        }
        return false;
    }
 
    private boolean isTicket(List<String> roles) {
        if (roles.contains("ROLE_TICKET")) {
            return true;
        }
        return false;
    }
    
    private boolean isPOS(List<String> roles) {
        if (roles.contains("ROLE_POS")) {
            return true;
        }
        return false;
    }
 
    
    private boolean isSuperUser(List<String> roles) {
        if (roles.contains("ROLE_SUPERUSER")) {
            return true;
        }
        return false;
    }
    private boolean isBilling(List<String> roles) {
        if (roles.contains("ROLE_BILLING")) {
            return true;
        }
        return false;
    }
    private boolean isReport(List<String> roles) {
        if (roles.contains("ROLE_REPORT")) {
            return true;
        }
        return false;
    }
    private boolean isInv(List<String> roles) {
        if (roles.contains("ROLE_INV")) {
            return true;
        }
        return false;
    }
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
 
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }
 
}