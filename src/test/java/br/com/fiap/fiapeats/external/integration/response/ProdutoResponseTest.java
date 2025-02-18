package br.com.fiap.fiapeats.external.integration.response;

import br.com.fiap.fiapeats.external.integration.feign.response.CategoriaResponse;
import br.com.fiap.fiapeats.external.integration.feign.response.ProdutoResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProdutoResponseTest {

    @Test
    void deveCriarProdutoResponseComSucesso() {

        var idProduto = UUID.randomUUID();
        ProdutoResponse response = new ProdutoResponse(idProduto, "Coca", "Coca Zero", new BigDecimal("10"), new CategoriaResponse(1L, "Bebida"));

        assertNotNull(response);
        assertEquals(idProduto, response.getId());
        assertEquals("Coca", response.getNome());
        assertEquals("Coca Zero", response.getDescricao());
        assertEquals(BigDecimal.TEN, response.getValor());
    }
}
