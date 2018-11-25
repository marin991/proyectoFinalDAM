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
@Table(name = "CATEGORIAS")
public class CategoriasEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int idPlataforma;
	String nombre;
	
	Set<JuegosEntity> juegosEntity;
	
	@Id//Indica que columna es una PK de SQL
	@Column(name = "ID_PLATAFORMAS")//Asignamos la variabe del getter a esta columna de la tabla
	public int getIdPlataforma() {
		return idPlataforma;
	}
	public void setIdPlataforma(int idPlataforma) {
		this.idPlataforma = idPlataforma;
		juegosEntity = new HashSet<JuegosEntity>();
	}
	
	@Column(name = "NOMBRE")
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
	 @JoinTable(name = "VIDEOJUEGOS_PERTENECEN_CATEGORIAS",//Nombre tabla N:N
     joinColumns = { @JoinColumn(name = "CATEGORIAS_ID_CATEGORIAS") },//Columna de la tabla pertenecieente a esta entity
     inverseJoinColumns = { @JoinColumn(name = "VIDEOJUEGOS_ID_VIDEOJUEGOS") })//Columna de la otra entidad presente en la tabla SQL
	public Set<JuegosEntity> getJuegosEntity() {
		return juegosEntity;
	}
	public void setJuegosEntity(Set<JuegosEntity> juegosEntity) {
		this.juegosEntity = juegosEntity;
	}
	
	
	
	


}
