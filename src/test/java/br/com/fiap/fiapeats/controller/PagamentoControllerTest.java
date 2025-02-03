package br.com.fiap.fiapeats.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import br.com.fiap.fiapeats.adapter.controller.PagamentoController;
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

import java.util.UUID;

public class PagamentoControllerTest {

    @InjectMocks
    private PagamentoController pagamentoController;
    @Mock
    private  CriarPagamentoUseCase criarPagamentoUseCase;
    @Mock
    private  AtualizarPagamentoUseCase atualizarPagamentoUseCase;
    private Pagamento pagamento;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pagamento = new Pagamento(UUID.randomUUID(), "http:pedido-notificacao", "codigoQR");
    }

    @Test
    void deveCriarPagamentoComSucesso() {
        CriarPagamentoDTO criarPagamentoDTO = new CriarPagamentoDTO(UUID.randomUUID(), "http:pedido-notificacao");

        when(criarPagamentoUseCase.criar(criarPagamentoDTO)).thenReturn(pagamento);

        CriarPagamentoResponse response = pagamentoController.criarPagamento(criarPagamentoDTO);

        assertThat(response).isNotNull();
        assertThat(response.getCodigoQR()).isEqualTo("codigoQR");
        verify(criarPagamentoUseCase, times(1)).criar(criarPagamentoDTO);
    }

    @Test
    void deveAtualizarPagamentoComSucesso() {
        pagamentoController.atualizarPagamento("1", "topico-teste");

        verify(atualizarPagamentoUseCase, times(1)).atualizar(any(), any()) ;
    }
}
