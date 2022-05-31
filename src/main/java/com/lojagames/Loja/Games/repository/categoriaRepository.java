package com.lojagames.Loja.Games.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lojagames.Loja.Games.model.categoriaModel;

@Repository
 
	public interface categoriaRepository extends JpaRepository<categoriaModel, Long>{
		public List <categoriaModel> findAllByDescricaoContainingIgnoreCase(String descricao);
	
	}


	
