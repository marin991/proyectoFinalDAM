package net.marin.proyectodam.utils.dto;

public class UserValueGameDTO {
	
	String userName;
	String gameName;
	int valoracion;
	int finalizado;
	String imagen;
	
	public UserValueGameDTO() {
		
		super();
	}
	
	public UserValueGameDTO(String userName, String gameName) {
		
		this.userName = userName;
		this.gameName = gameName;
		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public int getValoracion() {
		return valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}

	public int getFinalizado() {
		return finalizado;
	}

	public void setFinalizado(int finalizado) {
		this.finalizado = finalizado;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	
	

}
