package net.marin.proyectodam.web.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.primefaces.event.DragDropEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    }
    public void onGamesDrop(DragDropEvent ddEvent) {
        JuegoDTO game = ((JuegoDTO) ddEvent.getData());
  
        droppedGames.add(game);
        games.remove(game);
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
    
    
}
