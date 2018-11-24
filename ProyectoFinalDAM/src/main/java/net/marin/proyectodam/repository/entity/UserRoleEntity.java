package net.marin.proyectodam.repository.entity;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

//Clase entidad, mapea tablas SQL
@Entity
@Table(name = "user_role")
@IdClass(UserRoleEntity.class)
public class UserRoleEntity implements  Serializable{

	private static final long serialVersionUID = 1L;//Se genera al implementar serielizable
	String userName;
	int roleId;
	
	//CONSTRUCTOR
	
	public UserRoleEntity() {
		super();
	}
	
	/*
	 * Las anotaciones de hibernate (mapeo BBDD)pueden estar encima de los getters
	 * o de la declaración de las variables pero se tiene que elejir una opcion
	 * si se declaran sobre variables han de estar igual en todo el proyecto y viceversa.
	 * */
	@Id//Indica que columna es una PK de SQL
	@Column(name = "USER_NAME")//Asignamos la variabe del getter a esta columna de la tabla
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName =userName;
	}
	
	@Id
	@Column(name = "ROLE_ID")
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
}
