package br.com.fiap.fiapeats.adapter.presenters;

import br.com.fiap.fiapeats.domain.entities.Pedido;
import br.com.fiap.fiapeats.domain.entities.Produto;
import br.com.fiap.fiapeats.usecases.dtos.CriarPagamentoDTO;

import java.util.List;

public class PedidoPresenter {

  public static Pedido toCriarPedido(CriarPagamentoDTO criarPagamentoDTO) {
    List<Produto> listaProdutos = criarPagamentoDTO.getProdutos().stream()
            .map(produtoOrigem -> new Produto(
                    produtoOrigem.getId(),
                    produtoOrigem.getNome(),
                    produtoOrigem.getDescricao(),
                    produtoOrigem.getValor(),
                    produtoOrigem.getCategoriaDescricao())
            ).toList();

    return new Pedido(criarPagamentoDTO.getIdPedido(), listaProdutos);
  }
}
