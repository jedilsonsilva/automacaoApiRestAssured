package br.com.realize.tests.fase02.CartaoCredito.LimitesCreditoContaPosPago.tests;

import br.com.realize.runners.fase01;
import br.com.realize.runners.fase02;
import br.com.realize.suites.AllTests;
import br.com.realize.suites.Contract;
import br.com.realize.suites.Healthcheck;
import br.com.realize.tests.base.tests.BaseTest;
import br.com.realize.tests.fase02.CartaoCredito.ContasPagamentoPosPago.requests.GetContaPagamentoPosPagoRequest;
import br.com.realize.tests.fase02.CartaoCredito.LimitesCreditoContaPosPago.requests.GetLimiteCartaoCreditoRequest;
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
@Feature("Cartão de Crédito")
@DisplayName("Limite Cartão de Crédito")

public class GetLimiteCartaoCreditoTest extends BaseTest {

    GetLimiteCartaoCreditoRequest getLimiteCartaoCreditoRequest = new GetLimiteCartaoCreditoRequest();
    String linkSelf = getLimiteCartaoCreditoRequest.obterLinkSelf();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar o retorno do endpoint de limites de cartão de crédito.")
    public void testValidarInformacoesLimiteCartaoCredito() throws Exception {
        getLimiteCartaoCreditoRequest.retornaLimitesCartaoCredito()
                .then()
                .statusCode(200)
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body("meta.totalPages", greaterThan(0))
                .body("meta.totalRecords", greaterThan(0))
                .body("links.self", is(linkSelf));
    }
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({Contract.class, AllTests.class, fase02.class})
    @DisplayName("Validar a garantia do contrato do retorno de limites de cartão de crédito")
    public void testGarantirContratosLimitesCartaoCredito() throws Exception {
        getLimiteCartaoCreditoRequest.retornaLimitesCartaoCredito()
                .then()
                .statusCode(200)
                .assertThat().body(matchesJsonSchema(
                new File(Utils.getContractsBasePath("fase02/CartaoCredito/LimitesCreditoContaPosPago", "LimiteCartaoCredito"))));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar CPF inválido.")
    public void testCpfInvalido() throws Exception {
        getLimiteCartaoCreditoRequest.cpfInvalido()
                .then()
                .statusCode(400)
                .body("errors[0].title", equalTo("A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL."))
                .body("errors[0].detail", equalTo("O CPF informado é inválido."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar a consulta informando um CPF sem cartão de crédito.")
    public void testCpfSemCartaoCredito() throws Exception {
        getLimiteCartaoCreditoRequest.cpfSemCartaoCredito()
                .then()
                .statusCode(404)
                .body("errors[0].title", equalTo("O recurso solicitado não existe."))
                .body("errors[0].detail", equalTo("A conta do cartão não foi encontrada."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar a consulta informando um CPF e um CreditCardAccountId diferente.")
    public void testcontaDiferenteCreditCardAccountId() throws Exception {
        getLimiteCartaoCreditoRequest.contaDiferenteCreditCardAccountId()
                .then()
                .statusCode(404)
                .body("errors[0].title", equalTo("O recurso solicitado não existe."))
                .body("errors[0].detail", equalTo("A conta do cartão não foi encontrada."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar o retorno 404 - Path da API inválido no endpoint de limites de cartão de crédito.")
    public void testPathInvalido() throws Exception {
        getLimiteCartaoCreditoRequest.pathInvalido()
                .then()
                .statusCode(404)
                .body("errors[0].title", equalTo("O recurso solicitado não existe."))
                .body("errors[0].detail", equalTo("O endereço informado para esse endpoint está incorreto."));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("405 - Validar o status code informando um método não suportado no endpoint de de limites de cartão de crédito.")
    public void testMetodoNaoSuportado() throws Exception {
        getLimiteCartaoCreditoRequest.metodoNaoSuportado()
                .then()
                .statusCode(405)
                .body("errors.title", hasItem("Ocorreu um erro inesperado ao processar sua requisição."))
                .body("errors.detail", hasItem("Request method 'POST' not supported"));
    }

}