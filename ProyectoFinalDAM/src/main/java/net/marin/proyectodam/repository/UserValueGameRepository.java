package net.marin.proyectodam.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.marin.proyectodam.repository.entity.UsuarioValoraEntity;

@Repository
public interface UserValueGameRepository extends JpaRepository<UsuarioValoraEntity, Serializable>{
	
	public List<UsuarioValoraEntity> findByuserName(String userName);
	
	public boolean existsBygameName(String gameName);
	public boolean existsByUserName(String userName);
	
//	public boolean existsByTwoIds(String userName,String gameName);

}
