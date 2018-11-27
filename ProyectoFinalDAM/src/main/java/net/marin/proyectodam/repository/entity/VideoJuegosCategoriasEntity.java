package net.marin.proyectodam.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "VIDEOJUEGOS_PERTENECEN_CATEGORIAS")
@IdClass(VideoJuegosCategoriasEntity.class)
public class VideoJuegosCategoriasEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int idVideojuego;
	int idCategorias;
	
	public VideoJuegosCategoriasEntity() {
		super();
	}
	
	public VideoJuegosCategoriasEntity(int idVideojuego) {
		this.idVideojuego= idVideojuego;
		//appRoleEntities = new HashSet<AppRoleEntity>();//new HashSet<UserRoleEntity>();
	}
	
	@Id
	@Column(name = "ID_VIDEOJUEGOS")
	public int getIdVideojuego() {
		return idVideojuego;
	}
	public void setIdVideojuego(int idVideojuego) {
		this.idVideojuego = idVideojuego;
	}
	
	@Id
	@Column(name = "ID_CATEGORIAS")
	public int getIdCategorias() {
		return idCategorias;
	}
	public void setIdCategorias(int idCategorias) {
		this.idCategorias = idCategorias;
	}
	
	

}
