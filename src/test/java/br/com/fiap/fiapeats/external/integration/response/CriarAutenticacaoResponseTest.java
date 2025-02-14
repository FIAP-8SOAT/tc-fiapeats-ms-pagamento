package br.com.fiap.fiapeats.external.integration.response;

import br.com.fiap.fiapeats.external.integration.feign.response.CriarAutenticacaoResponse;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CriarAutenticacaoResponseTest {

    @Test
    void deveCriarAutenticacaoResponseComSucesso() {

        CriarAutenticacaoResponse response = new CriarAutenticacaoResponse("123456789", "Bearer");

        assertNotNull(response);
        assertEquals("123456789", response.getAccessToken());
        assertEquals("Bearer", response.getTokenType());
    }
}
