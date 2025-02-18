package br.com.fiap.fiapeats.external.config;

import br.com.fiap.fiapeats.adapter.controller.PagamentoController;
import br.com.fiap.fiapeats.adapter.gateway.integration.interfaces.PagamentoIntegration;
import br.com.fiap.fiapeats.adapter.gateway.integration.interfaces.PedidoIntegration;
import br.com.fiap.fiapeats.external.integration.feign.AutenticacaoFeign;
import br.com.fiap.fiapeats.external.integration.feign.PedidoFeign;
import br.com.fiap.fiapeats.external.integration.feign.PedidoPagamentoFeign;
import br.com.fiap.fiapeats.external.integration.mapper.PagamentoIntegrationMapper;
import br.com.fiap.fiapeats.external.integration.mapper.PagamentoPedidoIntegrationMapper;
import br.com.fiap.fiapeats.usecases.interfaces.in.pagamento.AtualizarPagamentoUseCase;
import br.com.fiap.fiapeats.usecases.interfaces.in.pagamento.CriarPagamentoUseCase;
import br.com.fiap.fiapeats.usecases.interfaces.out.pagamento.PagamentoGateway;
import br.com.fiap.fiapeats.usecases.interfaces.out.pedido.PedidoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BeanConfigurationTest {

    @InjectMocks
    private BeanConfiguration beanConfiguration;
    @Mock
    private PedidoGateway pedidoGateway;
    @Mock
    private PagamentoGateway pagamentoGateway;
    @Mock
    private PagamentoIntegration pagamentoIntegration;
    @Mock
    private PedidoIntegration pedidoIntegration;
    @Mock
    private AutenticacaoFeign autenticacaoFeign;
    @Mock
    private PedidoPagamentoFeign pedidoPagamentoFeign;
    @Mock
    private PagamentoIntegrationMapper pagamentoIntegrationMapper;
    @Mock
    private PagamentoPedidoIntegrationMapper pagamentoPedidoIntegrationMapper;
    @Mock
    private PedidoFeign pedidoFeign;
    @Mock
    private CriarPagamentoUseCase criarPagamentoUseCase;
    @Mock
    private AtualizarPagamentoUseCase atualizarPagamentoUseCase;

    @BeforeEach
    void setUp() {
        beanConfiguration = new BeanConfiguration();
    }

    @Test
    void deveCriarBeanCriarPagamentoUseCasePort() {
        CriarPagamentoUseCase useCase = beanConfiguration.criarPagamentoUseCasePort(pedidoGateway, pagamentoGateway);
        assertNotNull(useCase);
    }

    @Test
    void deveCriarBeanAtualizarPagamentoUseCasePort() {
        AtualizarPagamentoUseCase useCase = beanConfiguration.atualizarPagamentoUseCasePort(pedidoGateway, pagamentoGateway);
        assertNotNull(useCase);
    }

    @Test
    void deveCriarBeanPagamentoGateway() {
        PagamentoGateway gateway = beanConfiguration.pagamentoGateway(pagamentoIntegration);
        assertNotNull(gateway);
    }

    @Test
    void deveCriarBeanPedidoGateway() {
        PedidoGateway gateway = beanConfiguration.pedidoGateway(pedidoIntegration);
        assertNotNull(gateway);
    }

    @Test
    void deveCriarBeanPagamentoIntegration() {
        PagamentoIntegration integration = beanConfiguration.pagamentoIntegration(autenticacaoFeign, pedidoPagamentoFeign, pagamentoIntegrationMapper, pagamentoPedidoIntegrationMapper);
        assertNotNull(integration);
    }

    @Test
    void deveCriarBeanPedidoIntegration() {
        PedidoIntegration integration = beanConfiguration.pedidoIntegration(pedidoFeign);
        assertNotNull(integration);
    }

    @Test
    void deveCriarBeanPagamentoController() {
        PagamentoController controller = beanConfiguration.pagamentoController(criarPagamentoUseCase, atualizarPagamentoUseCase);
        assertNotNull(controller);
    }
}
