package net.marin.proyectodam.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PLATAFORMAS")
public class PlataformasEntity implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int idPlataforma;
	String nombre;
	
	public PlataformasEntity() {
		super();
	}
	
	public PlataformasEntity(int idPlataforma) {
		this.idPlataforma= idPlataforma;
		//appRoleEntities = new HashSet<AppRoleEntity>();//new HashSet<UserRoleEntity>();
	}
	
	@Id//Indica que columna es una PK de SQL
	@Column(name = "ID_PLATAFORMAS")//Asignamos la variabe del getter a esta columna de la tabla
	public int getIdPlataforma() {
		return idPlataforma;
	}

	public void setIdPlataforma(int idPlataforma) {
		this.idPlataforma = idPlataforma;
	}
	
	@Id//Indica que columna es una PK de SQL
	@Column(name = "ID_PLATAFORMAS")//Asignamos la variabe del getter a esta columna de la tabla
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
