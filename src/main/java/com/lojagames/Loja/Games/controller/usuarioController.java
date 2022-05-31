package com.lojagames.Loja.Games.controller;
import java.util.List;
import java.util.Optional;


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

import com.lojagames.Loja.Games.model.usuarioLogin;
import com.lojagames.Loja.Games.model.usuarioModel;
import com.lojagames.Loja.Games.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class usuarioController {
	
	@Autowired
	private UsuarioService UsuarioService;
	

	@Autowired
	private com.lojagames.Loja.Games.repository.usuarioRepository usuarioRepository;

	
	@GetMapping("/all")
	public ResponseEntity<List<usuarioModel>> getAll() {
		return ResponseEntity.ok(usuarioRepository.findAll());

	}

	@PostMapping("/logar")
	public ResponseEntity<usuarioLogin> login(@RequestBody Optional<usuarioLogin> user) {
		return UsuarioService.autenticaUsuario(user).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<usuarioModel> postUsuario( @RequestBody usuarioModel usuario) {

		return UsuarioService.cadastraUsuario(usuario)
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<usuarioModel> putUsuarioAtualizar(@RequestBody usuarioModel usuario) {

		return UsuarioService.atualizarUsuario(usuario)
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
	
	@PutMapping
	public ResponseEntity <usuarioModel> put( @RequestBody usuarioModel usuario) {
		return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.save(usuario));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return usuarioRepository.findById(id)
				.map(resposta -> {
				usuarioRepository.deleteById(id);
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());
	}
}


