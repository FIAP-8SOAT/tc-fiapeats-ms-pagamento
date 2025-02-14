package br.com.fiap.fiapeats.external.integration.response;

import br.com.fiap.fiapeats.external.integration.feign.response.PagamentoResponse;
import br.com.fiap.fiapeats.external.integration.feign.response.PedidoResponse;
import br.com.fiap.fiapeats.external.integration.feign.response.ProdutoResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PedidoResponseTest {

    @Test
    void deveCriarPagamentoPedidoResponseComSucesso() {

        var idPedido = UUID.randomUUID();
        ProdutoResponse produtoResponse = new ProdutoResponse(UUID.randomUUID(), "Coca", "Coca Zero", new BigDecimal("10"), "Bebida");
        PedidoResponse response = new PedidoResponse(idPedido, List.of(produtoResponse), "12345678912", new BigDecimal("10"), "Pendente", new PagamentoResponse("Pendente", 1L, null), LocalDateTime.now(), 15);

        assertNotNull(response);
        assertEquals(idPedido, response.getId());
        assertEquals("12345678912", response.getCpf());
        assertEquals(1, response.getProdutos().size());
        assertEquals(BigDecimal.TEN, response.getValor());
        assertEquals("Pendente", response.getStatusOrdem());
        assertNotNull(response.getPagamento());
        assertNotNull(response.getDataCriacao());
        assertEquals(15, response.getTempoEspera());
    }
}
