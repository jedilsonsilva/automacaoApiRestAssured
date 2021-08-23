package br.com.realize.tests.fase03.Pagamentos.ConsentimentoPagamento.tests;

import br.com.realize.runners.fase03;
import br.com.realize.suites.AllTests;
import br.com.realize.suites.Healthcheck;
import br.com.realize.tests.base.tests.BaseTest;
import br.com.realize.tests.fase03.Pagamentos.ConsentimentoPagamento.requests.PostAtualizacaoContaDebitoRequest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.*;

@Epic("Fase 03")
@Feature("Pagamento")
@DisplayName("Atualização dos dados da conta de débito e autorização do consentimento")

public class PostAtualizacaoContaDebitoTest extends BaseTest {

    PostAtualizacaoContaDebitoRequest postAtualizacaoContaDebitoRequest = new PostAtualizacaoContaDebitoRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Validar a atualização dos dados da conta de débito")
    public void testAtualizarDadosContaDebito() throws Exception {
        postAtualizacaoContaDebitoRequest.atualizarDadosContaDebito()
                .then()
                .statusCode(200)
                .time(lessThan(4L), TimeUnit.SECONDS);
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Validar a atualização da conta de débido informando um endpoint incorreto")
    public void testPathInvalido() throws Exception {
        postAtualizacaoContaDebitoRequest.pathInvalido()
                .then()
                .statusCode(404)
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body("errors.title", hasItem("O recurso solicitado não existe."))
                .body("errors.detail", hasItem("O endereço informado para esse endpoint está incorreto."));
    }
    @Ignore
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Validar a atualização da conta de débido não informando o header authorization")
    public void testAuthorizationNaoInformado() throws Exception {
        postAtualizacaoContaDebitoRequest.authorizationNaoInformado()
                .then()
                .statusCode(400)
                .time(lessThan(4L), TimeUnit.SECONDS);
    }
    @Ignore
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Validar a atualização da conta de débido informando um token inválido")
    public void testTokenInvalido() throws Exception {
        postAtualizacaoContaDebitoRequest.tokenInvalido()
                .then()
                .statusCode(401)
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body("errors.title", hasItem("Cabeçalho de autenticação ausente/inválido ou token inválido."))
                .body("errors.detail", hasItem("O access token informado é inválido."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Validar a atualização da conta de débido informando um consentId inválido")
    public void testConsentIdInvalido() throws Exception {
        postAtualizacaoContaDebitoRequest.consentIdNaoEncontrado()
                .then()
                .statusCode(400)
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body("errors.title", hasItem("A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL."))
                .body("errors.detail", hasItem("O consentId informado é inválido."));
    }

    @Ignore
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Validar a atualização da conta de débido informando um consentId que esteja com o status Consumed")
    public void testConsentIdConsumed() throws Exception {
        postAtualizacaoContaDebitoRequest.consentIdConsumed()
                .then()
                .statusCode(409)
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body("errors.title", hasItem("O consentimento não pode ser autorizado, pois seu status já havia sido alterado anteriormente."))
                .body("errors.detail", hasItem("Apenas é possível alterar um consentimento que se encontra no status \"AWAITING_AUTHORISATION\". O status atual do consentimento é \"CONSUMED\"."));
    }
    @Ignore
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Validar a atualização da conta de débido que a data/hora de expiração do consentimento é menor que a data/hora atual.")
    public void testExpirationDateTime() throws Exception {
        postAtualizacaoContaDebitoRequest.expirationDateTime()
                .then()
                .statusCode(412)
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body("errors.title", hasItem("Consentimento expirado."))
                .body("errors.detail", hasItem("O consentimento com id <consentId> não é mais válido pois já atingiu o tempo de expiração."));
    }
}
