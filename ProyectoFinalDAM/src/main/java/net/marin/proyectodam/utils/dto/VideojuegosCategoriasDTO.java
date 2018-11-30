package net.marin.proyectodam.utils.dto;

public class VideojuegosCategoriasDTO {
	
	int idVideojuego;
	int idCategorias;
	
	public VideojuegosCategoriasDTO() {
		
		super();
	}
	
	public VideojuegosCategoriasDTO(int idVideojuego,int idCategorias) {
		this.idVideojuego= idVideojuego;
		this.idCategorias = idCategorias;
		
	}
	
	public int getIdVideojuego() {
		return idVideojuego;
	}
	
	public void setIdVideojuego(int idVideojuego) {
		this.idVideojuego = idVideojuego;
	}
	
	public int getIdCategorias() {
		return idCategorias;
	}
	public void setIdCategorias(int idCategorias) {
		this.idCategorias = idCategorias;
	}
	
}
