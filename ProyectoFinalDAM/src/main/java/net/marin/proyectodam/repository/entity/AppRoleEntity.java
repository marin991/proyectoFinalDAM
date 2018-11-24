package net.marin.proyectodam.repository.entity;



import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

//Clase entidad, mapea tablas SQL
@Entity
@Table(name = "app_role")//Nombre de la tabla de la BBDD a la que asignaremos esta clase
public class AppRoleEntity implements Serializable{

	private static final long serialVersionUID = 1L;//Se genera al implementar serielizable
	
	int role_id;
	String role_name;
	Set<AppUserEntity> appUserEntities;
	
	//CONSTRUCTORES
	
	public AppRoleEntity() {
		super();
	}
	
	public AppRoleEntity(int role_id) {
		 
		this.role_id = role_id;
	    appUserEntities = new HashSet<AppUserEntity>();
	}
	
	/*
	 * Las anotaciones de hibernate (mapeo BBDD)pueden estar encima de los getters
	 * o de la declaración de las variables pero se tiene que elejir una opcion
	 * si se declaran sobre variables han de estar igual en todo el proyecto y viceversa.
	 * */
	@Id//Indica que columna es una PK de SQL
	@Column(name = "ROLE_ID") //Asignamos la variabe del getter a esta columna de la tabla
	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	
	@Column(name = "ROLE_NAME")//Asignamos la variabe del getter a esta columna de la tabla
	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	//Anotacion necesaria par que hibernate relacion N:N de dos tablas relacinando sus entity (se trata de la entidad fuerte porque no tiene mappedBy)
	@ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                CascadeType.PERSIST
            })
	 @JoinTable(name = "user_role",//Nombre tabla N:N
     joinColumns = { @JoinColumn(name = "ROLE_ID") },//Columna de la tabla pertenecieente a esta entity
     inverseJoinColumns = { @JoinColumn(name = "USER_NAME") })//Columna de la otra entidad presente en la tabla SQL 
	public Set<AppUserEntity> getAppUserEntities() {
		return appUserEntities;
	}
	
	public void setAppUserEntities(Set<AppUserEntity> appUserEntities) {
		this.appUserEntities = appUserEntities;
	}
	
}
