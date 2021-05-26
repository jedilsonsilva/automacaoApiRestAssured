package br.com.realize.tests.fase02.Contas.ListaContas.tests;

import br.com.realize.runners.fase02;
import br.com.realize.suites.AllTests;
import br.com.realize.suites.Contract;
import br.com.realize.tests.fase02.Contas.ListaContas.requests.GetContaRequest;
import br.com.realize.tests.base.tests.BaseTest;
import br.com.realize.suites.Healthcheck;
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
@Feature("Contas")
@DisplayName("Lista de Contas")
public class GetContaTest extends BaseTest {
    GetContaRequest getContaRequest = new GetContaRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar o retorno do endpoint de informações da conta")
    public void testValidarinformacoesConta() throws Exception {
        getContaRequest.obterInformacoesConta()
                .then()
                .log().all()
                .statusCode(200)
                .body("data.brandName", hasItem("REALIZE CFI S.A."))
                .body("data.brandID", hasItem("218b28be-b283-5994-973e-3bafbffccf0c"))
                .body("data.companyCnpj", hasItem("27351731000138"))
                .body("data.type", hasItem("CONTA_PAGAMENTO_PRE_PAGA"))
                .body("data.compeCode", hasItem("374"))
                .body("data.branchCode", hasItem("0001"))
                .body("data.number", hasItem("158076326"))
                .body("data.checkDigit", hasItem("6"))
                .body("data.accountID", hasItem("e92c51df-b68c-4faa-9cbc-4cb1b232781f"))
                .body("meta.totalRecords", is(1))
                .body("meta.totalPages", is(1))
                .extract().path("{data.accountID}");

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({Contract.class, AllTests.class, fase02.class})
    @DisplayName("Validar a garantia do contrato do retorno da lista de contas")
    public void testGarantirContratosInformacoesConta() throws Exception {
        getContaRequest.obterInformacoesConta().then()
                .statusCode(200)
                .assertThat().body(matchesJsonSchema(
                new File(Utils.getContractsBasePath("fase02/Contas/ListaContas", "ObterInformacoesConta"))));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar o retorno do endpoint de contas com tipo de conta igual a Depósito à vista")
    public void testTipoContaDepositoAVista() throws Exception {
        getContaRequest.tipoContaDepositoAVista()
                .then()
                .statusCode(200)
                .body("data", is(empty()))
                .body("meta.totalRecords", is(0))
                .body("meta.totalPages", is(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar o retorno do endpoint de contas com tipo de conta igual a Poupança")
    public void testTipoContaPoupanca() throws Exception {
        getContaRequest.tipoContaPoupanca()
                .then()
                .statusCode(200)
                .body("data", is(empty()))
                .body("meta.totalRecords", is(0))
                .body("meta.totalPages", is(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar comportamento da API quando informado CPF sem conta.")
    public void testCpfSemConta() throws Exception {
        getContaRequest.cpfSemConta()
                .then()
                .statusCode(200)
                .body("data", is(empty()));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar a obtenção de contas consentidas com CPF inválido.")
    public void testCpfInvalido() throws Exception {
        getContaRequest.cpfInvalido()
                .then()
                .statusCode(400)
                .body("errors[0].title", equalTo("A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL."))
                .body("errors[0].detail", equalTo("O CPF informado é inválido."));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar o retorno 404 - Path da API inválido no endpoint de lista de contas")
    public void testPathInvalido() throws Exception {
        getContaRequest.pathInvalido()
                .then()
                .log().all()
                .statusCode(404)
                .body("errors[0].title", equalTo("O recurso solicitado não existe."))
                .body("errors[0].detail", equalTo("O endereço informado para esse endpoint está incorreto."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("405 - Validar o status code informando um método não suportado no endpoint de lista de contas")
    public void testMetodoNaoSuportado() throws Exception {
        getContaRequest.metodoNaoSuportado()
                .then()
                .log().all()
                .statusCode(405)
                .body("errors.title", hasItem("Ocorreu um erro inesperado ao processar sua requisição."))
                .body("errors.detail", hasItem("Request method 'POST' not supported"));
    }

}