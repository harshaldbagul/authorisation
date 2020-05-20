package com.nissandigital.components.common.authorisation.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nissandigital.components.common.authorisation.model.API;
import com.nissandigital.components.common.authorisation.model.APIid;
import com.nissandigital.components.common.authorisation.model.Apps;
import com.nissandigital.components.common.authorisation.payload.APIops;
import com.nissandigital.components.common.authorisation.payload.ApiRole;
import com.nissandigital.components.common.authorisation.payload.apiPOJO;
import com.nissandigital.components.common.authorisation.repository.APIRepository;
import com.nissandigital.components.common.authorisation.services.ApiService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@javax.annotation.Generated(value = "com.nissandigital.components.common.authorisation.codegen.languages.SpringCodegen", date = "2018-11-14T08:34:28.245Z")

@Controller
public class ApiController {

    @Autowired
    Logger logger;
    
    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    @Autowired
    APIRepository apiRepository;
    @Autowired
    ApiService apiService;

    @org.springframework.beans.factory.annotation.Autowired
    public ApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }
	@CrossOrigin
    @GetMapping("/apis")
    public ModelAndView apiPage() {
    	return new ModelAndView("apis");
    }
	
	@CrossOrigin
	 @ApiResponses(value = { 
		        @ApiResponse(code = 201, message = "api created"),
		        @ApiResponse(code = 400, message = "invalid input, object invalid"),
		        @ApiResponse(code = 409, message = "an existing item already exists") })
    @PostMapping(value = "/apis")
    public ResponseEntity addApi (@RequestBody apiPOJO apis) {
        try
        {
		apiService.addAPI(apis);
        return new ResponseEntity(HttpStatus.OK);
        }
        catch(Exception e)
        {
        	String error="API couldn't be registered successfully";
        	return new ResponseEntity(error,HttpStatus.NOT_ACCEPTABLE);
        }
	}
	
	@CrossOrigin
	@GetMapping("/getApisList")
	public ResponseEntity showApplications() {
		List<String> appIdList = Arrays.asList(apiRepository.findAppId());
		List<String> roleList = Arrays.asList(apiRepository.findRoleNames());
		List<String> apiIdsList = Arrays.asList(apiRepository.findApiIds());
		List<String> apiUrlList = Arrays.asList(apiRepository.findApiUrls());
		List<String> methodList=Arrays.asList(apiRepository.findMethodType());
		HashMap<String, Object> jsonApiObject = new HashMap<>();
		jsonApiObject.put("AppName", appIdList);
		jsonApiObject.put("role", roleList);
		jsonApiObject.put("methodType", methodList);
		jsonApiObject.put("componentId", apiIdsList);
		jsonApiObject.put("apiUrl", apiUrlList);
		logger.info("api"+jsonApiObject);
		return new ResponseEntity(jsonApiObject, HttpStatus.CREATED);
	}
	@CrossOrigin
	@GetMapping("/getApis")
	public ResponseEntity<List<APIops>> showApis() {
		List<APIops> apis=apiService.showApis();
		return new ResponseEntity(apis, HttpStatus.OK);
	}
	
	@CrossOrigin
	@PostMapping(value="/deleteApi")
	public ResponseEntity deleteApi(@RequestBody APIops api) {
		
		 try {
			    apiService.deleteApis(api);
		        return new ResponseEntity(HttpStatus.OK);
		     }
         catch(Exception e)
		     {
        	 String error="API couldn't be deleted successfully";
		        return new ResponseEntity(error,HttpStatus.NOT_ACCEPTABLE);
		     }
		 }
	
	
	
	
	@CrossOrigin
	@PostMapping(value="/editApi")
	public ResponseEntity editApi(@RequestBody APIops api) {
		
		 try {
			    apiService.editApis(api);
		        return new ResponseEntity(HttpStatus.OK);
		     }
         catch(Exception e)
		     {
        	 String error="API couldn't be edited sucessfully";
		        return new ResponseEntity(error,HttpStatus.NOT_ACCEPTABLE);
		     }
		 }
}
