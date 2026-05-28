package br.com.serratec.trabfinal_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;


import br.com.serratec.trabfinal_api.errors.PecaException;
import br.com.serratec.trabfinal_api.model.Pecas;

import br.com.serratec.trabfinal_api.repository.PecasRepository;
import jakarta.validation.Valid;

@Service
public class PecasService {

    @Autowired
    private PecasRepository repository;

    public Pecas inserir(Pecas Pecas) {
        return repository.save(Pecas);
    }

    public Optional<Pecas> buscar(Long id) {
        return repository.findById(id);
    }

    public List<Pecas> listar() {
        return repository.findAll();
    }

    public void alterar(@Valid @RequestBody Pecas pecas, @PathVariable Long id) {
        if (repository.existsById(id)) {
            pecas.setId(id);
            repository.save(pecas);
        } else {
            throw new PecaException("Peça não encontrada. Alteração não realizada!");
        }
    }

    public void apagar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new PecaException("Peça não encontrada. Exclusão não realizada!");
        }
        
    }
}
