package net.marin.proyectodam.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.marin.proyectodam.repository.entity.AppUserEntity;

/*
 * Clase de repositorio con esta interfaz extendiando  de JPA podemos actuar en la BBDD recibe 
 * como parametro del set la entidad correspondiente (a su tabla SQl) así como serializable
 * */
@Repository
public interface AppUserRepository extends JpaRepository<AppUserEntity, Serializable>{
	
	//Metodo que borra por das (Tipo JPA declar nuevo metodo deleteBy Id nombre de clase)
	public  boolean deleteByUserName(String userName);
	
	
}