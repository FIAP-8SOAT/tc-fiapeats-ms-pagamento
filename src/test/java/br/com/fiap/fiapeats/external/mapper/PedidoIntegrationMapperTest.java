package br.com.fiap.fiapeats.external.mapper;

import br.com.fiap.fiapeats.domain.entities.Pedido;
import br.com.fiap.fiapeats.external.integration.feign.response.PedidoResponse;
import br.com.fiap.fiapeats.external.integration.feign.response.ProdutoResponse;
import br.com.fiap.fiapeats.external.integration.mapper.PedidoIntegrationMapperImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class PedidoIntegrationMapperTest {

    private final PedidoIntegrationMapperImpl pedidoIntegrationMapper = new PedidoIntegrationMapperImpl();

    @Test
    void toPedidoComSucesso() {

        var idPedido = UUID.randomUUID();
        ProdutoResponse produtoResponse = new ProdutoResponse(UUID.randomUUID(), "Coca", "Coca Zero", new BigDecimal("10"), "Bebida");
        PedidoResponse pedidoResponse = new PedidoResponse(idPedido, List.of(produtoResponse), "12345678912", new BigDecimal("10"), LocalDateTime.now(), 15);

        Pedido response = pedidoIntegrationMapper.toPedido(pedidoResponse);

        assertEquals(idPedido, response.getId());
        assertEquals("12345678912", response.getCliCpf());
        assertEquals(15, response.getTempoEspera());
        assertEquals(BigDecimal.TEN, response.getValor());
        assertEquals(1, response.getProdutos().size());
        assertNotNull(response.getDataHoraCriacao());
    }

    @Test
    void toPedidoRetornarNullQuandoRequestForNull() {
        Pedido response = pedidoIntegrationMapper.toPedido(null);

        assertNull(response);
    }

    @Test
    void toPedidoRetornarNullQuandoProdutoResponseForNull() {

        var idPedido = UUID.randomUUID();
        PedidoResponse pedidoResponse = new PedidoResponse(idPedido, null, "12345678912", new BigDecimal("10"), LocalDateTime.now(), 15);

        Pedido response = pedidoIntegrationMapper.toPedido(pedidoResponse);

        assertNull(response.getProdutos());
    }
}
