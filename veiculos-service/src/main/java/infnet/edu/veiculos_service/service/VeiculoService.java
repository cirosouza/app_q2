package infnet.edu.veiculos_service.service;

import infnet.edu.veiculos_service.model.Veiculo;
import infnet.edu.veiculos_service.repository.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;


    // Criar ou atualizar um veículo
    public Veiculo salvarVeiculo(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    // Buscar todos os veículos
    public List<Veiculo> listarVeiculos() {
        return veiculoRepository.findAll();
    }

    // Buscar um veículo por ID
    public Optional<Veiculo> buscarVeiculoPorId(Long id) {
        return veiculoRepository.findById(id);
    }

    // Buscar veículos por marca
    public List<Veiculo> buscarVeiculosPorMarca(String marca) {
        return veiculoRepository.findByMarca(marca);
    }

    // Buscar veículos por modelo
    public List<Veiculo> buscarVeiculosPorModelo(String modelo) {
        return veiculoRepository.findByModelo(modelo);
    }

    // Buscar veículos por ano de fabricação
    public List<Veiculo> buscarVeiculosPorAno(int anoFabricacao) {
        return veiculoRepository.findByAnoFabricacao(anoFabricacao);
    }

    // Buscar veículo por placa
    public Optional<Veiculo> buscarVeiculoPorPlaca(String placa) {
        return Optional.ofNullable(veiculoRepository.findByPlaca(placa));
    }

    // Deletar veículo por ID
    public void deletarVeiculo(Long id) {
        veiculoRepository.deleteById(id);
    }
}
