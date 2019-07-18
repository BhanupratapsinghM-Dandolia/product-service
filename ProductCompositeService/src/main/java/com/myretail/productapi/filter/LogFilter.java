package com.myretail.productapi.filter;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.DelegatingFilterProxy;

@Component
public class LogFilter extends DelegatingFilterProxy {

	public static final String HEADER_REQUEST_ID = "request-id";

	@Override
	public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		String requestId = request.getHeader(HEADER_REQUEST_ID);
		if (requestId == null || requestId.trim().isEmpty())
			requestId = UUID.randomUUID().toString();
		MDC.put(HEADER_REQUEST_ID, requestId);
		chain.doFilter(request, response);
	}
}
