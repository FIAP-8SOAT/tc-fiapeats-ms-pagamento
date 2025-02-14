package br.com.fiap.fiapeats.adapter.controller;

import br.com.fiap.fiapeats.adapter.presenters.PagamentoPresenter;
import br.com.fiap.fiapeats.domain.entities.Pagamento;
import br.com.fiap.fiapeats.usecases.dtos.*;
import br.com.fiap.fiapeats.usecases.interfaces.in.pagamento.AtualizarPagamentoUseCase;
import br.com.fiap.fiapeats.usecases.interfaces.in.pagamento.CriarPagamentoUseCase;

public class PagamentoController {

  private final CriarPagamentoUseCase criarPagamentoUseCase;
  private final AtualizarPagamentoUseCase atualizarPagamentoUseCase;

  public PagamentoController(
      CriarPagamentoUseCase criarPagamentoUseCase,
      AtualizarPagamentoUseCase atualizarPagamentoUseCase) {
    this.criarPagamentoUseCase = criarPagamentoUseCase;
    this.atualizarPagamentoUseCase = atualizarPagamentoUseCase;
  }

  public CriarPagamentoResponse criarPagamento(CriarPagamentoDTO criarPagamentoDTO) {
    Pagamento pagamento = criarPagamentoUseCase.criar(criarPagamentoDTO);

    return PagamentoPresenter.toCriarPagamentoResponse(pagamento);
  }

  public void atualizarPagamento(String idPedidoExterno, String topico) {
    atualizarPagamentoUseCase.atualizar(idPedidoExterno, topico);
  }
}
