package br.com.fiap.fiapeats.external.integration.request;

import br.com.fiap.fiapeats.external.integration.feign.request.ProdutosPedidoRequest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProdutosPedidoRequestTest {

    @Test
    void deveCriarProdutosPedidoRequestComSucesso() {

        var idProdutoPedido = UUID.randomUUID().toString();
        ProdutosPedidoRequest request = new ProdutosPedidoRequest(
                idProdutoPedido, "Bebida", "Coca", "Coca tradicional", new BigDecimal("10"), new BigDecimal("10"));

        assertNotNull(request);
        assertEquals(idProdutoPedido, request.getId());
        assertEquals(1, request.getQuantidade());
        assertEquals("Bebida", request.getCategoria());
        assertEquals("Coca", request.getNome());
        assertEquals("Coca tradicional", request.getDescricao());
        assertEquals(BigDecimal.TEN, request.getValorItem());
        assertEquals(BigDecimal.TEN, request.getValorTotal());
        assertEquals("unit", request.getUnidadeMedida());
    }
}
