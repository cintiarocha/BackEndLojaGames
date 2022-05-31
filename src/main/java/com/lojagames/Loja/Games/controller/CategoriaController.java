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

import com.lojagames.Loja.Games.model.categoriaModel;
import com.lojagames.Loja.Games.repository.categoriaRepository;

	@RestController
	@RequestMapping("/categoria")
	@CrossOrigin("*")
	public class CategoriaController {
		
		@Autowired
		private categoriaRepository repository;
		
		@GetMapping
		public ResponseEntity<List<categoriaModel>> getAll() {
			return ResponseEntity.ok(repository.findAll());
		}
		
		@GetMapping("/{id}")
		public ResponseEntity <categoriaModel> getById(@PathVariable Long id) {
			return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
					                      .orElse(ResponseEntity.notFound().build());
		}
		
		@GetMapping("/descricao/{descricao}")
		public ResponseEntity <List <categoriaModel>> getByDescricao(@PathVariable String descricao) {
			return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(descricao));
		}
		
		@PostMapping
		public ResponseEntity <categoriaModel> post( @RequestBody categoriaModel categoria) {
			return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(categoria));
		}
		
		@PutMapping
		public ResponseEntity <categoriaModel> put( @RequestBody categoriaModel categoria) {
			return ResponseEntity.status(HttpStatus.OK).body(repository.save(categoria));
		}
		
		@DeleteMapping("/{id}")
		public ResponseEntity<?> delete(@PathVariable Long id) {
			return repository.findById(id)
					.map(resposta -> {
						repository.deleteById(id);
						return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
					})
					.orElse(ResponseEntity.notFound().build());
		}

}
