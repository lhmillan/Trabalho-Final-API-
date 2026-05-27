package br.com.serratec.trabfinal_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.trabfinal_api.model.OS;


public interface OSRepository extends JpaRepository<OS, Long> {
}
