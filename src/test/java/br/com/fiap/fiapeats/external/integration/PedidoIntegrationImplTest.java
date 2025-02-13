package br.com.fiap.fiapeats.external.integration;

import br.com.fiap.fiapeats.domain.entities.PagamentoPedido;
import br.com.fiap.fiapeats.domain.entities.Pedido;
import br.com.fiap.fiapeats.domain.entities.Produto;
import br.com.fiap.fiapeats.domain.enums.StatusPagamento;
import br.com.fiap.fiapeats.external.integration.feign.PedidoFeign;
import br.com.fiap.fiapeats.external.integration.feign.response.*;
import br.com.fiap.fiapeats.external.integration.impl.PedidoIntegrationImpl;
import br.com.fiap.fiapeats.external.integration.mapper.PedidoIntegrationMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

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
    @Mock
    private PedidoIntegrationMapper mapper;
    private Pedido pedido;
    private PedidoResponse pedidoResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        var idPedido = UUID.randomUUID();
        pedido = new Pedido(idPedido, List.of(new Produto(UUID.randomUUID(), "Coca", "Coca Zero", new BigDecimal("10"), "Bebida")), "12345678901", new BigDecimal("10"), "Pendente", new PagamentoPedido("Pendente", 1L, null), LocalDateTime.now(), 15);
        pedidoResponse = new PedidoResponse(idPedido, List.of(new ProdutoResponse(UUID.randomUUID(), "Coca", "Coca Zero", new BigDecimal("10"), "Bebida")), "12345678901", new BigDecimal("10"), "Pendente", new PagamentoResponse("Pendente", 1L, null), LocalDateTime.now(), 15);
    }

    @Test
    void deveConsultarPedidoComSucesso(){

        when(pedidoFeign.consultar(anyString())).thenReturn(Optional.of(pedidoResponse));
        when(mapper.toPedido(pedidoResponse)).thenReturn(pedido);

        Pedido response = pedidoIntegration.consultarPedido(UUID.randomUUID().toString());

        assertNotNull(response);
        assertEquals(1, response.getProdutos().size());
        verify(pedidoFeign, times(1)).consultar(any());
        verify(mapper, times(1)).toPedido(any());
    }

    @Test
    void deveRetornarNullQuandoPedidoNaoEncontrado(){

        when(pedidoFeign.consultar(anyString())).thenReturn(Optional.empty());
        when(mapper.toPedido(pedidoResponse)).thenReturn(pedido);

        Pedido response = pedidoIntegration.consultarPedido(UUID.randomUUID().toString());

        assertNull(response);
    }

    @Test
    void deveAtualizarStatusPagamentoComSucesso(){
        String idPedido = UUID.randomUUID().toString();

        pedidoIntegration.atualizarStatusPagamento(idPedido, StatusPagamento.APROVADO);

        verify(pedidoFeign, times(1)).atualizarStatusPagamento(any(), any());
    }
}
