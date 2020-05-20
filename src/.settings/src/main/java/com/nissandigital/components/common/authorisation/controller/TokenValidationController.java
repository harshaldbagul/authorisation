package com.nissandigital.components.common.authorisation.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nissandigital.components.common.authorisation.services.JwtTokenProviderService;
import com.nissandigital.components.common.authorisation.services.TokenValidationService;

@RestController
public class TokenValidationController {
	
	@Autowired
	JwtTokenProviderService tokenProvider;
	
	@Autowired
	TokenValidationService tokenValidationService;
	
	@Autowired
	private Logger logger;
	
	@CrossOrigin
	@PostMapping("/tokenValidation")
	public String tokenValidation(HttpServletRequest request){
		
		String jwtToken = request.getHeader("token");
		String requestedURL = request.getHeader("url");
		String methodType=request.getHeader("methodType");
		//String userId = request.getHeader("userId");
		logger.info("From header: "+jwtToken);
		logger.info("From header before valdation function : "+jwtToken);
		logger.info("From header method : "+ methodType);

		String status=tokenValidationService.validateToken(jwtToken,requestedURL,methodType);
				return status;
		
	}

}
