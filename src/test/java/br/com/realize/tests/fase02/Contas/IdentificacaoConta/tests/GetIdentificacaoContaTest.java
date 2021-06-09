package br.com.realize.tests.fase02.Contas.IdentificacaoConta.tests;

import br.com.realize.runners.fase02;
import br.com.realize.suites.AllTests;
import br.com.realize.suites.Contract;
import br.com.realize.tests.fase02.CartaoCredito.ContasPagamentoPosPago.requests.GetContaPagamentoPosPagoRequest;
import br.com.realize.tests.fase02.CartaoCredito.ContasPagamentoPosPagoId.requests.GetContaPagamentoPosPagoIDRequest;
import br.com.realize.tests.fase02.Contas.IdentificacaoConta.requests.GetIdentificacaoContaRequest;
import br.com.realize.tests.base.tests.BaseTest;
import br.com.realize.suites.Healthcheck;
import br.com.realize.utils.Utils;
import com.github.javafaker.Faker;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.*;

@Epic("Fase 02")
@Feature("Contas")
@DisplayName("Identificação da Conta")

public class GetIdentificacaoContaTest extends BaseTest {

    GetIdentificacaoContaRequest getIdentificacaoContaRequest = new GetIdentificacaoContaRequest();
    String linkSelf = getIdentificacaoContaRequest.obterLinkSelf();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar o retorno do endpoint de informações da conta pelo enpoint de indentificação da conta")
    public void testValidarIdentificacaoConta() throws Exception {
        getIdentificacaoContaRequest.obterInformacoesContaPeloId()
                .then()
                .log().all()
                .statusCode(200);
    }
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({Contract.class, AllTests.class, fase02.class})
    @DisplayName("Validar a garantia do contrato do retorno da conta por ID")
    public void testGarantirContratosInformacoesConta() throws Exception {
        getIdentificacaoContaRequest.obterInformacoesContaPeloId()
                .then()
                .statusCode(200)
                .assertThat().body(matchesJsonSchema(
                new File(Utils.getContractsBasePath("fase02/Contas/IdentificacaoConta", "IdentificacaoConta"))));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar a obtenção de contas consentidas com ID inválido.")
    public void testIdInvalido() throws Exception {
        getIdentificacaoContaRequest.accountIdInvalido()
                .then()
                .statusCode(404)
                .body("errors[0].title", equalTo("O recurso solicitado não existe."))
                .body("errors[0].detail", equalTo("Nenhuma conta encontrada para o id e92c51df-b68c-4faa-9cbc-5555555555."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar o retorno 404 - Path da API inválido no endpoint de identificação da conta")
    public void testPathInvalido() throws Exception {
        getIdentificacaoContaRequest.pathInvalido()
                .then()
                .log().all()
                .statusCode(404)
                .body("errors[0].title", equalTo("O recurso solicitado não existe."))
                .body("errors[0].detail", equalTo("O endereço informado para esse endpoint está incorreto."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("405 - Validar o status code informando um método não suportado no endpoint de identificação da conta")
    public void testMetodoNaoSuportado() throws Exception {
        getIdentificacaoContaRequest.metodoNaoSuportado()
                .then()
                .log().all()
                .statusCode(405)
                .body("errors.title", hasItem("Ocorreu um erro inesperado ao processar sua requisição."))
                .body("errors.detail", hasItem("Request method 'POST' not supported"));
    }
}