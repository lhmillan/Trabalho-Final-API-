package br.com.serratec.trabfinal_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.trabfinal_api.model.Foto;
import br.com.serratec.trabfinal_api.model.Veiculo;

public interface FotoRepository extends JpaRepository<Foto, Long>{
	
	Optional<Foto> findByFuncionario(Veiculo veiculo);
	
}
