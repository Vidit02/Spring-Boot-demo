package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class AuthTokenFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = ((HttpServletRequest)(request));
		StringBuffer url = req.getRequestURL();
		System.out.println("url : " + url);
		String authToken = req.getHeader("authToken");
		String userid = req.getHeader("userId");
		System.out.println("authtoken Filter...");
		System.out.println("token ==> " + authToken);
		if (url.toString().contains("/public/")) {
//			HttpServletResponse resp = ((HttpServletResponse) (response));
//			resp.setContentType("application/json");
//			resp.setStatus(200);
//			resp.getWriter().write("{ \n 'msg':'Please Login and access Service'}");

			chain.doFilter(request, response);// accept-->process

		} else if (authToken == null || userid == null) {

			HttpServletResponse resp = ((HttpServletResponse) (response));
			resp.setContentType("application/json");
			resp.setStatus(401);
			resp.getWriter().write("{'msg':'Please Login and access Service'}");

		} else {

			chain.doFilter(request, response);// accept-->process
		}
		
	}

}
