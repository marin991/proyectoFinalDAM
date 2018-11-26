package net.marin.proyectodam.repository.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	
	Set<JuegosEntity> juegosEntityPlat;
	
	

	
	public PlataformasEntity() {
		super();
	}
	
	public PlataformasEntity(int idPlataforma) {
		this.idPlataforma= idPlataforma;
		juegosEntityPlat = new HashSet<JuegosEntity>();//new HashSet<UserRoleEntity>();
	}
	
	@Id//Indica que columna es una PK de SQL
	@Column(name = "ID_PLATAFORMAS")//Asignamos la variabe del getter a esta columna de la tabla
	public int getIdPlataforma() {
		return idPlataforma;
	}

	public void setIdPlataforma(int idPlataforma) {
		this.idPlataforma = idPlataforma;
	}
	
	
	@Column(name = "NOMBRE")//Asignamos la variabe del getter a esta columna de la tabla
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	@ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                CascadeType.PERSIST
            })
	 @JoinTable(name = "VIDEOJUEGOS_PERTENECEN_PLATAFORMAS",//Nombre tabla N:N
     joinColumns = { @JoinColumn(name = "ID_PLATAFORMAS") },//Columna de la tabla pertenecieente a esta entity
     inverseJoinColumns = { @JoinColumn(name = "ID_VIDEOJUEGOS") })//Columna de la otra entidad presente en la tabla SQL 
	public Set<JuegosEntity> getJuegosEntityPlat() {
		return juegosEntityPlat;
	}

	public void setJuegosEntityPlat(Set<JuegosEntity> juegosEntityPlat) {
		this.juegosEntityPlat = juegosEntityPlat;
	}
		
}
