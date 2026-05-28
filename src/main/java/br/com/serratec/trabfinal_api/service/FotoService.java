package br.com.serratec.trabfinal_api.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	
}
