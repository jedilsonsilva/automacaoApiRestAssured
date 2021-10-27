package br.com.realize.tests.fase02.CartaoCredito.ContaIdentificadaPorCreditCardAccountId.tests;

import br.com.realize.runners.fase02;
import br.com.realize.suites.AllTests;
import br.com.realize.suites.Contract;
import br.com.realize.suites.Healthcheck;
import br.com.realize.tests.base.tests.BaseTest;
import br.com.realize.tests.fase02.CartaoCredito.ContaIdentificadaPorCreditCardAccountId.requests.GetContaIdentificadaPorCreditCardAccountIdRequest;
import br.com.realize.utils.Utils;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import java.io.File;
import java.util.concurrent.TimeUnit;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.*;

@Epic("Fase 02")
@Feature("Cartão de Crédito")
@DisplayName("Identificação da conta identificada por creditCardAccountId")

public class GetContaIdentificadaPorCreditCardAccountIdTest extends BaseTest {
    GetContaIdentificadaPorCreditCardAccountIdRequest getContaIdentificadaPorCreditCardAccountIdRequest = new GetContaIdentificadaPorCreditCardAccountIdRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar o retorno do endpoint de identificação da conta identificada por creditCardAccountId")
    public void testValidarContaIdentificadaPorCreditCardAccountId() throws Exception {
        String linkSelf = getContaIdentificadaPorCreditCardAccountIdRequest.obterLinkSelfContaIdentificadaPorCreditCardAccountId();
        getContaIdentificadaPorCreditCardAccountIdRequest.retornaContaIdentificadaPorCreditCardAccountId()
                .then()
                .statusCode(200)
                .time(lessThan(10L), TimeUnit.SECONDS)
                .body("meta.totalPages", greaterThan(0))
                .body("meta.totalRecords", greaterThan(0))
                .body("links.self", is(linkSelf));;
    }
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({Contract.class, AllTests.class, fase02.class})
    @DisplayName("Validar a garantia do contrato do retorno de identificação da conta identificada por creditCardAccountId")
    public void testGarantirContratosContaIdentificadaPorCreditCardAccountId() throws Exception {
        getContaIdentificadaPorCreditCardAccountIdRequest.retornaContaIdentificadaPorCreditCardAccountId()
                .then()
                .statusCode(200)
                .assertThat().body(matchesJsonSchema(
                new File(Utils.getContractsBasePath("fase02/CartaoCredito/ContaIdentificadaPorCreditCardAccountId", "ContaIdentificadaPorCreditCardAccountId"))));
    }
   @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar comportamento da API quando informado CPF sem conta.")
    public void testCpfSemConta() throws Exception {
       getContaIdentificadaPorCreditCardAccountIdRequest.cpfSemConta()
                .then()
                .statusCode(200)
                //.time(lessThan(10L), TimeUnit.SECONDS)
                .body("data", is(empty()));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar comportamento da API quando informado CPF inválido.")
    public void testCpfInvalido() throws Exception {
        getContaIdentificadaPorCreditCardAccountIdRequest.cpfInvalido()
                .then()
                .statusCode(400)
                .time(lessThan(10L), TimeUnit.SECONDS)
                .body("errors[0].title", equalTo("A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL."))
                .body("errors[0].detail", equalTo("O CPF informado é inválido."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar comportamento da API quando informados CPF e creditCardAccountId de contas diferentes.")
    public void testCPfDiferenteCreditCardDiferente() throws Exception {
        getContaIdentificadaPorCreditCardAccountIdRequest.cpfdiferenteCreditCard()
                .then()
                .statusCode(404)
                .time(lessThan(10L), TimeUnit.SECONDS)
                .body("errors[0].title", equalTo("O recurso solicitado não existe."))
                .body("errors[0].detail", equalTo("A conta do cartão não foi encontrada."));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar o retorno 404 - Path da API inválido no endpoint de identificação da conta identificada por creditCardAccountId")
    public void testPathInvalido() throws Exception {
        getContaIdentificadaPorCreditCardAccountIdRequest.pathInvalido()
                .then()
                .statusCode(404)
                .time(lessThan(10L), TimeUnit.SECONDS)
                .body("errors[0].title", equalTo("O recurso solicitado não existe."))
                .body("errors[0].detail", equalTo("O endereço informado para esse endpoint está incorreto."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar o retorno 404 - CreditCardAccountIdInvalido inválido")
    public void testCreditCardAccountIdInvalido() throws Exception {
        getContaIdentificadaPorCreditCardAccountIdRequest.creditCardAccountIdInvalido()
                .then()
                .statusCode(404)
                .time(lessThan(10L), TimeUnit.SECONDS)
                .body("errors[0].title", equalTo("O recurso solicitado não existe."))
                .body("errors[0].detail", equalTo("A conta do cartão não foi encontrada."));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("405 - Validar o status code informando um método não suportado no endpoint de identificação da conta identificada por creditCardAccountId")
    public void testMetodoNaoSuportado() throws Exception {
        getContaIdentificadaPorCreditCardAccountIdRequest.metodoNaoSuportado()
                .then()
                .statusCode(405)
                .time(lessThan(10L), TimeUnit.SECONDS)
                .body("errors.title", hasItem("Ocorreu um erro inesperado ao processar sua requisição."))
                .body("errors.detail", hasItem("Request method 'POST' not supported"));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("406 - A solicitação continha um cabeçalho Accept diferente dos tipos de mídia permitidos ou um conjunto de caracteres diferente de UTF-8")
    public void testAcceptDiferente() throws Exception {
        getContaIdentificadaPorCreditCardAccountIdRequest.acceptDiferente()
                .then()
                .time(lessThan(10L), TimeUnit.SECONDS)
                .statusCode(406);
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("406 - A solicitação continha um cabeçalho Accept diferente dos tipos de mídia permitidos ou um conjunto de caracteres diferente de UTF-8")
    public void testerroGateway() throws Exception {
        getContaIdentificadaPorCreditCardAccountIdRequest.erroGateway()
                .then()
                .statusCode(500)
                .time(lessThan(10L), TimeUnit.SECONDS)
                .body("errors.title", hasItem("Ocorreu um erro inesperado ao processar sua requisição."))
                .body("errors.detail", hasItem("Required String parameter 'cpf' is not present"));
    }
}