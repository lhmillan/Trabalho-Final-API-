package br.com.serratec.trabfinal_api.service;

import br.com.serratec.trabfinal_api.dto.request.ServicoRequestDTO;
import br.com.serratec.trabfinal_api.dto.response.ServicoResponseDTO;
import br.com.serratec.trabfinal_api.model.PecaServico;
import br.com.serratec.trabfinal_api.model.Pecas;
import br.com.serratec.trabfinal_api.model.Servico;
import br.com.serratec.trabfinal_api.repository.PecasRepository;
import br.com.serratec.trabfinal_api.repository.ServicoRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoService {

    private final PecasRepository pecasRepository;
    private final ServicoRepository servicoRepository;

    public ServicoService(ServicoRepository servicoRepository, PecasRepository pecasRepository) {
        this.servicoRepository = servicoRepository;
        this.pecasRepository = pecasRepository;
    }

    private void atualizarDadosServico(Servico servico, ServicoRequestDTO dto) {

        servico.setDescricao(dto.descricao());
        servico.setValor(dto.valor());
        servico.setTempoEstimado(dto.tempoEstimado());
    }

    private ServicoResponseDTO converterParaDTO(Servico servico) {

        return new ServicoResponseDTO(
                servico.getId(),
                servico.getDescricao(),
                servico.getValor(),
                servico.getTempoEstimado());
    }

    public List<ServicoResponseDTO> listarServicos() {

        return servicoRepository.findAll().stream().map(this::converterParaDTO).toList();
    }

    public ServicoResponseDTO buscarPorId(Long id) {

        Servico servico = servicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado!"));

        return converterParaDTO(servico);
    }

    public ServicoResponseDTO cadastrarServico(ServicoRequestDTO dto) {

        Servico servico = new Servico();
        atualizarDadosServico(servico, dto);
        servico = servicoRepository.save(servico);

        for (PecaServico pecas : servico.getPecasUsada()) {
         Pecas peca = pecasRepository.findById(pecas.getPeca().getId())
         .orElseThrow(() -> new RuntimeException("Peca não encontrada"));
             if (pecas.getQtdUsada() > peca.getQuantidade()) {
                throw new RuntimeException("Estoque insuficiente de "+peca.getNomePeça());
             }
             Integer novoEstoque = pecas.getQtdUsada() - peca.getQuantidade();
             peca.setQuantidade(novoEstoque);
             pecasRepository.save(peca);
        }

        return converterParaDTO(servico);
    }

    public ServicoResponseDTO atualizarServico(Long id,
            ServicoRequestDTO dto) {

        Servico servico = servicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado!"));

        atualizarDadosServico(servico, dto);
        servico = servicoRepository.save(servico);

        return converterParaDTO(servico);
    }

    public void removerServico(Long id) {

        Servico servico = servicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado!"));

        servicoRepository.delete(servico);
    }

}
