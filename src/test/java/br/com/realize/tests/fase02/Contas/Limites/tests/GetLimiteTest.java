package br.com.realize.tests.fase02.Contas.Limites.tests;

import br.com.realize.suites.AllTests;
import br.com.realize.suites.Contract;
import br.com.realize.tests.fase02.Contas.Limites.requests.GetLimiteRequest;
import br.com.realize.tests.base.tests.BaseTest;
import br.com.realize.suites.Healthcheck;
import br.com.realize.tests.fase02.Contas.ListaContas.requests.GetContaRequest;
import br.com.realize.utils.Utils;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.*;

@Feature("Limites")
public class GetLimiteTest extends BaseTest {
    String AccountIDInvalido = GetContaRequest.AccountIDInvalido;
    GetLimiteRequest getLimiteRequest = new GetLimiteRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class})
    @DisplayName("Validar a obtenção dos limites da conta.")
    public void testObterLimites() throws Exception {
        String linkSelf = getLimiteRequest.obterLinkSelfLimitesConta();
        getLimiteRequest.obterLimitesConta()
                .then()
                .log().all()
                .statusCode(200)
                .body("data.overdraftLimitContracted", is(0))
                .body("data.overdraftLimitUsed", is(0))
                .body("meta.totalRecords", is(1))
                .body("meta.totalPages", is(1))
                .body("links.self", equalTo(linkSelf));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class})
    @DisplayName("Validar a obtenção dos limites da conta com ID inválido.")
    public void testIdInvalidoLimite() throws Exception {
        getLimiteRequest.idInvalidoLimite()
                .then()
                .statusCode(404)
                .body("errors.title", hasItem("O recurso solicitado não existe."))
                .body("errors.detail", hasItem("Nenhuma conta encontrada para o id " + AccountIDInvalido + "."));//CRIAR UMA VARIAVEL PARA O ID INVALIDO
    }
//VALIDAÇÕES DOS STATUS CODE DE ERRO

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class})
    @DisplayName("405 - Validar o status code informando um método não suportado na obtenção de limites da conta")
    public void testMetodoNaoSuportadoLimites() throws Exception {
        getLimiteRequest.metodoNaoSuportadoLimites()
                .then()
                .log().all()
                .statusCode(405)
                .body("errors.title", hasItem("correu um erro inesperado ao processar sua requisição."))
                .body("errors.detail", hasItem("Request method 'POST' not supported"));
    }
//GARANTIA DO CONTRATO
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({Contract.class, AllTests.class})
    @DisplayName("Validar a garantia do retorno do limite das contas")
    public void testGarantirContratosLimitesConta() throws Exception {
        getLimiteRequest.obterLimitesConta().then()
                .statusCode(200)
                .assertThat().body(matchesJsonSchema(
                new File(Utils.getContractsBasePath("Fase02/Contas/Limites", "ObterLimiteConta"))));
    }

}