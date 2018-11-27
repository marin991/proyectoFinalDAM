package net.marin.proyectodam.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "VIDEOJUEGOS_PERTENECEN_PLATAFORMAS")
@IdClass(VideojuegosPlataformasEntity.class)
public class VideojuegosPlataformasEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	int idVideojuego;
	int idPlataforma;
	
	public VideojuegosPlataformasEntity() {
		super();
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
	@Column(name = "ID_PLATAFORMAS")
	public int getIdPlataforma() {
		return idPlataforma;
	}
	public void setIdPlataforma(int idPlataforma) {
		this.idPlataforma = idPlataforma;
	}
	
}
