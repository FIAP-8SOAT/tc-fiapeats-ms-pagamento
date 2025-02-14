package br.com.fiap.fiapeats.external.integration.response;

import br.com.fiap.fiapeats.external.integration.feign.response.CategoriaResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CategoriaResponseTest {

    @Test
    void deveCriarCategoriaResponseComSucesso() {

        CategoriaResponse response = new CategoriaResponse(1L, "Bebida");

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Bebida", response.getDescricao());
    }
}
