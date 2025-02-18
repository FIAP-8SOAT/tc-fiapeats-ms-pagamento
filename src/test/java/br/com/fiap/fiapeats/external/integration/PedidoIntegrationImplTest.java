package br.com.fiap.fiapeats.external.integration;

import br.com.fiap.fiapeats.domain.entities.Categoria;
import br.com.fiap.fiapeats.domain.entities.PagamentoPedido;
import br.com.fiap.fiapeats.domain.entities.Pedido;
import br.com.fiap.fiapeats.domain.entities.Produto;
import br.com.fiap.fiapeats.domain.enums.StatusPagamento;
import br.com.fiap.fiapeats.external.integration.feign.PedidoFeign;
import br.com.fiap.fiapeats.external.integration.feign.response.*;
import br.com.fiap.fiapeats.external.integration.impl.PedidoIntegrationImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PedidoIntegrationImplTest {

    @InjectMocks
    private PedidoIntegrationImpl pedidoIntegration;
    @Mock
    private PedidoFeign pedidoFeign;
    private Pedido pedido;
    private PedidoResponse pedidoResponse;
    private Produto produto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        var idPedido = UUID.randomUUID();
        produto = new Produto(UUID.randomUUID(), "Coca", "Coca Zero", new BigDecimal("10"), "Bebida");
        pedido = new Pedido(idPedido, List.of(produto), "12345678901", new BigDecimal("10"), "Pendente", new PagamentoPedido("Pendente", 1L, null), LocalDateTime.now(), 15);
        pedidoResponse = new PedidoResponse(idPedido, List.of(new ProdutoResponse(UUID.randomUUID(), "Coca", "Coca Zero", new BigDecimal("10"), new CategoriaResponse(1L, "Bebida"))), "12345678901", new BigDecimal("10"), "Pendente", new PagamentoResponse("Pendente", 1L, null), LocalDateTime.now(), 15);
    }

    @Test
    void deveConsultarPedidoComSucesso(){

        when(pedidoFeign.consultar(anyString())).thenReturn(Optional.of(pedidoResponse));

        Pedido response = pedidoIntegration.consultarPedido(UUID.randomUUID().toString());

        assertNotNull(response);
        assertEquals(1, response.getProdutos().size());
        verify(pedidoFeign, times(1)).consultar(any());
    }

    @Test
    void deveAtualizarStatusPagamentoComSucesso(){
        String idPedido = UUID.randomUUID().toString();

        pedidoIntegration.atualizarStatusPagamento(idPedido, StatusPagamento.APROVADO);

        verify(pedidoFeign, times(1)).atualizarStatusPagamento(any(), any());
    }
}
