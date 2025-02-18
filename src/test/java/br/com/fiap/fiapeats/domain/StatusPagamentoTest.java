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
        assertEquals("Pendente", StatusPagamento.PENDENTE.getNome());
        assertEquals("Aprovado", StatusPagamento.APROVADO.getNome());
        assertEquals("Recusado", StatusPagamento.RECUSADO.getNome());
        assertEquals("Em analise", StatusPagamento.EM_ANALISE.getNome());
        assertEquals("Estornado", StatusPagamento.ESTORNADO.getNome());
        assertEquals("Cancelado", StatusPagamento.CANCELADO.getNome());
        assertEquals("Desconhecido", StatusPagamento.DESCONHECIDO.getNome());
    }

    @Test
    void testGetValidName() {
        assertEquals(1, StatusPagamento.get("Pendente"));
        assertEquals(2, StatusPagamento.get("Aprovado"));
        assertEquals(3, StatusPagamento.get("Recusado"));
        assertEquals(4, StatusPagamento.get("Em analise"));
        assertEquals(5, StatusPagamento.get("Estornado"));
        assertEquals(6, StatusPagamento.get("Cancelado"));
        assertEquals(7, StatusPagamento.get("Desconhecido"));
    }

    @Test
    void testGetInvalidName() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            StatusPagamento.get("Invalido");
        });

        assertEquals("Nome inválido: Invalido", exception.getMessage());
    }
}
