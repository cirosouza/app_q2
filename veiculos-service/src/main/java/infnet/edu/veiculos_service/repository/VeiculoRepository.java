package infnet.edu.veiculos_service.repository;

import infnet.edu.veiculos_service.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    // Método para encontrar veículos por marca
    List<Veiculo> findByMarca(String marca);

    // Método para encontrar veículos por modelo
    List<Veiculo> findByModelo(String modelo);

    // Método para encontrar veículos por ano de fabricação
    List<Veiculo> findByAnoFabricacao(int anoFabricacao);

    // Método para encontrar veículos por placa
    Veiculo findByPlaca(String placa);
}