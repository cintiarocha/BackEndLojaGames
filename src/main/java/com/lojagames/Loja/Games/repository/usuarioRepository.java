package com.lojagames.Loja.Games.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lojagames.Loja.Games.model.usuarioModel;
@Repository
public interface usuarioRepository extends JpaRepository <usuarioModel, Long> {
	
	// Quando temos mais de uma possível resposta, usamos o Optional.
	public Optional <usuarioModel> findByUsuario(String usuario); 

}
