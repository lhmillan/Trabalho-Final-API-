package br.com.serratec.trabfinal_api.controllers;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.trabfinal_api.model.Pecas;
import br.com.serratec.trabfinal_api.service.PecasService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pecas")
public class PecasController {

    @Autowired
    private PecasService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pecas inserir(@Valid @RequestBody Pecas pecas) {
        return service.inserir(pecas);
    }

    @GetMapping
    public ResponseEntity<Page<Pecas>> listar(Pageable pageable) {
        return ResponseEntity.ok(service.listar(pageable));
    }

    @GetMapping("/{id}")
    public Optional<Pecas> buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterar(@PathVariable Long id, @Valid @RequestBody Pecas pecas) {
        service.alterar(pecas, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apagar(@PathVariable Long id) {
        service.apagar(id);
    }
}