package br.com.realize.tests.fase03.pagamentos.consentimentoPagamentoPix.tests;

import br.com.realize.runners.fase03;
import br.com.realize.suites.AllTests;
import br.com.realize.suites.Healthcheck;
import br.com.realize.tests.base.tests.BaseTest;
import br.com.realize.tests.fase03.pagamentos.consentimentoPagamentoPix.requests.PostAutorizarConsentimentoPagamentoPixRequest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import java.util.concurrent.TimeUnit;

import static br.com.realize.tests.fase03.pagamentos.factory.AutorizacaoConsentimentoPagamentoPixDataFactory.consentIdParaAutorizarConsentimento;
import static br.com.realize.tests.fase03.pagamentos.factory.ConsentimentoPagamentoPixDataFactory.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.*;

@Epic("Fase 03")
@Feature("Pagamento")
@DisplayName("Autorização de consentimento de pagamento")

public class PostAutorizarConsentimentoPagamentoPixTest extends BaseTest {

    PostAutorizarConsentimentoPagamentoPixRequest postAutorizarConsentimentoPagamentoPixRequest = new PostAutorizarConsentimentoPagamentoPixRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Validar a autorização do consentimento de pagamento")
    public void testAutorizarConsentimento() throws Exception {
        postAutorizarConsentimentoPagamentoPixRequest.autorizarConsentimento()
                .then()
                .statusCode(200)
                .time(lessThan(10L), TimeUnit.SECONDS);
       given()
                .when()
                .get(urlConsetimentoPagamento + consentIdParaAutorizarConsentimento)
                .then()
                .body("data.status", equalTo("AUTHORISED"));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Validar a atualização dos dados da conta de débito")
    public void testAutorizarConsentimentoAutorizado() throws Exception {
        postAutorizarConsentimentoPagamentoPixRequest.autorizarConsentimentoAutorizado()
                .then()
                .statusCode(409)
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body("errors.title", hasItem("A ação não pôde ser executada pois o status do consentimento já foi alterado anteriormente."))
                .body("errors.detail", hasItem("Apenas é possível alterar um consentimento que se encontra no status AWAITING_AUTHORISATION. O status atual do consentimento é AUTHORISED."));
    }
}
