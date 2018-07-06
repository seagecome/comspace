package com.ethan.gap.web.controller.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ethan.gap.web.config.WebsiteApplicationConfig;
import com.ethan.gap.web.vo.ViewPassVo;

@Controller
@RequestMapping("/wrapper/*")
public class InterceptorAnalysisController {
	
	@Autowired
	private WebsiteApplicationConfig config;
	
	@RequestMapping(value="/fromRequest.do")
	public String fromRequest(HttpServletRequest request, HttpServletResponse response, Model model, String userName, String userId, ViewPassVo vpv){
		model.addAttribute("config", config);
		return "loginInfoShow";
	}
	
	@RequestMapping(value="/fromPojo.do")
	public String fromPojo(HttpServletRequest request, HttpServletResponse response, Model model, ViewPassVo vpv){
		System.out.println("===userid="+ vpv.getUserId() + "===userName=" + vpv.getUserName());
		model.addAttribute("config", config);
		return "loginInfoShow";
	}
}
