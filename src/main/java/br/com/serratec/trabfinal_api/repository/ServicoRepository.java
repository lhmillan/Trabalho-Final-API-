package br.com.serratec.trabfinal_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.trabfinal_api.model.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long> {

}
