package br.com.fiap.fiapeats.external.integration.response;

import br.com.fiap.fiapeats.external.integration.feign.response.ConsultarPagamentoPedidoResponse;
import br.com.fiap.fiapeats.external.integration.feign.response.PagamentoPedidoResponse;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ConsultarPagamentoPedidoResponseTest {

    @Test
    void deveCriarConsultarPagamentoPedidoResponseComSucesso() {

        List<PagamentoPedidoResponse> pagamentoPedidoResponseList = List.of(
                new PagamentoPedidoResponse("123", "approved"));
        ConsultarPagamentoPedidoResponse response = new ConsultarPagamentoPedidoResponse("approved", "123456987", pagamentoPedidoResponseList);

        assertNotNull(response);
        assertEquals("approved", response.getStatus());
        assertEquals("123456987", response.getIdPedido());
        assertEquals(1, response.getPagamento().size());
    }
}
