package com.toast.talkit.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.toast.talkit.model.dto.MemberDto;
import com.toast.talkit.service.AuthService;
import com.toast.talkit.service.MemberService;

@SessionAttributes("user, token")
@Controller
public class MemberController {
	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	AuthService authService;

	@Autowired
	MemberService memberService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Model model) {
		ModelAndView mav = new ModelAndView("board");
		
		Map<String, String> responseData = authService.getUserStatus();
		
		MemberDto memberDto = MemberDto.intiPaycoMember(responseData.get("idNo"), responseData.get("id"));
		
		logger.info(authService.getUserStatus().toString());
		logger.info(memberDto);
		
		return mav;
	}

}
