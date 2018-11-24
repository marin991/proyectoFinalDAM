package net.marin.proyectodam.web.managedbean;
/*
import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.marin.proyectodam.service.UserService;
import net.marin.proyectodam.utils.dto.AppUserDTO;
NO SE USA PARA NADA SUS METODOS ESTAN IMPLEMENTADOS EN AppManagedBean
@ManagedBean(name="loginManagedBean")
@Component
public class LoginManagedBean implements Serializable {
	
	/*
	 * Inyectamos la dependencia.
	 
	@Autowired
	UserService loginService;
	
	
	
	private static final long serialVersionUID = -2152389656664659476L;
	
	private String das;
	private String password;
	private boolean logeado = false;
	
	private AppUserDTO loginAppUserDTO;
	
	public LoginManagedBean() {
		super();
	}
	
	@PostConstruct
	public void onInit() {
		loginAppUserDTO = new AppUserDTO();

	}
	
	public boolean estaLogeado() {
		return logeado;
	}
	

	public void login() {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage msg = null;
		System.out.println("Este es el das: "+das+" ");
		
		AppUserDTO appUserDTOToLog = loginService.login(loginAppUserDTO.getDas());

		//if (loginAppUserDTO.getDas() != null && loginAppUserDTO.getDas().equals("admin") && loginAppUserDTO.getEncryptedPassword() != null && loginAppUserDTO.getEncryptedPassword().equals("admin")) {
		if(appUserDTOToLog !=null && appUserDTOToLog.getDas().equals(loginAppUserDTO.getDas()) && appUserDTOToLog.getEncryptedPassword().equals(loginAppUserDTO.getEncryptedPassword())) {
						if(appUserDTOToLog.getStatus() == 1){
				
				logeado = false;
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Usuario bloqueado");
			}
			else {
				logeado = true;
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@", das);

			}
		} else {
			logeado = false;
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Credenciales no válidas");
		}

		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		context.addCallbackParam("estaLogeado", logeado);
		
		//Aqui te diriges a las páginas 		

		if (logeado) {
			context.addCallbackParam("view", "createUser.xhtml");
		System.out.println("jojojojjojojojojjo");}
		
	}
	public boolean findOne(String das) {
		//showInfoMessage("Pa", "Mostrando lista Usuarios");
		return loginService.findOne(das);
	}
	
	public void logout() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();
		logeado = false;
	}


	public String getDas() {
		return das;
	}

	

	public void setDas(String das) {
		this.das = das;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isLogeado() {
		return logeado;
	}


	public void setLogeado(boolean logeado) {
		this.logeado = logeado;
	}

	public AppUserDTO getLoginAppUserDTO() {
		return loginAppUserDTO;
	}

	public void setLoginAppUserDTO(AppUserDTO loginAppUserDTO) {
		this.loginAppUserDTO = loginAppUserDTO;
	}
}*/