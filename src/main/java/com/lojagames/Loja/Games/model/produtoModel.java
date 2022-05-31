package com.lojagames.Loja.Games.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;


@Entity
@Table (name = "tb_produto")
public class produtoModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long cod;
	
	
	public String nome;
	
	
	public String plataforma;
	
	@NotNull
	
	public int qtd;
	
	@NotNull
	
	public double preco;
	
	@NotNull
	@ManyToOne
	@JsonIgnoreProperties("produto")
	private categoriaModel categoria;

	@ManyToOne
	@JsonIgnoreProperties("produto")
	private usuarioModel usuario;
	
	public Long getCod() {
		return cod;
	}

	public void setCod(Long cod) {
		this.cod = cod;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public categoriaModel getCategoria() {
		return categoria;
	}

	public void setCategoria(categoriaModel categoria) {
		this.categoria = categoria;
	}
	
	public usuarioModel getUsuario() {
		return usuario;
	}

	public void setUsuario(usuarioModel usuario) {
		this.usuario = usuario;
	}

}
