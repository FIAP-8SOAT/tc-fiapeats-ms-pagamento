package br.com.fiap.fiapeats.external.config;

import br.com.fiap.fiapeats.FiapeatsApplication;
import br.com.fiap.fiapeats.adapter.controller.PagamentoController;
import br.com.fiap.fiapeats.adapter.gateway.integration.interfaces.PagamentoIntegration;
import br.com.fiap.fiapeats.adapter.gateway.integration.interfaces.PedidoIntegration;
import br.com.fiap.fiapeats.usecases.interfaces.in.pagamento.AtualizarPagamentoUseCase;
import br.com.fiap.fiapeats.usecases.interfaces.in.pagamento.CriarPagamentoUseCase;
import br.com.fiap.fiapeats.usecases.interfaces.out.pagamento.PagamentoGateway;
import br.com.fiap.fiapeats.usecases.interfaces.out.pedido.PedidoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class BeanConfigurationTest {

    private AnnotationConfigApplicationContext context;

    @BeforeEach
    void setUp() {
        context = new AnnotationConfigApplicationContext(FiapeatsApplication.class);
    }

    @Test
    void criarPagamentoUseCaseBeanIsLoaded() {
        CriarPagamentoUseCase bean = context.getBean(CriarPagamentoUseCase.class);
        assertThat(bean).isNotNull();
    }

    @Test
    void atualizarPagamentoUseCaseBeanIsLoaded() {
        AtualizarPagamentoUseCase bean = context.getBean(AtualizarPagamentoUseCase.class);
        assertThat(bean).isNotNull();
    }

    @Test
    void pagamentoGatewayBeanIsLoaded() {
        PagamentoGateway bean = context.getBean(PagamentoGateway.class);
        assertThat(bean).isNotNull();
    }

    @Test
    void pedidoGatewayBeanIsLoaded() {
        PedidoGateway bean = context.getBean(PedidoGateway.class);
        assertThat(bean).isNotNull();
    }

    @Test
    void pagamentoIntegrationBeanIsLoaded() {
        PagamentoIntegration bean = context.getBean(PagamentoIntegration.class);
        assertThat(bean).isNotNull();
    }

    @Test
    void pedidoIntegrationBeanIsLoaded() {
        PedidoIntegration bean = context.getBean(PedidoIntegration.class);
        assertThat(bean).isNotNull();
    }

    @Test
    void pagamentoControllerBeanIsLoaded() {
        PagamentoController bean = context.getBean(PagamentoController.class);
        assertThat(bean).isNotNull();
    }
}
