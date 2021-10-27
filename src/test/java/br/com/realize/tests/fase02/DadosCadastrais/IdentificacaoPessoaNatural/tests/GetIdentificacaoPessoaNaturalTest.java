package br.com.realize.tests.fase02.DadosCadastrais.IdentificacaoPessoaNatural.tests;

import br.com.realize.runners.fase02;
import br.com.realize.suites.AllTests;
import br.com.realize.suites.Contract;
import br.com.realize.tests.base.tests.BaseTest;
import br.com.realize.suites.Healthcheck;
import br.com.realize.tests.fase02.DadosCadastrais.IdentificacaoPessoaNatural.requests.GetIdentificacaoPessoaNaturalRequest;
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

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.*;

@Epic("Fase 02")
@Feature("Dados Cadastrais")
@DisplayName("Identificação Pessoa Natural")

public class GetIdentificacaoPessoaNaturalTest extends BaseTest {

    GetIdentificacaoPessoaNaturalRequest getIdentificacaoPessoaNaturalRequest = new GetIdentificacaoPessoaNaturalRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar o retorno do endpoint de indentificação de pessoa natural")
    public void testValidarIdentificacaoPessoaNatural() throws Exception {
        getIdentificacaoPessoaNaturalRequest.obterIdentificacaoPessoaNatural()
                .then()
                .log().all()
                .statusCode(200)
                .time(lessThan(10L), TimeUnit.SECONDS)
                .body("meta.totalPages", greaterThan(0))
                .body("meta.totalRecords", greaterThan(0));
    }
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({Contract.class, AllTests.class, fase02.class})
    @DisplayName("Validar a garantia do contrato do retorno da conta por ID")
    public void testGarantirContratosIdentificacaoPessoaNatural() throws Exception {
        getIdentificacaoPessoaNaturalRequest.obterIdentificacaoPessoaNatural()
                .then()
                .statusCode(200)
                .assertThat().body(matchesJsonSchema(
                new File(Utils.getContractsBasePath("fase02/Contas/IdentificacaoPessoaNatural", "IdentificacaoPessoaNatural"))));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Validar o retorno 404 - Path da API inválido no endpoint de identificação de pessoa natural")
    public void testPathInvalido() throws Exception {
        getIdentificacaoPessoaNaturalRequest.pathInvalido()
                .then()
                .statusCode(404)
                .body("errors[0].title", equalTo("O recurso solicitado não existe."))
                .body("errors[0].detail", equalTo("O endereço informado para esse endpoint está incorreto."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("405 - Validar o status code informando um método não suportado no endpoint de identificação de pessoa natural")
    public void testMetodoNaoSuportado() throws Exception {
        getIdentificacaoPessoaNaturalRequest.metodoNaoSuportado()
                .then()
                .statusCode(405)
                .body("errors.title", hasItem("Ocorreu um erro inesperado ao processar sua requisição."))
                .body("errors.detail", hasItem("Request method 'POST' not supported"));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("400 - Validar o retorno do endpoint de indentificação de pessoa natural informando um CPF inválido.")
    public void testCpfInvalido() throws Exception {
        getIdentificacaoPessoaNaturalRequest.cpfInvalido()
                .then()
                .statusCode(400)
                .body("errors[0].title", equalTo("A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL."))
                .body("errors[0].detail", equalTo("O CPF informado é inválido."));
    }
}