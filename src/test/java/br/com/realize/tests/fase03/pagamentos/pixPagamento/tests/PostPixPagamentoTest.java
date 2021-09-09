package br.com.realize.tests.fase03.pagamentos.pixPagamento.tests;

import br.com.realize.runners.fase03;
import br.com.realize.suites.AllTests;
import br.com.realize.suites.Contract;
import br.com.realize.suites.Healthcheck;
import br.com.realize.tests.base.tests.BaseTest;
import br.com.realize.tests.fase03.pagamentos.pixPagamento.requests.PostPixPagamentoRequest;
import br.com.realize.utils.Utils;
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.*;

@Epic("Fase 03")
@Feature("Consentimento")
@DisplayName("Incluir consentimento de iniciação de pagamento")

public class PostPixPagamentoTest extends BaseTest {

    PostPixPagamentoRequest postPixPagamentoRequest = new PostPixPagamentoRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Validar a inclusão PiX iniciação de Pagamento")
    public void testValidarInclusaoPixIniciacaoPagamento() throws Exception {
        postPixPagamentoRequest.inserirPixIniciacaoPagamento()
                .then()
                .statusCode(200)
                .time(lessThan(4L), TimeUnit.SECONDS);
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Validar a inclusão PiX iniciação de Pagamento com um X-idempotency já utilizado")
    public void testValidarInclusaoPixIniciacaoPagamentoXidempotencyJaUtilizado() throws Exception {
        postPixPagamentoRequest.inserirPixIniciacaoPagamentoXidempotencyJaUtilizado()
                .then()
                .statusCode(400)
                .time(lessThan(4L), TimeUnit.SECONDS);
    }
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({Contract.class, AllTests.class, fase03.class})
    @DisplayName("Validar a garantia do contrato do retorno da inclusão de consentimento")
    public void testGarantirContratosInclusaoConsentimento() throws Exception {
        postPixPagamentoRequest.inserirPixIniciacaoPagamento()
                .then()
                .statusCode(200)
                .assertThat().body(matchesJsonSchema(
                new File(Utils.getContractsBasePath("fase03/pagamentos/pixPagamento", "PostPixPagamento"))));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Path da API inválido no endpoint de exclusão de consentimento")
    public void testPathInvalido() throws Exception {
        postPixPagamentoRequest.pathInvalido()
                .then()
                .statusCode(404)
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body("errors.title", hasItem("O recurso solicitado não existe."))
                .body("errors.detail", hasItem("O endereço informado para esse endpoint está incorreto."));
    }
   /* @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Requisição malformada, body com dados que não correspondem ao endpoint de inclusão de consentimento")
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
    @DisplayName("Requisição malformada, body com dados que não correspondem ao endpoint de inclusão de consentimento")
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
    @DisplayName("405 - Validar o status code informando um método não suportado no endpoint de exclusão de consentimento")
    public void testMetodoNaoSuportado() throws Exception {
        postConsentimentoPagamentoRequest.metodoNaoSuportado()
                .then()
                .statusCode(405)
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body("errors.title", hasItem("Ocorreu um erro inesperado ao processar sua requisição."))
                .body("errors.detail", hasItem("Request method 'DELETE' not supported"));
    }
    @Ignore
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Validar o retorno do endpoint de exclusão de consentimento quando informando a política de segurança é violada.")
    public void testPoliticaSegurancaViolada() throws Exception {
        postConsentimentoPagamentoRequest.politicaSegurancaVioalada()
                .then()
                .statusCode(403)
                .time(lessThan(4L), TimeUnit.SECONDS);
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Validar o retorno do endpoint de exclusão de consentimento quando o recurso é inesistente.")
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
    @DisplayName("Validar o retorno do endpoint de exclusão de consentimento quando informando um accept diferente.")
    public void testAcceptDiferente() throws Exception {
        postConsentimentoPagamentoRequest.acceptDiferente()
                .then()
                .statusCode(406)
                .time(lessThan(4L), TimeUnit.SECONDS);
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Validar o retorno do endpoint de exclusão de consentimento quando informando um payload com formato não suportado.")
    public void testPayloadNaoSuportado() throws Exception {
        postConsentimentoPagamentoRequest.payloadNaoSuportado()
                .then()
                .statusCode(415)
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body("errors.title", hasItem("O formato do payload não é um formato suportado."))
                .body("errors.detail", hasItem("Content type 'text/plain;charset=ISO-8859-1' not supported"));

    }
    @Ignore
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Validar o retorno do endpoint de exclusão de consentimento quando informando um payload com formato não suportado.")
    public void testImpossivelProcessarInstrucoes() throws Exception {
        postConsentimentoPagamentoRequest.impossivelProcessarInstrucoes()
                .then()
                .statusCode(422)
                .time(lessThan(4L), TimeUnit.SECONDS);
    }
    @Ignore
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Validar o retorno do endpoint de exclusão de consentimento quando muitas solicitações são feitas dentro de um determinado período.")
    public void testMuitasSolicitacoesFeitas() throws Exception {
        postConsentimentoPagamentoRequest.muitasSolicitacoesFeitas()
                .then()
                .statusCode(429)
                .time(lessThan(4L), TimeUnit.SECONDS);
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Validar o retorno do endpoint de exclusão de consentimento quando ocorre erro de Gateway.")
    public void testErroGateway() throws Exception {
        postConsentimentoPagamentoRequest.erroGateway()
                .then()
                .statusCode(500)
                .time(lessThan(4L), TimeUnit.SECONDS);
    }*/
}