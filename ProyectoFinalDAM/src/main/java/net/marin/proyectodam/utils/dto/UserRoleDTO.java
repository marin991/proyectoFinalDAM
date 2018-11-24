package net.marin.proyectodam.utils.dto;

public class UserRoleDTO {

	/*
	 * Declaramos las variables
	 */
	String userName;
	int roleId;


	/*
	 * Declaramos los constructores 
	 */
	
	public UserRoleDTO() {
		super();
	}
	 public UserRoleDTO(String userName, int roleId) {
		 
		 this.userName = userName;
		 this.roleId = roleId;
	 }

	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	
	
}

