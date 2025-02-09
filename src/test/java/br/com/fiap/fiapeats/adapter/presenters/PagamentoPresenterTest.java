package br.com.fiap.fiapeats.adapter.presenters;

import br.com.fiap.fiapeats.domain.entities.Pagamento;
import br.com.fiap.fiapeats.usecases.dtos.CriarPagamentoResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class PagamentoPresenterTest {

    @Test
    void deveRetornarCriarPagamentoResponseComCodigoQrCorreto() {
        Pagamento pagamento = new Pagamento(UUID.randomUUID(), "http:pedido-notificacao", "codigoQR");

        CriarPagamentoResponse response = PagamentoPresenter.toCriarPagamentoResponse(pagamento);

        assertNotNull(response);
        assertEquals("codigoQR", response.getCodigoQR());
    }

    @Test
    void deveLancarNullPointerExceptionQuandoPagamentoForNulo() {
        assertThrows(NullPointerException.class, () -> PagamentoPresenter.toCriarPagamentoResponse(null));
    }
}
