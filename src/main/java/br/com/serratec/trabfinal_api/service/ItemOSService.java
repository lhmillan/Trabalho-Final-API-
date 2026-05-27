package br.com.serratec.trabfinal_api.service;

import br.com.serratec.trabfinal_api.dto.request.ItemOSRequestDTO;
import br.com.serratec.trabfinal_api.dto.response.ItemOSResponseDTO;
import br.com.serratec.trabfinal_api.model.ItemOS;
import br.com.serratec.trabfinal_api.model.OS;
import br.com.serratec.trabfinal_api.model.Servico;
import br.com.serratec.trabfinal_api.repository.ItemOSRepository;
import br.com.serratec.trabfinal_api.repository.OSRepository;
import br.com.serratec.trabfinal_api.repository.ServicoRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemOSService {

    private final ItemOSRepository itemOSRepository;
    private final ServicoRepository servicoRepository;
    private final OSRepository osRepository;

    public ItemOSService(ItemOSRepository itemOSRepository, ServicoRepository servicoRepository, OSRepository osRepository) {

        this.itemOSRepository = itemOSRepository;
        this.servicoRepository = servicoRepository;
        this.osRepository = osRepository;
    }

    public List<ItemOSResponseDTO> listarItensOS() {

        return itemOSRepository.findAll().stream().map(this::converterParaDTO).toList();
    }

    public ItemOSResponseDTO buscarPorId(Long id) {

        ItemOS itemOS = itemOSRepository.findById(id).orElseThrow(() ->
                        new RuntimeException("ItemOS não encontrado!"));

        return converterParaDTO(itemOS);
    }

    public ItemOSResponseDTO cadastrar(ItemOSRequestDTO dto) {

        Servico servico = servicoRepository.findById(dto.servico().getId()).orElseThrow(() ->
                        new RuntimeException("Serviço não encontrado!"));

        OS ordemServico = osRepository.findById(dto.ordemServico().getId()).orElseThrow(() ->
                        new RuntimeException("OS não encontrada!"));

        Double desconto = dto.desconto() == null ? 0.0 : dto.desconto();

        if (desconto > servico.getValor()) {

            throw new RuntimeException(
                    "Desconto não pode ser maior que o valor do serviço!");
        }

        ItemOS itemOS = new ItemOS();

        atualizarDadosItemOS(itemOS,
                dto,
                servico,
                ordemServico,
                desconto);

        itemOS = itemOSRepository.save(itemOS);

        return converterParaDTO(itemOS);
    }

    public void remover(Long id) {

        ItemOS itemOS = itemOSRepository.findById(id).orElseThrow(() ->
                        new RuntimeException("ItemOS não encontrado!"));

        itemOSRepository.delete(itemOS);
    }

    private void atualizarDadosItemOS(ItemOS itemOS,
                                      ItemOSRequestDTO dto,
                                      Servico servico,
                                      OS ordemServico,
                                      Double desconto) {

        itemOS.setServico(servico);
        itemOS.setOrdemServico(ordemServico);
        itemOS.setQuantidade(dto.quantidade());
        itemOS.setValor(servico.getValor());
        itemOS.setDesconto(desconto);
        Double subtotal = (servico.getValor() - desconto) * dto.quantidade();

        itemOS.setSubtotal(subtotal);
    }

    private ItemOSResponseDTO converterParaDTO(ItemOS itemOS) {

        return new ItemOSResponseDTO(
                itemOS.getId(),
                itemOS.getOrdemServico(),
                itemOS.getServico(),
                itemOS.getValor(),
                itemOS.getDesconto(),
                itemOS.getQuantidade(),
                itemOS.getSubtotal()
        );
    }
}