package br.com.fiap.fiapeats.adapter.controller;

import br.com.fiap.fiapeats.domain.entities.Pagamento;
import br.com.fiap.fiapeats.usecases.dtos.CriarPagamentoDTO;
import br.com.fiap.fiapeats.usecases.dtos.CriarPagamentoResponse;
import br.com.fiap.fiapeats.usecases.interfaces.in.pagamento.AtualizarPagamentoUseCase;
import br.com.fiap.fiapeats.usecases.interfaces.in.pagamento.CriarPagamentoUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

public class PagamentoControllerTest {

    @InjectMocks
    private PagamentoController pagamentoController;
    @Mock
    private  CriarPagamentoUseCase criarPagamentoUseCase;
    @Mock
    private  AtualizarPagamentoUseCase atualizarPagamentoUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarPagamentoComSucesso() {
        var idPedido = UUID.randomUUID();
        CriarPagamentoDTO criarPagamentoDTO = new CriarPagamentoDTO(idPedido, "http:pedido-notificacao");
        Pagamento pagamento = new Pagamento(idPedido, "http:pedido-notificacao", "codigoQR");

        when(criarPagamentoUseCase.criar(criarPagamentoDTO)).thenReturn(pagamento);

        CriarPagamentoResponse response = pagamentoController.criarPagamento(criarPagamentoDTO);

        assertNotNull(response);
        assertEquals(response.getCodigoQR(), "codigoQR");
        verify(criarPagamentoUseCase, times(1)).criar(criarPagamentoDTO);
    }

    @Test
    void deveAtualizarPagamentoComSucesso() {
        pagamentoController.atualizarPagamento("1", "topico-teste");

        verify(atualizarPagamentoUseCase, times(1)).atualizar(any(), any()) ;
    }
}
