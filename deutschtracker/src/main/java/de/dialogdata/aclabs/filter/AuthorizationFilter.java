package de.dialogdata.aclabs.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.dialogdata.aclabs.entities.UserBE;


public class AuthorizationFilter implements Filter {

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("lslsal");
		// TODO Auto-generated method stub
		
	}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {    
        HttpServletRequest req = (HttpServletRequest) request;
        UserBE user = (UserBE) req.getSession().getAttribute("user");

<<<<<<< HEAD
        if ((user != null ) || req.getRequestURI().endsWith("login.xhtml")) {
=======
        if ((auth != null && auth.isLoggedIn()) || req.getRequestURI().endsWith("login.xhtml")) {
        	       	
        	// User is logged in, so just continue request.
>>>>>>> 3e77563b63d972c706103e341008692835ddeea9
        	
            chain.doFilter(request, response);
        } else {
            // User is not logged in, so redirect to login
        	
            HttpServletResponse res = (HttpServletResponse) response;
            res.sendRedirect(req.getContextPath() + "/faces/login.xhtml");
        }
    }

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	
    
}