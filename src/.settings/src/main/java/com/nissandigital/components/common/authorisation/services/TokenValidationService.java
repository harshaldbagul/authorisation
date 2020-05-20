package com.nissandigital.components.common.authorisation.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

 
enum Status{
	unauthorised,expired,authorised;
}

@Service
public class TokenValidationService {
	
	@Value("${app.jwtSecret}")
	private String jwtSecret;
	
	@Value("${app.jwtExpirationInSec}")
	private int jwtExpirationInSec;
	
	//@Value("${authorisation.server.base.url}")
	//private String baseUrl;
	
	@Autowired
	private Logger logger;
	
	static String userId = null;
	static String appName = null;
	enum Status{
		unauthorised,expired,authorised;
	}
	//possible statuses : authorised , unauthorised , expired
	public String validateToken(String jwtToken, String requestedURL,String methodType) {

		String[] validStates= {"unauthorised","expired","authorised"};
		String status=validStates[0];
		//logger.info("jwt secret key: "+ jwtSecret);
			try {
				Jws<Claims> jws = (Jws<Claims>) Jwts.parser()
						.setSigningKey(jwtSecret)
						.parseClaimsJws(jwtToken);
			Claims jwtBody = jws.getBody();
			HashMap<String, Object> privateClaims = new HashMap<>();
			privateClaims = (HashMap<String, Object>) jwtBody.get("pri");
			userId = (String) privateClaims.get("uid");
			//logger.info("userId from pri claim: "+userId);
			appName = (String) privateClaims.get("app");
			List<List> apiUrls=(List<List>) privateClaims.get("api");
		HashMap<String,String> components = (HashMap<String,String>) privateClaims.get("con");
		
			
			logger.info("Api list from token" +apiUrls);
			//logger.info("Component list from token" + components);
			//logger.info("User Id from header: "+userId);	
			//logger.info("requested url from header: "+requestedURL);
			//logger.info("privateClaims"+privateClaims);
			//logger.info("Body: "+jwtBody);
			
			
			
			for(int i=0;i<apiUrls.size();i++) {
				
				logger.info("Api list from token"+i+" " +apiUrls.get(i));

				Pattern patt=Pattern.compile((String) ((List)apiUrls.get(i)).get(0));
				Matcher matcher=patt.matcher(requestedURL);
				if(matcher.matches() && ((String)((List)apiUrls.get(i)).get(1)).equals(methodType))
				{
					status=validStates[2];
				}
			}
			
			logger.info("status: "+status);
			
			} catch (SignatureException e) {
				logger.info("signature exception: "+e);
			}
			catch (MalformedJwtException e) {
				logger.info("Token expired: "+e);
				
			}
			catch (ExpiredJwtException e) {
				logger.info(""+e);
				status = validStates[1];
			    /*String uri = baseUrl+"/getToken";
	            RestTemplate restTemplate = new RestTemplate();
	            HttpHeaders headers = new HttpHeaders();
	         
	            //logger.info("userId for header: "+userId);
	            logger.info("old token from cookie: "+jwtToken);
	            //headers.set("App-Name", appName);
	            headers.set("userId", userId);
	            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
	            //logger.info("userid in header: "+ headers.get("userId"));
	            ResponseEntity<?> result = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
	            String tokenBody=(String) result.getBody();
	            String token = tokenBody.substring(16, tokenBody.length()-23);
	            logger.info("new jwt token after expiration check: "+token);
				status = validateToken(token, requestedURL,userId);*/
				return status;
				
			}
			catch (UnsupportedJwtException e) {
				logger.info(""+e);
			}
			catch (IllegalArgumentException e) {
				logger.info(""+e);
			}
			return status;
		}
}
