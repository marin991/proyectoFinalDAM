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

import net.marin.proyectodam.repository.entity.CategoriasEntity;

@Entity
@Table(name = "VIDEOJUEGOS23")
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
	
	//private Set<CategoriasEntity> categoriasEntity;

	
	public JuegosEntity() {
		super();
	}
	
	public JuegosEntity(int idVideojuego) {
		this.idVideojuego= idVideojuego;
		//categoriasEntity = new HashSet<CategoriasEntity>();//new HashSet<UserRoleEntity>();
		plataformasEntity = new HashSet<PlataformasEntity>();//new HashSet<UserRoleEntity>();

	}
	
	@Id//Indica que columna es una PK de SQL
	@Column(name = "ID_VIDEOJUEGOS")//Asignamos la variabe del getter a esta columna de la tabla
	public int getIdVideojuego() {
		return idVideojuego;
	}

	public void setIdVideojuego(int idVideojuego) {
		this.idVideojuego = idVideojuego;
	//	categoriasEntity = new HashSet<CategoriasEntity>();
		plataformasEntity = new HashSet<PlataformasEntity>();

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
	
	public void addUserRoleEntities(PlataformasEntity plataformasEntity) {
		this.plataformasEntity.add(plataformasEntity);//add(userRoleEntity);
	}
	
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            },mappedBy = "juegosEntityPlat")
	public Set<PlataformasEntity> getPlataformasEntity() {
		return plataformasEntity;
	}

	public void setPlataformasEntity(Set<PlataformasEntity> plataformasEntity) {
		this.plataformasEntity = plataformasEntity;
	}
	
}
