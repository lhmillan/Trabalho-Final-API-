package br.com.serratec.trabfinal_api.service;

import br.com.serratec.trabfinal_api.dto.request.VeiculoRequestDTO;
import br.com.serratec.trabfinal_api.dto.response.VeiculoResponseDTO;
import br.com.serratec.trabfinal_api.model.Cliente;
import br.com.serratec.trabfinal_api.model.Veiculo;
import br.com.serratec.trabfinal_api.repository.ClienteRepository;
import br.com.serratec.trabfinal_api.repository.VeiculoRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final ClienteRepository clienteRepository;

    public VeiculoService(VeiculoRepository veiculoRepository, ClienteRepository clienteRepository) {

        this.veiculoRepository = veiculoRepository;
        this.clienteRepository = clienteRepository;
    }

    private void atualizarDadosVeiculo(Veiculo veiculo, VeiculoRequestDTO dto, Cliente cliente) {

        veiculo.setPlaca(dto.placa());
        veiculo.setMarca(dto.marca());
        veiculo.setModelo(dto.modelo());
        veiculo.setCor(dto.cor());
        veiculo.setAno(dto.ano());
        veiculo.setCliente(cliente);
    }

    private VeiculoResponseDTO converterParaDTO(Veiculo veiculo) {

        return new VeiculoResponseDTO(
                veiculo.getId(),
                veiculo.getCliente().getId(),
                veiculo.getPlaca(),
                veiculo.getMarca(),
                veiculo.getModelo(),
                veiculo.getCor(),
                veiculo.getAno()
        );
    }

    public List<VeiculoResponseDTO> listarVeiculos() {

        return veiculoRepository.findAll().stream().map(this::converterParaDTO).toList();
    }

    public VeiculoResponseDTO buscarPorId(Long id) {

        Veiculo veiculo = veiculoRepository.findById(id).orElseThrow(() ->
                        new RuntimeException("Veículo não encontrado!"));

        return converterParaDTO(veiculo);
    }

    public VeiculoResponseDTO cadastrarVeiculo(VeiculoRequestDTO dto) {

        Cliente cliente = clienteRepository.findById(dto.clienteId()).orElseThrow(() ->
                        new RuntimeException("Cliente não encontrado!"));

        Veiculo veiculo = new Veiculo();

        atualizarDadosVeiculo(veiculo, dto, cliente);

        veiculo = veiculoRepository.save(veiculo);

        return converterParaDTO(veiculo);
    }

    public VeiculoResponseDTO atualizarVeiculo(Long id,
                                               VeiculoRequestDTO dto) {

        Veiculo veiculo = veiculoRepository.findById(id).orElseThrow(() ->
                        new RuntimeException("Veículo não encontrado!"));

        Cliente cliente = clienteRepository.findById(dto.clienteId()).orElseThrow(() ->
                        new RuntimeException("Cliente não encontrado!"));

        atualizarDadosVeiculo(veiculo, dto, cliente);

        veiculo = veiculoRepository.save(veiculo);

        return converterParaDTO(veiculo);
    }

    public void removerVeiculo(Long id) {

        Veiculo veiculo = veiculoRepository.findById(id).orElseThrow(() ->
                        new RuntimeException("Veículo não encontrado!"));

        veiculoRepository.delete(veiculo);
    }


}