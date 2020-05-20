package com.nissandigital.components.common.authorisation.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nissandigital.components.common.authorisation.model.UserId;
import com.nissandigital.components.common.authorisation.model.Users;
import com.nissandigital.components.common.authorisation.payload.UserPOJO;
import com.nissandigital.components.common.authorisation.repository.AppRepository;
import com.nissandigital.components.common.authorisation.repository.UsersRepository;

import ch.qos.logback.classic.Logger;
@Service
public class UserService {

	@Autowired
	Logger logger;
	@Autowired
	UsersRepository usersRepository;
	@Autowired
	AppRepository appRepository;
	
	public void saveUser(List<UserPOJO> userList) throws Exception {

	
	      
		for(UserPOJO user : userList)
		{
	    
	    String userId=user.getUserId();
	    String appName=user.getAppId();
	    String[] roles=user.getRole().split(",");
	    Boolean grant=user.isGrantAccess();
	    Boolean app=appRepository.findAppId(appName);
	      if(app==true) {
	    	  for(String role : roles)
	  	    {
	  	    	usersRepository.saveUsers(userId,appName,role,grant);
	  	    }	  
	      }
	      else
	      {
	    	  throw new Exception();
	      }
	}
 }
	public void deleteUser(UserPOJO user) {
		
		    String userId=user.getUserId();
		    String appName=user.getAppId();
		    String role=user.getRole();
		    Boolean grant=user.isGrantAccess();
	        usersRepository.deleteUser(userId,appName,role,grant);		
	}
	public List<UserPOJO> showUsers() {
		

		List<Users> users = (List<Users>) usersRepository.findAll();
		List<UserPOJO>userPOJO =new ArrayList<UserPOJO>();
		for(int i=0;i<users.size();i++)
		{
	    
	    UserId userId=users.get(i).getUserIdRole();
	    String userID=userId.getUserId();
	    String appName=userId.getAppId();
	    String role=userId.getRole();
	    Boolean grant=users.get(i).isGrantAccess();
	    UserPOJO user=new UserPOJO(userID,appName,role,grant);
	    userPOJO.add(i, user);
	    logger.info(""+userPOJO.get(i));
		}
		return userPOJO;
		
	}
	public void editUser(UserPOJO user) {
		String appId=user.getAppId();
		String userId=user.getUserId();
		String role=user.getRole();
		boolean grantAccess=user.isGrantAccess();
		usersRepository.editUser(userId,appId,role,grantAccess);
		// TODO Auto-generated method stub
		
	}
}