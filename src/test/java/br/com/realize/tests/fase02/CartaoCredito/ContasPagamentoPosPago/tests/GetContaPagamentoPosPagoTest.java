package br.com.realize.tests.fase02.CartaoCredito.ContasPagamentoPosPago.tests;

import br.com.realize.runners.fase01;
import br.com.realize.runners.fase02;
import br.com.realize.suites.AllTests;
import br.com.realize.suites.Contract;
import br.com.realize.suites.Healthcheck;
import br.com.realize.tests.base.tests.BaseTest;
import br.com.realize.tests.fase02.CartaoCredito.ContasPagamentoPosPago.requests.GetContaPagamentoPosPagoRequest;
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
@DisplayName("Lista de contas pagamento pós-pago")

public class GetContaPagamentoPosPagoTest extends BaseTest {

    GetContaPagamentoPosPagoRequest getContaPagamentoPosPagoRequest = new GetContaPagamentoPosPagoRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar o retorno do endpoint de contas de pagamento pós-pago")
    public void testValidarinformacoesConta() throws Exception {
        getContaPagamentoPosPagoRequest.retornaContasPagamentoPosPago()
                .then()
                .log().all()
                .statusCode(200);
    }
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({Contract.class, AllTests.class, fase02.class})
    @DisplayName("Validar a garantia do contrato do retorno da lista de contas pagamento pós-pago")
    public void testGarantirContratosContaPagamentoPosPago() throws Exception {
        getContaPagamentoPosPagoRequest.retornaContasPagamentoPosPago()
                .then()
                .statusCode(200)
                .assertThat().body(matchesJsonSchema(
                new File(Utils.getContractsBasePath("fase02/CartaoCredito/ContasPagamentoPosPago", "ContaPagamentoPosPago"))));
    }

   @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar comportamento da API quando informado CPF sem conta.")
    public void testCpfSemConta() throws Exception {
        getContaPagamentoPosPagoRequest.cpfSemConta()
                .then()
                .statusCode(200)
                .body("data", is(empty()));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar a obtenção de contas consentidas com CPF inválido.")
    public void testCpfInvalido() throws Exception {
        getContaPagamentoPosPagoRequest.cpfInvalido()
                .then()
                .log().all()
                .statusCode(400)
                .body("errors[0].title", equalTo("A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL."))
                .body("errors[0].detail", equalTo("O CPF informado é inválido."));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar o retorno 404 - Path da API inválido no endpoint de Conta de pagamento pós-pago")
    public void testPathInvalido() throws Exception {
        getContaPagamentoPosPagoRequest.pathInvalido()
                .then()
                .log().all()
                .statusCode(404)
                .body("errors[0].title", equalTo("O recurso solicitado não existe."))
                .body("errors[0].detail", equalTo("O endereço informado para esse endpoint está incorreto."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase01.class})
    @DisplayName("Validar o retorno 404 - Número da página não localizado no endpoint de Conta de pagamento pós-pago")
    public void testNumeroPaginaNaoLocalizado() throws Exception {
        getContaPagamentoPosPagoRequest.numeroPaginaNaoLocalizado()
                .then()
                .log().all()
                .statusCode(404)
                .body("errors[0].title", equalTo("O recurso solicitado está acima do permitido."))
                .body("errors[0].detail", equalTo("O número da página (parâmetro page) é maior do que o permitido na consulta (1)."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase01.class})
    @DisplayName("Validar o retorno 400 - Número da página é zero no endpoint de Conta de pagamento pós-pago")
    public void testNumeroPaginaZero() throws Exception {
        getContaPagamentoPosPagoRequest.numeroPaginaZero()
                .then()
                .log().all()
                .statusCode(400)
                .body("errors[0].title", equalTo("Número da página inválido."))
                .body("errors[0].detail", equalTo("O número da página (parâmetro page) informado é inválido. São permitidos valores numéricos com valor mínimo igual a 1."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase01.class})
    @DisplayName("Validar o retorno 400 - Número da página inválido no endpoint de Conta de pagamento pós-pago")
    public void testNumeroPaginaInvalido() throws Exception {
        getContaPagamentoPosPagoRequest.numeroPaginaInvalido()
                .then()
                .log().all()
                .statusCode(400)
                .body("errors[0].title", equalTo("Número da página inválido."))
                .body("errors[0].detail", equalTo("O número da página (parâmetro page) informado é inválido. São permitidos valores numéricos com valor mínimo igual a 1."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase01.class})
    @DisplayName("Validar o retorno 400 - Tamanho da página é zero no endpoint de Conta de pagamento pós-pago")
    public void testTamanhoPaginaZero() throws Exception {
        getContaPagamentoPosPagoRequest.tamanhoPaginaZero()
                .then()
                .log().all()
                .statusCode(400)
                .body("errors[0].title", equalTo("Tamanho da página inválido."))
                .body("errors[0].detail", equalTo("O tamanho da página (parâmetro page-size) informado é inválido. São permitidos valores numéricos de 10 a 1000."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase01.class})
    @DisplayName("Validar o retorno 400 - Tamanho da página inválido no endpoint de Conta de pagamento pós-pago")
    public void testTamanhoPaginaInvalido() throws Exception {
        getContaPagamentoPosPagoRequest.tamanhoPaginaInvalido()
                .then()
                .log().all()
                .statusCode(400)
                .body("errors[0].title", equalTo("Tamanho da página inválido."))
                .body("errors[0].detail", equalTo("O tamanho da página (parâmetro page-size) informado é inválido. São permitidos valores numéricos de 10 a 1000."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase01.class})
    @DisplayName("Validar o retorno 422 - Tamanho da página superior ao permitido no endpoint de Conta de pagamento pós-pago")
    public void testTamanhoPaginaSuperior() throws Exception {
        getContaPagamentoPosPagoRequest.tamanhoPaginaSuperior()
                .then()
                .log().all()
                .statusCode(422)
                .body("errors[0].title", equalTo("O recurso solicitado está acima do permitido."))
                .body("errors[0].detail", equalTo("O tamanho da página (parâmetro page-size) informado é superior ao limite previsto (1000)."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("405 - Validar o status code informando um método não suportado no endpoint de Conta de pagamento pós-pago")
    public void testMetodoNaoSuportado() throws Exception {
        getContaPagamentoPosPagoRequest.metodoNaoSuportado()
                .then()
                .log().all()
                .statusCode(405)
                .body("errors.title", hasItem("Ocorreu um erro inesperado ao processar sua requisição."))
                .body("errors.detail", hasItem("Request method 'POST' not supported"));
    }

}