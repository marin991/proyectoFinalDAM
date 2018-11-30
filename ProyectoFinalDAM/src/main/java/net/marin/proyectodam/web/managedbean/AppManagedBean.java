package net.marin.proyectodam.web.managedbean;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.event.DragDropEvent;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import net.marin.proyectodam.repository.entity.AppRoleEntity;
import net.marin.proyectodam.service.UserService;
import net.marin.proyectodam.utils.dto.AppUserDTO;
import net.marin.proyectodam.utils.dto.JuegoDTO;
import net.marin.proyectodam.utils.dto.UserRoleDTO;

@ManagedBean(name="appManagedBean")
@Component
public class AppManagedBean extends GenericManagedBean {

	private static final long serialVersionUID = 1L;

	/*
	 * Inyectamos la dependencia.
	 */
	@Autowired
	UserService userService;

	/*
	 * Declaramos las variables que serán usadas desde el .xhtml 
	 * Comunica el .xhtml con el ManagedBean.java
	 */
	private AppUserDTO managedBeanUserDTO;
	private UserRoleDTO managedBeanUserRoleDTO;
	private boolean logeado = false;
	private List<AppUserDTO> listAppUserDTO;
	private String userNameToDelete;
	private List<AppUserDTO> listAppUserSelected;
	
	
	
	//
	private List<AppUserDTO> droppedGames;
	
	@Autowired
	private PasswordEncoder encoder;//Security se usa para encriptar el pass
	
	
	/*
	 * Constructor por defecto
	 */
	public AppManagedBean() {
		super();
	}
	
	/*
	 * Instanciamos las variables para que podamos usarlas.
	 */
	@PostConstruct
	public void onInit() {
		managedBeanUserDTO = new AppUserDTO();
		managedBeanUserRoleDTO = new UserRoleDTO();
		listAppUserDTO = new ArrayList<>();
		userNameToDelete = "";
		listAppUserSelected = new ArrayList<>();
		
	}
	
	/**
	 * Método que crea un usuario
	 */
	public void newUser() {
		
		managedBeanUserDTO.setEncryptedPassword(encoder.encode(managedBeanUserDTO.getEncryptedPassword()));
		
		try {
			userService.newAppUser(managedBeanUserDTO);
			showInfoMessage("Exito", "Usuario creado satisfactoriamente.");
		}
		catch (Exception e) {
			showErrorMessage("Error ", e.getMessage());
		}
	}
	
	/**
	 * Método que crea un rols
	 */
	public void newRole() {
		
		managedBeanUserRoleDTO.setUserName(managedBeanUserDTO.getUserName());

		try {
			userService.newUserRole(managedBeanUserRoleDTO);
			showInfoMessage("Exito", "Usuario rol asignado satisfactoriamente.");
		}
		catch (Exception e) {
			showErrorMessage("Error ", e.getMessage());
		}
	}
	
	public UserRoleDTO getManagedBeanUserRoleDTO() {
		return managedBeanUserRoleDTO;
	}

	public void setManagedBeanUserRoleDTO(UserRoleDTO managedBeanUserRoleDTO) {
		this.managedBeanUserRoleDTO = managedBeanUserRoleDTO;
	}

	public void newUserAndRoleUser() {
		
		newUser();
		if(managedBeanUserRoleDTO.getRoleId() == 1) {
			newRole();
			managedBeanUserRoleDTO.setRoleId(2);
			newRole();
		}
		else {
			newRole();

		}
	}
	
		public void newUserAndRoleUserWithReset() {
		
		newUser();
		if(managedBeanUserRoleDTO.getRoleId() == 1) {
			newRole();
			managedBeanUserRoleDTO.setRoleId(2);
			newRole();
		}
		else {
			newRole();
			
		}
		reset();
	}
	/**
	 * Método que busca todos los usuarios en la BBDD
	 * @return
	 */
	public void findAll() {
		
		showInfoMessage("Exito", "Mostrando lista Usuarios");
		listAppUserDTO = userService.findAll();
	}
	

	/**
	 * Método que se encarga de limpiar los datos que hay en pantalla
	 */
	public void reset() {
		managedBeanUserDTO = new AppUserDTO();
		listAppUserDTO.clear();
		// tambien serviria poner listUserDTO = new ArrayList<>();
	}
	
	/**
	 * Método que se encarga de borrar usuario
	 */
	
	public void deleteUser() {

		try {
			//Esta linea borra el el registro user_role porque hay que borrarlo antes debido a la foreign key
			userService.deleteAppUser(getUserNameToDelete());
			showInfoMessage("Exito", "Usuario " + userNameToDelete + " borrado");
		} catch (Exception e) {
			showErrorMessage("Error ", e.getMessage());
		}
	}
	
	/**
	 * Metodo  que borra los usuarios seleccionados
	 * @return
	 */
	
	public void deleteSelectedUsers() {
	
		for (AppUserDTO appUsers : listAppUserSelected) {
			try {
				userService.deleteAppUser(appUsers.getUserName());//deleteAppUser(appUsers.getUserName());
				showInfoMessage("Exito", "El usuario con nombre de usuario " + appUsers.getUserName() + " borrado"  );
			} catch (Exception e) {
				showErrorMessage("Error", e.getMessage());
			}
		}
		listAppUserDTO = userService.findAll();
	}
	
	/**
	 * Métodos para la edición de usuarios se ejecuta al pulasar el boton editar de la pagina modify users
	 * @param event
	 */
	
	public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Usuario editado", ((AppUserDTO) event.getObject()).getUserName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        // Modificamos el código para poder usarlo en la capa de servicio
        AppUserDTO userDTOToUpdate = (AppUserDTO) event.getObject();
        try {
        	
			userService.updateAppUser(userDTOToUpdate);
			if(userDTOToUpdate.getAppRoleEntityId()>0 ) {
				userService.updateAppUserRole(userDTOToUpdate);
			}
			
			
		} catch (Exception e) {
			showErrorMessage("Error", e.getMessage());
		}
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edición cancelada", ((AppUserDTO) event.getObject()).getUserName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public boolean estaLogeado() {
		return logeado;
	}
    
    public UserDetails loginSecurity() {
    	
    	AppUserDTO appUserDTOToLog = userService.login(managedBeanUserDTO.getUserName());
    	UserDetails userDetails= (UserDetails)appUserDTOToLog;
    	GrantedAuthority role = new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				
				return Integer.toString(appUserDTOToLog.getAppRoleEntities().size());
				 
			}
		};
		return userDetails;
    }
    
    //Convierte list en un set
    public List<AppRoleEntity> setToList(Set<AppRoleEntity> appRoleEntities ){
    	
    	List<AppRoleEntity> listRoles = new ArrayList<AppRoleEntity>();
    	listRoles.addAll(appRoleEntities);
    	return listRoles;
    	
    }
    
    //Recorre el set de roles y devuelve string
    public String returnRole(Set<AppRoleEntity> appRoleEntities) {
    	
    	String rol ="";
    	for(AppRoleEntity i: appRoleEntities) {
    		
    		rol+=" "+i.getRole_name();
    	}
    	return rol.toLowerCase();
    }
    
	public void logout() {
		System.out.println("Pasando por logout");
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();
		logeado = false;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Metodos del la s galerias
	public void onGameDrop(DragDropEvent ddEvent) {
        AppUserDTO game = ((AppUserDTO) ddEvent.getData());
  
        droppedGames.add(game);
        listAppUserDTO.remove(game);
    }
     /*
    public void setService(CarService service) {
        this.service = service;
    }*/
 
    public List<AppUserDTO> getGames;
 
    public void setSelectedGame(AppUserDTO managedBeanUserDTO) {
        this.managedBeanUserDTO = managedBeanUserDTO;
    }
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	public boolean findOne(String userName) {
		//showInfoMessage("Pa", "Mostrando lista Usuarios");
		
		return userService.findOne(userName);
	}
    
    // GETTERS Y SETTERS

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public AppUserDTO getNewAppUserDTO() {
		return managedBeanUserDTO;
	}

	public void setNewAppUserDTO(AppUserDTO newAppUserDTO) {
		this.managedBeanUserDTO = newAppUserDTO;
	}

	public List<AppUserDTO> getListAppUserDTO() {
		return listAppUserDTO;
	}

	public void setListAppUserDTO(List<AppUserDTO> listAppUserDTO) {
		this.listAppUserDTO = listAppUserDTO;
	}

	public String getUserNameToDelete() {
		return userNameToDelete;
	}

	public void setUserNameToDelete(String userNameToDelete) {
		this.userNameToDelete = userNameToDelete;
	}

	public List<AppUserDTO> getListAppUserSelected() {
		return listAppUserSelected;
	}

	public void setListAppUserSelected(List<AppUserDTO> listAppUserSelected) {
		this.listAppUserSelected = listAppUserSelected;
	}

	public AppUserDTO getManagedBeanUserDTO() {
		return managedBeanUserDTO;
	}

	public void setManagedBeanUserDTO(AppUserDTO managedBeanUserDTO) {
		this.managedBeanUserDTO = managedBeanUserDTO;
	}

	public boolean isLogeado() {
		return logeado;
	}

	public void setLogeado(boolean logeado) {
		this.logeado = logeado;
	}
}
