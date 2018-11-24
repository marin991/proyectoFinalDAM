package net.marin.proyectodam.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.marin.proyectodam.repository.entity.UserRoleEntity;


@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Serializable>{
	
	
	//Borra el usuario pasandole el UserName
	public void deleteByUserName(String userNameToDelete);
	
}