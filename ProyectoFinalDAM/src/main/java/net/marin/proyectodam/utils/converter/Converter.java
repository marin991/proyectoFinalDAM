package net.marin.proyectodam.utils.converter;




import java.util.ArrayList;
import java.util.List;

import net.marin.proyectodam.repository.entity.AppRoleEntity;
import net.marin.proyectodam.repository.entity.AppUserEntity;
import net.marin.proyectodam.repository.entity.JuegoEntity;
import net.marin.proyectodam.repository.entity.UserRoleEntity;
import net.marin.proyectodam.repository.entity.VideoJuegosCategoriasEntity;
import net.marin.proyectodam.repository.entity.VideojuegosPlataformasEntity;
import net.marin.proyectodam.utils.dto.AppRoleDTO;
import net.marin.proyectodam.utils.dto.AppUserDTO;
import net.marin.proyectodam.utils.dto.JuegoDTO;
import net.marin.proyectodam.utils.dto.UserRoleDTO;
//Clase que convierte DTO en Entity
import net.marin.proyectodam.utils.dto.VideojuegosCategoriasDTO;
import net.marin.proyectodam.utils.dto.VideojuegosPlataformasDTO;
public class Converter {
	
	//Metodos para los objetos y entidades APP_USER
	public static AppUserEntity appUserDTOtoUserEntity(AppUserDTO appUserDTO) {
		AppUserEntity appUserEntity = new AppUserEntity();
		appUserEntity.setUserName(appUserDTO.getUserName());
		appUserEntity.setEncryptedPassword(appUserDTO.getEncryptedPassword());
		appUserEntity.setNombre(appUserDTO.getNombre());
		appUserEntity.setApellidos(appUserDTO.getApellidos());
		appUserEntity.setStatus(appUserDTO.getStatus());
		appUserEntity.setFirstVisit(appUserDTO.getFirstVisit());
		appUserEntity.setAppRoleEntities(appUserDTO.getAppRoleEntities());
		return appUserEntity;
	}
	
	public static AppUserDTO appUserEntityToUserDTO(AppUserEntity appUserEntity) {
		AppUserDTO appUserDTO = new AppUserDTO();
		appUserDTO.setUserName(appUserEntity.getUserName());
		appUserDTO.setEncryptedPassword(appUserEntity.getEncryptedPassword());
		appUserDTO.setNombre(appUserEntity.getNombre());
		appUserDTO.setApellidos(appUserEntity.getApellidos());
		appUserDTO.setStatus(appUserEntity.getStatus());
		appUserDTO.setFirstVisit(appUserEntity.getFirstVisit());
		//System.out.println("converter "+appUserEntity.getUserRoleEntities().size());
		appUserDTO.setAppRoleEntities(appUserEntity.getAppRoleEntities());
		return appUserDTO;
	}
	
	//Metodos para los objetos y entidades APP_ROLE
	public static AppRoleEntity appRoleDTOtoAppRoleEntity(AppRoleDTO appRoleDTO) {
		AppRoleEntity appRoleEntity = new AppRoleEntity();
		appRoleEntity.setRole_id(appRoleDTO.getRoleId());
		appRoleEntity.setRole_name(appRoleDTO.getRole_name());
		appRoleEntity.setAppUserEntities(appRoleDTO.getAppUserEntities());
		return appRoleEntity;
	}
	public static AppRoleDTO appRoleEntityToAppRoleDTO(AppRoleEntity appRoleEntity) {
		AppRoleDTO appRoleDTO = new AppRoleDTO();
		appRoleDTO.setRoleId(appRoleEntity.getRole_id());
		appRoleDTO.setRole_name(appRoleEntity.getRole_name());
		appRoleDTO.setAppUserEntities(appRoleEntity.getAppUserEntities());
		
		return appRoleDTO;
	}
	//Metodos para los objetos y entidades USER_ROLE	
	public static UserRoleEntity userRoleDTOtoUserRoleEntity(UserRoleDTO userRoleDTO) {
		UserRoleEntity userRoleEntity = new UserRoleEntity();
		userRoleEntity.setUserName(userRoleDTO.getUserName());
		userRoleEntity.setRoleId(userRoleDTO.getRoleId());
		
		return userRoleEntity;
	}
	
	public static UserRoleDTO userRoleEntityToUserRoleDTO(UserRoleEntity userRoleEntity) {
		UserRoleDTO userRoleDTO = new UserRoleDTO();
		userRoleDTO.setUserName(userRoleEntity.getUserName());
		userRoleDTO.setRoleId(userRoleEntity.getRoleId());
		return userRoleDTO;
	}
	
	//Metodos para los objetos y entidades Juego	
	public static JuegoEntity juegoDTOtoJuegoEntity(JuegoDTO juegoDTO) {
			JuegoEntity juegoEntity = new JuegoEntity();
			juegoEntity.setIdVideojuego(juegoDTO.getIdVideojuego());
			juegoEntity.setNombre(juegoDTO.getNombre());
			juegoEntity.setAño(juegoDTO.getAño());
			juegoEntity.setImagen(juegoDTO.getImagen());
			juegoEntity.setCategoriasEntity(juegoDTO.getCategoriasEntity());
			juegoEntity.setPlataformasEntity(juegoDTO.getPlataformasEntity());
			juegoEntity.setUsuarioValoraEntites(juegoDTO.getUsuarioValoraEntites());
			return juegoEntity;
		}
	
	//Metodos para los objetos y entidades Juego	
		public static JuegoDTO juegoEntityToJuegoDTO(JuegoEntity juegoEntity) {
				JuegoDTO juegoDTO = new JuegoDTO();
				juegoDTO.setIdVideojuego(juegoEntity.getIdVideojuego());
				juegoDTO.setNombre(juegoEntity.getNombre());
				juegoDTO.setAño(juegoEntity.getAño());
				juegoDTO.setImagen(juegoEntity.getImagen());
				juegoDTO.setCategoriasEntity(juegoEntity.getCategoriasEntity());
				juegoDTO.setPlataformasEntity(juegoEntity.getPlataformasEntity());
				juegoDTO.setUsuarioValoraEntites(juegoEntity.getUsuarioValoraEntites());
				return juegoDTO;
			}
	//Metodos para los objetos y entidades JuegoCategoria	
	public static VideoJuegosCategoriasEntity juegoCatDTOtoJuegoCatEntity(VideojuegosCategoriasDTO juegoCatDTO) {
			VideoJuegosCategoriasEntity juegoCatEntity = new VideoJuegosCategoriasEntity();
			juegoCatEntity.setIdVideojuego(juegoCatDTO.getIdVideojuego());
			juegoCatEntity.setIdCategorias(juegoCatDTO.getIdCategorias());
			return juegoCatEntity;
	}
	//Metodos para los objetos y entidades JuegoCategoria	
		public static VideojuegosCategoriasDTO juegoCatEntitytoJuegoCatDTO(VideoJuegosCategoriasEntity juegoCatEntity) {
				VideojuegosCategoriasDTO juegocCatDTO = new VideojuegosCategoriasDTO();
				juegocCatDTO.setIdVideojuego(juegoCatEntity.getIdVideojuego());
				juegocCatDTO.setIdCategorias(juegoCatEntity.getIdCategorias());
				return juegocCatDTO;
	}
	
		//Metodos para los objetos y entidades JuegoCategoria	
		public static VideojuegosPlataformasEntity juegoPlatDTOtoJuegoPlatEntity(VideojuegosPlataformasDTO juegoPlatDTO) {
			VideojuegosPlataformasEntity juegoPlatEntity = new VideojuegosPlataformasEntity();
			juegoPlatEntity.setIdVideojuego(juegoPlatEntity.getIdVideojuego());
			juegoPlatEntity.setIdPlataforma(juegoPlatDTO.getIdPlataforma());
			return juegoPlatEntity;
		}
		//Metodos para los objetos y entidades JuegoCategoria	
			public static VideojuegosPlataformasDTO juegoPlatEntitytoJuegoPlatDTO(VideojuegosPlataformasEntity juegoPlatEntity) {
					VideojuegosPlataformasDTO juegocPlatDTO = new VideojuegosPlataformasDTO();
					juegocPlatDTO.setIdVideojuego(juegoPlatEntity.getIdVideojuego());
					juegocPlatDTO.setIdPlataforma(juegoPlatEntity.getIdPlataforma());
					return juegocPlatDTO;
		}
	
	public static List<AppUserDTO> listAppUserEntityToListUserDTO(List<AppUserEntity> listAppUserEntity){
		List<AppUserDTO> listAppUserDTO = new ArrayList<>();
		for(AppUserEntity appUserEntity : listAppUserEntity) {
			AppUserDTO appUserDTO = new AppUserDTO();
			appUserDTO = appUserEntityToUserDTO(appUserEntity);
			listAppUserDTO.add(appUserDTO);	
		}
		return listAppUserDTO;
	}
	
	public static List<AppRoleDTO> listAppRoleEntityToListAppRoleDTO(List<AppRoleEntity> listAppRoleEntity){
		List<AppRoleDTO> listAppRoleDTO = new ArrayList<>();
		for(AppRoleEntity appRoleEntity : listAppRoleEntity) {
			AppRoleDTO appRoleDTO = new AppRoleDTO();
			appRoleDTO = appRoleEntityToAppRoleDTO(appRoleEntity);
			listAppRoleDTO.add(appRoleDTO);
		}
		return listAppRoleDTO;
	}

	public static List<UserRoleDTO> userRoleEntityToUserRoleDTO(List<UserRoleEntity> listuserRoleEntity){
		List<UserRoleDTO> listUserRoleDTO = new ArrayList<>();
		for(UserRoleEntity userRoleEntity : listuserRoleEntity) {
			UserRoleDTO userRoleDTO = new UserRoleDTO();
			userRoleDTO = userRoleEntityToUserRoleDTO(userRoleEntity);
			listUserRoleDTO.add(userRoleDTO);
		}
		return listUserRoleDTO;
	}
	
	public static List<JuegoDTO> listJuegoEntityToListJuegoDTO(List<JuegoEntity> listJuegoEntity){
		List<JuegoDTO> listJuegoDTO = new ArrayList<>();
		for(JuegoEntity juegoEntity : listJuegoEntity) {
			JuegoDTO juegoDTO = new JuegoDTO();
			juegoDTO = juegoEntityToJuegoDTO(juegoEntity);
			listJuegoDTO.add(juegoDTO);
		}
		return listJuegoDTO;
	}
}
