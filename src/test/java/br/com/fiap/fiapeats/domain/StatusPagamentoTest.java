package br.com.fiap.fiapeats.domain;

import br.com.fiap.fiapeats.domain.enums.StatusPagamento;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StatusPagamentoTest {

    @Test
    void testGetCodigo() {
        assertEquals(1, StatusPagamento.PENDENTE.getCodigo());
        assertEquals(2, StatusPagamento.APROVADO.getCodigo());
        assertEquals(3, StatusPagamento.RECUSADO.getCodigo());
        assertEquals(4, StatusPagamento.EM_ANALISE.getCodigo());
        assertEquals(5, StatusPagamento.ESTORNADO.getCodigo());
        assertEquals(6, StatusPagamento.CANCELADO.getCodigo());
        assertEquals(7, StatusPagamento.DESCONHECIDO.getCodigo());
    }

    @Test
    void testGetNome() {
        assertEquals("PENDENTE", StatusPagamento.PENDENTE.getNome());
        assertEquals("APROVADO", StatusPagamento.APROVADO.getNome());
        assertEquals("RECUSADO", StatusPagamento.RECUSADO.getNome());
        assertEquals("EM_ANALISE", StatusPagamento.EM_ANALISE.getNome());
        assertEquals("ESTORNADO", StatusPagamento.ESTORNADO.getNome());
        assertEquals("CANCELADO", StatusPagamento.CANCELADO.getNome());
        assertEquals("DESCONHECIDO", StatusPagamento.DESCONHECIDO.getNome());
    }

    @Test
    void testGetValidName() {
        assertEquals(1, StatusPagamento.get("PENDENTE"));
        assertEquals(2, StatusPagamento.get("APROVADO"));
        assertEquals(3, StatusPagamento.get("RECUSADO"));
        assertEquals(4, StatusPagamento.get("EM_ANALISE"));
        assertEquals(5, StatusPagamento.get("ESTORNADO"));
        assertEquals(6, StatusPagamento.get("CANCELADO"));
        assertEquals(7, StatusPagamento.get("DESCONHECIDO"));
    }

    @Test
    void testGetInvalidName() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            StatusPagamento.get("INVALIDO");
        });

        assertEquals("Nome inválido: INVALIDO", exception.getMessage());
    }
}
