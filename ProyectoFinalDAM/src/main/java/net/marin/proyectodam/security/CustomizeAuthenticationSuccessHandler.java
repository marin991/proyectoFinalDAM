package net.marin.proyectodam.security;


import java.io.IOException;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

//Clase de Spring security se encarga de gestionar el exito o fallo de la autentificón así como asignarnos a que pagina vamos dependiendo de esta.
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy(); //Variable realcionada con las redirecciones necesrio inicializar

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException {
	
		
		handle(request, response, authentication);
	
		HttpSession session = request.getSession(false);
		
		//Tiempo de sesión activa
		if (session != null) {
			session.setMaxInactiveInterval(1000);
		}

		clearAuthenticationAttributes(request);

	
	}
	public void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException{
		
		String targetUrl = determineTargetUrl(authentication);
		//Este if comprueba si la respuesta ya ha sido guardada no es necesario pero biene bien a modo debug
		if (response.isCommitted()) {
              System.out.println("Response has already been committed. Unable to redirect to " + targetUrl);
        }
		else {
			System.out.println("No commited");
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}
	
	//Metodo que devuelve un string con la url a la que iremos tras hacer login dependendo de los permisos de este
	protected String determineTargetUrl(Authentication authentication) {
		
		boolean isUser = false;
		boolean isAdmin = false;
		boolean isBloqueado = false;
		
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {
			
			if(grantedAuthority.getAuthority().equals("Bloqueado")){
				isBloqueado = true;
			}
			
			if(grantedAuthority.getAuthority().equals("ROLE_USER")) {
				isUser = true;
				
			}
			
			else if(grantedAuthority.getAuthority().equals("ROLE_ADMIN")){
				isAdmin = true;
			}
			
			
		}
		
		if(!isBloqueado) {
			if(isAdmin) {
				return("/pages/admin/adminhome.xhtml");
			}
			else if(isUser) {
				return("/pages/user/userhome.xhtml");
			}
			else {
				throw new IllegalStateException();//Devuelve error ilegal a  la consola en la web se producuce fallo pero no indica cual es
			}
		} else {
			return("/accesobloqueado.xhtml");//Página a la que van los usuario con status Bloqueado			
		}
	}
	
	//Limpia los atritbutos de la sesión
	protected void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}
	
	public RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}
	
	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}
	
	
}
