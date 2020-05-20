package com.nissandigital.components.common.authorisation.repository;

/*
	import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

	import org.springframework.data.jpa.repository.JpaRepository;
	import org.springframework.data.jpa.repository.Modifying;
	import org.springframework.data.jpa.repository.Query;
	import org.springframework.stereotype.Repository;

import com.nissandigital.components.common.authorisation.model.Component;
import com.nissandigital.components.common.authorisation.model.ComponentId;


	@Transactional
	@Repository
	public interface ComponentRepository extends JpaRepository<Component, ComponentId>{

		@Modifying
		@Query(value = "insert into component (app_id,api_url,componentid, Role)	VALUES (:app_id,:api_url,:componentid,:Role)",nativeQuery=true)
		void saveAPI(String app_id,String api_url,String componentid,String role);

		@Query(value="select componentid from component where Role=?1 and api_url=?2 and app_id=?3",nativeQuery=true)
		String fetchConstraints(String role, String apiUrls, String appName);

		@Query(value="select api_url from component where Role=?1 and app_id=?2",nativeQuery=true)
		List<String> findApiUrls(String role, String appName);

		@Query(value="select app_id from component",nativeQuery=true)
		String[] findAppId();
		
		@Query(value="select Role from component",nativeQuery=true)
		String[] findRoleNames();
		
		@Query(value="select componentid from component",nativeQuery=true)
		String[] findComponentIds();
		
		@Query(value="select api_url from component",nativeQuery=true)
		String[] findApiUrls();

		
	}

*/
