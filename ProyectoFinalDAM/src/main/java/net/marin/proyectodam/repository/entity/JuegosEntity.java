package net.marin.proyectodam.repository.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "VIDEOJUEGOS")
public class JuegosEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int idVideojuego;
	String nombre;
	int año;
	String imagen;
	
	private Set<PlataformasEntity> plataformasEntity;
	
	public JuegosEntity() {
		super();
	}
	
	public JuegosEntity(int idVideojuego) {
		this.idVideojuego= idVideojuego;
		//appRoleEntities = new HashSet<AppRoleEntity>();//new HashSet<UserRoleEntity>();
	}
	
	@Id//Indica que columna es una PK de SQL
	@Column(name = "ID_VIDEOJUEGOS")//Asignamos la variabe del getter a esta columna de la tabla
	public int getIdVideojuego() {
		return idVideojuego;
	}

	public void setIdVideojuego(int idVideojuego) {
		this.idVideojuego = idVideojuego;
	}
	
	@Column(name = "NOMBRE")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Column(name = "AÑO")
	public int getAño() {
		return año;
	}
	
	public void setAño(int año) {
		this.año = año;
	}
	
	@Column(name = "IMAGEN")
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	@ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            },mappedBy = "juegosEntity")
	public Set<PlataformasEntity> getPlataformasEntity() {
		return plataformasEntity;
	}

	public void setPlataformasEntity(Set<PlataformasEntity> plataformasEntity) {
		this.plataformasEntity = plataformasEntity;
	}
	
	
	
	
}
