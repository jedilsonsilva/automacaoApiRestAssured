package br.com.realize.tests.fase02.Contas.Saldos.tests;

import br.com.realize.suites.AllTests;
import br.com.realize.suites.Contract;
import br.com.realize.tests.fase02.Contas.ListaContas.requests.GetContaRequest;
import br.com.realize.tests.base.tests.BaseTest;
import br.com.realize.suites.Healthcheck;
import br.com.realize.tests.fase02.Contas.Saldos.requests.GetSaldoRequest;
import br.com.realize.utils.Utils;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.*;

@Feature("Saldos")
public class GetSaldoTest extends BaseTest {
    String AccountIDInvalido = GetContaRequest.AccountIDInvalido;
    GetSaldoRequest getSaldoRequest = new GetSaldoRequest();


    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({Contract.class, AllTests.class})
    @DisplayName("Validar a garantia do contrato do retorno do saldo das contas")
    public void testGarantirContratosSaldoConta() throws Exception {
        getSaldoRequest.obterSaldoConta().then()
                .statusCode(200)
                .assertThat().body(matchesJsonSchema(
                new File(Utils.getContractsBasePath("Fase02/Contas/Saldos", "ObterSaldoConta"))));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class})
    @DisplayName("Validar a obtenção do saldo da conta.")
    public void testObterSaldo() throws Exception {
        getSaldoRequest.obterSaldoConta()
                .then()
                .log().all()
                .statusCode(200)
                .body("meta.totalRecords", is(1));
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
    @DisplayName("405 - Validar o status code informando um método não suportado na obtenção de saldos da conta")
    public void testMetodoNaoSuportadoSaldo() throws Exception {
        getSaldoRequest.metodoNaoSuportadoSaldo()
                .then()
                .log().all()
                .statusCode(405)
                .body("errors.title", hasItem("correu um erro inesperado ao processar sua requisição."))
                .body("errors.detail", hasItem("Request method 'POST' not supported"));
    }
}