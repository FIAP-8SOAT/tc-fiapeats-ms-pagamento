package br.com.fiap.fiapeats.external.integration.request;

import br.com.fiap.fiapeats.external.integration.feign.request.CriarPagamentoPedidoRequest;
import br.com.fiap.fiapeats.external.integration.feign.request.ProdutosPedidoRequest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CriarPagamentoPedidoRequestTest {

    @Test
    void deveCriarPagamentoPedidoRequestComSucesso() {

        var externalId = UUID.randomUUID().toString();
        List<ProdutosPedidoRequest> produtosPedidoRequestList = List.of(new ProdutosPedidoRequest(
                        UUID.randomUUID().toString(), "Bebida", "Coca", "Coca tradicional", new BigDecimal("10"), new BigDecimal("10")));
        CriarPagamentoPedidoRequest request = new CriarPagamentoPedidoRequest(externalId, "http:pedido-notificacao", new BigDecimal("10"), produtosPedidoRequestList);

        assertNotNull(request);
        assertEquals(externalId, request.getId());
        assertEquals(1, request.getItens().size());
        assertEquals("pedido-compo", request.getNome());
        assertEquals("pedido-compo", request.getDescricao());
        assertEquals("http:pedido-notificacao", request.getUrlNotificacao());
        assertEquals(BigDecimal.TEN, request.getValorTotal());
        assertNotNull(request.getDataExpiracao());
    }
}
