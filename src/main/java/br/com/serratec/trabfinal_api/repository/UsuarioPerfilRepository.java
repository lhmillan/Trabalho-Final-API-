package br.com.serratec.trabfinal_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.trabfinal_api.model.UsuarioPerfil;
import br.com.serratec.trabfinal_api.model.UsuarioPerfilPK;

public interface UsuarioPerfilRepository extends JpaRepository<UsuarioPerfil, UsuarioPerfilPK>{
	
	
}
