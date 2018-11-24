package net.marin.proyectodam.service.impl;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import net.marin.proyectodam.repository.AppUserRepository;
import net.marin.proyectodam.repository.UserRoleRepository;
import net.marin.proyectodam.repository.entity.AppUserEntity;
import net.marin.proyectodam.repository.entity.UserRoleEntity;
import net.marin.proyectodam.service.UserService;
import net.marin.proyectodam.utils.converter.Converter;
import net.marin.proyectodam.utils.dto.AppUserDTO;
import net.marin.proyectodam.utils.dto.UserRoleDTO;

//Clase con metodos de persistencia Jpa comunica las difernetes repositorios de las entidades a traves de la interfaz
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	AppUserRepository appUserRepository;//Repsitorio de la entidad necesario para usar los metodos de persistencia de su entidad
	@Autowired
	UserRoleRepository userRoleRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;//Security se usa para encriptar el pass
	
	//CONSTRUCTOR
	public UserServiceImpl() {
		super();
	}
	
	//Crea nuevos usuarios en la BBDD
	@Override
	public void newAppUser(AppUserDTO appUserDTO) throws Exception {
		
		AppUserEntity appUserEntity = Converter.appUserDTOtoUserEntity(appUserDTO);	
		
		if(appUserRepository.existsById(appUserDTO.getUserName())) {
			throw new Exception("El usuario con nombre " + appUserDTO.getUserName() + " ya existe");
			
		}
		else {
			appUserRepository.save(appUserEntity);
		}
	}
	
	//Crea nuevos roles en la BBDD
	@Override
	public void newUserRole(UserRoleDTO userRoleDTO) throws Exception {
	
		UserRoleEntity userRoleEntity = Converter.userRoleDTOtoUserRoleEntity(userRoleDTO);	
		userRoleRepository.save(userRoleEntity);
		
	}
	
	//Devuelve un list con todos los usuarios de la BBDD
	@Override
	public List<AppUserDTO> findAll() {
		
		//buscamos en la base de datos todos los registros de la tabla
		List<AppUserEntity> listAppUserEntity = appUserRepository.findAll();
		//Como lo que nos devolvio fue todo una lista de tipo UserEntity
		//tenemos que convertirlo en una lista de tipo UserDTO
		List<AppUserDTO> listAppUserDTO = Converter.listAppUserEntityToListUserDTO(listAppUserEntity);
		return listAppUserDTO;
		
		// Lo ideal es sustiuir las dos lineas anteriores por: (lo dejamos así para que no se pierdan)
		// return Converter.listUserEntityToListUserDTO(listUserEntity);
	}
	
	//Devuelve true si el usuario con este nombre existe
	@Override
	public boolean findOne(String userName) {
		
		return appUserRepository.existsById(userName);
	}
	
	//Borra usuarios
	@Override
	@Transactional
	public void deleteAppUser(String userNameToDelete) {
		
		if (appUserRepository.existsById(userNameToDelete)) {			// Si el usuario existe, lo borra
	
			appUserRepository.deleteById(userNameToDelete); 			// lo borra por el id, que es el nombre
		}
		else {
			System.out.println("Usuario inexiste");
		}
	}
	
	//Borra el usuario de la BBDD con ese nombre de usuario
	@Override
	@Transactional
	public void deleteAppUserRole(String userNameToDelete) {
		
		if (userRoleRepository.existsById(userNameToDelete)) {			// Si el usuario existe, lo borra
			
			userRoleRepository.deleteByUserName(userNameToDelete);
			
		} else {
			System.out.println("UserRol inexiste");
		}
	}

	//Metodo que modifica UserRole en la BBDD
	@Override
	public void updateAppUserRole(AppUserDTO appUserDTOToUpdate) throws Exception {
		
		UserRoleDTO userRoleDTOToUpdate = new UserRoleDTO(appUserDTOToUpdate.getUserName(), appUserDTOToUpdate.getAppRoleEntityId());
		
		//Si el rol usuaro como ha cambiado lo qu hago es borrar el rol de administrador 
		if (appUserDTOToUpdate.getAppRoleEntityId() == 2) {
			
			userRoleDTOToUpdate.setRoleId(1);
			userRoleRepository.delete(Converter.userRoleDTOtoUserRoleEntity(userRoleDTOToUpdate));
			userRoleDTOToUpdate.setRoleId(2);
			userRoleRepository.save(Converter.userRoleDTOtoUserRoleEntity(userRoleDTOToUpdate));
		}
		else {
			userRoleRepository.save(Converter.userRoleDTOtoUserRoleEntity(userRoleDTOToUpdate));
		}
	}
	
	//Metodo que modifica usuarios en la BBDD
	@Override
	public void updateAppUser(AppUserDTO appUserDTOToUpdate) throws Exception {
		if (appUserRepository.existsById(appUserDTOToUpdate.getUserName())) {
			appUserRepository.save(Converter.appUserDTOtoUserEntity(appUserDTOToUpdate));
			
		} else {
			System.out.println("El usuario a modificar no existe.");
			
		}
	}
	
	//Metodo que devuelve un user dto (creo un objeto vacio por que es un metodo raro que devuelve un obejto vacio)
	@Override
	public AppUserDTO login(String userName) {
		
	
		AppUserDTO dtoNulo = null;
		if(appUserRepository.findById(userName).isPresent()) {
			
			return Converter.appUserEntityToUserDTO(appUserRepository.findById(userName).get());

		}
		else
			return dtoNulo;
	}

}//FIN CLASSE
