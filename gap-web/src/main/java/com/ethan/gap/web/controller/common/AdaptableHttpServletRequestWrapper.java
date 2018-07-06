package com.ethan.gap.web.controller.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class AdaptableHttpServletRequestWrapper extends HttpServletRequestWrapper {

	private static final Logger logger = LoggerFactory.getLogger(AdaptableHttpServletRequestWrapper.class);
	private Map<String, String[]> adaptableParams = new HashMap<String, String[]>();
	
	
	public AdaptableHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		this.adaptableParams.putAll(request.getParameterMap());
	}
	
	@Override
	public String getParameter(String name){
		String[] params = adaptableParams.get(name);
		if(params == null || params.length == 0){
			return "";
		}
		return modifyParamPrefix(name, params[0]);
	}

	private String modifyParamPrefix(String paramKey, String paramValue){
		String modifyStr = "seage".concat(paramValue);
		addParameter(paramKey, modifyStr);
		return modifyStr;
	}
	
	private void addParameter(String name, Object value){
		if(value != null){
			if(value instanceof String[]){
				this.adaptableParams.put(name, (String[])value);
			}else if(value instanceof String){
				this.adaptableParams.put(name, new String[]{(String) value});
			}else{
				this.adaptableParams.put(name, new String[]{String.valueOf(value)});
			}
		}
	}
	
	@Autowired
	public String[] getParameterValues(String name){
		return adaptableParams.get(name);
	}

	public Map getParameterMap(){
		return adaptableParams;
	}
	
}
