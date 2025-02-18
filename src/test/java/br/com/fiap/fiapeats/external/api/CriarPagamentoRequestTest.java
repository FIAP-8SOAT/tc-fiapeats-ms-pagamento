package br.com.fiap.fiapeats.external.api;

import br.com.fiap.fiapeats.external.api.contracts.request.CriarPagamentoRequest;
import br.com.fiap.fiapeats.external.api.contracts.request.ProdutoRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;
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
        ProdutoRequest produtoRequest = new ProdutoRequest(UUID.randomUUID(), "produto", "produto", BigDecimal.TEN, "Bebida");
        CriarPagamentoRequest request = new CriarPagamentoRequest(UUID.randomUUID(), List.of(produtoRequest));

        Set<ConstraintViolation<CriarPagamentoRequest>> violations = validator.validate(request);

        assertThat(violations).isEmpty();

    }

    @Test
    void deveRetornarErroQuandoIdPedidoForNulo() {
        ProdutoRequest produtoRequest = new ProdutoRequest(UUID.randomUUID(), "produto", "produto", BigDecimal.TEN, "Bebida");
        CriarPagamentoRequest request = new CriarPagamentoRequest(null, List.of(produtoRequest));

        Set<ConstraintViolation<CriarPagamentoRequest>> violations = validator.validate(request);

        assertThat(violations).isNotEmpty();
    }

}
