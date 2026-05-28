package br.com.serratec.trabfinal_api.controllers;

import java.util.List;

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

import br.com.serratec.trabfinal_api.dto.request.ServicoRequestDTO;
import br.com.serratec.trabfinal_api.dto.response.ServicoResponseDTO;
import br.com.serratec.trabfinal_api.service.FotoService;
import br.com.serratec.trabfinal_api.service.ServicoService;
import br.com.serratec.trabfinal_api.service.ServicoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/servicos")
public class ServicoController {
 @Autowired
    private ServicoService service;
    
    @Autowired
    private FotoService fotoService; 

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoResponseDTO cadastrarServico(@Valid @RequestBody ServicoRequestDTO dto) {
        return service.cadastrarServico(dto);
    }

    @GetMapping
    public ResponseEntity<List<ServicoResponseDTO>> listarServicos() {
        List<ServicoResponseDTO> lista = service.listarServicos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoResponseDTO> buscarPorId(@PathVariable Long id) {
        ServicoResponseDTO dto = service.buscarPorId(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public void atualizarServico(@PathVariable Long id, @Valid @RequestBody ServicoRequestDTO dto) {
        service.atualizarServico(id, dto);
    }

    @DeleteMapping("/{id}")
    public void removerServico(@PathVariable Long id) {
        service.removerServico(id);
    }
}