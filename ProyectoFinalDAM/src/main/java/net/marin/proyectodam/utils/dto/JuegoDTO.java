package net.marin.proyectodam.utils.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;

import net.marin.proyectodam.repository.entity.AppRoleEntity;
import net.marin.proyectodam.repository.entity.AppUserEntity;
import net.marin.proyectodam.repository.entity.CategoriasEntity;
import net.marin.proyectodam.repository.entity.JuegoEntity;
import net.marin.proyectodam.repository.entity.PlataformasEntity;
import net.marin.proyectodam.repository.entity.UsuarioValoraEntity;

@ManagedBean(name = "juegosDTO")
@ViewScoped
public class JuegoDTO implements Serializable {
  

	/*
	 * Declaramos las variables
	 */
	int idVideojuego;
	String nombre;
	int año;
	String imagen;
	
	private Set<PlataformasEntity> plataformasEntity;
	
	private Set<CategoriasEntity> categoriasEntity;
	
	private Set<UsuarioValoraEntity> usuarioValoraEntites;
	
	int categoryEntityId;
	int platFormEntityId;
	
	//CONSTRUCTOR
	
	public JuegoDTO() {
		super();
	}

	public int getIdVideojuego() {
		return idVideojuego;
	}

	public void setIdVideojuego(int idVideojuego) {
		this.idVideojuego = idVideojuego;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getAño() {
		return año;
	}

	public void setAño(int año) {
		this.año = año;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Set<PlataformasEntity> getPlataformasEntity() {
		return plataformasEntity;
	}

	public void setPlataformasEntity(Set<PlataformasEntity> plataformasEntity) {
		this.plataformasEntity = plataformasEntity;
	}

	public Set<CategoriasEntity> getCategoriasEntity() {
		return categoriasEntity;
	}

	public void setCategoriasEntity(Set<CategoriasEntity> categoriasEntity) {
		this.categoriasEntity = categoriasEntity;
	}

	public Set<UsuarioValoraEntity> getUsuarioValoraEntites() {
		return usuarioValoraEntites;
	}

	public void setUsuarioValoraEntites(Set<UsuarioValoraEntity> usuarioValoraEntites) {
		this.usuarioValoraEntites = usuarioValoraEntites;
	}

	public int getCategoryEntityId() {
		return categoryEntityId;
	}

	public void setCategoryEntityId(int categoryEntityId) {
		this.categoryEntityId = categoryEntityId;
	}

	public int getPlatFormEntityId() {
		return platFormEntityId;
	}

	public void setPlatFormEntityId(int platFormEntityId) {
		this.platFormEntityId = platFormEntityId;
	}
	
	
}