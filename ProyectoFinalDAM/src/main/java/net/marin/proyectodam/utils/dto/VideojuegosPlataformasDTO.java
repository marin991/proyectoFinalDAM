package net.marin.proyectodam.utils.dto;

public class VideojuegosPlataformasDTO {
	
	int idVideojuego;
	int idPlataforma;
	
	public VideojuegosPlataformasDTO() {
		
		super();
	}
	
	public VideojuegosPlataformasDTO(int idVideojuego,int idPlataforma) {
		this.idVideojuego= idVideojuego;
		this.idPlataforma = idPlataforma;
		
	}
	public int getIdVideojuego() {
		return idVideojuego;
	}
	public void setIdVideojuego(int idVideojuego) {
		this.idVideojuego = idVideojuego;
	}
	public int getIdPlataforma() {
		return idPlataforma;
	}
	public void setIdPlataforma(int idPlataforma) {
		this.idPlataforma = idPlataforma;
	}
	
}
