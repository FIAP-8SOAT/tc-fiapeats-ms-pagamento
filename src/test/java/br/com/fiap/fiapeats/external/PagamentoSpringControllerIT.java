//package br.com.fiap.fiapeats.external;
//
//import br.com.fiap.fiapeats.external.api.contracts.request.CriarPagamentoRequest;
//import org.junit.jupiter.api.*;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.TestPropertySource;
//
//import java.util.UUID;
//
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.Matchers.notNullValue;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class PagamentoSpringControllerIT {
//
//    @LocalServerPort
//    private int port;
//
//    private CriarPagamentoRequest criarPagamentoRequest;
//
//    @BeforeEach
//    void setUp() {
//        criarPagamentoRequest = new CriarPagamentoRequest(UUID.fromString("d212192c-8155-440a-9eda-3d77732458bb"), "https://34f9-45-173-179-9.ngrok-free.app/fiapeats/pagamento/notificacao");
//    }
//
//    @Test
//    @Order(1)
//    void deveCriarCodigoPagamentoComSucesso() {
//        given()
//                .port(port)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .body(criarPagamentoRequest)
//                .when()
//                .post("/fiapeats/pagamento")
//                .then()
//                .statusCode(201)
//                .body("codigoQR", notNullValue());
//    }
//
//    @Test
//    @Order(2)
//    void deveAtualizarPagamentoComSucesso() {
//        given()
//                .port(port)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .queryParam("topic", "merchant_order")
//                .queryParam("id", "28237058216")
//                .when()
//                .post("/fiapeats/pagamento/notificacao")
//                .then()
//                .statusCode(200);
//    }
//}
