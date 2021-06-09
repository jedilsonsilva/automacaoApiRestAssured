package br.com.realize.tests.fase02.Contas.Saldos.tests;

import br.com.realize.runners.fase02;
import br.com.realize.suites.AllTests;
import br.com.realize.suites.Contract;
import br.com.realize.tests.fase02.Contas.ListaContas.requests.GetContaRequest;
import br.com.realize.tests.base.tests.BaseTest;
import br.com.realize.suites.Healthcheck;
import br.com.realize.tests.fase02.Contas.Saldos.requests.GetSaldoRequest;
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
@DisplayName("Saldo da Conta")
public class GetSaldoTest extends BaseTest {
    String AccountIDInvalido = GetContaRequest.AccountIDInvalido;
    GetSaldoRequest getSaldoRequest = new GetSaldoRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar o retorno das informações do endpoint de saldo da conta")
    public void testObterSaldo() throws Exception {
        getSaldoRequest.obterSaldoConta()
                .then()
                .statusCode(200);
    }
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({Contract.class, AllTests.class})
    @DisplayName("Validar a garantia do contrato do retorno do saldo da conta")
    public void testGarantirContratosSaldoConta() throws Exception {
        getSaldoRequest.obterSaldoConta()
                .then()
                .statusCode(200)
                .assertThat().body(matchesJsonSchema(
                new File(Utils.getContractsBasePath("fase02/Contas/Saldos", "ObterSaldoConta"))));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class})
    @DisplayName("Validar a obtenção do saldo da conta com ID inválido.")
    public void testIdInvalidoSaldo() throws Exception {
        getSaldoRequest.idInvalidoSaldo()
                .then()
                .statusCode(404)
                .body("errors.title", hasItem("O recurso solicitado não existe."))
                .body("errors.detail", hasItem("Nenhuma conta encontrada para o id " + AccountIDInvalido + "."));
    }
//VALIDAÇÕES DOS STATUS CODE DE ERRO
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class})
    @DisplayName("Validar retorno 405 -Método não suportado no endpoint de saldos da conta")
    public void testMetodoNaoSuportado() throws Exception {
        getSaldoRequest.metodoNaoSuportado()
                .then()
                .log().all()
                .statusCode(405)
                .body("errors.title", hasItem("Ocorreu um erro inesperado ao processar sua requisição."))
                .body("errors.detail", hasItem("Request method 'POST' not supported"));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar o retorno 404 - Path da API inválido no endpoint de saldos da conta")
    public void testPathInvalido() throws Exception {
        getSaldoRequest.pathInvalido()
                .then()
                .log().all()
                .statusCode(404)
                .body("errors[0].title", equalTo("O recurso solicitado não existe."))
                .body("errors[0].detail", equalTo("O endereço informado para esse endpoint está incorreto."));
    }
}