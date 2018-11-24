package net.marin.proyectodam.utils.dto;

import java.util.Set;

import net.marin.proyectodam.repository.entity.AppRoleEntity;

import java.util.List;

public class AppUserDTO {

	/*
	 * Declaramos las variables
	 */
	String userName;
	String encryptedPassword;
	String nombre;
	String apellidos;
	String status;
	int firstVisit ;
	int appRoleEntityId;
	private Set<AppRoleEntity> appRoleEntities ;
	private List<AppRoleEntity> listAppRoleEntities ;

	//GETTERS Y SETTERS
	
	public AppUserDTO() {
		super();
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encrypted_password) {
		this.encryptedPassword = encrypted_password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getFirstVisit() {
		return firstVisit;
	}

	public void setFirstVisit(int firstVisit) {
		this.firstVisit = firstVisit;
	}

	public Set<AppRoleEntity> getAppRoleEntities() {
		return appRoleEntities;
	}

	public void setAppRoleEntities(Set<AppRoleEntity> appRoleEntities) {
		this.appRoleEntities = appRoleEntities;
	}

	public int getAppRoleEntityId() {
		return appRoleEntityId;
	}

	public void setAppRoleEntityId(int appRoleEntityId) {
		this.appRoleEntityId = appRoleEntityId;
	}

	public List<AppRoleEntity> getListAppRoleEntities() {
		return listAppRoleEntities;
	}

	public void setListAppRoleEntities(List<AppRoleEntity> listAppRoleEntities) {
		this.listAppRoleEntities = listAppRoleEntities;
	}	
}

