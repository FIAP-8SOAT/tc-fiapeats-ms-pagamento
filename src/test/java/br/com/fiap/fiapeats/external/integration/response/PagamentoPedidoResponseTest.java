package br.com.fiap.fiapeats.external.integration.response;

import br.com.fiap.fiapeats.external.integration.feign.response.PagamentoPedidoResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PagamentoPedidoResponseTest {

    @Test
    void deveCriarPagamentoPedidoResponseComSucesso() {

        PagamentoPedidoResponse response = new PagamentoPedidoResponse("123", "approved");

        assertNotNull(response);
        assertEquals("approved", response.getStatus());
        assertEquals("123", response.getId());
    }
}
