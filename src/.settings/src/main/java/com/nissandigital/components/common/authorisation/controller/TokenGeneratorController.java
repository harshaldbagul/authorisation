package com.nissandigital.components.common.authorisation.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nissandigital.components.common.authorisation.payload.JwtAuthenticationResponse;
import com.nissandigital.components.common.authorisation.repository.UsersRepository;
import com.nissandigital.components.common.authorisation.services.JwtTokenProviderService;



@RestController
public class TokenGeneratorController {

	@Autowired
	JwtTokenProviderService tokenProvider;
	@Autowired
	UsersRepository user;
	
	@Autowired
	Logger logger;

	@CrossOrigin
	@PostMapping("/getToken")
	public ResponseEntity<?> createToken(HttpServletRequest request) {
		
		//String device = request.getHeader("header");
		String appName = request.getHeader("appName");
		logger.info(appName);


		if(appName.equals("Insights"))
		{

			String udid=request.getHeader("udid");

			String userId=user.findUserIdByudid(udid);
			logger.info(udid);
			String jwt = tokenProvider.generateToken(userId,appName);
			Boolean grants=tokenProvider.checkTokenStatus(userId,appName);
			if(jwt=="usernotfound")
			{
				return new ResponseEntity(HttpStatus.NOT_FOUND);
			}
			else if(!grants)
			{
				return new ResponseEntity(HttpStatus.UNAUTHORIZED);
			}
			else
			  return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
		}
		else
		{
			String userId = request.getHeader("userId");
			logger.info("userid: "+userId);
		String jwt = tokenProvider.generateToken(userId,appName);
		Boolean grants=tokenProvider.checkTokenStatus(userId,appName);
		if(jwt=="usernotfound")
		{
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		else if(!grants)
		{
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);
		}
		else
		  return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}

	}
}
