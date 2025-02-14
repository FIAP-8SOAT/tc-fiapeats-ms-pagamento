package br.com.fiap.fiapeats.external.config;

import org.junit.jupiter.api.Test;
import io.swagger.v3.oas.models.OpenAPI;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SwaggerConfigurationTest {

    private final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SwaggerConfiguration.class);

    @Test
    void openAPIBeanIsLoaded() {
        OpenAPI bean = context.getBean(OpenAPI.class);
        assertThat(bean).isNotNull();
        assertThat(bean.getInfo().getTitle()).isEqualTo("FiapEats");
        assertThat(bean.getInfo().getDescription()).isEqualTo("Documentação técnica para serviço de pagamento");
        assertThat(bean.getInfo().getVersion()).isEqualTo("1.0.0");
    }

    @Test
    void internalGroupedOpenApiBeanIsLoaded() {
        GroupedOpenApi bean = context.getBean(GroupedOpenApi.class);
        assertThat(bean).isNotNull();
        assertThat(bean.getGroup()).isEqualTo("interno");
        assertThat(bean.getPathsToMatch()).containsExactlyInAnyOrder("/pagamento/**");
    }
}
