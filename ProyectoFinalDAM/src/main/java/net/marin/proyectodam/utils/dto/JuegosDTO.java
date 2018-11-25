package net.marin.proyectodam.utils.dto;

import java.io.Serializable;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;

import net.marin.proyectodam.repository.entity.AppUserEntity;

@ManagedBean(name = "juegosDTO")
@ViewScoped
public class JuegosDTO implements Serializable {
  

	/*
	 * Declaramos las variables
	 */
	int idVideojuego;
	String nombre;
	int año;
	String imagen;
	
	
	//CONSTRUCTOR
	
	public JuegosDTO() {
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

}