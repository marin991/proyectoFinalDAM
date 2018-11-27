package net.marin.proyectodam.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.marin.proyectodam.repository.entity.VideojuegosPlataformasEntity;

@Repository
public interface JuegosPlatRepository extends JpaRepository<VideojuegosPlataformasEntity, Serializable>{ 
		
}