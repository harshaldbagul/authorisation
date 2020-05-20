package com.nissandigital.components.common.authorisation.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.nissandigital.components.common.authorisation.model.API;
import com.nissandigital.components.common.authorisation.model.APIid;


@Transactional
@Repository
public interface APIRepository extends JpaRepository<API, APIid>{

	@Modifying
	@Query(value = "insert into api (role,api_url, app_id,method_type,componentid)	VALUES (:role,:api_url,:app_id,:method_type,:componentid)",nativeQuery=true)
	void saveAPI(String role,String api_url,String app_id,String method_type,String componentid);
	
	@Query(value= "select api_url,method_type from api,users where users.user_id=?1 and users.app_id=?3 and api.app_id=?3 and api.role=?2 and users.role=?2", nativeQuery = true)
	List<String> getAPIList(String userId, String role, String appName);

	@Query(value="select distinct api_id from api where api_url=?1 and app_id=?2",nativeQuery=true)
	String findAPIid(String apiURL,String appID);

	@Query(value="select app_id from api",nativeQuery = true)
	String[] findAppId();
	
	@Query(value="select role from api",nativeQuery = true)
	String[] findRoleNames();
	
	@Query(value="select api_id from api",nativeQuery = true)
	String[] findApiIds();
	
	@Query(value="select api_url from api",nativeQuery = true)
	String[] findApiUrls();
	
	@Query(value="select method_type from api",nativeQuery = true)
	String[] findMethodType();

	@Query(value="select app_id,api_url,role,method_type from api",nativeQuery=true)
	List<API> findAllApis();

	@Modifying
	@Query(value="delete from api where api_url=?1 and role=?2 and method_type=?3 ",nativeQuery=true)
	void deleteApi(String apiURL,String role,String method);
	

	@Query(value="select api_url from api where role=?1 and app_id=?2",nativeQuery=true)
	List<String> findApiUrls(String role, String appName);


	@Query(value="select componentid from api where role=?1 and api_url=?2 and app_id=?3",nativeQuery=true)
	String[] fetchConstraints(String role, String apiUrls, String appName);

	@Modifying
	@Query(value="update api set componentid=?5 where app_id=?1 and api_url=?2 and method_type=?3 and role=?4  ",nativeQuery=true)
	
	void editApi(String app_id, String api_url, String method_type, String role, String componentid);

	
}
