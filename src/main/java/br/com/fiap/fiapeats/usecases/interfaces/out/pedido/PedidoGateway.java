package br.com.fiap.fiapeats.usecases.interfaces.out.pedido;

import br.com.fiap.fiapeats.domain.entities.Pedido;
import br.com.fiap.fiapeats.domain.enums.StatusPagamento;
import org.springframework.http.HttpStatusCode;

public interface PedidoGateway {

    Pedido consultar(String idPedido);

    void atualizarStatusPagamento(String idPedido, StatusPagamento statusPagamento);

}
