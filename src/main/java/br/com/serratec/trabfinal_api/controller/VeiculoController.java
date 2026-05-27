package br.com.serratec.trabfinal_api.controller;

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

import br.com.serratec.trabfinal_api.dto.VeiculoDTO;
import br.com.serratec.trabfinal_api.service.VeiculoService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoService service;

    public VeiculoController(VeiculoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<VeiculoDTO> inserir(@Valid @RequestBody VeiculoDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.inserir(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeiculoDTO> editar(@PathVariable Long id, @Valid @RequestBody VeiculoDTO dto) {
        return ResponseEntity.ok(service.editar(id, dto));
    }

    @GetMapping
    public Page<VeiculoDTO> listar(Pageable pageable) {
        return service.listar(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
