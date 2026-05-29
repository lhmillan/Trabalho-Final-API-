package br.com.serratec.trabfinal_api.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import br.com.serratec.trabfinal_api.controllers.VeiculoController;
import br.com.serratec.trabfinal_api.model.Foto;
import br.com.serratec.trabfinal_api.model.Veiculo;
import br.com.serratec.trabfinal_api.repository.FotoRepository;

@Service
public class FotoService {
	
	@Autowired
	private FotoRepository repository;
	
	public Foto inserir(Veiculo veiculo, MultipartFile file) throws IOException {
		Foto foto = new Foto(null,file.getBytes(),file.getContentType(),file.getName(),veiculo);
		return repository.save(foto);
	}
	
	public Foto buscar (Long id) {
		Veiculo veiculo = new Veiculo();
		veiculo.setId(id);
		return repository.findByVeiculo(veiculo).get();
	}
	
	//public Foto atualizar(Veiculo veiculo, MultipartFile file) throws IOException {
	//    
	//    Optional<Foto> foto = repository.findByVeiculo(veiculo);
	//    
	//    if (foto.isPresent()) {
	//        Foto fotoExistente = foto.get();
	//        
	//        fotoExistente.setDados(file.getBytes());
	//        fotoExistente.setTipo(file.getContentType());
	//        fotoExistente.setNome(file.getOriginalFilename()); 
	//        
	//        return repository.save(fotoExistente);
	//    }
	//    
	//   
	//    return inserir(veiculo, file);
	//}
	
	
}
