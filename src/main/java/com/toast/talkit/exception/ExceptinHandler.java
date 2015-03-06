package com.toast.talkit.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@ControllerAdvice
public class ExceptinHandler {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public ModelAndView moveErrorView(String errorMessage){
		ModelAndView mav = new ModelAndView(new RedirectView("error"));		
		mav.addObject(errorMessage);
		
		return mav;
	}
	
	
	
	@ExceptionHandler(JsonException.class)
	public void handleException(Exception e) {
		String errorMessage = e.getCause().getMessage();
		
		logger.error(errorMessage, e.getCause());
		
		moveErrorView(errorMessage);
	}

}
