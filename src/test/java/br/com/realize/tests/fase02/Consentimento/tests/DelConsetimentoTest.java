package br.com.realize.tests.fase02.Consentimento.tests;

import br.com.realize.runners.fase02;
import br.com.realize.suites.AllTests;
import br.com.realize.suites.Healthcheck;
import br.com.realize.tests.base.tests.BaseTest;
import br.com.realize.tests.fase02.Consentimento.requests.DelConsentimentoRequest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import java.util.concurrent.TimeUnit;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.*;

@Epic("Fase 02")
@Feature("Consentimento")
@DisplayName("Deletar consentimento")

public class DelConsetimentoTest extends BaseTest {

    String token = "004904950354";
    DelConsentimentoRequest delConsentimentoRequest = new DelConsentimentoRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar a exclusão de consentimento")
    public void testValidarExclusaoConsentimento() throws Exception {
//EXCLUIR O CONSENTIMENTO
        delConsentimentoRequest.deletarPedidoConsetimento()
                .then().log().body()
                .statusCode(204)
                .time(lessThan(4L), TimeUnit.SECONDS);
//VALIDAR QUE O CONSENTIMENTO FOI EXCLUÍDO. O STATUS DEVE SER "REJECTED".
         given()
                .header("Authorization", token)
                .contentType("application/json")
         .when()
                .get("/consents/v1/consents/" + delConsentimentoRequest.consentId)
         .then()
                .body("data.status", equalTo("REJECTED"));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Path da API inválido no endpoint de exclusão de consentimento")
    public void testPathInvalido() throws Exception {
        delConsentimentoRequest.pathInvalido()
                .then()
                .statusCode(404)
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body("errors.title", hasItem("O recurso solicitado não existe."))
                .body("errors.detail", hasItem("O endereço informado para esse endpoint está incorreto."));;;
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("405 - Validar o status code informando um método não suportado no endpoint de exclusão de consentimento")
    public void testMetodoNaoSuportado() throws Exception {
        delConsentimentoRequest.metodoNaoSuportado()
                .then()
                .statusCode(405)
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body("errors.title", hasItem("Ocorreu um erro inesperado ao processar sua requisição."))
                .body("errors.detail", hasItem("Request method 'PUT' not supported"));
    }
   /* @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar o retorno do endpoint de exclusão de consentimento quando informando um token inválido.")
    public void testTokenInvalido() throws Exception {
        delConsentimentoRequest.tokenInvalido()
                .then()
                .statusCode(401)
                .time(lessThan(4L), TimeUnit.SECONDS);
    }*/
    @Ignore
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar o retorno do endpoint de exclusão de consentimento quando informando a política de segurança é violada.")
    public void testPoliticaSegurancaViolada() throws Exception {
        delConsentimentoRequest.politicaSegurancaVioalada()
                .then()
                .statusCode(403)
                .time(lessThan(4L), TimeUnit.SECONDS);
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar o retorno do endpoint de exclusão de consentimento quando informando a política de segurança é violada.")
    public void testRecursoSolicitadoNaoExiste() throws Exception {
        delConsentimentoRequest.recursoNaoExiste()
                .then()
                .statusCode(404)
                .time(lessThan(4L), TimeUnit.SECONDS);
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar o retorno do endpoint de exclusão de consentimento quando informando um accept diferente.")
    public void testAcceptDiferente() throws Exception {
        delConsentimentoRequest.acceptDiferente()
                .then()
                .statusCode(406)
                .time(lessThan(4L), TimeUnit.SECONDS);
    }
    @Ignore
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar o retorno do endpoint de exclusão de consentimento quando muitas solicitações são feitas dentro de um determinado período.")
    public void testMuitasSolicitacoesFeitas() throws Exception {
        delConsentimentoRequest.muitasSolicitacoesFeitas()
                .then()
                .statusCode(429)
                .time(lessThan(4L), TimeUnit.SECONDS);
    }
    @Ignore
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar o retorno do endpoint de exclusão de consentimento quando ocorre erro de Gateway.")
    public void testErroGateway() throws Exception {
        delConsentimentoRequest.erroGateway()
                .then()
                .statusCode(500)
                .time(lessThan(4L), TimeUnit.SECONDS);
    }
}