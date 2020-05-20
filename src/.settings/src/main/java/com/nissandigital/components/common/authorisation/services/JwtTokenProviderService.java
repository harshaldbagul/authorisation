package com.nissandigital.components.common.authorisation.services;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nissandigital.components.common.authorisation.repository.APIRepository;
import com.nissandigital.components.common.authorisation.repository.AppRepository;
import com.nissandigital.components.common.authorisation.repository.UsersRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtTokenProviderService {

	@Value("${app.jwtSecret}")
	private String jwtSecret;
	
	
	/*@Value("${app.jwtExpirationInSec}")
	private int jwtExpirationInSec;
	*/
	
	@Autowired
	private Logger logger;
	@Autowired
	private APIRepository apiRepository;
	
	@Autowired
	private AppRepository appRepository;
	
	@Autowired
	private UsersRepository userRepository;
	
	public Boolean checkTokenStatus(String userId, String appName) {
		Boolean status=true;
		status=userRepository.findGrantStatus(userId,appName);
		return status;
	}
	public String generateToken(String userId,String appName) {

		Date now = new Date();
		
		String name=userRepository.getName(userId, appName);
		//logger.info(name);
		double jwtExpirationInHour= appRepository.getExpiryTime(appName);
		int jwtExpirationInSec=(int) (jwtExpirationInHour*3600);
		
		Date expiryDate = new Date(now.getTime() + jwtExpirationInSec*1000);
		
		
		int user=userRepository.findUserAndApp(userId,appName);
		if(user==0)
		{
			return "usernotfound";
		}
		else if(user>0) {
		List<String> roles = Arrays.asList(userRepository.findRoles(userId,appName)).stream().distinct().collect(Collectors.toList());
		
		List<String> distinctApiUrls = new ArrayList<>();
		List<String> roleComponentValues;
		HashMap<String, List<String>> roleComponent=new HashMap<>() ;
		List<String> apiUrls=new ArrayList();
			for (String role : roles) {
				apiUrls.addAll(apiRepository.findApiUrls(role,appName));
				distinctApiUrls = apiUrls.stream().distinct().collect(Collectors.toList());
			}
	
		
		for(String role : roles) {
			List<String> roleComponentList = new ArrayList<>();
			for (String apis : distinctApiUrls) {
				
				roleComponentValues = Arrays.asList(apiRepository.fetchConstraints(role,apis,appName)).stream().distinct().collect(Collectors.toList());
				if(roleComponentValues!= null) {
					roleComponentList.addAll(roleComponentValues);
				}
			}
			
			roleComponent.put(role, roleComponentList);

		}
		
		
		
		List<String> apis = new ArrayList();
		@SuppressWarnings("unchecked")
		List<String> apiList=new ArrayList();
		for (String role : roles) {
			apis.addAll(apiRepository.getAPIList(userId,role,appName));
			apiList = apis.stream().distinct().collect(Collectors.toList()); 			
		}

		
		
		
		//JWT token
		HashMap<String, Object> reservedClaims = new HashMap<>();
		//HashMap<String, Object> publicClaims = new HashMap<>();
		HashMap<String, Object> privateClaims = new HashMap<>();
		Map<String, Object> claims = new HashMap<>();
		reservedClaims.put("iss", "NDHAuth");
		reservedClaims.put("sub", "User entitlements");
		reservedClaims.put("aud", userId);
		
		
		privateClaims.put("app", appName);
		privateClaims.put("uid", userId);
		if(!(name==null)) {
			privateClaims.put("name", name);	
		}
		privateClaims.put("api", apiList);
		privateClaims.put("con", roleComponent);
		
		claims.put("res", reservedClaims);
		claims.put("pri",privateClaims);
		
		Claims payload = Jwts.claims(claims);
		

		String jwt =  Jwts.builder()
				.setHeaderParam("typ", "JWT")
				.setClaims(payload)
				.setIssuedAt(new Date())
				.setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
		logger.info("jwt token:" + jwt);
		return jwt;
		}
		
		return "null";
	}

	
}
