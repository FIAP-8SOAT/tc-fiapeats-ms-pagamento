package br.com.fiap.fiapeats.usecases.interfaces.in.pagamento;

import br.com.fiap.fiapeats.domain.entities.Pagamento;
import br.com.fiap.fiapeats.usecases.dtos.CriarPagamentoDTO;

public interface CriarPagamentoUseCase {
  Pagamento criar(CriarPagamentoDTO criarPagamentoDTO);
}
