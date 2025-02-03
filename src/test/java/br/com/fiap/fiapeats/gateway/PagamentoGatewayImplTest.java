package br.com.fiap.fiapeats.gateway;

import br.com.fiap.fiapeats.adapter.gateway.integration.impl.PagamentoGatewayImpl;
import br.com.fiap.fiapeats.adapter.gateway.integration.interfaces.PagamentoIntegration;
import br.com.fiap.fiapeats.domain.entities.*;
import br.com.fiap.fiapeats.usecases.dtos.CriarPagamentoDTO;
import br.com.fiap.fiapeats.usecases.dtos.CriarPagamentoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class PagamentoGatewayImplTest {

    @InjectMocks
    private PagamentoGatewayImpl pagamentoGateway;
    @Mock
    private PagamentoIntegration pagamentoIntegration;
    private Pagamento pagamento;
    private PagamentoPedidoExterno pagamentoPedidoExterno;
    private Pedido pedido;
    private UUID idPedido;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        idPedido = UUID.randomUUID();
        pagamento = new Pagamento(UUID.randomUUID(), "http:pedido-notificacao", "codigoQR");
        pagamentoPedidoExterno = new PagamentoPedidoExterno(
                "Pendente", "123", List.of(new PagamentoExterno("1", "Pendente")));
        pedido = new Pedido(idPedido, List.of(new Produto(UUID.randomUUID(), "Produto", "Descricao Produto", BigDecimal.TEN, "Categoria")),
                "12345678901", BigDecimal.TEN, LocalDateTime.now(), 10);
    }

    @Test
    void deveCriarCodigoPagamentoComSucesso() {
        Pagamento pagamentoRequest = new Pagamento(UUID.randomUUID(), "http:pedido-notificacao", null);

        when(pagamentoIntegration.criarCodigoPagamento(pedido, pagamentoRequest)).thenReturn(pagamento);

        Pagamento response = pagamentoGateway.criar(pedido, pagamentoRequest);

        assertThat(response).isNotNull();
        assertThat(response.getCodigoQr()).isEqualTo("codigoQR");
        verify(pagamentoIntegration, times(1)).criarCodigoPagamento(any(), any());
    }

    @Test
    void deveConsultarPagamentoPedidoComSucesso() {
        when(pagamentoIntegration.consultarStatusPagamentoPedido(idPedido.toString())).thenReturn(pagamentoPedidoExterno);

        PagamentoPedidoExterno response = pagamentoGateway.consultar(idPedido.toString());

        assertThat(response).isNotNull();
        assertThat(response.getIdPedido()).isEqualTo("123");
        verify(pagamentoIntegration, times(1)).consultarStatusPagamentoPedido(any());
    }
}
