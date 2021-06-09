package br.com.realize.tests.fase02.CartaoCredito.ContasPagamentoPosPagoId.tests;

import br.com.realize.runners.fase01;
import br.com.realize.runners.fase02;
import br.com.realize.suites.AllTests;
import br.com.realize.suites.Contract;
import br.com.realize.suites.Healthcheck;
import br.com.realize.tests.base.tests.BaseTest;
import br.com.realize.tests.fase02.CartaoCredito.ContasPagamentoPosPago.requests.GetContaPagamentoPosPagoRequest;
import br.com.realize.tests.fase02.CartaoCredito.ContasPagamentoPosPagoId.requests.GetContaPagamentoPosPagoIDRequest;
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

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.*;

@Epic("Fase 02")
@Feature("Cartão de Crédito")
@DisplayName("Lista de contas pagamento pós-pago com ID")

public class GetContaPagamentoPosPagoIDTest extends BaseTest {
    GetContaPagamentoPosPagoIDRequest getContaPagamentoPosPagoIDRequest = new GetContaPagamentoPosPagoIDRequest();
    String linkSelf = getContaPagamentoPosPagoIDRequest.obterLinkSelfContaPagamentoPosPagoID();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar o retorno do endpoint de contas de pagamento pós-pago com ID")
    public void testValidarinformacoesContaPosPagoID() throws Exception {
        getContaPagamentoPosPagoIDRequest.retornaContasPagamentoPosPagoID()
                .then()
                .log().all()
                .statusCode(200);
    }
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({Contract.class, AllTests.class, fase02.class})
    @DisplayName("Validar a garantia do contrato do retorno da lista de contas pagamento pós-pago com ID")
    public void testGarantirContratosContaPagamentoPosPagoID() throws Exception {
        getContaPagamentoPosPagoIDRequest.retornaContasPagamentoPosPagoID()
                .then()
                .statusCode(200)
                .assertThat().body(matchesJsonSchema(
                new File(Utils.getContractsBasePath("fase02/CartaoCredito/ContasPagamentoPosPagoID", "ContaPagamentoPosPagoID"))));
    }

   @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar comportamento da API quando informado CPF sem conta.")
    public void testCpfSemConta() throws Exception {
       getContaPagamentoPosPagoIDRequest.cpfSemConta()
                .then()
                .statusCode(200)
                .body("data", is(empty()));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar comportamento da API quando informado CPF inválido.")
    public void testCpfInvalido() throws Exception {
        getContaPagamentoPosPagoIDRequest.cpfInvalido()
                .then()
                .statusCode(400)
                .body("errors[0].title", equalTo("A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL."))
                .body("errors[0].detail", equalTo("O CPF informado é inválido."));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar o retorno 404 - Path da API inválido no endpoint de Conta de pagamento pós-pago por ID")
    public void testPathInvalido() throws Exception {
        getContaPagamentoPosPagoIDRequest.pathInvalido()
                .then()
                .log().all()
                .statusCode(404)
                .body("errors[0].title", equalTo("O recurso solicitado não existe."))
                .body("errors[0].detail", equalTo("O endereço informado para esse endpoint está incorreto."));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("405 - Validar o status code informando um método não suportado no endpoint de Conta de pagamento pós-pago por ID")
    public void testMetodoNaoSuportado() throws Exception {
        getContaPagamentoPosPagoIDRequest.metodoNaoSuportado()
                .then()
                .log().all()
                .statusCode(405)
                .body("errors.title", hasItem("Ocorreu um erro inesperado ao processar sua requisição."))
                .body("errors.detail", hasItem("Request method 'POST' not supported"));
    }

}