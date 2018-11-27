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
	
	Set<JuegoEntity> juegosEntityCat;
	

	public CategoriasEntity() {
		super();
	}
	
	public CategoriasEntity(int idPlataforma) {
		this.idPlataforma= idPlataforma;
		juegosEntityCat = new HashSet<JuegoEntity>();//new HashSet<UserRoleEntity>();
	}
	
	@Id//Indica que columna es una PK de SQL
	@Column(name = "ID_CATEGORIAS")//Asignamos la variabe del getter a esta columna de la tabla
	public int getIdPlataforma() {
		return idPlataforma;
	}
	public void setIdPlataforma(int idPlataforma) {
		this.idPlataforma = idPlataforma;
		juegosEntityCat = new HashSet<JuegoEntity>();
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
     joinColumns = { @JoinColumn(name = "ID_CATEGORIAS") },//Columna de la tabla pertenecieente a esta entity
     inverseJoinColumns = { @JoinColumn(name = "ID_VIDEOJUEGOS") })//Columna de la otra entidad presente en la tabla SQL
	public Set<JuegoEntity> getjuegosEntityCat() {
		return juegosEntityCat;
	}
	
	public void setjuegosEntityCat(Set<JuegoEntity> juegosEntityCat) {
		this.juegosEntityCat = juegosEntityCat;
	}
	
}
