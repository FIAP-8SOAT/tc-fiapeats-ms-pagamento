package br.com.fiap.fiapeats.external.mapper;

import br.com.fiap.fiapeats.domain.entities.PagamentoPedidoExterno;
import br.com.fiap.fiapeats.external.integration.feign.response.ConsultarPagamentoPedidoResponse;
import br.com.fiap.fiapeats.external.integration.feign.response.PagamentoPedidoResponse;
import br.com.fiap.fiapeats.external.integration.mapper.PagamentoPedidoIntegrationMapperImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PagamentoPedidoIntegrationMapperTest {

    private final PagamentoPedidoIntegrationMapperImpl pagamentoPedidoIntegrationMapper = new PagamentoPedidoIntegrationMapperImpl();

    @Test
    void toPedidoComSucesso() {
        List<PagamentoPedidoResponse> pagamentoPedidoResponseList = List.of(
                new PagamentoPedidoResponse("123", "approved"));
        ConsultarPagamentoPedidoResponse consultarPagamentoPedidoResponse = new ConsultarPagamentoPedidoResponse("approved", "123456987", pagamentoPedidoResponseList);

        PagamentoPedidoExterno response = pagamentoPedidoIntegrationMapper.toPedido(consultarPagamentoPedidoResponse);

        assertEquals("123456987", response.getIdPedido());
        assertEquals("approved", response.getStatus());
        assertEquals(1, response.getPagamento().size());
    }

    @Test
    void toPedidoRetornarNullQuandoRequestForNull() {
        PagamentoPedidoExterno response = pagamentoPedidoIntegrationMapper.toPedido(null);

        assertNull(response);
    }

    @Test
    void toPedidoRetornarNullQuandoPagamentoPedidoResponseForNull() {
        ConsultarPagamentoPedidoResponse consultarPagamentoPedidoResponse = new ConsultarPagamentoPedidoResponse("approved", "123456987", null);

        PagamentoPedidoExterno response = pagamentoPedidoIntegrationMapper.toPedido(consultarPagamentoPedidoResponse);

        assertNull(response.getPagamento());
    }
}
