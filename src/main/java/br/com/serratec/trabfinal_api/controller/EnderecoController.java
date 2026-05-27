package br.com.serratec.trabfinal_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.trabfinal_api.dto.EnderecoResponseDTO;
import br.com.serratec.trabfinal_api.service.EnderecoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping
public class EnderecoController {
    @Autowired
    private EnderecoService service;

    @GetMapping("{cep}")
    public ResponseEntity<EnderecoResponseDTO> buscar(@RequestParam String cep) {
        return ResponseEntity.ok(service.buscarCep(cep));    
    }
    
}
