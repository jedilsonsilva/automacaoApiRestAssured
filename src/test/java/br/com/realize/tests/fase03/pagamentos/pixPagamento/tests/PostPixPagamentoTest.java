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
@Feature("MVP5 - Criação do pagamento por PiX pelo client da iniciadora")
@DisplayName("Criar iniciação de pagamento PiX")

public class PostPixPagamentoTest extends BaseTest {

    PostPixPagamentoRequest postPixPagamentoRequest = new PostPixPagamentoRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Validar a inclusão de iniciação de pagamento PiX.")
    public void testValidarInclusaoPixIniciacaoPagamento() throws Exception {
        postPixPagamentoRequest.inserirPixIniciacaoPagamento()
                .then()
                .statusCode(200)
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body("meta.totalPages", greaterThan(0))
                .body("meta.totalRecords", greaterThan(0))
                .body("data.paymentId", is(not(emptyOrNullString())) );
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Validar a inclusão de iniciação de pagamento PiX informando um ConsentId rejeitado.")
    public void testValidarInclusaoPixIniciacaoPagamentoComConsentIdRejeitado() throws Exception {
        postPixPagamentoRequest.inserirPixIniciacaoPagamentoConsentIdRejeitado()
                .then()
                .statusCode(422)
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body("errors.title", hasItem("A requisição não pôde ser processada devido à violação na lógica de negócio."))
                .body("errors.detail", hasItem("O pagamento não pode ser criado pois o consentimento não se encontra com status AUTHORIZED. O status atual do consentimento é REJECTED."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Validar a inclusão de iniciação de pagamento PiX informando um ConsentId aguardando autorização.")
    public void testValidarInclusaoPixIniciacaoPagamentoComConsentIAguardandoAutorizacao() throws Exception {
        postPixPagamentoRequest.inserirPixIniciacaoPagamentoConsentIdAguardandoAutorizacao()
                .then()
                .statusCode(422)
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body("errors.title", hasItem("A requisição não pôde ser processada devido à violação na lógica de negócio."))
                .body("errors.detail", hasItem("O pagamento não pode ser criado pois o consentimento não se encontra com status AUTHORIZED. O status atual do consentimento é AWAITING_AUTHORISATION."));
    }
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({Contract.class, AllTests.class, fase03.class})
    @DisplayName("Validar a garantia do contrato do retorno da inclusão de iniciação de pagamento PiX.")
    public void testGarantirContratosInclusaoConsentimento() throws Exception {
        postPixPagamentoRequest.inserirPixIniciacaoPagamentoContrato()
                .then().log().all()
                .statusCode(200)
                .assertThat().body(matchesJsonSchema(
                new File(Utils.getContractsBasePath("fase03/pagamentos/pixPagamento", "PostPixPagamento"))));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Path da API inválido no endpoint de inclusão de iniciação de pagamento PiX.")
    public void testPathInvalido() throws Exception {
        postPixPagamentoRequest.pathInvalido()
                .then()
                .statusCode(404)
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body("errors.title", hasItem("O recurso solicitado não existe."))
                .body("errors.detail", hasItem("O endereço informado para esse endpoint está incorreto."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Validar o status code informando um método não suportado no endpoint inclusão de iniciação de pagamento PiX.")
    public void testMetodoNaoSuportado() throws Exception {
        postPixPagamentoRequest.metodoNaoSuportado()
                .then()
                .statusCode(405)
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body("errors.title", hasItem("Ocorreu um erro inesperado ao processar sua requisição."))
                .body("errors.detail", hasItem("Request method 'PUT' not supported"));
    }
}