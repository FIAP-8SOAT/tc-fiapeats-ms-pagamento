package br.com.fiap.fiapeats.external.mapper;

import br.com.fiap.fiapeats.external.api.contracts.request.CriarPagamentoRequest;
import br.com.fiap.fiapeats.external.api.contracts.request.ProdutoRequest;
import br.com.fiap.fiapeats.external.api.mapper.PagamentoMapperImpl;
import br.com.fiap.fiapeats.usecases.dtos.CriarPagamentoDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PagamentoMapperTest {

    private final PagamentoMapperImpl pagamentoMapper = new PagamentoMapperImpl();

    @Test
    void toCriarPagamentoDTOComSucesso() {
        var idPedido = UUID.randomUUID();
        ProdutoRequest produtoRequest = new ProdutoRequest(UUID.randomUUID(), "produto", "produto", BigDecimal.TEN, "Bebida");
        CriarPagamentoRequest criarPagamentoRequest = new CriarPagamentoRequest(idPedido, List.of(produtoRequest));

        CriarPagamentoDTO response = pagamentoMapper.toCriarPagamentoDTO(criarPagamentoRequest);

        assertEquals(idPedido, response.getIdPedido());
        assertEquals(1, response.getProdutos().size());
    }

    @Test
    void toCriarPagamentoDTORetornarNullQuandoRequestForNull() {
        CriarPagamentoDTO response = pagamentoMapper.toCriarPagamentoDTO(null);

        assertNull(response);
    }
}
