package br.com.serratec.trabfinal_api.controllers;

import br.com.serratec.trabfinal_api.dto.request.OrdemServicoRequestDTO;
import br.com.serratec.trabfinal_api.dto.response.OrdemServicoResponseDTO;
import br.com.serratec.trabfinal_api.service.OSService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/os")
public class OrdemServicoController {

    private final OSService service;

    public OrdemServicoController(OSService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<OrdemServicoResponseDTO>> listarOS(Pageable pageable) {

        return ResponseEntity.ok(service.listarOS(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemServicoResponseDTO> buscarPorId(@PathVariable Long id) {

        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<OrdemServicoResponseDTO> cadastrarOS(
            @Valid @RequestBody OrdemServicoRequestDTO dto) {

        OrdemServicoResponseDTO os = service.cadastrarOS(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(os);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdemServicoResponseDTO> atualizarOS(
            @PathVariable Long id,
            @Valid @RequestBody OrdemServicoRequestDTO dto) {

        return ResponseEntity.ok(service.atualizarOS(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removerOS(@PathVariable Long id) {

        service.removerOS(id);

        return ResponseEntity.ok("OS removida com sucesso!");
    }
}