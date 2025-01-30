package br.com.fiap.fiapeats.external.integration.impl;

import br.com.fiap.fiapeats.adapter.gateway.integration.interfaces.PedidoIntegration;
import br.com.fiap.fiapeats.domain.entities.Pedido;
import br.com.fiap.fiapeats.domain.utils.Constants;
import br.com.fiap.fiapeats.external.integration.feign.PedidoFeign;
import br.com.fiap.fiapeats.external.integration.mapper.PedidoIntegrationMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;

@Slf4j
public class PedidoIntegrationImpl implements PedidoIntegration {

  private final PedidoFeign pedidoFeign;
  private final PedidoIntegrationMapper mapper;

  public PedidoIntegrationImpl(PedidoFeign pedidoFeign, PedidoIntegrationMapper pedidoIntegrationMapper) {
    this.pedidoFeign = pedidoFeign;
    this.mapper = pedidoIntegrationMapper;
  }


  @Override
  public Pedido consultarPedido(String idPedido) {
    log.info(
            "correlationId={"
                    + ThreadContext.get(Constants.CORRELATION_ID)
                    + "} "
                    + "[PedidoIntegrationImpl-consultarPedido] ");

    return mapper.toPedido(pedidoFeign.consultar(idPedido).orElse(null));
  }
}
