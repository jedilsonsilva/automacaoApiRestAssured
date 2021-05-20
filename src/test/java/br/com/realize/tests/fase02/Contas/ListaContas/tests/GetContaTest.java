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

@Epic("fase02")
@Feature("Contas")
public class GetContaTest extends BaseTest {
    GetContaRequest getContaRequest = new GetContaRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar a obtenção das informações da conta.")
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
    @DisplayName("Validar tipo de conta igual a Depósito à vista.")
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
    @DisplayName("Validar tipo de conta igual a Poupança.")
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
    @DisplayName("Validar o retorno 404 - Número da página não localizado.")
    public void testNumeroPaginaNaoLocalizado() throws Exception {
        getContaRequest.numeroPaginaNaoLocalizado()
                .then()
                .log().all()
                .statusCode(404)
                .body("errors[0].title", equalTo("O recurso solicitado está acima do permitido."))
                .body("errors[0].detail", equalTo("O número da página (parâmetro page) é maior do que o permitido na consulta (1)."));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar o retorno 404 - Path da API inválido")
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
    @DisplayName("Validar o retorno 400 - Número da página é zero.")
    public void testNumeroPaginaZero() throws Exception {
        getContaRequest.numeroPaginaZero()
                .then()
                .log().all()
                .statusCode(400)
                .body("errors[0].title", equalTo("Número da página inválido."))
                .body("errors[0].detail", equalTo("O número da página (parâmetro page) informado é inválido. São permitidos valores numéricos com valor mínimo igual a 1."));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar o retorno 400 - Número da página inválido.")
    public void testNumeroPaginaInvalido() throws Exception {
        getContaRequest.numeroPaginaInvalido()
                .then()
                .log().all()
                .statusCode(400)
                .body("errors[0].title", equalTo("Número da página inválido."))
                .body("errors[0].detail", equalTo("O número da página (parâmetro page) informado é inválido. São permitidos valores numéricos com valor mínimo igual a 1."));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar o retorno 400 - Tamanho da página é zero.")
    public void testTamanhoPaginaZero() throws Exception {
        getContaRequest.tamanhoPaginaZero()
                .then()
                .log().all()
                .statusCode(400)
                .body("errors[0].title", equalTo("Tamanho da página inválido."))
                .body("errors[0].detail", equalTo("O tamanho da página (parâmetro page-size) informado é inválido. São permitidos valores numéricos de 10 a 1000."));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar o retorno 400 - Tamanho da página inválido.")
    public void testTamanhoPaginaInvalido() throws Exception {
        getContaRequest.tamanhoPaginaInvalido()
                .then()
                .log().all()
                .statusCode(400)
                .body("errors[0].title", equalTo("Tamanho da página inválido."))
                .body("errors[0].detail", equalTo("O tamanho da página (parâmetro page-size) informado é inválido. São permitidos valores numéricos de 10 a 1000."));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar o retorno 422 - Tamanho da página superior ao permitido.")
    public void testTamanhoPaginaSuperior() throws Exception {
        getContaRequest.tamanhoPaginaSuperior()
                .then()
                .log().all()
                .statusCode(422)
                .body("errors[0].title", equalTo("O recurso solicitado está acima do permitido."))
                .body("errors[0].detail", equalTo("O tamanho da página (parâmetro page-size) informado é superior ao limite previsto (1000)."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("405 - Validar o status code informando um método não suportado")
    public void testMetodoNaoSuportado() throws Exception {
        getContaRequest.metodoNaoSuportado()
                .then()
                .log().all()
                .statusCode(405)
                .body("errors.title", hasItem("Ocorreu um erro inesperado ao processar sua requisição."))
                .body("errors.detail", hasItem("Request method 'POST' not supported"));
    }

}