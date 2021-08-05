package br.com.realize.tests.fase02.CartaoCredito.ApiPrivateConta.tests;

import br.com.realize.runners.fase02;
import br.com.realize.suites.AllTests;
import br.com.realize.suites.Contract;
import br.com.realize.suites.Healthcheck;
import br.com.realize.tests.fase02.CartaoCredito.ApiPrivateConta.requests.GetPrivateContaCartaoCompraCreditoRequest;
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
@DisplayName("API Private Conta Cartão Compra/Crédito")

public class GetPrivateContaCartaoCompraCreditoTest {
    GetPrivateContaCartaoCompraCreditoRequest getPrivateContaCartaoCompraCreditoRequest = new GetPrivateContaCartaoCompraCreditoRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar o retorno do endpoint de cartão Bandeira própria - CCR")
    public void testValidarCartaoBandeiraPropria() throws Exception {
        getPrivateContaCartaoCompraCreditoRequest.retornaCartaoBandeiraPropria()
                .then()
                .statusCode(200)
                .time(lessThan(10L), TimeUnit.SECONDS)
                .body("produto", equalTo("CCR"))
                .body("cartao", notNullValue());
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar o retorno do endpoint de cartão Meu Cartão - CBR")
    public void testValidarCartaoMeuCartao() throws Exception {
        getPrivateContaCartaoCompraCreditoRequest.retornaCartaoMeuCartao()
                .then()
                .statusCode(200)
                .time(lessThan(10L), TimeUnit.SECONDS)
                .body("produto", equalTo("CBR"))
                .body("cartao", notNullValue());
    }
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({Contract.class, AllTests.class, fase02.class})
    @DisplayName("Validar a garantia do contrato do retorno da API private conta cartão de compra/crédito")
    public void testGarantirContratosContaIdentificadaPorCreditCardAccountId() throws Exception {
        getPrivateContaCartaoCompraCreditoRequest.retornaCartaoMeuCartao()
                .then()
                .statusCode(200)
                .time(lessThan(10L), TimeUnit.SECONDS)
                .assertThat().body(matchesJsonSchema(
                new File(Utils.getContractsBasePath("fase02/CartaoCredito/ApiPrivateConta", "PrivateContaCartaoCompraCredito"))));
    }

   @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar comportamento da API quando informado CPF sem conta.")
    public void testCpfSemConta() throws Exception {
       getPrivateContaCartaoCompraCreditoRequest.cpfSemConta()
                .then()
                .statusCode(404)
               .time(lessThan(10L), TimeUnit.SECONDS);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar comportamento da API quando informado CPF inválido.")
    public void testCpfInvalido() throws Exception {
        getPrivateContaCartaoCompraCreditoRequest.cpfInvalido()
                .then()
                .statusCode(400)
                .time(lessThan(10L), TimeUnit.SECONDS);
    }

  /*  @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar o retorno 404 - Path da API inválido no endpoint API private conta cartão de compra/crédito")
    public void testPathInvalido() throws Exception {
        getPrivateContaCartaoCompraCreditoRequest.pathInvalido()
                .then()
                .statusCode(404);
    }*/
}