package net.marin.proyectodam.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USUARIOS_VALORA_VIDEOJUEGOS")
public class UsuarioValoraEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private JuegosEntity juego;
	
	private AppUserEntity usuario;
	

	int valoracion;
	int finalizado;
	

	@Id//Indica que columna es una PK de SQL
	@ManyToOne
	@JoinColumn(name = "ID_VIDEOJUEGOS")//Asignamos la variabe del getter a esta columna de la tabla
	public JuegosEntity getJuego() {
		return juego;
	}
	public void setJuego(JuegosEntity juego) {
		this.juego = juego;
	}
	
	@Id//Indica que columna es una PK de SQL
	@ManyToOne
	@JoinColumn(name = "USER_NAME")
	public AppUserEntity getUsuario() {
		return usuario;
	}
	public void setUsuario(AppUserEntity usuario) {
		this.usuario = usuario;
	}
	
	@Column(name = "VALORACION")
	public int getValoracion() {
		return valoracion;
	}
	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}
	
	@Column(name = "FINALIZADO")
	public int getFinalizado() {
		return finalizado;
	}
	public void setFinalizado(int finalizado) {
		this.finalizado = finalizado;
	}
	
	



}
