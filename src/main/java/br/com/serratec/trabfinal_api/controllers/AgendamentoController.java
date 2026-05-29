package br.com.serratec.trabfinal_api.controllers;

import br.com.serratec.trabfinal_api.dto.response.AgendamentoResponseDTO;
import br.com.serratec.trabfinal_api.dto.request.AgendamentoRequestDTO;
import br.com.serratec.trabfinal_api.service.AgendamentoService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    private final AgendamentoService service;

    public AgendamentoController(AgendamentoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AgendamentoResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<AgendamentoResponseDTO> cadastrar(@Valid @RequestBody
                                                            AgendamentoRequestDTO dto) {
        AgendamentoResponseDTO response = service.cadastrar(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendamentoResponseDTO> atualizar(
            @PathVariable Long id, @Valid
            @RequestBody AgendamentoRequestDTO dto) {

        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remover(
            @PathVariable Long id ){

        service.remover(id);

        return ResponseEntity.ok("Agendamento removido com sucesso!");
    }


}
