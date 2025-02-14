package br.com.fiap.fiapeats.adapter.gateway;

import br.com.fiap.fiapeats.adapter.gateway.integration.impl.PedidoGatewayImpl;
import br.com.fiap.fiapeats.adapter.gateway.integration.interfaces.PedidoIntegration;
import br.com.fiap.fiapeats.domain.entities.*;
import br.com.fiap.fiapeats.domain.enums.StatusPagamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class PedidoGatewayImplTest {

    @InjectMocks
    private PedidoGatewayImpl pedidoGatewayImpl;
    @Mock
    private PedidoIntegration pedidoIntegration;
    private Pedido pedido;
    private UUID idPedido;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        idPedido = UUID.randomUUID();
        pedido = new Pedido(idPedido, List.of(new Produto(UUID.randomUUID(), "Produto", "Descricao Produto", BigDecimal.TEN, "Categoria")),
                "12345678901", BigDecimal.TEN, "Pendente", new PagamentoPedido("Pendente", 1L, null), LocalDateTime.now(), 10);
    }

    @Test
    void deveConsultarPedidoComSucesso() {
        when(pedidoIntegration.consultarPedido(idPedido.toString())).thenReturn(pedido);

        Pedido response = pedidoGatewayImpl.consultar(idPedido.toString());

        assertNotNull(response);
        assertEquals(response.getId(), idPedido);
        assertEquals(response.getCpf(), "12345678901");
        assertEquals(response.getValor(), BigDecimal.TEN);
        assertNotNull(response.getDataCriacao());
        assertEquals(response.getTempoEspera(), 10);
        assertEquals(response.getStatusOrdem(), "Pendente");
        assertEquals(response.getProdutos().size(), 1);
        assertNotNull(response.getPagamento());
        verify(pedidoIntegration, times(1)).consultarPedido(any());
    }

    @Test
    void deveAtualizarStatusPagamentoComSucesso() {
        pedidoGatewayImpl.atualizarStatusPagamento(idPedido.toString(), StatusPagamento.APROVADO);

        verify(pedidoIntegration, times(1)).atualizarStatusPagamento(any(), any());
    }
}
