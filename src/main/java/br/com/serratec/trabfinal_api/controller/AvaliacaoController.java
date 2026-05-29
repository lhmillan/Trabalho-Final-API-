package br.com.serratec.trabfinal_api.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    private final br.com.serratec.trabfinal_api.service.AvaliacaoService service;

    public AvaliacaoController(br.com.serratec.trabfinal_api.service.AvaliacaoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<br.com.serratec.trabfinal_api.dto.response.AvaliacaoResponseDTO> criar(@RequestBody br.com.serratec.trabfinal_api.dto.request.AvaliacaoRequestDTO dto) {
        return ResponseEntity.ok(service.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<br.com.serratec.trabfinal_api.dto.response.AvaliacaoResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<br.com.serratec.trabfinal_api.dto.response.AvaliacaoResponseDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}