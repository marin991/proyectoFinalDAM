package net.marin.proyectodam.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import net.marin.proyectodam.repository.AppUserRepository;
import net.marin.proyectodam.repository.entity.AppRoleEntity;
import net.marin.proyectodam.utils.converter.Converter;
import net.marin.proyectodam.utils.dto.AppUserDTO;



//Clase del security que se encarga de mostrar los asignar roles y staus de los usuarios logados así como su estatus a a las athorities que usa security
@Service("securityServiceImpl")
public class SecurityServiceImpl implements UserDetailsService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private BCryptPasswordEncoder encoder; //Encripta pasword
	@ManagedProperty("#{userService}")
	UserServiceImpl userService;
	@Autowired
	AppUserRepository appUserRepository;

	AppRoleEntity appRoleEntity = new AppRoleEntity();
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			
		AppUserDTO usu = Converter.appUserEntityToUserDTO(appUserRepository.findById(username).get());

		// Authorities a partir de roles
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		//Por cada AppRoleEntity en usu.getAppRoleEntities()
		for(AppRoleEntity i: usu.getAppRoleEntities()) {
			
			appRoleEntity = i;
			//Si el rol es admin se para el bucle
			if(i.getRole_name().equals("ROLE_ADMIN")) {
				break;
			}
		}
		
		//Guarda los roles del usuario en las authotities que utiliza security
		GrantedAuthority roles = new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return appRoleEntity.getRole_name();
			}
		};
		
		//Hace lo mismo que el metodo anterior pero con elestatus asignando la autoridad de bloqueado o permitido 
		GrantedAuthority statusPermitido = new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return usu.getStatus();
			}
		};
		
		//Añado los roles del usuario a la lista de tipo Authority
		authorities.add(roles);
		
		//anadir una nueva autoridad para status 0 = permitido
		authorities.add(statusPermitido);

		//Paso los datos del usuario a userDetails para que sean validados contra la bbdd
		User userDetails = new User(usu.getUserName(),usu.getEncryptedPassword(), authorities);
		return userDetails;

	}
	
	public UserDetails loadUserByPassword(UserDetails password) {
		
		return password;
		
	}

}
