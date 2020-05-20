/*package com.nissandigital.components.common.authorisation.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nissandigital.components.common.authorisation.repository.ComponentRepository;
@javax.annotation.Generated(value = "com.nissandigital.components.common.authorisation.codegen.languages.SpringCodegen", date = "2018-11-14T08:34:28.245Z")

@Controller
public class ComponentController {
   
    @Autowired
    ComponentRepository componentRepository;
    
    @Autowired
    Logger logger;
	
    @CrossOrigin
    @GetMapping("/components")
    public ModelAndView componentPage() {
    	return new ModelAndView("components");
    }
	
	@CrossOrigin
    @PostMapping(value = "/components")
    public ModelAndView addComponent (@RequestParam String appId,@RequestParam String apiUrl,@RequestParam String componentId,@RequestParam String role) {
        componentRepository.saveAPI(appId,apiUrl,componentId,role);

        return new ModelAndView("components");
    }

	@CrossOrigin
	@GetMapping("/getComponentsList")
	public ResponseEntity showApplications() {
		List<String> appIdList = Arrays.asList(componentRepository.findAppId());
		List<String> roleList = Arrays.asList(componentRepository.findRoleNames());
		List<String> componentIdList = Arrays.asList(componentRepository.findComponentIds());
		List<String> apiUrlList = Arrays.asList(componentRepository.findApiUrls());
		HashMap<String, Object> jsonComponentObject = new HashMap<>();
		jsonComponentObject.put("AppName", appIdList);
		jsonComponentObject.put("Role", roleList);
		jsonComponentObject.put("componentId", componentIdList);
		jsonComponentObject.put("apiUrl", apiUrlList);
		logger.info(""+jsonComponentObject);
		return new ResponseEntity(jsonComponentObject, HttpStatus.CREATED);
		
	}
}*/
