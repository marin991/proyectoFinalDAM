package net.marin.proyectodam.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import net.marin.proyectodam.service.impl.SecurityServiceImpl;


//Clase que maneja los roles de securiy simplemente sen encarga de los acceso trabaja junto con la otra clase CustomiceAthenticationHandler

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private SecurityServiceImpl securityServiceImpl;
	
	@Override
	protected void configure(final HttpSecurity http) throws Exception{
		
		http.csrf().disable().
			authorizeRequests()
			.antMatchers("/javax.faces.resource/*","/index.xhtml","/pages/user/accesodenegado.xhtml","/accesobloqueado.xhtml","/accesoerror.xhtml", 
					"/css/**", "/images/**", "/js/**", "/layout/**", "/WEB-INF/**" ).permitAll()
			/*
			 * Páginas con acceso para todo los roles y usuario no 
			 * registrados hay que añadir ademas las carpetas de css y demas recurso acceder como la libreeia de primafaces
			*/
			.antMatchers("/pages/admin/**").hasAuthority("ROLE_ADMIN").anyRequest()//Indicamos las páginas con solo para los administradores
			.authenticated()
			.and()
			.formLogin()
			.loginPage("/index.xhtml").loginProcessingUrl("/login")//Pagina donde esta el login y action del form de este
			.successHandler(customizeAuthenticationSuccessHandler())	//Clase para redireccionar segun rol
			.failureUrl("/accesoerror.xhtml")	//Página a la que vas si fallas al meter los datos del usuario
			.usernameParameter("userName").passwordParameter("password")//Name de los inputs del form de login
			.and()
			.logout()
			.logoutUrl("/logout")	//action del logout
			.logoutSuccessUrl("/index.xhtml")	//Redireccion al hacer logout
			
			.deleteCookies("JSESSIONID")	//Se eliminan las cookies de la sesion
			.invalidateHttpSession(false)
			.and()
			.exceptionHandling().accessDeniedPage("/pages/user/accesodenegado.xhtml")	//Si el usuario no tiene permisos se redirige aqui
			.and()
			.sessionManagement()
			.invalidSessionUrl("/index.xhtml")
			.and()
			.authenticationProvider(authProvider());	//Necesario para la encriptación de la contraseña
		
		
	}
	
	
	//Autentificacion contraseña encripted password
	@Bean
	public DaoAuthenticationProvider authProvider() {
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(securityServiceImpl);
	    authProvider.setPasswordEncoder(encoder());
	    return authProvider;
	}
	//Generado por security
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(securityServiceImpl).passwordEncoder(encoder());
	}
	//Generado por security
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	//Bean de la clase para redirecciones
	@Bean
	public AuthenticationSuccessHandler customizeAuthenticationSuccessHandler() {
		return new CustomizeAuthenticationSuccessHandler();
	}
	//Be1an de la clase para encriptar la contrasena
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}
