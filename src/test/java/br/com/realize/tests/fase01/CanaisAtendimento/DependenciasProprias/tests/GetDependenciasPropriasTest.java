package br.com.realize.tests.fase01.CanaisAtendimento.DependenciasProprias.tests;

import br.com.realize.runners.fase01;
import br.com.realize.suites.AllTests;
import br.com.realize.tests.base.tests.BaseTest;
import br.com.realize.suites.Healthcheck;
import br.com.realize.tests.fase01.CanaisAtendimento.DependenciasProprias.requests.GetDependenciasPropriasRequest;
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
import static org.hamcrest.Matchers.*;

@Epic("Fase 01")
@Feature("Canais de Atendimento")
@DisplayName("Depedências Próprias")
public class GetDependenciasPropriasTest extends BaseTest {

    GetDependenciasPropriasRequest getDependenciasPropriasRequest = new GetDependenciasPropriasRequest();
    String linkSelf = getDependenciasPropriasRequest.obterLinkSelfDependenciasProprias();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase01.class})
    @DisplayName("Validar o retorno das informações gerais de dependências próprias")
    public void testDependenciasProprias() throws Exception {
        getDependenciasPropriasRequest.obterInformacoesDependenciasProprias()
                .then()
                .log().all()
                .statusCode(200)
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body("meta.totalPages", greaterThan(0))
                .body("meta.totalRecords", greaterThan(0))
                .body("links.self", is(linkSelf));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Garantir o contrato do retorno de dependências próprias")
    @Category({AllTests.class, fase01.class})
    public void testGarantirContratosDependenciasProprias() throws Exception {
        getDependenciasPropriasRequest.obterInformacoesDependenciasProprias()
                .then()
                .statusCode(200).assertThat()
                .assertThat().body(matchesJsonSchema(
                new File(Utils.getContractsBasePath("fase01/CanaisAtendimento/DependenciasProprias", "DependenciasProprias"))));    }


//VALIDAÇÕES DOS STATUS CODE DE ERRO
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase01.class})
    @DisplayName("Validar o retorno 404 - Número da página não localizado no endpoint de dependências próprias")
    public void testNumeroPaginaNaoLocalizado() throws Exception {
        getDependenciasPropriasRequest.numeroPaginaNaoLocalizado()
                .then()
                .statusCode(404)
                .body("errors[0].title", equalTo("O recurso solicitado está acima do permitido."))
                .body("errors[0].detail", equalTo("O número da página (parâmetro page) é maior do que o permitido na consulta (1)."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase01.class})
    @DisplayName("Validar o retorno 404 - Path da API inválido no endpoint de dependências próprias")
    public void testPathInvalido() throws Exception {
        getDependenciasPropriasRequest.pathInvalido()
                .then()
                .statusCode(404)
                .body("errors[0].title", equalTo("O recurso solicitado não existe."))
                .body("errors[0].detail", equalTo("O endereço informado para esse endpoint está incorreto."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase01.class})
    @DisplayName("Validar o retorno 400 - Número da página é zero no endpoint de dependências próprias")
    public void testNumeroPaginaZero() throws Exception {
        getDependenciasPropriasRequest.numeroPaginaZero()
                .then()
                .statusCode(400)
                .body("errors[0].title", equalTo("Número da página inválido."))
                .body("errors[0].detail", equalTo("O número da página (parâmetro page) informado é inválido. São permitidos valores numéricos com valor mínimo igual a 1."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase01.class})
    @DisplayName("Validar o retorno 400 - Número da página inválido no endpoint de dependências próprias")
    public void testNumeroPaginaInvalido() throws Exception {
        getDependenciasPropriasRequest.numeroPaginaInvalido()
                .then()
                .statusCode(400)
                .body("errors[0].title", equalTo("Número da página inválido."))
                .body("errors[0].detail", equalTo("O número da página (parâmetro page) informado é inválido. São permitidos valores numéricos com valor mínimo igual a 1."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase01.class})
    @DisplayName("Validar o retorno 400 - Tamanho da página é zero no endpoint de dependências próprias")
    public void testTamanhoPaginaZero() throws Exception {
        getDependenciasPropriasRequest.tamanhoPaginaZero()
                .then()
                .statusCode(400)
                .body("errors[0].title", equalTo("Tamanho da página inválido."))
                .body("errors[0].detail", equalTo("O tamanho da página (parâmetro page-size) informado é inválido. São permitidos valores numéricos de 10 a 1000."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase01.class})
    @DisplayName("Validar o retorno 400 - Tamanho da página inválido no endpoint de dependências próprias")
    public void testTamanhoPaginaInvalido() throws Exception {
        getDependenciasPropriasRequest.tamanhoPaginaInvalido()
                .then()
                .statusCode(400)
                .body("errors[0].title", equalTo("Tamanho da página inválido."))
                .body("errors[0].detail", equalTo("O tamanho da página (parâmetro page-size) informado é inválido. São permitidos valores numéricos de 10 a 1000."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase01.class})
    @DisplayName("Validar o retorno 422 - Tamanho da página superior ao permitido no endpoint de dependências próprias")
    public void testTamanhoPaginaSuperior() throws Exception {
        getDependenciasPropriasRequest.tamanhoPaginaSuperior()
                .then()
                .statusCode(422)
                .body("errors[0].title", equalTo("O recurso solicitado está acima do permitido."))
                .body("errors[0].detail", equalTo("O tamanho da página (parâmetro page-size) informado é superior ao limite previsto (1000)."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase01.class})
    @DisplayName("405 - Validar o status code informando um método não suportado no endpoint de dependências próprias")
    public void testMetodoNaoSuportado() throws Exception {
        getDependenciasPropriasRequest.metodoNaoSuportado()
                .then()
                .statusCode(405)
                .body("errors.title", hasItem("Ocorreu um erro inesperado ao processar sua requisição."))
                .body("errors.detail", hasItem("Request method 'POST' not supported"));
    }
}
