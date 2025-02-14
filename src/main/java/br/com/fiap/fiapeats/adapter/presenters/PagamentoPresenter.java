package br.com.fiap.fiapeats.adapter.presenters;

import br.com.fiap.fiapeats.domain.entities.Pagamento;
import br.com.fiap.fiapeats.usecases.dtos.CriarPagamentoResponse;

public class PagamentoPresenter {

  public static CriarPagamentoResponse toCriarPagamentoResponse(Pagamento pagamento) {
    return new CriarPagamentoResponse(pagamento.getCodigoQr());
  }
}
