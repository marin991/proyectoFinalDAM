package net.marin.proyectodam.service;

import java.util.List;

import net.marin.proyectodam.utils.dto.AppUserDTO;
import net.marin.proyectodam.utils.dto.JuegoDTO;
import net.marin.proyectodam.utils.dto.UserRoleDTO;
import net.marin.proyectodam.utils.dto.VideojuegosCategoriasDTO;
import net.marin.proyectodam.utils.dto.VideojuegosPlataformasDTO;

//Interfaz que implementa  UserService implements necesaria para poder usar la clase con los metodos desde el AppManagedBean (Herencia multiple)
public interface UserService {

	public void newAppUser(AppUserDTO appUserDTO) throws Exception;
	
	public void deleteAppUser(String userNameToDelete) throws Exception;
	
	public void deleteAppUserRole(String userNameToDelete) throws Exception;
	
	public void deleteGame(int gameToDelete);

	public List<AppUserDTO> findAll();
	
	public List<JuegoDTO> findAllGames();
	
	public void updateAppUser(AppUserDTO userDTOToUpdate) throws Exception;
	
	public boolean findOne(String userName);
	
	public  AppUserDTO login(String userName);

	void newUserRole(UserRoleDTO userRoleDTO) throws Exception;

	void updateAppUserRole(AppUserDTO appUserDTOToUpdate) throws Exception;
	
	public void newJuego(JuegoDTO juegoDTO) throws Exception;
	
	public void newCategory(VideojuegosCategoriasDTO juegoCatDTO) throws Exception;
	
	public void newPlatform(VideojuegosPlataformasDTO juegoCatDTO) throws Exception;
	
	public void updateGame(JuegoDTO juegoDTOToUpdate) throws Exception;

	
}