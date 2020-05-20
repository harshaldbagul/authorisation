package com.nissandigital.components.common.authorisation.repository;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nissandigital.components.common.authorisation.model.Apps;

@Transactional
@Repository
public interface AppRepository extends CrudRepository<Apps, String>{

	@Modifying
	@Query(value = "insert into app( app_id, callbackurl,expiry_time)	VALUES (:app_id,:callbackurl,:expiry_time)",nativeQuery=true)
	void saveApp(@Valid String app_id, String callbackurl,double expiry_time);

	@Modifying
	@Query(value="delete from app where app_id=?1",nativeQuery=true)
	void deleteApp(String appId);

	@Query(value="select expiry_time from app where app_id=?1",nativeQuery=true)
	double getExpiryTime(String appName);

	@Query(value="select cast(count(*)as bit) from app where app_id=?1",nativeQuery=true)
	Boolean findAppId(String appId);

	@Modifying
	@Query(value = "update app set expiry_time=?2,callbackurl=?3 where app_id=?1",nativeQuery=true)
	void editApp(String app_id, double expiry_time, String callbackUrl);

}
