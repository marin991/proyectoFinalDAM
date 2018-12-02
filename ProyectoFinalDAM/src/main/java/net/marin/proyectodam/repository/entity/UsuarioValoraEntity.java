package net.marin.proyectodam.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "USUARIOS_VALORA_VIDEOJUEGOS")
@IdClass(UserValueId.class)
public class UsuarioValoraEntity implements Serializable{
	
	 
	private static final long serialVersionUID = 1L;
		
	
	String userName;
	String gameName;
	int valoracion;
	int finalizado;
	String imagen;
	

	public UsuarioValoraEntity() {
		super();
	}

	public UsuarioValoraEntity(String userName, String gameName) {
		
		this.userName = userName;
		this.gameName = gameName;
	}
	
	@Id
	@Column(name = "USERNAME")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Id
	@Column(name = "GAMENAME")
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	
	@Column(name = "VALORACION")
	public int getValoracion() {
		return valoracion;
	}
	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}
	
	@Column(name = "COMPLETED")
	public int getFinalizado() {
		return finalizado;
	}
	public void setFinalizado(int finalizado) {
		this.finalizado = finalizado;
	}
	@Column(name = "IMAGEN")
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	

}
