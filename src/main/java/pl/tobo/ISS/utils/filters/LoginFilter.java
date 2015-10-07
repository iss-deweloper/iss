/**
*  Copyright (C) 2014,2015 Tomasz Bosak.
**/
package pl.tobo.ISS.utils.filters;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pl.tobo.ISS.utils.StringConstants;

@WebFilter("/*")
public class LoginFilter implements Filter{
	private static Logger logger = Logger.getLogger("pl.tobo.ISS");
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
			logger.entering("LoginFilter","doFilter");
			HttpServletRequest request = (HttpServletRequest) req;
	        HttpServletResponse response = (HttpServletResponse) res;
	        HttpSession session = request.getSession(false);

	        String uri = URLDecoder.decode(request.getRequestURI(), "UTF-8");
	        logger.finer("Request uri: "+ uri);

	        boolean needsLogin = true;
	        /** FIXED: 
	         Related to the application path
	              see: http://tobo.zz.mu/view.php?id=9
	        **/
	        String context = request.getContextPath();
	        logger.finer("Request context: "+ context);
	        /**      
	         Allows /static content be visible for not logged users:
	              https://github.com/iss-deweloper/iss/issues/15
	        **/
	        if (uri.equals(context+"/login") || 
	        		uri.equals(context+"/register") || 
	        		uri.equals(context+"/slideshow") ||
	        	    uri.startsWith(context+"/Resources") || 
	        	    uri.equals(context+"/") ||
	        	    uri.startsWith("/static"))
	        	needsLogin = false;
	        
	        if (needsLogin && (session == null || 
	            	session.getAttribute(
	        	    StringConstants.REQUEST_ATTR_LOGGED_USER) == null) ) {
	        	logger.info("User not logged for servlet uri: "+uri+" with context: "+context);
	        	request.getRequestDispatcher(StringConstants.ISS_VIEW_PATH+"login.jsp").forward(
						request, response);
	        } else {
	            chain.doFilter(req, res); // Logged-in user found, so just continue request.
	        }
	        logger.exiting("LoginFilter","doFilter");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		 logger.entering("LoginFilter","init");
		 logger.exiting("LoginFilter","init");
	}

}
