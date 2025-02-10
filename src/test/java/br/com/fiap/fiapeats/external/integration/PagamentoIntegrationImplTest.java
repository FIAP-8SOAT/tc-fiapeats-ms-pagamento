package br.com.fiap.fiapeats.external.integration;

import br.com.fiap.fiapeats.domain.entities.Pagamento;
import br.com.fiap.fiapeats.domain.entities.PagamentoPedidoExterno;
import br.com.fiap.fiapeats.domain.entities.Pedido;
import br.com.fiap.fiapeats.domain.entities.Produto;
import br.com.fiap.fiapeats.external.integration.feign.AutenticacaoFeign;
import br.com.fiap.fiapeats.external.integration.feign.PedidoPagamentoFeign;
import br.com.fiap.fiapeats.external.integration.feign.response.ConsultarPagamentoPedidoResponse;
import br.com.fiap.fiapeats.external.integration.feign.response.CriarAutenticacaoResponse;
import br.com.fiap.fiapeats.external.integration.feign.response.CriarPagamentoPedidoResponse;
import br.com.fiap.fiapeats.external.integration.impl.PagamentoIntegrationImpl;
import br.com.fiap.fiapeats.external.integration.mapper.PagamentoIntegrationMapper;
import br.com.fiap.fiapeats.external.integration.mapper.PagamentoPedidoIntegrationMapper;
import br.com.fiap.fiapeats.usecases.exceptions.NotFoundException;
import feign.FeignException;
import feign.Request;
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

public class PagamentoIntegrationImplTest {

    @InjectMocks
    private PagamentoIntegrationImpl pagamentoIntegration;
    @Mock
    private AutenticacaoFeign autenticacaoFeign;
    @Mock
    private PedidoPagamentoFeign pedidoPagamentoFeign;
    @Mock
    private PagamentoIntegrationMapper pagamentoIntegrationMapper;
    @Mock
    private PagamentoPedidoIntegrationMapper pagamentoPedidoIntegrationMapper;
    private CriarPagamentoPedidoResponse criarPagamentoPedidoResponse;
    private Pedido pedido;
    private Pagamento pagamento;
    private CriarAutenticacaoResponse criarAutenticacaoResponse;
    private PagamentoPedidoExterno pagamentoPedidoExterno;
    private ConsultarPagamentoPedidoResponse consultarPagamentoPedidoResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        var idPedido = UUID.randomUUID();
        criarPagamentoPedidoResponse = new CriarPagamentoPedidoResponse(idPedido.toString(), "codigoQR");
        pedido = new Pedido(idPedido, List.of(new Produto(UUID.randomUUID(), "Coca", "Coca Zero", new BigDecimal("10"), "Bebida")), "12345678901", new BigDecimal("10"), LocalDateTime.now(), 15);
        pagamento = new Pagamento(idPedido, "http:pedido-notificacao", "codigoQR");
        pagamentoPedidoExterno = new PagamentoPedidoExterno("approved", idPedido.toString(), List.of());
        consultarPagamentoPedidoResponse = new ConsultarPagamentoPedidoResponse("approved", idPedido.toString(), List.of());
        criarAutenticacaoResponse = new CriarAutenticacaoResponse("123456789123", "Bearer");
    }

    @Test
    void deveCriarCodigoPagamentoComSucesso(){

        when(autenticacaoFeign.obterToken(any())).thenReturn(criarAutenticacaoResponse);
        when(pedidoPagamentoFeign.criar(any(), any())).thenReturn(criarPagamentoPedidoResponse);
        when(pagamentoIntegrationMapper.toPagamento(criarPagamentoPedidoResponse)).thenReturn(pagamento);

        Pagamento response = pagamentoIntegration.criarCodigoPagamento(pedido, pagamento);

        assertNotNull(response);
        assertEquals(response.getCodigoQr(), "codigoQR");
        verify(pedidoPagamentoFeign, times(1)).criar(any(), any());
    }

    @Test
    void deveConsultarStatusPagamentoPedidoComSucesso(){

        when(autenticacaoFeign.obterToken(any())).thenReturn(criarAutenticacaoResponse);
        when(pedidoPagamentoFeign.consultar(any(), any())).thenReturn(consultarPagamentoPedidoResponse);
        when(pagamentoPedidoIntegrationMapper.toPedido(any())).thenReturn(pagamentoPedidoExterno);

        PagamentoPedidoExterno response = pagamentoIntegration.consultarStatusPagamentoPedido(UUID.randomUUID().toString());

        assertNotNull(response);
        assertEquals(response.getStatus(), "approved");
        verify(pedidoPagamentoFeign, times(1)).consultar(any(), any());
    }

    @Test
    void consultarStatusPagamentoPedidoNotFoundException() {
        String idPedidoExterno = "12345";

        FeignException feignException = FeignException.errorStatus("consultar", feign.Response.builder()
                .status(404)
                .request(Request.create(
                        Request.HttpMethod.GET,
                        "https://example.com/api/pedido",
                        java.util.Collections.emptyMap(),
                        null,
                        null,
                        null
                ))
                .build());

        when(autenticacaoFeign.obterToken(any())).thenReturn(criarAutenticacaoResponse);
        when(pagamentoPedidoIntegrationMapper.toPedido(any())).thenReturn(pagamentoPedidoExterno);
        when(pedidoPagamentoFeign.consultar(anyString(), eq(idPedidoExterno))).thenThrow(feignException);

        NotFoundException exception = assertThrows(NotFoundException.class, () ->
                pagamentoIntegration.consultarStatusPagamentoPedido(idPedidoExterno));

        assertEquals("Id pedido não encontrado", exception.getMessage());
    }

    @Test
    void consultarStatusPagamentoPedidoReturnNull() {
        String idPedidoExterno = "12345";

        FeignException feignException = FeignException.errorStatus("consultar", feign.Response.builder()
                .status(500)
                .request(Request.create(
                        Request.HttpMethod.GET,
                        "https://example.com/api/pedido",
                        java.util.Collections.emptyMap(),
                        null,
                        null,
                        null
                ))
                .build());

        when(autenticacaoFeign.obterToken(any())).thenReturn(criarAutenticacaoResponse);
        when(pagamentoPedidoIntegrationMapper.toPedido(any())).thenReturn(pagamentoPedidoExterno);
        when(pedidoPagamentoFeign.consultar(anyString(), eq(idPedidoExterno))).thenThrow(feignException);

        PagamentoPedidoExterno response = pagamentoIntegration.consultarStatusPagamentoPedido(idPedidoExterno);

        assertNull(response);
    }

}
