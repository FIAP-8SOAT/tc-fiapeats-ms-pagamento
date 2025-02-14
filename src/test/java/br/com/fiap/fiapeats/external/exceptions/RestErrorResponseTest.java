package br.com.fiap.fiapeats.external.exceptions;

import br.com.fiap.fiapeats.external.api.exceptions.RestErrorResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class RestErrorResponseTest {

    @Test
    void deveCriarRestErrorResponseComMensagemEListaDeErros() {
        String mensagem = "Erro de validação";
        List<String> erros = Arrays.asList("Campo obrigatório", "Formato inválido");

        RestErrorResponse response = new RestErrorResponse(mensagem, erros);

        assertEquals(response.getMessage(), mensagem);
        assertEquals(response.getErrors(), erros);
        assertThat(response.getTimestamp()).isNotZero();
    }

    @Test
    void deveCriarRestErrorResponseComMensagemESemErros() {
        String mensagem = "Erro de validação";

        RestErrorResponse response = new RestErrorResponse(mensagem, null);

        assertEquals(response.getMessage(), mensagem);
        assertNull(response.getErrors());
        assertThat(response.getTimestamp()).isNotZero();
    }

    @Test
    void deveCriarRestErrorResponseComListaVaziaDeErros() {
        String mensagem = "Erro de validação";
        List<String> erros = Collections.emptyList();

        RestErrorResponse response = new RestErrorResponse(mensagem, erros);

        assertEquals(response.getMessage(), mensagem);
        assertEquals(response.getErrors(), erros);
        assertThat(response.getTimestamp()).isNotZero();
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"Erro de validação"})
    void deveCriarRestErrorResponseComMensagemESemErros(String mensagem) {
        RestErrorResponse response = new RestErrorResponse(mensagem, null);


        assertEquals(response.getMessage(), mensagem);
        assertNull(response.getErrors());
        assertThat(response.getTimestamp()).isNotZero();
    }

    @Test
    void deveCriarRestErrorResponseComMensagemNula() {
        List<String> erros = Arrays.asList("Campo obrigatório", "Formato inválido");

        RestErrorResponse response = new RestErrorResponse(null, erros);

        assertNull(response.getMessage());
        assertEquals(response.getErrors(), erros);
        assertThat(response.getTimestamp()).isNotZero();
    }
}
