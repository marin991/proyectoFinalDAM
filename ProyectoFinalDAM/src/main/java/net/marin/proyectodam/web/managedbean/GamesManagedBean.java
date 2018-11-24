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
    
    private List<AppUserDTO> games;
     
    private List<AppUserDTO> droppedGames;
     
    private AppUserDTO selectedGame;
     
    @PostConstruct
    public void init() {
    	System.out.println("\n gamessss");
        games = userService.findAll();
        droppedGames = new ArrayList<AppUserDTO>();
    }
     
    public void onGamesDrop(DragDropEvent ddEvent) {
        AppUserDTO game = ((AppUserDTO) ddEvent.getData());
  
        droppedGames.add(game);
        games.remove(game);
    }
     
    public void setService(UserService userService) {
        this.userService = userService;
    }
 
    public List<AppUserDTO> getGames() {
        return games;
    }
 
    public List<AppUserDTO> getDroppedGames() {
        return droppedGames;
    }    
 
    public AppUserDTO getSelectedGame() {
        return selectedGame;
    }
 
    public void setSelectedGame(AppUserDTO selectedGame) {
        this.selectedGame = selectedGame;
    }
}
