package net.marin.proyectodam.web.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.primefaces.event.DragDropEvent;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.marin.proyectodam.repository.entity.AppRoleEntity;
import net.marin.proyectodam.repository.entity.CategoriasEntity;
import net.marin.proyectodam.service.UserService;
import net.marin.proyectodam.utils.dto.AppUserDTO;
import net.marin.proyectodam.utils.dto.JuegoDTO;
import net.marin.proyectodam.utils.dto.PlataformasDTO;
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
     
    private JuegoDTO selectedGame;
     
    @PostConstruct
    public void init() {
    	managedJuegoDTO = new JuegoDTO();
    	managedPlataformaDTO = new VideojuegosPlataformasDTO();
    	managedCategoriaDTO = new VideojuegosCategoriasDTO();
    	System.out.println("\n gamessss");
        games = userService.findAllGames();
        droppedGames = new ArrayList<JuegoDTO>();
        gamesSelected = new ArrayList<JuegoDTO>();
    }
    
    public void findAll() {
		
		showInfoMessage("Exito", "Mostrando lista Usuarios");
		games = userService.findAllGames();
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
			userService.updateGameCategory(juegoToUpdate);
			
		} catch (Exception e) {
			showErrorMessage("Error", e.getMessage());
		}
    }
    
    public void reset() {
    	managedJuegoDTO = new JuegoDTO();
    	games.clear();
		// tambien serviria poner listUserDTO = new ArrayList<>();
	}
	
	public int returnCategory(Set<CategoriasEntity> categoryEntities) {
	
		int idCategoria = 8;
		System.out.println("categoryEntities.size();"+categoryEntities.size());
		System.out.println("categoryEntities.iterator().next().getidCategoria();"+categoryEntities.iterator().next().getidCategoria()); 
		for(CategoriasEntity i: categoryEntities) {
			
			idCategoria = i.getidCategoria();
			System.out.println("i.getidCategoria();"+i.getidCategoria());
		}
		//idCategoria = categoryEntities.size();
		return idCategoria;
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
    
    
}
