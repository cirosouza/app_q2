package infnet.edu.veiculos_service;

import infnet.edu.veiculos_service.controller.VeiculoController;
import infnet.edu.veiculos_service.model.Veiculo;
import infnet.edu.veiculos_service.service.VeiculoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class VeiculoControllerTest {

    @Mock
    private VeiculoService veiculoService;

    @InjectMocks
    private VeiculoController veiculoController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(veiculoController).build();
    }

    @Test
    void testCriarVeiculo() {
        Veiculo veiculo = new Veiculo();
        veiculo.setMarca("Toyota");
        veiculo.setModelo("Corolla");

        when(veiculoService.salvarVeiculo(any(Veiculo.class))).thenReturn(veiculo);

        ResponseEntity<Veiculo> response = veiculoController.criarVeiculo(veiculo);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(veiculo, response.getBody());
    }

    @Test
    void testListarVeiculos() {
        Veiculo veiculo1 = new Veiculo();
        Veiculo veiculo2 = new Veiculo();
        when(veiculoService.listarVeiculos()).thenReturn(Arrays.asList(veiculo1, veiculo2));

        ResponseEntity<List<Veiculo>> response = veiculoController.listarVeiculos();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testBuscarVeiculoPorId() {
        Long id = 1L;
        Veiculo veiculo = new Veiculo();
        when(veiculoService.buscarVeiculoPorId(id)).thenReturn(Optional.of(veiculo));

        ResponseEntity<Veiculo> response = veiculoController.buscarVeiculoPorId(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(veiculo, response.getBody());
    }

    @Test
    void testBuscarVeiculoPorIdNotFound() {
        Long id = 2L;
        when(veiculoService.buscarVeiculoPorId(id)).thenReturn(Optional.empty());

        ResponseEntity<Veiculo> response = veiculoController.buscarVeiculoPorId(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testBuscarVeiculosPorMarca() {
        String marca = "Toyota";
        Veiculo veiculo = new Veiculo();
        when(veiculoService.buscarVeiculosPorMarca(marca)).thenReturn(Collections.singletonList(veiculo));

        ResponseEntity<List<Veiculo>> response = veiculoController.buscarVeiculosPorMarca(marca);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testDeletarVeiculo() {
        Long id = 1L;
        doNothing().when(veiculoService).deletarVeiculo(id);

        ResponseEntity<Void> response = veiculoController.deletarVeiculo(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(veiculoService, times(1)).deletarVeiculo(id);
    }
}
