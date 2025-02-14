package br.com.fiap.fiapeats.external.api;

import br.com.fiap.fiapeats.external.api.contracts.request.CriarPagamentoRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import java.util.UUID;

public class CriarPagamentoRequestTest {

    private final Validator validator;

    CriarPagamentoRequestTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void deveValidarCriarPagamentoRequestComSucesso() {
        CriarPagamentoRequest request = new CriarPagamentoRequest(UUID.randomUUID(), "http:pedido-notificacao");

        Set<ConstraintViolation<CriarPagamentoRequest>> violations = validator.validate(request);

        assertThat(violations).isEmpty();

    }

    @Test
    void deveRetornarErroQuandoIdPedidoForNulo() {
        CriarPagamentoRequest request = new CriarPagamentoRequest(null, "http:pedido-notificacao");

        Set<ConstraintViolation<CriarPagamentoRequest>> violations = validator.validate(request);

        assertThat(violations).isNotEmpty();
    }

    @Test
    void deveRetornarErroQuandoUrlNotificacaoForVazia() {
        CriarPagamentoRequest request = new CriarPagamentoRequest(UUID.randomUUID(), "");

        Set<ConstraintViolation<CriarPagamentoRequest>> violations = validator.validate(request);

        assertThat(violations).isNotEmpty();
    }


}
