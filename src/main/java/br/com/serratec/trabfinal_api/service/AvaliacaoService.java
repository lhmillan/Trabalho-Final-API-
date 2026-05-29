package br.com.serratec.trabfinal_api.service;


import org.springframework.stereotype.Service;

import br.com.serratec.trabfinal_api.dto.response.AvaliacaoResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvaliacaoService {

    private final br.com.serratec.trabfinal_api.repository.AvaliacaoRepository repository;

    public AvaliacaoService(br.com.serratec.trabfinal_api.repository.AvaliacaoRepository repository) {
        this.repository = repository;
    }

    public br.com.serratec.trabfinal_api.dto.response.AvaliacaoResponseDTO criar(br.com.serratec.trabfinal_api.dto.request.AvaliacaoRequestDTO dto) {
        br.com.serratec.trabfinal_api.model.Avaliacao avaliacao = new br.com.serratec.trabfinal_api.model.Avaliacao(
                dto.getNota(),
                dto.getComentario(),
                dto.getClienteId(),
                dto.getServicoId()
        );

        br.com.serratec.trabfinal_api.model.Avaliacao salvo = repository.save(avaliacao);

        return new br.com.serratec.trabfinal_api.dto.response.AvaliacaoResponseDTO(
                salvo.getId(),
                salvo.getNota(),
                salvo.getComentario(),
                salvo.getClienteId(),
                salvo.getServicoId()
        );
    }

    public List<br.com.serratec.trabfinal_api.dto.response.AvaliacaoResponseDTO> listar() {
        return repository.findAll().stream()
                .map(a -> new AvaliacaoResponseDTO(
                        a.getId(),
                        a.getNota(),
                        a.getComentario(),
                        a.getClienteId(),
                        a.getServicoId()
                )).collect(Collectors.toList());
    }

    public br.com.serratec.trabfinal_api.dto.response.AvaliacaoResponseDTO buscarPorId(Long id) {
        br.com.serratec.trabfinal_api.model.Avaliacao a = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avaliação não encontrada"));

        return new br.com.serratec.trabfinal_api.dto.response.AvaliacaoResponseDTO(
                a.getId(),
                a.getNota(),
                a.getComentario(),
                a.getClienteId(),
                a.getServicoId()
        );
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
