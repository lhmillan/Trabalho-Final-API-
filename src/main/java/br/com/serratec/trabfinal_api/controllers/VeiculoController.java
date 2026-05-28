package br.com.serratec.trabfinal_api.controllers;

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

import br.com.serratec.trabfinal_api.dto.request.VeiculoRequestDTO;
import br.com.serratec.trabfinal_api.dto.response.VeiculoResponseDTO;
import br.com.serratec.trabfinal_api.model.Veiculo;
import br.com.serratec.trabfinal_api.service.VeiculoService;
import br.com.serratec.trabfinal_api.service.VeiculoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VeiculoResponseDTO cadastrarVeiculo(@Valid @RequestBody VeiculoRequestDTO dto) {
        return service.cadastrarVeiculo(dto);
    }

    @GetMapping
    public ResponseEntity<List<VeiculoResponseDTO>> listarVeiculos() {
        List<VeiculoResponseDTO> lista = service.listarVeiculos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoResponseDTO> buscarPorId(@PathVariable Long id) {
        VeiculoResponseDTO dto = service.buscarPorId(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public void atualizarVeiculo(@PathVariable Long id, @Valid @RequestBody VeiculoRequestDTO dto) {
        service.atualizarVeiculo(id, dto);
    }

    @DeleteMapping("/{id}")
    public void removerVeiculo(@PathVariable Long id) {
        service.removerVeiculo(id);
    }
    
}
