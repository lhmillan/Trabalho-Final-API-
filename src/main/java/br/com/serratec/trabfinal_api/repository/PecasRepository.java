package br.com.serratec.trabfinal_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.trabfinal_api.model.Pecas;

public interface PecasRepository extends JpaRepository<Pecas, Long> {
}
