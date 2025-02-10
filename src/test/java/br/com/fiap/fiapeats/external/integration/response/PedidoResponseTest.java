package br.com.fiap.fiapeats.external.integration.response;

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
        PedidoResponse response = new PedidoResponse(idPedido, List.of(produtoResponse), "12345678912", new BigDecimal("10"), LocalDateTime.now(), 15);

        assertNotNull(response);
        assertEquals(idPedido, response.getId());
        assertEquals("12345678912", response.getCliCpf());
        assertEquals(1, response.getProdutos().size());
        assertEquals(BigDecimal.TEN, response.getValor());
        assertNotNull(response.getDataHoraCriacao());
        assertEquals(15, response.getTempoEspera());
    }
}
