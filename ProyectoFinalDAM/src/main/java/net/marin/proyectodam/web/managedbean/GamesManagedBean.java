package net.marin.proyectodam.web.managedbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.DragDropEvent;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import net.marin.proyectodam.repository.entity.AppRoleEntity;
import net.marin.proyectodam.repository.entity.CategoriasEntity;
import net.marin.proyectodam.repository.entity.PlataformasEntity;
import net.marin.proyectodam.service.UserService;
import net.marin.proyectodam.utils.dto.AppUserDTO;
import net.marin.proyectodam.utils.dto.JuegoDTO;
import net.marin.proyectodam.utils.dto.PlataformasDTO;
import net.marin.proyectodam.utils.dto.UserValueGameDTO;
import net.marin.proyectodam.utils.dto.VideojuegosCategoriasDTO;
import net.marin.proyectodam.utils.dto.VideojuegosPlataformasDTO;

@ManagedBean(name = "gamesManagedBean")
@Component
public class GamesManagedBean extends GenericManagedBean implements Serializable {
  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@ManagedProperty("#{gameService}")
	
	@Autowired
	UserService userService;
    
	JuegoDTO managedJuegoDTO;
	VideojuegosPlataformasDTO managedPlataformaDTO;
	VideojuegosCategoriasDTO managedCategoriaDTO;
	int idGameToDelete;
    private List<JuegoDTO> gamesSelected;

	
    private List<JuegoDTO> games;
     
    private List<JuegoDTO> droppedGames;
    
    private List<UserValueGameDTO> listUserValueGames;
    
    private List<UserValueGameDTO> listUserValueGamesSelected;
    
    UserValueGameDTO userValueGameDTO;
    
    UserValueGameDTO userValueGameDTOToDelete;

     
    private JuegoDTO selectedGame;
    
    Authentication authentication;
    
    @PostConstruct
    public void init() {
    	authentication = SecurityContextHolder.getContext().getAuthentication();
    	managedJuegoDTO = new JuegoDTO();
    	userValueGameDTO = new UserValueGameDTO();
    	managedPlataformaDTO = new VideojuegosPlataformasDTO();
    	managedCategoriaDTO = new VideojuegosCategoriasDTO();
    	System.out.println("\n gamessss");
        games = userService.findAllGames();
        //listUserValueGames = userService.findAllUserValues());
        droppedGames = new ArrayList<JuegoDTO>();
        gamesSelected = new ArrayList<JuegoDTO>();
        listUserValueGamesSelected = new ArrayList<UserValueGameDTO>();
        
    }
    
    public void deleteUserValueGame() {

		try {
			//Esta linea borra el el registro user_role porque hay que borrarlo antes debido a la foreign key
			userService.deleteGameValue(userValueGameDTOToDelete);
			showInfoMessage("Exito", "Usuario " + userValueGameDTOToDelete.getGameName() + " borrado");
		} catch (Exception e) {
			showErrorMessage("Error ", e.getMessage());
		}
	}
    
    public void findAll() {

		showInfoMessage("Exito", "Mostrando lista Usuarios");
		games = userService.findAllGames();
	}
    
    public void findAllValues() {
    	authentication = SecurityContextHolder.getContext().getAuthentication();

		showInfoMessage("Exito", "Mostrando lista Usuarios");
        listUserValueGames = userService.findAllUserValues(authentication.getName());
	}
    public void reload() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }
    
    public void newGame() {
		
		try {
			userService.newJuego(managedJuegoDTO);
			showInfoMessage("Exito", "Usuario creado satisfactoriamente.");
		}
		catch (Exception e) {
			showErrorMessage("Error ", e.getMessage());
		}
	}
    
    public void newCategory() {
    	
    	managedCategoriaDTO.setIdVideojuego(managedJuegoDTO.getIdVideojuego());
		try {
			showInfoMessage("Exito", "Usuario creado satisfactoriamente.");
			userService.newCategory(managedCategoriaDTO);
		}
		catch (Exception e) {
			showErrorMessage("Error ", e.getMessage());
		}
	}
    public void newPlatform() {
		
    	managedPlataformaDTO.setIdVideojuego(managedJuegoDTO.getIdVideojuego());
		try {
			System.out.println("managedPlataformaDTO.getIdVideojuego()"+managedPlataformaDTO.getIdVideojuego());
			userService.newPlatform(managedPlataformaDTO);
			showInfoMessage("Exito", "Usuario creado satisfactoriamente.");
		}
		catch (Exception e) {
			showErrorMessage("Error ", e.getMessage());
		}
	}
 
    
    public void newGameAndCategory() {
    	
    	newGame();
    	newCategory();
    	newPlatform();
    	reset();
    }
    
    public void deleteGame() {
    	
    	try {
			userService.deleteGame(getIdGameToDelete());//deleteAppUser(appUsers.getUserName());
			showInfoMessage("Exito", "El juego con ID " + managedJuegoDTO.getIdVideojuego() + " borrado"  );
		} catch (Exception e) {
			showErrorMessage("Error", e.getMessage());
		}

    }
    
    
    public void deleteSelectedGames() {
    	
		for (JuegoDTO gamesSelectedf : gamesSelected) {	
			try {
				userService.deleteGame(gamesSelectedf.getIdVideojuego());//deleteAppUser(appUsers.getUserName());
				showInfoMessage("Exito", "El juego con ID " + gamesSelectedf.getIdVideojuego() + " borrado"  );
			} catch (Exception e) {
				showErrorMessage("Error", e.getMessage());
			}
		}
		gamesSelected = userService.findAllGames();
	}
    
    public void onGamesDrop(DragDropEvent ddEvent) {
        JuegoDTO game = ((JuegoDTO) ddEvent.getData());
  
        droppedGames.add(game);
        games.remove(game);
    }
    
    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("VideoJuego editado con exito"+((JuegoDTO) event.getObject()).getIdVideojuego());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        // Modificamos el código para poder usarlo en la capa de servicio
        JuegoDTO juegoToUpdate = (JuegoDTO) event.getObject();
        try {
        	
			userService.updateGame(juegoToUpdate);
			System.out.println("onRowEdit()"+juegoToUpdate.getCategoryEntityId());
			
			if(juegoToUpdate.getCategoryEntityId()!=0) {
				
				userService.updateGameCategory(juegoToUpdate);
			}
			
			if(juegoToUpdate.getPlatFormEntityId()!=0) {
				
				userService.updateGamePlatform(juegoToUpdate);
			}
			
		} catch (Exception e) {
			showErrorMessage("Error", e.getMessage());
		}
        managedJuegoDTO = juegoToUpdate;
    }
    
    public void onRowEditValue(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("VideoJuego editado con exito"+((UserValueGameDTO) event.getObject()).getGameName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
       // Modificamos el código para poder usarlo en la capa de servicio
       UserValueGameDTO userValueGameToUpdate = (UserValueGameDTO) event.getObject();
       try {
    	 //  userService.deleteGameValue(userValueGameToUpdate);
    	   userService.updateUserValueGame(userValueGameToUpdate);
			System.out.println("onRowEdit()"+userValueGameToUpdate.getGameName());
			
			
		} catch (Exception e) {
			showErrorMessage("Error", e.getMessage());
		}
       userValueGameDTO = userValueGameToUpdate;
    }
    
    public void reset() {
    	managedJuegoDTO = new JuegoDTO();
    	games.clear();
		// tambien serviria poner listUserDTO = new ArrayList<>();
	}
    
    public void resetForUser() throws IOException {
    	//managedJuegoDTO = new JuegoDTO();
    	//games.clear();
    	//selectedGame = new JuegoDTO();
    	droppedGames.clear();
    	gamesSelected.clear();
    	games = userService.findAllGames();
    	reload();
	}
    
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edición cancelada", ((AppUserDTO) event.getObject()).getUserName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void resetForValue() throws IOException {
    	//managedJuegoDTO = new JuegoDTO();
    	listUserValueGames.clear();
    	//listUserValueGamesSelected.clear();
    	//reload();
		// tambien serviria poner listUserDTO = new ArrayList<>();
	}
    public void userValueGame() throws Exception {
       
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String currentPrincipalName = authentication.getName();
    	System.out.println("currentPrincipalName"+currentPrincipalName);
    	System.out.println("userValueGame()"+selectedGame.getNombre());
    	UserValueGameDTO userValueGame = new UserValueGameDTO(currentPrincipalName, selectedGame.getNombre());
    	userValueGame.setImagen(selectedGame.getImagen());
    	userService.newUserValueGame(userValueGame);
    	reload();
    }
	
	public String returnCategory(Set<CategoriasEntity> categoryEntities) {
	
		String idCategoria = "";
		System.out.println("categoryEntities.size();"+categoryEntities.size());
		System.out.println("categoryEntities.iterator().next().getidCategoria();"+categoryEntities.iterator().next().getidCategoria()); 
		for(CategoriasEntity i: categoryEntities) {
			
			idCategoria = i.getNombre();
			System.out.println("i.getidCategoria();"+i.getidCategoria());
		}
		//idCategoria = categoryEntities.size();
		return idCategoria;
    }
	
	public String returnPlatform(Set<PlataformasEntity> platformEntities) {
		
		String idPlataforma = "";
		System.out.println("categoryEntities.size();"+platformEntities.size());
		System.out.println("categoryEntities.iterator().next().getidPlataforma();"+platformEntities.iterator().next().getIdPlataforma()); 
		for(PlataformasEntity i: platformEntities) {
			
			idPlataforma = i.getNombre();
			System.out.println("i.getidCategoria();"+i.getIdPlataforma());
		}
		//idCategoria = categoryEntities.size();
		return idPlataforma;
    }
    
    public void setService(UserService userService) {
        this.userService = userService;
    }
 
    public List<JuegoDTO> getGames() {
        return games;
    }
 
    public List<JuegoDTO> getDroppedGames() {
        return droppedGames;
    }    
 
    public JuegoDTO getSelectedGame() {
        return selectedGame;
    }
 
    public void setSelectedGame(JuegoDTO selectedGame) {
        this.selectedGame = selectedGame;
    }

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public JuegoDTO getManagedJuegoDTO() {
		return managedJuegoDTO;
	}

	public void setManagedJuegoDTO(JuegoDTO managedJuegoDTO) {
		this.managedJuegoDTO = managedJuegoDTO;
	}

	public void setGames(List<JuegoDTO> games) {
		this.games = games;
	}

	public void setDroppedGames(List<JuegoDTO> droppedGames) {
		this.droppedGames = droppedGames;
	}

	public VideojuegosPlataformasDTO getManagedPlataformaDTO() {
		return managedPlataformaDTO;
	}

	public void setManagedPlataformaDTO(VideojuegosPlataformasDTO managedPlataformaDTO) {
		this.managedPlataformaDTO = managedPlataformaDTO;
	}

	public VideojuegosCategoriasDTO getManagedCategoriaDTO() {
		return managedCategoriaDTO;
	}

	public void setManagedCategoriaDTO(VideojuegosCategoriasDTO managedCategoriaDTO) {
		this.managedCategoriaDTO = managedCategoriaDTO;
	}

	public int getIdGameToDelete() {
		return idGameToDelete;
	}

	public void setIdGameToDelete(int idGameToDelete) {
		this.idGameToDelete = idGameToDelete;
	}

	public List<JuegoDTO> getGamesSelected() {
		return gamesSelected;
	}

	public void setGamesSelected(List<JuegoDTO> gamesSelected) {
		this.gamesSelected = gamesSelected;
	}

	public List<UserValueGameDTO> getListUserValueGames() {
		return listUserValueGames;
	}

	public void setListUserValueGames(List<UserValueGameDTO> listUserValueGames) {
		this.listUserValueGames = listUserValueGames;
	}

	public List<UserValueGameDTO> getListUserValueGamesSelected() {
		return listUserValueGamesSelected;
	}

	public void setListUserValueGamesSelected(List<UserValueGameDTO> listUserValueGamesSelected) {
		this.listUserValueGamesSelected = listUserValueGamesSelected;
	}

	public boolean equals(Object obj) {
		return userValueGameDTO.equals(obj);
	}

	public String getUserName() {
		return userValueGameDTO.getUserName();
	}

	public void setUserName(String userName) {
		userValueGameDTO.setUserName(userName);
	}

	public String getGameName() {
		return userValueGameDTO.getGameName();
	}

	public void setGameName(String gameName) {
		userValueGameDTO.setGameName(gameName);
	}

	public int getValoracion() {
		return userValueGameDTO.getValoracion();
	}

	public void setValoracion(int valoracion) {
		userValueGameDTO.setValoracion(valoracion);
	}

	public int getFinalizado() {
		return userValueGameDTO.getFinalizado();
	}

	public int hashCode() {
		return userValueGameDTO.hashCode();
	}

	public void setFinalizado(int finalizado) {
		userValueGameDTO.setFinalizado(finalizado);
	}

	public String getImagen() {
		return userValueGameDTO.getImagen();
	}

	public void setImagen(String imagen) {
		userValueGameDTO.setImagen(imagen);
	}

	public String toString() {
		return userValueGameDTO.toString();
	}

	public UserValueGameDTO getUserValueGameDTO() {
		return userValueGameDTO;
	}

	public void setUserValueGameDTO(UserValueGameDTO userValueGameDTO) {
		this.userValueGameDTO = userValueGameDTO;
	}

	public UserValueGameDTO getUserValueGameDTOToDelete() {
		return userValueGameDTOToDelete;
	}

	public void setUserValueGameDTOToDelete(UserValueGameDTO userValueGameDTOToDelete) {
		this.userValueGameDTOToDelete = userValueGameDTOToDelete;
	}
	
	
    
    
}
