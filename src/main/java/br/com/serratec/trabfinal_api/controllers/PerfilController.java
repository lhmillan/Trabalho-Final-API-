package br.com.serratec.trabfinal_api.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.trabfinal_api.model.Perfil;
import br.com.serratec.trabfinal_api.service.PerfilService;

@RequestMapping("/perfis")
@RestController
public class PerfilController {
	 
	@Autowired
	private PerfilService service;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Perfil inserir(@RequestBody Perfil perfil) {
		return service.inserir(perfil);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Perfil> buscar(@PathVariable Long id){
		Optional<Perfil> perfil = service.buscar(id);
		if(perfil.isPresent()) {
			return ResponseEntity.ok(perfil.get());
		}
		return ResponseEntity.notFound().build();
	}
	
}
