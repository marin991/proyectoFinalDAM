package net.marin.proyectodam.web.managedbean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
//Clase de la que extiende nuestro managed bean necesario por Spring con primefaces
public abstract class GenericManagedBean implements Serializable {

	public GenericManagedBean() {
		super();
	}
	
	public void showInfoMessage(String title, String details) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, title, details));
    }
 
    public void showWarnMessage(String title, String details) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, title, details));
    }
 
    public void showErrorMessage(String title, String details) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, title, details));
    }
 
    public void showFatalMessage(String title, String details) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, title, details));
    }

}
