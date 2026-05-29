package br.com.serratec.trabfinal_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.trabfinal_api.dto.request.UsuarioRequestDTO;
import br.com.serratec.trabfinal_api.dto.response.UsuarioResponseDTO;
import br.com.serratec.trabfinal_api.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    // @PostMapping
    // @ResponseStatus(HttpStatus.CREATED)
    // public Usuario inserir(@RequestBody Usuario usuario) {
    //     return service.inserir(usuario);
    // }


    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> inserir(@RequestBody UsuarioRequestDTO dto){
        return ResponseEntity.created(null).body(service.inserir(dto));
    }
}