package com.lojagames.Loja.Games.controller;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lojagames.Loja.Games.model.produtoModel;
import com.lojagames.Loja.Games.repository.produtoRepository;

@RestController
@RequestMapping("/produto")
@CrossOrigin("*")
public class produtoController {

	@Autowired
	private produtoRepository repository;
	
	@GetMapping
	public ResponseEntity<List<produtoModel>> getAll() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{cod}")
	public ResponseEntity <produtoModel> getByCod(@PathVariable Long cod) {
		return repository.findById(cod).map(resp -> ResponseEntity.ok(resp))
				                      .orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity <List <produtoModel>> getByNome(@PathVariable String nome) {
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@PostMapping
	public ResponseEntity <produtoModel> post( @RequestBody produtoModel produto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto));
	}
	
	@PutMapping
	public ResponseEntity <produtoModel> put( @RequestBody produtoModel produto) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(produto));
	}
	
	@DeleteMapping("/{cod}")
	public ResponseEntity<?> delete(@PathVariable Long cod) {
		return repository.findById(cod)
				.map(resposta -> {
					repository.deleteById(cod);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());
	}
}

