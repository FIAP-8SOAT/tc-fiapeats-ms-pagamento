package br.com.fiap.fiapeats.external.integration.request;

import br.com.fiap.fiapeats.external.integration.feign.request.CriarAutenticacaoRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CriarAutenticacaoRequestTest {

    @Test
    void deveCriarAutenticacaoRequestComValoresDefaults() {
        CriarAutenticacaoRequest request = new CriarAutenticacaoRequest();

        assertNotNull(request);
        assertEquals("7643347265125418", request.getClientId());
        assertEquals("FHhqSmS3qzVw5KxdINqqZJpk6UqGLxBq", request.getClientSecret());
        assertEquals("client_credentials", request.getGrantType());
        assertEquals("false", request.getTestToken());
    }
}
