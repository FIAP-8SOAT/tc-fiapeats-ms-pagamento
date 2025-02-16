package br.com.fiap.fiapeats.usecases.pagamento;

import br.com.fiap.fiapeats.domain.entities.Pagamento;
import br.com.fiap.fiapeats.external.config.BeanConfiguration;
import br.com.fiap.fiapeats.usecases.dtos.CriarPagamentoDTO;
import br.com.fiap.fiapeats.usecases.exceptions.NotFoundException;
import br.com.fiap.fiapeats.usecases.interfaces.in.pagamento.CriarPagamentoUseCase;
import br.com.fiap.fiapeats.usecases.interfaces.out.pagamento.PagamentoGateway;
import br.com.fiap.fiapeats.usecases.interfaces.out.pedido.PedidoGateway;
import org.springframework.beans.factory.annotation.Value;

public class CriarPagamentoUseCaseImpl implements CriarPagamentoUseCase {

  @Value("${url.notificacao}")
  private String urlNotificacao;
  private final PedidoGateway pedidoGateway;
  private final PagamentoGateway pagamentoGateway;

  public CriarPagamentoUseCaseImpl(
      PedidoGateway pedidoGateway, PagamentoGateway pagamentoGateway) {
    this.pedidoGateway = pedidoGateway;
    this.pagamentoGateway = pagamentoGateway;
  }

  @Override
  public Pagamento criar(CriarPagamentoDTO criarPagamentoDTO) {
    var pedido = pedidoGateway.consultar(criarPagamentoDTO.getIdPedido().toString());
    if (pedido == null) {
      throw new NotFoundException("Id pedido não encontrado");
    }

    return pagamentoGateway.criar(
            pedido,
            new Pagamento(
                    criarPagamentoDTO.getIdPedido(), getUrlNotificacao(criarPagamentoDTO), null));
  }

  public String getUrlNotificacao(CriarPagamentoDTO criarPagamentoDTO){
      if (criarPagamentoDTO.getUrlNotificacao() == null){
          return urlNotificacao;
      }
      return criarPagamentoDTO.getUrlNotificacao();
  }
}
