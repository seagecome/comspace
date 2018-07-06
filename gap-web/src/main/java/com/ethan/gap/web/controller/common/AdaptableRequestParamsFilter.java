package com.ethan.gap.web.controller.common;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class AdaptableRequestParamsFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		AdaptableHttpServletRequestWrapper httpRequestWrapper = new AdaptableHttpServletRequestWrapper(request);
		filterChain.doFilter(httpRequestWrapper, response);
	}

}
