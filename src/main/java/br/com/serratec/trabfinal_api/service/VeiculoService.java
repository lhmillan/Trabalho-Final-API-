package br.com.serratec.trabfinal_api.service;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.serratec.trabfinal_api.dto.request.VeiculoRequestDTO;
import br.com.serratec.trabfinal_api.dto.response.ClienteResponseDTO;
import br.com.serratec.trabfinal_api.dto.response.VeiculoResponseDTO;
import br.com.serratec.trabfinal_api.model.Cliente;
import br.com.serratec.trabfinal_api.model.Veiculo;
import br.com.serratec.trabfinal_api.repository.ClienteRepository;
import br.com.serratec.trabfinal_api.repository.VeiculoRepository;
import jakarta.transaction.Transactional;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FotoService fotoService;

    private void atualizarDadosVeiculo(Veiculo veiculo, VeiculoRequestDTO dto, Cliente cliente) {

        veiculo.setPlaca(dto.placa());
        veiculo.setMarca(dto.marca());
        veiculo.setModelo(dto.modelo());
        veiculo.setCor(dto.cor());
        veiculo.setAno(dto.ano());
        veiculo.setCliente(cliente);
    }

    private VeiculoResponseDTO converterParaDTO(Veiculo veiculo) {
        Cliente c = veiculo.getCliente();
        ClienteResponseDTO clientedto = new ClienteResponseDTO(c.getId(), c.getNome(), c.getTelefone(), c.getCpf(),
                c.getEmail(), c.getEndereco());
        return new VeiculoResponseDTO(
                veiculo.getId(),
                veiculo.getPlaca(),
                veiculo.getMarca(),
                veiculo.getModelo(),
                veiculo.getCor(),
                veiculo.getAno(),
                clientedto,
                null);

    }

    public List<VeiculoResponseDTO> listarVeiculos() {

        return veiculoRepository.findAll().stream().map(this::converterParaDTO).toList();
    }

    public VeiculoResponseDTO buscarPorId(Long id) {

        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado!"));

        return converterParaDTO(veiculo);
    }

    public VeiculoResponseDTO cadastrarVeiculo(VeiculoRequestDTO dto) {
        Veiculo veiculo = new Veiculo();
        Cliente cliente = clienteRepository.findById(dto.clienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));

        atualizarDadosVeiculo(veiculo, dto, cliente);

        veiculo = veiculoRepository.save(veiculo);

        return converterParaDTO(veiculo);
    }

    public VeiculoResponseDTO atualizarVeiculo(Long id,
            VeiculoRequestDTO dto) {

        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado!"));

        Cliente cliente = clienteRepository.findById(dto.clienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));

        atualizarDadosVeiculo(veiculo, dto, cliente);

        veiculo = veiculoRepository.save(veiculo);

        return converterParaDTO(veiculo);
    }

    public void removerVeiculo(Long id) {

        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado!"));

        veiculoRepository.delete(veiculo);
    }

    public List<VeiculoResponseDTO> listarComFoto() {
        return veiculoRepository.findAll().stream().map(veiculo -> adicionarUriFoto(veiculo))
                .collect(Collectors.toList());
    }

    // retorna um dto com o link para abrir a foto que foi salva
    public VeiculoResponseDTO adicionarUriFoto(Veiculo veiculo) {
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/veiculos/{id}/foto")
                .buildAndExpand(veiculo.getId()).toUri();
        Cliente c = veiculo.getCliente();
        ClienteResponseDTO clientedto = new ClienteResponseDTO(c.getId(), c.getNome(), c.getTelefone(), c.getCpf(),
                c.getEmail(), c.getEndereco());

        VeiculoResponseDTO dto = new VeiculoResponseDTO(
                veiculo.getId(),
                veiculo.getPlaca(),
                veiculo.getMarca(),
                veiculo.getModelo(),
                veiculo.getCor(),
                veiculo.getAno(),
                clientedto,
                uri.toString());
        return dto;
    }

    @Transactional
    public VeiculoResponseDTO inserir(VeiculoRequestDTO dto, MultipartFile file) throws IOException {
        Veiculo veiculo = new Veiculo();

        // nao quero criar o optional
        Cliente cliente = clienteRepository.findById(dto.clienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));

        atualizarDadosVeiculo(veiculo, dto, cliente);

        veiculo = veiculoRepository.save(veiculo);
        fotoService.inserir(veiculo, file);
        return adicionarUriFoto(veiculo);
    }

}