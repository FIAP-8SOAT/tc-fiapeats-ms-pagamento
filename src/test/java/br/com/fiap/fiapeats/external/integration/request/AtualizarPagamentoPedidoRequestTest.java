package br.com.fiap.fiapeats.external.integration.request;

import br.com.fiap.fiapeats.domain.enums.StatusPagamento;
import br.com.fiap.fiapeats.external.integration.feign.request.AtualizarPagamentoPedidoRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AtualizarPagamentoPedidoRequestTest {

    @Test
    void deveCriarAtualizarPagamentoPedidoRequestComSucesso() {
        AtualizarPagamentoPedidoRequest request = new AtualizarPagamentoPedidoRequest(StatusPagamento.APROVADO.getCodigo(), StatusPagamento.APROVADO.getNome());

        assertNotNull(request);
        assertEquals(2L, request.getId());
        assertEquals("Aprovado", request.getStatus());
    }
}
