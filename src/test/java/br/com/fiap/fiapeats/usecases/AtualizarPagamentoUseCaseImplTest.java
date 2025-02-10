package br.com.fiap.fiapeats.usecases;

import br.com.fiap.fiapeats.domain.entities.PagamentoExterno;
import br.com.fiap.fiapeats.domain.entities.PagamentoPedidoExterno;
import br.com.fiap.fiapeats.domain.entities.Pedido;
import br.com.fiap.fiapeats.domain.entities.Produto;
import br.com.fiap.fiapeats.domain.enums.StatusPagamento;
import br.com.fiap.fiapeats.usecases.exceptions.NotFoundException;
import br.com.fiap.fiapeats.usecases.interfaces.out.pagamento.PagamentoGateway;
import br.com.fiap.fiapeats.usecases.interfaces.out.pedido.PedidoGateway;
import br.com.fiap.fiapeats.usecases.pagamento.AtualizarPagamentoUseCaseImpl;
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
import static org.mockito.Mockito.*;

public class AtualizarPagamentoUseCaseImplTest {

    @InjectMocks
    private AtualizarPagamentoUseCaseImpl atualizarPagamentoUseCase;
    @Mock
    private PedidoGateway pedidoGateway;
    @Mock
    private PagamentoGateway pagamentoGateway;
    private Pedido pedido;
    private PagamentoPedidoExterno pagamentoPedidoExterno;
    private static final String STATUS_PAGAMENTO_PEDIDO = "closed";
    private static final String TOPICO_PEDIDO = "merchant_order";
    private static final String STATUS_PAGAMENTO = "approved";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pedido = new Pedido(UUID.randomUUID(), List.of(new Produto(UUID.randomUUID(), "Coca", "Coca Zero", new BigDecimal("10"), "Bebida")), "12345678901", new BigDecimal("10"), LocalDateTime.now(), 15);
        pagamentoPedidoExterno = new PagamentoPedidoExterno(STATUS_PAGAMENTO_PEDIDO, UUID.randomUUID().toString(), List.of(new PagamentoExterno("123", STATUS_PAGAMENTO)));
    }

    @Test
    void deveAtualizarPagamentoParaAprovadoComSucesso() {
        String idPedidoExterno = "123456";

        when(pagamentoGateway.consultar(idPedidoExterno)).thenReturn(pagamentoPedidoExterno);
        when(pedidoGateway.consultar(anyString())).thenReturn(pedido);
        when(pedidoGateway.atualizarStatusPagamento(pedido.getId().toString(), StatusPagamento.APROVADO)).thenReturn(200);

        assertDoesNotThrow(() -> atualizarPagamentoUseCase.atualizar(idPedidoExterno, TOPICO_PEDIDO));

        verify(pagamentoGateway, times(1)).consultar(anyString());
        verify(pedidoGateway, times(1)).consultar(anyString());
        verify(pedidoGateway, times(1)).atualizarStatusPagamento(anyString(), any());
    }

    @Test
    void deveAtualizarPagamentoParaPendenteComSucesso() {
        String idPedidoExterno = "123456";
        PagamentoPedidoExterno pagamentoPedido = new PagamentoPedidoExterno("OUTRO_STATUS", UUID.randomUUID().toString(), List.of(new PagamentoExterno("123", STATUS_PAGAMENTO)));

        when(pagamentoGateway.consultar(idPedidoExterno)).thenReturn(pagamentoPedido);
        when(pedidoGateway.consultar(anyString())).thenReturn(pedido);
        when(pedidoGateway.atualizarStatusPagamento(pedido.getId().toString(), StatusPagamento.PENDENTE)).thenReturn(200);

        assertDoesNotThrow(() -> atualizarPagamentoUseCase.atualizar(idPedidoExterno, TOPICO_PEDIDO));

        verify(pagamentoGateway, times(1)).consultar(anyString());
        verify(pedidoGateway, times(1)).consultar(anyString());
        verify(pedidoGateway, times(1)).atualizarStatusPagamento(anyString(), any());
    }

    @Test
    void deveRetornarNotFoundExceptionQuandoPedidoNaoEncontrado() {
        String idPedidoExterno = "123456";

        when(pagamentoGateway.consultar(idPedidoExterno)).thenReturn(pagamentoPedidoExterno);
        when(pedidoGateway.consultar(anyString())).thenReturn(pedido);
        when(pedidoGateway.atualizarStatusPagamento(pedido.getId().toString(), StatusPagamento.APROVADO)).thenReturn(500);

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                atualizarPagamentoUseCase.atualizar(idPedidoExterno, TOPICO_PEDIDO));

        verify(pagamentoGateway, times(1)).consultar(anyString());
        verify(pedidoGateway, times(1)).consultar(anyString());
        verify(pedidoGateway, times(1)).atualizarStatusPagamento(anyString(), any());
        assertEquals("Erro ao atualizar o status do pagamento. HTTP: 500", exception.getMessage());
    }

    @Test
    void deveRetornarRuntimeExceptionQuandoAtualizarRetornarDiferenteDeSucesso() {
        String idPedidoExterno = "123456";
        when(pagamentoGateway.consultar(idPedidoExterno)).thenReturn(pagamentoPedidoExterno);
        when(pedidoGateway.consultar(anyString())).thenReturn(null);

        NotFoundException exception = assertThrows(NotFoundException.class, () ->
                atualizarPagamentoUseCase.atualizar(idPedidoExterno, TOPICO_PEDIDO));

        verify(pagamentoGateway, times(1)).consultar(anyString());
        verify(pedidoGateway, times(1)).consultar(anyString());
        assertEquals("Id pedido vinculado ao id pedido externo não encontrado", exception.getMessage());
    }

    @Test
    void deveDescartarRequisicaoQuandoTopicoForDiferenteDeMerchant_Order() {
        String idPedidoExterno = "123456";

        assertDoesNotThrow(() -> atualizarPagamentoUseCase.atualizar(idPedidoExterno, "OUTRO_TOPICO"));

        verifyNoInteractions(pagamentoGateway);
        verifyNoInteractions(pedidoGateway);
    }


}
