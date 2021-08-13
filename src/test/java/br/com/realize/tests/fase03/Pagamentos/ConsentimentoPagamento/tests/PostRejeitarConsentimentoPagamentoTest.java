package br.com.realize.tests.fase03.Pagamentos.ConsentimentoPagamento.tests;

import br.com.realize.runners.fase03;
import br.com.realize.suites.AllTests;
import br.com.realize.suites.Healthcheck;
import br.com.realize.tests.base.tests.BaseTest;
import br.com.realize.tests.fase03.Pagamentos.ConsentimentoPagamento.requests.PostRejeitarConsentimentoPagamentoRequest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.*;

@Epic("Fase 03")
@Feature("Pagamento")
@DisplayName("Autorização de consentimento de pagamento")

public class PostRejeitarConsentimentoPagamentoTest extends BaseTest {

    PostRejeitarConsentimentoPagamentoRequest postRejeitarConsentimentoPagamentoRequest = new PostRejeitarConsentimentoPagamentoRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Validar a rejeição do consentimento de pagamento")
    public void testRejeitarConsentimento() throws Exception {
        postRejeitarConsentimentoPagamentoRequest.rejeitarConsentimentoPagamento()
                .then()
                .statusCode(200)
                .time(lessThan(4L), TimeUnit.SECONDS);
        given()
                .when()
                .get(postRejeitarConsentimentoPagamentoRequest.url
                        + postRejeitarConsentimentoPagamentoRequest.consentId)
                .then()
                .body("data.status", equalTo("REJECTED"));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Validar a rejeição de consentimento de pagamento que já esteja rejeitado")
    public void testRejeitarConsentimentoRejeitado() throws Exception {
        postRejeitarConsentimentoPagamentoRequest.rejeitarConsentimentoPagamentoRejeitado()
                .then()
                .statusCode(409)
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body("errors.title", hasItem("A ação não pôde ser executada pois o status do consentimento já foi alterado anteriormente."))
                .body("errors.detail", hasItem("Apenas é possível alterar um consentimento que se encontra no status \"AWAITING_AUTHORISATION\". O status atual do consentimento é REJECTED."));
    }
}
