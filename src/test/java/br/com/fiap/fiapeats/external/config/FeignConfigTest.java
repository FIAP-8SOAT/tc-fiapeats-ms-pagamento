package br.com.fiap.fiapeats.external.config;

import br.com.fiap.fiapeats.FiapeatsApplication;
import br.com.fiap.fiapeats.usecases.interfaces.in.pagamento.CriarPagamentoUseCase;
import feign.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class FeignConfigTest {

    private AnnotationConfigApplicationContext context;

    @BeforeEach
    void setUp() {
        context = new AnnotationConfigApplicationContext(FeignConfig.class);
    }

    @Test
    void testFeignClientBeanIsLoaded() {
        Client bean = context.getBean(Client.class);
        assertThat(bean).isNotNull();
    }
}
