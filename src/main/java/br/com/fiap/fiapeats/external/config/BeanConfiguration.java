package br.com.fiap.fiapeats.external.config;

import br.com.fiap.fiapeats.adapter.controller.PagamentoController;
import br.com.fiap.fiapeats.adapter.gateway.integration.impl.PagamentoGatewayImpl;
import br.com.fiap.fiapeats.adapter.gateway.integration.interfaces.PagamentoIntegration;
import br.com.fiap.fiapeats.external.integration.feign.AutenticacaoFeign;
import br.com.fiap.fiapeats.external.integration.feign.PedidoPagamentoFeign;
import br.com.fiap.fiapeats.external.integration.impl.PagamentoIntegrationImpl;
import br.com.fiap.fiapeats.external.integration.mapper.PagamentoIntegrationMapper;
import br.com.fiap.fiapeats.external.integration.mapper.PagamentoPedidoIntegrationMapper;
import br.com.fiap.fiapeats.usecases.interfaces.in.pagamento.AtualizarPagamentoUseCase;
import br.com.fiap.fiapeats.usecases.interfaces.in.pagamento.CriarPagamentoUseCase;
import br.com.fiap.fiapeats.usecases.interfaces.out.pagamento.PagamentoGateway;
import br.com.fiap.fiapeats.usecases.interfaces.out.pedido.PedidoGateway;
import br.com.fiap.fiapeats.usecases.pagamento.AtualizarPagamentoUseCaseImpl;
import br.com.fiap.fiapeats.usecases.pagamento.CriarPagamentoUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

  @Bean
  public CriarPagamentoUseCase criarPagamentoUseCasePort(
          PedidoGateway pedidoGateway, PagamentoGateway pagamentoGateway) {
    return new CriarPagamentoUseCaseImpl(pedidoGateway, pagamentoGateway);
  }

  @Bean
  public AtualizarPagamentoUseCase atualizarPagamentoUseCasePort(
      PedidoGateway pedidoGateway, PagamentoGateway pagamentoGateway) {
    return new AtualizarPagamentoUseCaseImpl(pedidoGateway, pagamentoGateway);
  }

  @Bean
  public PagamentoGateway pagamentoGateway(PagamentoIntegration pagamentoIntegration) {
    return new PagamentoGatewayImpl(pagamentoIntegration);
  }

  @Bean
  public PagamentoIntegration pagamentoIntegration(
      AutenticacaoFeign autenticacaoFeign,
      PedidoPagamentoFeign pedidoPagamentoFeign,
      PagamentoIntegrationMapper pagamentoIntegrationMapper,
      PagamentoPedidoIntegrationMapper pedidoIntegrationMapper) {
    return new PagamentoIntegrationImpl(
        autenticacaoFeign, pedidoPagamentoFeign, pagamentoIntegrationMapper, pedidoIntegrationMapper);
  }

  @Bean
  public PagamentoController pagamentoController(
      CriarPagamentoUseCase criarPagamentoUseCase,
      AtualizarPagamentoUseCase atualizarPagamentoUseCase) {
    return new PagamentoController(criarPagamentoUseCase, atualizarPagamentoUseCase);
  }
}
