package br.com.fiap.fiapeats.external.mapper;

import br.com.fiap.fiapeats.domain.entities.Pagamento;
import br.com.fiap.fiapeats.external.integration.feign.response.CriarPagamentoPedidoResponse;
import br.com.fiap.fiapeats.external.integration.mapper.PagamentoIntegrationMapperImpl;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PagamentoIntegrationMapperTest {

    private final PagamentoIntegrationMapperImpl pagamentoIntegrationMapper = new PagamentoIntegrationMapperImpl();

    @Test
    void toPagamentoComSucesso() {
        var idPedido = UUID.randomUUID();
        CriarPagamentoPedidoResponse criarPagamentoPedidoResponse = new CriarPagamentoPedidoResponse(idPedido.toString(), "codigoQR");

        Pagamento response = pagamentoIntegrationMapper.toPagamento(criarPagamentoPedidoResponse);

        assertNull(response.getIdPedido());
        assertNull(response.getUrlNotificacao());
        assertEquals("codigoQR", response.getCodigoQr());
    }

    @Test
    void toPagamentoRetornarNullQuandoRequestForNull() {
        Pagamento response = pagamentoIntegrationMapper.toPagamento(null);

        assertNull(response);
    }
}
