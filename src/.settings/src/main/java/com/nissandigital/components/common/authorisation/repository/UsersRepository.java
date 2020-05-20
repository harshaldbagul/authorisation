package com.nissandigital.components.common.authorisation.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.annotations.SQLInsert;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nissandigital.components.common.authorisation.model.Users;


@Transactional
@Repository
public interface UsersRepository extends CrudRepository<Users, String>{

	@Modifying
	@Query(value = "insert into users (user_id, app_id, role,grant_access)	VALUES (:user_Id,:app_Id,:role,:grant_access)",nativeQuery=true)
	void saveUsers(String user_Id, String app_Id, String role,Boolean grant_access);

	@Query(value="select role from users where user_id=?1 and app_id=?2",nativeQuery=true)
	String[] findRoles(String user_id, String appName);

	@Query(value="select app_id from users",nativeQuery=true)
	String[] findAppId();

	@Query(value="select role from users",nativeQuery=true)
	String[] findRoleNames();

	@Query(value="select user_id from users", nativeQuery = true)
	String[] findUserIds();
	
	@Query(value="select count(*) from users where user_id=?1 and app_id=?2",nativeQuery=true)
	int findUserAndApp(String userId, String appName);

	@Query(value="select grant_access from users where user_id=?1 and app_id=?2",nativeQuery=true)
	Boolean findGrantStatus(String userId, String appName);

	@Modifying
	@Query(value="insert into users (user_id,role,app_id,udid,name,grant_access) VALUES(:user_Id,:role,:app_Id,:udid,:name,:grant_access)",nativeQuery=true)
	void InSightsRegister(String user_Id, String role, String app_Id, String udid, String name, boolean grant_access);
	
	@Query(value="select user_id,udid,name,grant_access from users",nativeQuery=true)
	List<List> findUserInSights();

	@Query(value="select user_id from users where udid=?1",nativeQuery=true)
	String findUserIdByudid(String udid);

	
	
	@Query(value="select grant_access from users",nativeQuery=true)
	Boolean[] findGrantAccess();

	
	
	@Query(value="select name from users where user_id=?1 and app_id=?2",nativeQuery=true)
	String getName(String userId, String appName);

	@Modifying
	@Query(value="delete from users where user_id=?1 and app_id=?2 and role=?3 and grant_access=?4 ",nativeQuery=true)
	void deleteUser(String userId,String appName,String role,Boolean grant);

	@Modifying
	@Query(value="update users set grant_access=?2 where udid=?1",nativeQuery=true)
	void changeGrantAcess(String uuid, Boolean status);

	@Modifying
	@Query(value="update users set grant_access=?4 where user_id=?1 and app_id=?2 and role=?3 ",nativeQuery=true)
	void editUser(String user_id, String app_id, String role, boolean grantAccess);


}
