package br.com.serratec.trabfinal_api.model;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class UsuarioPerfil {
	
	@EmbeddedId
	private UsuarioPerfilPK id = new UsuarioPerfilPK();
	
	private LocalDate dataCriacao;
	private Boolean ativo;
	
	public UsuarioPerfil() {
		
	}

	public UsuarioPerfil(Usuario usuario, Perfil perfil, LocalDate dataCriacao, Boolean ativo) {
		id.setUsuario(usuario);
		id.setPerfil(perfil);
		this.dataCriacao = dataCriacao;
		this.ativo = ativo;
	}

	public UsuarioPerfilPK getId() {
		return id;
	}

	public void setId(UsuarioPerfilPK id) {
		this.id = id;
	}
	
	public void setUsuario(Usuario usuario) {
		id.setUsuario(usuario);
	}
	
	public Usuario getUsuario() {
		return id.getUsuario();
	}
	
	public void setPerfil(Perfil perfil) {
		id.setPerfil(perfil);
	}
	
	public Perfil getPerfil() {
		return id.getPerfil();
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioPerfil other = (UsuarioPerfil) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
