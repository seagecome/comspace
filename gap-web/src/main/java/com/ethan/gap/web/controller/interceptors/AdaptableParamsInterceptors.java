package com.ethan.gap.web.controller.interceptors;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ethan.gap.dal.model.UserLoginInfo;

public class AdaptableParamsInterceptors implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView mav) throws Exception {
		mav.addObject("accountId", request.getParameter("userId"));
		mav.addObject("accountType", request.getParameter("userName"));
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		Enumeration paramEnum = request.getParameterNames();
		while(paramEnum.hasMoreElements()){
			String paramName = (String)paramEnum.nextElement();
			System.out.println("===paraName=" + paramName);
			String[] params = request.getParameterValues(paramName);
			String paramValue = null;
			if(params != null && params.length > 1){
				paramValue = convertArrToStr(params);
			}else{
				paramValue = request.getParameter(paramName);
			}
			System.out.println("===paramValue=" + paramValue);
		}
		return true;
	}

	private String convertArrToStr(String[] params){
		String resultStr = null;
		for(int i = 0; i < params.length; i++){
			resultStr += params[i];
		}
		return resultStr;
	}
	
}
