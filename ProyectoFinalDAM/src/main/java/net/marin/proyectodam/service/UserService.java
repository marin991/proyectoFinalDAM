package net.marin.proyectodam.service;

import java.util.List;

import net.marin.proyectodam.utils.dto.AppUserDTO;
import net.marin.proyectodam.utils.dto.UserRoleDTO;

//Interfaz que implementa  UserService implements necesaria para poder usar la clase con los metodos desde el AppManagedBean (Herencia multiple)
public interface UserService {

	public void newAppUser(AppUserDTO appUserDTO) throws Exception;
	
	
	public void deleteAppUser(String userNameToDelete) throws Exception;
	
	public void deleteAppUserRole(String userNameToDelete) throws Exception;

	public List<AppUserDTO> findAll();
	
	public void updateAppUser(AppUserDTO userDTOToUpdate) throws Exception;

	public boolean findOne(String userName);
	
	public  AppUserDTO login(String userName);

	void newUserRole(UserRoleDTO userRoleDTO) throws Exception;

	void updateAppUserRole(AppUserDTO appUserDTOToUpdate) throws Exception;

	
}