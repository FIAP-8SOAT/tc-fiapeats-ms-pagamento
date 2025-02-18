package br.com.fiap.fiapeats.external;

import br.com.fiap.fiapeats.adapter.controller.PagamentoController;
import br.com.fiap.fiapeats.external.api.PagamentoSpringController;
import br.com.fiap.fiapeats.external.api.contracts.request.CriarPagamentoRequest;
import br.com.fiap.fiapeats.external.api.contracts.request.ProdutoRequest;
import br.com.fiap.fiapeats.external.api.mapper.PagamentoMapper;
import br.com.fiap.fiapeats.usecases.dtos.CriarPagamentoDTO;
import br.com.fiap.fiapeats.usecases.dtos.CriarPagamentoResponse;
import br.com.fiap.fiapeats.usecases.dtos.ProdutosDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PagamentoSpringControllerTest {

    @InjectMocks
    private PagamentoSpringController pagamentoSpringController;
    @Mock
    private PagamentoController pagamentoController;
    @Mock
    private PagamentoMapper pagamentoMapper;
    private CriarPagamentoDTO criarPagamentoDTO;
    private ProdutosDTO produtosDTO;
    private CriarPagamentoResponse criarPagamentoResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        var idPedido = UUID.randomUUID();
        produtosDTO = new ProdutosDTO(UUID.randomUUID(), "produto", "produto", BigDecimal.TEN, "Bebida");
        criarPagamentoDTO = new CriarPagamentoDTO(idPedido, List.of(produtosDTO));
        criarPagamentoResponse = new CriarPagamentoResponse("codigoQR");
    }

    @Test
    void deveCriarPagamentoComSucesso() {
        ProdutoRequest produtoRequest = new ProdutoRequest(UUID.randomUUID(), "produto", "produto", BigDecimal.TEN, "Bebida");
        CriarPagamentoRequest criarPagamentoRequest = new CriarPagamentoRequest(UUID.randomUUID(), List.of(produtoRequest));

        when(pagamentoMapper.toCriarPagamentoDTO(criarPagamentoRequest)).thenReturn(criarPagamentoDTO);
        when(pagamentoController.criarPagamento(criarPagamentoDTO)).thenReturn(criarPagamentoResponse);

        ResponseEntity<CriarPagamentoResponse> response = pagamentoSpringController.criarCodigoPagamento(UUID.randomUUID().toString(), criarPagamentoRequest);

        assertNotNull(response.getBody());
        assertEquals(201, response.getStatusCode().value());
        assertEquals("codigoQR", response.getBody().getCodigoQR());
        verify(pagamentoMapper, times(1)).toCriarPagamentoDTO(any());
        verify(pagamentoController, times(1)).criarPagamento(any());
    }

    @Test
    void deveAtualizarPagamentoComSucesso() {
        pagamentoSpringController.atualizarPagamento(UUID.randomUUID().toString(), "topico-teste", "123456");

        verify(pagamentoController, times(1)).atualizarPagamento(any(), any());
    }
}
