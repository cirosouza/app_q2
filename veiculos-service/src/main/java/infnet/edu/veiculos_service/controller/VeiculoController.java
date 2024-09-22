package infnet.edu.veiculos_service.controller;

import infnet.edu.veiculos_service.model.Veiculo;
import infnet.edu.veiculos_service.service.VeiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/veiculos")
@RequiredArgsConstructor
public class VeiculoController {

    private final VeiculoService veiculoService;

    // Criar ou atualizar um veículo
    @PostMapping
    public ResponseEntity<Veiculo> criarVeiculo(@RequestBody Veiculo veiculo) {
        Veiculo savedVeiculo = veiculoService.salvarVeiculo(veiculo);
        return new ResponseEntity<>(savedVeiculo, HttpStatus.CREATED);
    }

    // Listar todos os veículos
    @GetMapping
    public ResponseEntity<List<Veiculo>> listarVeiculos() {
        List<Veiculo> veiculos = veiculoService.listarVeiculos();
        return new ResponseEntity<>(veiculos, HttpStatus.OK);
    }

    // Buscar um veículo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> buscarVeiculoPorId(@PathVariable Long id) {
        Optional<Veiculo> veiculo = veiculoService.buscarVeiculoPorId(id);
        return veiculo.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Buscar veículos por marca
    @GetMapping("/marca/{marca}")
    public ResponseEntity<List<Veiculo>> buscarVeiculosPorMarca(@PathVariable String marca) {
        List<Veiculo> veiculos = veiculoService.buscarVeiculosPorMarca(marca);
        return new ResponseEntity<>(veiculos, HttpStatus.OK);
    }

    // Buscar veículos por modelo
    @GetMapping("/modelo/{modelo}")
    public ResponseEntity<List<Veiculo>> buscarVeiculosPorModelo(@PathVariable String modelo) {
        List<Veiculo> veiculos = veiculoService.buscarVeiculosPorModelo(modelo);
        return new ResponseEntity<>(veiculos, HttpStatus.OK);
    }

    // Buscar veículos por ano de fabricação
    @GetMapping("/ano/{anoFabricacao}")
    public ResponseEntity<List<Veiculo>> buscarVeiculosPorAno(@PathVariable int anoFabricacao) {
        List<Veiculo> veiculos = veiculoService.buscarVeiculosPorAno(anoFabricacao);
        return new ResponseEntity<>(veiculos, HttpStatus.OK);
    }

    // Buscar veículo por placa
    @GetMapping("/placa/{placa}")
    public ResponseEntity<Veiculo> buscarVeiculoPorPlaca(@PathVariable String placa) {
        Optional<Veiculo> veiculo = veiculoService.buscarVeiculoPorPlaca(placa);
        return veiculo.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Deletar veículo por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVeiculo(@PathVariable Long id) {
        veiculoService.deletarVeiculo(id);
        return ResponseEntity.noContent().build();
    }
}

