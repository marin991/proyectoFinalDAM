package net.marin.proyectodam.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.marin.proyectodam.repository.entity.VideoJuegosCategoriasEntity;


@Repository
public interface JuegoCatRepository extends JpaRepository<VideoJuegosCategoriasEntity, Serializable>{ 
		
}
