package br.com.fiap.fiapeats.external.integration.mapper;

import br.com.fiap.fiapeats.domain.entities.Pedido;
import br.com.fiap.fiapeats.domain.entities.Produto;
import br.com.fiap.fiapeats.external.integration.feign.response.PedidoResponse;

import java.util.List;

public class PedidoIntegrationMapper {

    public static Pedido toPedido(PedidoResponse pedidoResponse) {
        List<Produto> listaProdutos = pedidoResponse.getProdutos().stream()
                .map(produtoOrigem -> new Produto(
                        produtoOrigem.getId(),
                        produtoOrigem.getNome(),
                        produtoOrigem.getDescricao(),
                        produtoOrigem.getValor(),
                        produtoOrigem.getCategoria().getDescricao()
                ))
                .toList();

        return new Pedido(pedidoResponse.getId(), listaProdutos);
    }

}
