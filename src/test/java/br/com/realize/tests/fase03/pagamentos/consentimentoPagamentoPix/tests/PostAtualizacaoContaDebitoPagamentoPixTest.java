package br.com.realize.tests.fase03.pagamentos.consentimentoPagamentoPix.tests;

import br.com.realize.runners.fase03;
import br.com.realize.suites.AllTests;
import br.com.realize.suites.Healthcheck;
import br.com.realize.tests.base.tests.BaseTest;
import br.com.realize.tests.fase03.pagamentos.consentimentoPagamentoPix.requests.PostAtualizacaoContaDebitoPagamentoPixRequest;
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

public class PostAtualizacaoContaDebitoPagamentoPixTest extends BaseTest {

    PostAtualizacaoContaDebitoPagamentoPixRequest postAtualizacaoContaDebitoPagamentoPixRequest = new PostAtualizacaoContaDebitoPagamentoPixRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Validar a atualização dos dados da conta de débito")
    public void testAtualizarDadosContaDebito() throws Exception {
        postAtualizacaoContaDebitoPagamentoPixRequest.atualizarDadosContaDebito()
                .then()
                .statusCode(200)
                .time(lessThan(6L), TimeUnit.SECONDS);
    }
}
