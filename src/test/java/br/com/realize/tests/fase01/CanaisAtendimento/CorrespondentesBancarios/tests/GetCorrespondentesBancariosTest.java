package br.com.realize.tests.fase01.CanaisAtendimento.CorrespondentesBancarios.tests;

import br.com.realize.suites.AllTests;
import br.com.realize.suites.Contract;
import br.com.realize.tests.fase01.CanaisAtendimento.CorrespondentesBancarios.requests.GetCorrespondentesBancariosRequest;
import br.com.realize.tests.base.tests.BaseTest;
import br.com.realize.runners.fase01;
import br.com.realize.suites.Healthcheck;
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
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

@Epic("Fase 01")
@Feature("Canais de Atendimento")
@DisplayName("Correspondentes Bancários")
public class GetCorrespondentesBancariosTest extends BaseTest {

    GetCorrespondentesBancariosRequest getCorrespondentesBancariosRequest = new GetCorrespondentesBancariosRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase01.class})
    @DisplayName("Validar o retorno das informações gerais de correspondentes bancários")
    public void testCorrespondentesBancarios() throws Exception {
        String linkSelf = getCorrespondentesBancariosRequest.obterLinkSelfCorrespondentesBancarios();
        String linkNext = getCorrespondentesBancariosRequest.obterLinkNextCorrespondentesBancarios();
        String linkLast = getCorrespondentesBancariosRequest.obterLinkLastCorrespondentesBancarios();
        String linkFirst = getCorrespondentesBancariosRequest.obterLinkFirstCorrespondentesBancarios();
        String linkPrev = getCorrespondentesBancariosRequest.obterLinkPrevCorrespondentesBancarios();

        getCorrespondentesBancariosRequest.obterInformacoesCorrespondentesBancarios()
                .then()
                .log().all()
                .statusCode(200)
                .body("data.companies[0].cnpjNumber", equalTo("27351731"))
                .body("data.companies[0].name", equalTo("REALIZE CRÉDITO, FINANCIAMENTO E INVESTIMENTO S.A."))
                .body("meta.totalRecords", equalTo(366))
                .body("links.self", equalTo(linkSelf))
                .body("links.first", equalTo(linkFirst))
                .body("links.prev", equalTo(linkPrev))
                .body("links.next", equalTo(linkNext))
                .body("links.last", equalTo(linkLast));
    }
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({Contract.class, AllTests.class, fase01.class})
    @DisplayName("Navegar na primeira página")
    public void testNavegarPrimeiraPagina() throws Exception {
        String linkFirst = getCorrespondentesBancariosRequest.obterLinkFirstCorrespondentesBancarios();
        getCorrespondentesBancariosRequest.navegarPrimeiraPagina()
                .then()
                .statusCode(200)
                .body("links.self", equalTo(linkFirst));
    }
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({Contract.class, AllTests.class, fase01.class})
    @DisplayName("Navegar para a próxima página")
    public void testNavegarProximaPagina() throws Exception {
        String linkNext = getCorrespondentesBancariosRequest.obterLinkNextCorrespondentesBancarios();
        getCorrespondentesBancariosRequest.navegarProximaPagina()
                .then()
                .statusCode(200)
                .body("links.self", equalTo(linkNext));
    }
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({Contract.class, AllTests.class, fase01.class})
    @DisplayName("Navegar para a página anterior")
    public void testNavegarPaginaAnterior() throws Exception {
        String linkPrev = getCorrespondentesBancariosRequest.obterLinkPrevCorrespondentesBancarios();
        getCorrespondentesBancariosRequest.navegarPaginaAnterior()
                .then()
                .statusCode(200)
                .body("links.self", equalTo(linkPrev));
    }
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({Contract.class, AllTests.class, fase01.class})
    @DisplayName("Navegar para a última página")
    public void testNavegarUltimaPagina() throws Exception {
        String linkLast = getCorrespondentesBancariosRequest.obterLinkLastCorrespondentesBancarios();
        getCorrespondentesBancariosRequest.navegarUltimaPagina()
                .then()
                .statusCode(200)
                .body("links.self", equalTo(linkLast));
    }
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({Contract.class, AllTests.class, fase01.class})
    @DisplayName("Garantir o contrato do retorno de correspondentes bancários")
    public void testGarantirContratosCorrespondentesBancarios() throws Exception {
        getCorrespondentesBancariosRequest.obterInformacoesCorrespondentesBancarios()
                .then()
                .statusCode(200)
                .assertThat().body(matchesJsonSchema(
                new File(Utils.getContractsBasePath("fase01/CanaisAtendimento/CorrespondentesBancarios", "CorrespondentesBancarios"))));
    }

//VALIDAÇÕES DOS STATUS CODE DE ERRO
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase01.class})
    @DisplayName("Validar o retorno 404 - Número da página não localizado no endpoint de correspondentes bancários")
    public void testNumeroPaginaNaoLocalizado() throws Exception {
        getCorrespondentesBancariosRequest.numeroPaginaNaoLocalizado()
                .then()
                .log().all()
                .statusCode(404)
                .body("errors[0].title", equalTo("O recurso solicitado está acima do permitido."))
                .body("errors[0].detail", equalTo("O número da página (parâmetro page) é maior do que o permitido na consulta (15)."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase01.class})
    @DisplayName("Validar o retorno 404 - Path da API inválido no endpoint de correspondentes bancários")
    public void testPathInvalido() throws Exception {
        getCorrespondentesBancariosRequest.pathInvalido()
                .then()
                .log().all()
                .statusCode(404)
                .body("errors[0].title", equalTo("O recurso solicitado não existe."))
                .body("errors[0].detail", equalTo("O endereço informado para esse endpoint está incorreto."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase01.class})
    @DisplayName("Validar o retorno 400 - Número da página é zero no endpoint de correspondentes bancários")
    public void testNumeroPaginaZero() throws Exception {
        getCorrespondentesBancariosRequest.numeroPaginaZero()
                .then()
                .log().all()
                .statusCode(400)
                .body("errors[0].title", equalTo("Número da página inválido."))
                .body("errors[0].detail", equalTo("O número da página (parâmetro page) informado é inválido. São permitidos valores numéricos com valor mínimo igual a 1."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase01.class})
    @DisplayName("Validar o retorno 400 - Número da página inválido no endpoint de correspondentes bancários")
    public void testNumeroPaginaInvalido() throws Exception {
        getCorrespondentesBancariosRequest.numeroPaginaInvalido()
                .then()
                .log().all()
                .statusCode(400)
                .body("errors[0].title", equalTo("Número da página inválido."))
                .body("errors[0].detail", equalTo("O número da página (parâmetro page) informado é inválido. São permitidos valores numéricos com valor mínimo igual a 1."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase01.class})
    @DisplayName("Validar o retorno 400 - Tamanho da página é zero no endpoint de correspondentes bancários")
    public void testTamanhoPaginaZero() throws Exception {
        getCorrespondentesBancariosRequest.tamanhoPaginaZero()
                .then()
                .log().all()
                .statusCode(400)
                .body("errors[0].title", equalTo("Tamanho da página inválido."))
                .body("errors[0].detail", equalTo("O tamanho da página (parâmetro page-size) informado é inválido. São permitidos valores numéricos de 10 a 1000."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase01.class})
    @DisplayName("Validar o retorno 400 - Tamanho da página inválido no endpoint de correspondentes bancários")
    public void testTamanhoPaginaInvalido() throws Exception {
        getCorrespondentesBancariosRequest.tamanhoPaginaInvalido()
                .then()
                .log().all()
                .statusCode(400)
                .body("errors[0].title", equalTo("Tamanho da página inválido."))
                .body("errors[0].detail", equalTo("O tamanho da página (parâmetro page-size) informado é inválido. São permitidos valores numéricos de 10 a 1000."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase01.class})
    @DisplayName("Validar o retorno 422 - Tamanho da página superior ao permitido no endpoint de correspondentes bancários")
    public void testTamanhoPaginaSuperior() throws Exception {
        getCorrespondentesBancariosRequest.tamanhoPaginaSuperior()
                .then()
                .log().all()
                .statusCode(422)
                .body("errors[0].title", equalTo("O recurso solicitado está acima do permitido."))
                .body("errors[0].detail", equalTo("O tamanho da página (parâmetro page-size) informado é superior ao limite previsto (1000)."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase01.class})
    @DisplayName("405 - Validar o status code informando um método não suportado no endpoint de correspondentes bancários")
    public void testMetodoNaoSuportado() throws Exception {
        getCorrespondentesBancariosRequest.metodoNaoSuportado()
                .then()
                .log().all()
                .statusCode(405)
                .body("errors.title", hasItem("Ocorreu um erro inesperado ao processar sua requisição."))
                .body("errors.detail", hasItem("Request method 'POST' not supported"));
    }
}
