package net.marin.proyectodam.repository.entity;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

//Clase entidad, mapea tablas SQL
@Entity
@Table(name = "app_user")//Nombre de la tabla de la BBDD a la que asignaremos esta clase
public class AppUserEntity implements Serializable{
	
    	
	private static final long serialVersionUID = 1L;//Se genera al implementar serielizable
	
	String userName;
	String encryptedPassword;
	String nombre;
	String apellidos;
	String status;
	int firstVisit ;
	private Set<AppRoleEntity> appRoleEntities ;//= new HashSet<UserRoleEntity>();
	
	//CONSTRUCTORES
	
	public AppUserEntity() {
		super();
	}
	public AppUserEntity(String userName) {
		this.userName= userName;
		appRoleEntities = new HashSet<AppRoleEntity>();//new HashSet<UserRoleEntity>();
	}
	
	/*
	 * Las anotaciones de hibernate (mapeo BBDD)pueden estar encima de los getters
	 * o de la declaración de las variables pero se tiene que elejir una opcion
	 * si se declaran sobre variables han de estar igual en todo el proyecto y viceversa.
	 * */
	@Id//Indica que columna es una PK de SQL
	@Column(name = "USER_NAME")//Asignamos la variabe del getter a esta columna de la tabla
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name = "ENCRYPTED_PASSWORD")
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	
	
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	
	@Column(name = "NOMBRE")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Column(name = "APELLIDOS")
	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "FIRST_VISIT")
	public int getFirstVisit() {
		return firstVisit;
	}

	public void setFirstVisit(int firstVisit) {
		this.firstVisit = firstVisit;
	}
	
	public void addUserRoleEntities(AppRoleEntity appRoleEntity) {
		this.appRoleEntities.add(appRoleEntity);//add(userRoleEntity);
	}
	
	//Anotacion necesaria par que hibernate relacion N:N de dos tablas relacinando sus entity 
	@ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            },mappedBy = "appUserEntities")//Entidad debil de la relación N:N 
	public Set<AppRoleEntity> getAppRoleEntities() {
		return appRoleEntities;
	}

	public void setAppRoleEntities(Set<AppRoleEntity> appRoleEntities) {
		this.appRoleEntities = appRoleEntities;
	}
	
	/*
	 * Ultimo cambio sirve para borrar los roles asignados al usuario 
	 * antes de eliminar al usuario parecido a aplicar un metodo cascada
	 */
	@PreRemove
	private void removeRolesFromUsers() {
	    for (AppRoleEntity u : appRoleEntities) { 
	        u.appUserEntities.remove(this);
	    }
	}
	
}
