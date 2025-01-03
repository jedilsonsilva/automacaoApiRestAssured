package br.com.realize.tests.fase02.Contas.Limites.tests;

import br.com.realize.runners.fase02;
import br.com.realize.suites.AllTests;
import br.com.realize.suites.Contract;
import br.com.realize.tests.fase02.Contas.Limites.requests.GetLimiteRequest;
import br.com.realize.tests.base.tests.BaseTest;
import br.com.realize.suites.Healthcheck;
import br.com.realize.tests.fase02.Contas.ListaContas.requests.GetContaRequest;
import br.com.realize.utils.Utils;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.*;

@Epic("Fase 02")
@Feature("Contas")
@DisplayName("Limite da Conta")
public class GetLimiteTest extends BaseTest {
    String AccountIDInvalido = GetContaRequest.AccountIDInvalido;
    GetLimiteRequest getLimiteRequest = new GetLimiteRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class})
    @DisplayName("Validar o retorno das informações do endpoint de limites da conta.")
    public void testObterLimites() throws Exception {
        String linkSelf = getLimiteRequest.obterLinkSelf();
        getLimiteRequest.obterLimitesConta()
                .then()
                .statusCode(200)
                .body("data.overdraftContractedLimit", is(0))
                .body("data.overdraftContractedLimitCurrency", is("BRL"))
                .body("data.overdraftUsedLimit", is(0))
                .body("data.overdraftUsedLimitCurrency", is("BRL"))
                .body("data.unarrangedOverdraftAmount", is(0))
                .body("data.unarrangedOverdraftAmountCurrency", is("BRL"))
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body("meta.totalPages", greaterThan(0))
                .body("meta.totalRecords", greaterThan(0))
                .body("links.self", is(linkSelf));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class})
    @DisplayName("Validar o retorno 404 - Obtenção dos limites da conta com ID inválido.")
    public void testIdInvalidoLimite() throws Exception {
        getLimiteRequest.idInvalidoLimite()
                .then()
                .statusCode(404)
                .body("errors.title", hasItem("O recurso solicitado não existe."))
                .body("errors.detail", hasItem("Nenhuma conta encontrada para o id " + AccountIDInvalido + "."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar o retorno 404 - Path da API inválido no endpoint de limites da conta")
    public void testPathInvalido() throws Exception {
        getLimiteRequest.pathInvalido()
                .then()
                .statusCode(404)
                .body("errors[0].title", equalTo("O recurso solicitado não existe."))
                .body("errors[0].detail", equalTo("O endereço informado para esse endpoint está incorreto."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar o retorno 405 - Método não suportado no endpoint de limites da conta")
    public void testMetodoNaoSuportado() throws Exception {
        getLimiteRequest.metodoNaoSuportado()
                .then()
                .statusCode(405)
                .body("errors.title", hasItem("Ocorreu um erro inesperado ao processar sua requisição."))
                .body("errors.detail", hasItem("Request method 'POST' not supported"));
    }
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({Contract.class, AllTests.class})
    @DisplayName("Validar a garantia do contrato de retorno do limite das contas")
    public void testGarantirContratosLimitesConta() throws Exception {
        getLimiteRequest.obterLimitesConta().then()
                .statusCode(200)
                .assertThat().body(matchesJsonSchema(
                new File(Utils.getContractsBasePath("fase02/Contas/Limites", "ObterLimiteConta"))));
    }
}