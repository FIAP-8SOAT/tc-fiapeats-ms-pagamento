package br.com.fiap.fiapeats.usecases;

import br.com.fiap.fiapeats.domain.entities.*;
import br.com.fiap.fiapeats.usecases.dtos.CriarPagamentoDTO;
import br.com.fiap.fiapeats.usecases.exceptions.NotFoundException;
import br.com.fiap.fiapeats.usecases.interfaces.out.pagamento.PagamentoGateway;
import br.com.fiap.fiapeats.usecases.interfaces.out.pedido.PedidoGateway;
import br.com.fiap.fiapeats.usecases.pagamento.CriarPagamentoUseCaseImpl;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class CriarPagamentoUseCaseImplTest {

    @InjectMocks
    private CriarPagamentoUseCaseImpl criarPagamentoUseCase;
    @Mock
    private PedidoGateway pedidoGateway;
    @Mock
    private PagamentoGateway pagamentoGateway;
    private Pedido pedido;
    private Pagamento pagamento;
    private CriarPagamentoDTO criarPagamentoDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        var idPedido = UUID.randomUUID();
        pedido = new Pedido(idPedido, List.of(new Produto(UUID.randomUUID(), "Coca", "Coca Zero", new BigDecimal("10"), new Categoria(1L, "Bebida"))), "12345678901", new BigDecimal("10"), "Pendente", new PagamentoPedido("Pendente", 1L, null), LocalDateTime.now(), 15);
        pagamento = new Pagamento(idPedido, "http:pedido-notificacao", "codigoQR");
        criarPagamentoDTO = new CriarPagamentoDTO(idPedido, "http:pedido-notificacao");
    }

    @Test
    void deveAtualizarPagamentoParaAprovadoComSucesso() {
        when(pedidoGateway.consultar(anyString())).thenReturn(pedido);
        when(pagamentoGateway.criar(any(), any())).thenReturn(pagamento);

        Pagamento response = criarPagamentoUseCase.criar(criarPagamentoDTO);

        assertNotNull(response);
        assertNotNull(response.getCodigoQr());
        verify(pedidoGateway, times(1)).consultar(anyString());
        verify(pagamentoGateway, times(1)).criar(any(), any());
    }

    @Test
    void deveRetornarNotFoundExceptionQuandoIdPedidoNaoEncontrado() {
        when(pedidoGateway.consultar(anyString())).thenReturn(null);

        NotFoundException exception = assertThrows(NotFoundException.class, () ->
                criarPagamentoUseCase.criar(criarPagamentoDTO));

        verify(pedidoGateway, times(1)).consultar(anyString());
        assertEquals("Id pedido não encontrado", exception.getMessage());
    }


}
