package net.marin.proyectodam.utils.dto;


import java.util.Set;

import net.marin.proyectodam.repository.entity.AppUserEntity;


public class AppRoleDTO {

	/*
	 * Declaramos las variables
	 */
	int roleId;
	String role_name;
	Set<AppUserEntity> appUserEntities;
	
	//CONSTRUCTOR
	
	public AppRoleDTO() {
		super();
	}

	//GETTERS Y SETTERS

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRole_name() {
		return role_name;
	}


	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public Set<AppUserEntity> getAppUserEntities() {
		return appUserEntities;
	}

	public void setAppUserEntities(Set<AppUserEntity> appUserEntities) {
		this.appUserEntities = appUserEntities;
	}

}

