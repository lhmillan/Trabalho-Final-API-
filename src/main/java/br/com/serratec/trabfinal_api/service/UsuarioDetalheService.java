package br.com.serratec.trabfinal_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.serratec.trabfinal_api.errors.UsuarioException;
import br.com.serratec.trabfinal_api.model.Usuario;
import br.com.serratec.trabfinal_api.repository.UsuarioRepository;

@Service
public class UsuarioDetalheService implements UserDetailsService {
    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository.findByEmail(username);
        System.out.println("UsuarioDetalheService:"+username);
        if (usuario != null) {
            return usuario;
        }
        throw new UsuarioException("Email não encontrado!");
    }

}