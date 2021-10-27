package br.com.realize.tests.fase03.pagamentos.consentimentoPagamentoPix.tests;

import br.com.realize.runners.fase03;
import br.com.realize.suites.AllTests;
import br.com.realize.suites.Contract;
import br.com.realize.suites.Healthcheck;
import br.com.realize.tests.base.tests.BaseTest;
import br.com.realize.tests.fase03.pagamentos.consentimentoPagamentoPix.requests.PostConsentimentoPagamentoRequest;
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
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.*;

@Epic("Fase 03")
@Feature("Criação de Consentimento de pagamento por PiX pelo client da iniciadora")
@DisplayName("Incluir consentimento de iniciação de pagamento")

public class PostConsentimentoPagamentoTest extends BaseTest {

    PostConsentimentoPagamentoRequest postConsentimentoPagamentoRequest = new PostConsentimentoPagamentoRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Validar a inclusão de consentimento de iniciação de Pagamento por PiX")
    public void testValidarInclusaoConsentimentoPagamentoPix() throws Exception {
        postConsentimentoPagamentoRequest.inserirPedidoConsetimentoPagamento()
                .then()
                .statusCode(200)
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body("meta.totalPages", greaterThan(0))
                .body("meta.totalRecords", greaterThan(0))
                .body("data.status", equalTo("AWAITING_AUTHORISATION"));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Validar idempontency com dados diferentes")
    public void testValidaridempotencyUsadoDadosDiferentes() throws Exception {
        postConsentimentoPagamentoRequest.idempotencyUsadoDadosDiferentes()
                .then()
                .statusCode(400)
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body("errors.title", hasItem("A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL."))
                .body("errors.detail", hasItem("Dados do objeto Payment diferente do informado para este x-idempotency-key"));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({Contract.class, AllTests.class, fase03.class})
    @DisplayName("Validar a garantia do contrato do retorno da inclusão de consentimento de pagamento por PiX")
    public void testGarantirContratosInclusaoConsentimento() throws Exception {
        postConsentimentoPagamentoRequest.inserirGarantirContratoCriacaoConsentimento()
                .then()
                .statusCode(200)
                .assertThat().body(matchesJsonSchema(
                new File(Utils.getContractsBasePath("fase03/Pagamentos/ConsentimentoPagamentoPix", "PostConsentimentoPagamentoPix"))));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Path da API inválido no endpoint de inclusão de consentimento de pagamento por Pix")
    public void testPathInvalido() throws Exception {
        postConsentimentoPagamentoRequest.pathInvalido()
                .then()
                .statusCode(404)
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body("errors.title", hasItem("O recurso solicitado não existe."))
                .body("errors.detail", hasItem("O endereço informado para esse endpoint está incorreto."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Requisição malformada, body com dados que não correspondem ao endpoint de inclusão de consentimento de pagamento por Pix")
    public void testRequisicaoMalformada() throws Exception {
        postConsentimentoPagamentoRequest.requisicaoMalFormada()
                .then()
                .statusCode(400)
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body("errors.title", hasItem("A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Validar informações obrigatórias não informadas na inclusão de consentimento de pagamento por Pix")
    public void testInformacoesObrigatorias() throws Exception {
        postConsentimentoPagamentoRequest.informacoesObrigatorias()
                .then()
                .statusCode(400)
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body("errors.title", hasItem("A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Valdiar a inclusão de consentimento de pagamento por pix informando um método inválido")
    public void testMetodoNaoSuportado() throws Exception {
        postConsentimentoPagamentoRequest.metodoNaoSuportado()
                .then()
                .statusCode(405)
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body("errors.title", hasItem("Ocorreu um erro inesperado ao processar sua requisição."))
                .body("errors.detail", hasItem("Request method 'DELETE' not supported"));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Validar o retorno do endpoint de inclusão de consentimento de pagamento por Pix quando o recurso é inexistente.")
    public void testRecursoInexistente() throws Exception {
        postConsentimentoPagamentoRequest.recursoInexistente()
                .then()
                .statusCode(404)
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body("errors.title", hasItem("O recurso solicitado não existe."))
                .body("errors.detail", hasItem("O endereço informado para esse endpoint está incorreto."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Validar o retorno do endpoint de inclusão de consentimento de pagamento por pix quando informando um accept diferente.")
    public void testAcceptDiferente() throws Exception {
        postConsentimentoPagamentoRequest.acceptDiferente()
                .then()
                .statusCode(406)
                .time(lessThan(4L), TimeUnit.SECONDS);
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Validar o retorno do endpoint de inclusão de consentimento de pagamento por pix quando informando um payload com formato não suportado.")
    public void testPayloadNaoSuportado() throws Exception {
        postConsentimentoPagamentoRequest.payloadNaoSuportado()
                .then()
                .statusCode(415)
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body("errors.title", hasItem("O formato do payload não é um formato suportado."))
                .body("errors.detail", hasItem("Content type 'text/plain;charset=ISO-8859-1' not supported"));

    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Validar o retorno do endpoint de inclusão de consentimento de pagamento por pix quando ocorre erro de Gateway.")
    public void testErroGateway() throws Exception {
        postConsentimentoPagamentoRequest.erroGateway()
                .then()
                .statusCode(500)
                .time(lessThan(4L), TimeUnit.SECONDS);
    }
}