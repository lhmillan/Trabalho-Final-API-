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

import br.com.serratec.trabfinal_api.dto.request.ClienteRequestDTO;
import br.com.serratec.trabfinal_api.dto.response.ClienteResponseDTO;
import br.com.serratec.trabfinal_api.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponseDTO cadastrarCliente(@Valid @RequestBody ClienteRequestDTO dto) {
        return service.cadastrarCliente(dto);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listarClientes() {
        List<ClienteResponseDTO> lista = service.listarClientes();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscarPorId(@PathVariable Long id) {
        ClienteResponseDTO dto = service.buscarPorId(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public void atualizarCliente(@PathVariable Long id, @Valid @RequestBody ClienteRequestDTO dto) {
        service.atualizarCliente(id, dto);
    }

    @DeleteMapping("/{id}")
    public void removerCliente(@PathVariable Long id) {
        service.removerCliente(id);
    }

}
