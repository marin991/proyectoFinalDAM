package net.marin.proyectodam.repository.entity;

import java.io.Serializable;

public class UserValueId implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String userName;
	String gameName;
	
	public UserValueId() {
		
	}
	
	public UserValueId(String userName,String gameName) {
		
		this.userName = userName;
		this.gameName = gameName;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	
	
}
