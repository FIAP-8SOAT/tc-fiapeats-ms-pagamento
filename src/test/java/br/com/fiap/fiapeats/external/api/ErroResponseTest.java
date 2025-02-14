package br.com.fiap.fiapeats.external.api;

import br.com.fiap.fiapeats.external.api.contracts.response.ErroResponse;
import br.com.fiap.fiapeats.external.api.exceptions.RestErrorResponse;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ErroResponseTest {

    @Test
    void deveCriarRestErrorResponseComMensagemEListaDeErros() {
        String mensagem = "Erro de validação";
        List<String> erros = Arrays.asList("Campo obrigatório", "Formato inválido");

        RestErrorResponse response = new RestErrorResponse(mensagem, erros);

        assertThat(response.getMessage()).isEqualTo(mensagem);
        assertThat(response.getErrors()).isEqualTo(erros);
        assertThat(response.getTimestamp()).isNotZero();
    }

    @Test
    void deveCriarRestErrorResponseComMensagemESemErros() {
        String mensagem = "Erro de validação";

        RestErrorResponse response = new RestErrorResponse(mensagem, null);

        assertThat(response.getMessage()).isEqualTo(mensagem);
        assertThat(response.getErrors()).isNull();
        assertThat(response.getTimestamp()).isNotZero();
    }

    @Test
    void deveCriarRestErrorResponseComListaVaziaDeErros() {
        String mensagem = "Erro de validação";
        List<String> erros = Collections.emptyList();

        RestErrorResponse response = new RestErrorResponse(mensagem, erros);

        assertThat(response.getMessage()).isEqualTo(mensagem);
        assertThat(response.getErrors()).isEqualTo(erros);
        assertThat(response.getTimestamp()).isNotZero();
    }

    @Test
    void deveCriarErroResponseComTodosOsCampos() {
        ErroResponse erroResponse = new ErroResponse("TipoErro", "CodigoErro", "Mensagem de erro");

        assertThat(erroResponse.getTipo()).isEqualTo("TipoErro");
        assertThat(erroResponse.getCodigo()).isEqualTo("CodigoErro");
        assertThat(erroResponse.getMensagem()).isEqualTo("Mensagem de erro");
    }

    @Test
    void deveCriarErroResponseComConstrutorSemArgumentos() {
        ErroResponse erroResponse = new ErroResponse();

        assertThat(erroResponse.getTipo()).isNull();
        assertThat(erroResponse.getCodigo()).isNull();
        assertThat(erroResponse.getMensagem()).isNull();
    }

    @Test
    void deveSetarEObterCamposDeErroResponse() {
        ErroResponse erroResponse = new ErroResponse();
        erroResponse.setTipo("TipoErro");
        erroResponse.setCodigo("CodigoErro");
        erroResponse.setMensagem("Mensagem de erro");

        assertThat(erroResponse.getTipo()).isEqualTo("TipoErro");
        assertThat(erroResponse.getCodigo()).isEqualTo("CodigoErro");
        assertThat(erroResponse.getMensagem()).isEqualTo("Mensagem de erro");
    }
}
