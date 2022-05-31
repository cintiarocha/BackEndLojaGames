package com.lojagames.Loja.Games.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lojagames.Loja.Games.model.produtoModel;


@Repository
public interface produtoRepository extends JpaRepository<produtoModel, Long>{
	public List <produtoModel> findAllByNomeContainingIgnoreCase(String nome);
}
