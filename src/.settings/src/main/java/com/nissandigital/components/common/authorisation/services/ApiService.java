package com.nissandigital.components.common.authorisation.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nissandigital.components.common.authorisation.model.API;
import com.nissandigital.components.common.authorisation.model.APIid;
import com.nissandigital.components.common.authorisation.model.UserId;
import com.nissandigital.components.common.authorisation.model.Users;
import com.nissandigital.components.common.authorisation.payload.APIops;
import com.nissandigital.components.common.authorisation.payload.ApiRole;
import com.nissandigital.components.common.authorisation.payload.UserPOJO;
import com.nissandigital.components.common.authorisation.payload.apiPOJO;
import com.nissandigital.components.common.authorisation.repository.APIRepository;
import com.nissandigital.components.common.authorisation.repository.AppRepository;

import ch.qos.logback.classic.Logger;

@Service
public class ApiService {

	@Autowired
	Logger logger;
	@Autowired
	APIRepository apiRepository;
	
	@Autowired
	AppRepository appRepository;
	public static int count;
	public String generateApiID(String appID,String apiURL) {
		String apiID;
		String api=apiRepository.findAPIid(apiURL,appID);
		if(api==null)
		{
		  apiID=appID+count++;
		  return apiID;
	    }
		else {
			apiID=api;
			return apiID;
		}
			
	}
	public void addAPI(apiPOJO apis) throws Exception {
		   //String apiId=apiService.generateApiID(appId,apiURL);
	       String apiURL=apis.getApiURL();
	       String appId=apis.getAppId();
	       
	       Boolean app=appRepository.findAppId(appId);
	       if(app==true)
	       {
	       ApiRole[] roless=apis.getRoles();
	       logger.info(""+roless.length);
	    for(ApiRole roles:roless)
	    {
	        String[] methods=roles.getMethodType();

	        String componentID=roles.getComponentID();
	        String role=roles.getRole();
	        logger.info(role);
	        for (String methodType : methods) {
	        apiRepository.saveAPI(role,apiURL,appId,methodType,componentID);
	        }
	    }
	       }
	       else
	       {
	    	   throw new Exception();
	       }
	}
	public List<APIops> showApis() {

		List<API> apis = (List<API>) apiRepository.findAll();
		List<APIops>apiPOJO =new ArrayList<APIops>();
		for(int i=0;i<apis.size();i++)
		{
	    
	    APIid apiId=apis.get(i).getIdRole();
	    String apiURL=apiId.getApiURL();
	    String appName=apiId.getAppId();
	    String role=apiId.getRole();
	    String methods=apiId.getMethodId();
	    String components=apis.get(i).getComponentID();
	    APIops api=new APIops(apiURL,appName,role,methods,components);
	    apiPOJO.add(i, api);
		}
		return apiPOJO;
	}
	public void deleteApis(APIops api) {
		String apiURL=api.getApiURL();
	    String role=api.getRole();
	    String method=api.getMethodId();
	    String component=api.getComponentID();
	    String appId=api.getAppId();
        apiRepository.deleteApi(apiURL,role,method);
	}
	public void editApis(APIops api) {
		String appId=api.getAppId();
		String apiUrl=api.getApiURL();
		String methodType=api.getMethodId();
		String role=api.getRole();
		String componentId=api.getComponentID();
		apiRepository.editApi(appId,apiUrl,methodType,role,componentId);
		// TODO Auto-generated method stub
		
	}
}
