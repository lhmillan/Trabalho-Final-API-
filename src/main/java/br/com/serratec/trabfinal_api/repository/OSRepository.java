package br.com.serratec.trabfinal_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.serratec.trabfinal_api.model.OS;


public interface OSRepository extends JpaRepository<OS, Long> {
    Page<OS> findAll(Pageable pageable);
}
