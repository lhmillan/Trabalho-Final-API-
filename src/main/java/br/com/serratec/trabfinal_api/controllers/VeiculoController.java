package br.com.serratec.trabfinal_api.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.serratec.trabfinal_api.dto.request.VeiculoRequestDTO;
import br.com.serratec.trabfinal_api.dto.response.VeiculoResponseDTO;
import br.com.serratec.trabfinal_api.model.Foto;
import br.com.serratec.trabfinal_api.service.FotoService;
import br.com.serratec.trabfinal_api.service.VeiculoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService service;
    
    @Autowired
    private FotoService fotoService; 

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
    
    @GetMapping("/{id}/foto")
	public ResponseEntity<byte[]> buscarPorFoto(@PathVariable Long id) {
		
		Foto foto = fotoService.buscar(id);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", foto.getTipo());
		headers.add("Content-lenght", String.valueOf(foto.getDados().length));
		return new ResponseEntity<>(foto.getDados(),headers,HttpStatus.OK);
	
	}
	
	@PostMapping(consumes = {org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE})
	public VeiculoResponseDTO inserir(@RequestPart("veiculo") VeiculoRequestDTO dto, @RequestPart("file") MultipartFile file) throws IOException {
		return service.inserir(dto, file);
	}
	
	//@PutMapping(value = "/{id}/foto", consumes = {org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE})
	//public ResponseEntity<VeiculoResponseDTO> atualizar(
	//        @PathVariable Long id, 
	//        @RequestPart("veiculo") @Valid VeiculoRequestDTO dto, 
	//        @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {
	//    
	//    VeiculoResponseDTO response = fotoService.atualizar(dto, file);
	//    return ResponseEntity.ok(response);
	//}
	
	
    
}
