package br.com.realize.tests.fase01.apiComunsInternas.backofficeOutages.tests;

import br.com.realize.runners.fase01;
import br.com.realize.runners.fase02;
import br.com.realize.suites.AllTests;
import br.com.realize.suites.Contract;
import br.com.realize.suites.Healthcheck;
import br.com.realize.tests.base.tests.BaseTest;
import br.com.realize.tests.fase01.apiComunsInternas.backofficeOutages.requests.GetBackofficeOutagesRequest;
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
import static org.hamcrest.Matchers.equalTo;

@Epic("Fase 01")
@Feature("API - Comuns e Internas")
@DisplayName("Backoffice/Outages")
public class GetBackofficeOutagesTest extends BaseTest{

        GetBackofficeOutagesRequest getBackofficeOutagesRequest = new GetBackofficeOutagesRequest();

        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno de indisponibilidade sem informar o período inicial e final")
        public void testIndisponibilidadeSemPeriodoInformado() throws Exception {
                getBackofficeOutagesRequest.obterIndisponibilidadeSemPeriodoInformado()
                    .then()
                    .statusCode(200);
        }
        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno de indisponibilidade informando somente a data inicial")
        public void testIndisponibilidadeSomenteDataInicioInformada() throws Exception {
                getBackofficeOutagesRequest.obterIndisponibilidadeSomenteDataInicioInformada()
                        .then()
                        .statusCode(200);
        }
        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno de indisponibilidade informando as datas inicial e final")
        public void testIndisponibilidadeDataInicioFimInformadas() throws Exception {
                getBackofficeOutagesRequest.obterIndisponibilidadeDataInicioFimInformadas()
                        .then()
                        .statusCode(200);
        }
        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno de indisponibilidade informando somente a data fim")
        public void testIndisponibilidadeSomenteFimInformada() throws Exception {
                getBackofficeOutagesRequest.obterIndisponibilidadeSomenteDataFimInformada()
                        .then()
                        .statusCode(400)
                        .body("errors[0].title", equalTo("A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL."))
                        .body("errors[0].detail", equalTo("É preciso informar o parâmetro dataInicio quando se informa o parâmetro dataFim."));

        }
        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno de indisponibilidade informando a data início maior que a data fim")
        public void testIndisponibilidadeDataInicioMaiorDataFim() throws Exception {
                getBackofficeOutagesRequest.obterIndisponibilidadeDataInicioMaiorDataFim()
                        .then()
                        .statusCode(400)
                        .body("errors[0].title", equalTo("A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL."))
                        .body("errors[0].detail", equalTo("O parâmetro dataFim não pode ser menor que o parâmetro dataInicio."));

        }
        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno de indisponibilidade informando a data inicial inválida")
        public void testIndisponibilidadeDataInicialInvalida() throws Exception {
                getBackofficeOutagesRequest.obterIndisponibilidadeDataInicialInvalida()
                        .then()
                        .statusCode(400)
                        .body("errors[0].title", equalTo("A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL."))
                        .body("errors[0].detail", equalTo("O parâmetro dateFrom é inválido."));
        }
        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno de indisponibilidade informando a data final inválida")
        public void testIndisponibilidadeDataFinalInvalida() throws Exception {
                getBackofficeOutagesRequest.obterIndisponibilidadeDataFinalInvalida()
                        .then()
                        .statusCode(400)
                        .body("errors[0].title", equalTo("A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL."))
                        .body("errors[0].detail", equalTo("O parâmetro dateTo é inválido."));

        }
        @Test
        @Severity(SeverityLevel.BLOCKER)
        @Category({Contract.class, AllTests.class, fase01.class})
        @DisplayName("Garantir o contrato do retorno da consulta de indisponibilidade")
        public void testGarantirContratosConsultarIndisponibilidade() throws Exception {
                getBackofficeOutagesRequest.obterIndisponibilidadeSemPeriodoInformado()
                    .then()
                    .statusCode(200)
                    .assertThat().body(matchesJsonSchema(
                    new File(Utils.getContractsBasePath("fase01/apiComunsInternas/backofficeOutages", "GetBackofficeOutages"))));
        }
        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno 404 - Número da página não localizado no endpoint de backoffice/outage")
        public void testNumeroPaginaNaoLocalizado() throws Exception {
                getBackofficeOutagesRequest.numeroPaginaNaoLocalizado()
                    .then()
                    .statusCode(404)
                    .body("errors[0].title", equalTo("O recurso solicitado está acima do permitido."))
                    .body("errors[0].detail", equalTo("O número da página (parâmetro page) é maior do que o permitido na consulta (2)."));
        }
        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno 404 - Path da API inválido no endpoint de backoffice/outage")
        public void testPathInvalido() throws Exception {
                getBackofficeOutagesRequest.pathInvalido()
                    .then()
                    .statusCode(404)
                    .body("errors[0].title", equalTo("O recurso solicitado não existe."))
                    .body("errors[0].detail", equalTo("O endereço informado para esse endpoint está incorreto."));
        }
        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno 400 - Número da página é zero no endpoint de backoffice/outage")
        public void testNumeroPaginaZero() throws Exception {
                getBackofficeOutagesRequest.numeroPaginaZero()
                    .then()
                    .statusCode(400)
                    .body("errors[0].title", equalTo("Número da página inválido."))
                    .body("errors[0].detail", equalTo("O número da página (parâmetro page) informado é inválido. São permitidos valores numéricos com valor mínimo igual a 1."));
        }
        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno 400 - Número da página inválido no endpoint de backoffice/outage")
        public void testNumeroPaginaInvalido() throws Exception {
                getBackofficeOutagesRequest.numeroPaginaInvalido()
                    .then()
                    .statusCode(400)
                    .body("errors[0].title", equalTo("Número da página inválido."))
                    .body("errors[0].detail", equalTo("O número da página (parâmetro page) informado é inválido. São permitidos valores numéricos com valor mínimo igual a 1."));
        }
        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno 400 - Tamanho da página é zero no endpoint de backoffice/outage")
        public void testTamanhoPaginaZero() throws Exception {
                getBackofficeOutagesRequest.tamanhoPaginaZero()
                    .then()
                    .statusCode(400)
                    .body("errors[0].title", equalTo("Tamanho da página inválido."))
                    .body("errors[0].detail", equalTo("O tamanho da página (parâmetro page-size) informado é inválido. São permitidos valores numéricos de 10 a 1000."));
        }
        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno 400 - Tamanho da página inválido no endpoint de backoffice/outage")
        public void testTamanhoPaginaInvalido() throws Exception {
                getBackofficeOutagesRequest.tamanhoPaginaInvalido()
                    .then()
                    .statusCode(400)
                    .body("errors[0].title", equalTo("Tamanho da página inválido."))
                    .body("errors[0].detail", equalTo("O tamanho da página (parâmetro page-size) informado é inválido. São permitidos valores numéricos de 10 a 1000."));
        }
        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno 422 - Tamanho da página superior ao permitido no endpoint de backoffice/outage")
        public void testTamanhoPaginaSuperior() throws Exception {
                getBackofficeOutagesRequest.tamanhoPaginaSuperior()
                    .then()
                    .statusCode(422)
                    .body("errors[0].title", equalTo("O recurso solicitado está acima do permitido."))
                    .body("errors[0].detail", equalTo("O tamanho da página (parâmetro page-size) informado é superior ao limite previsto (1000)."));
        }
        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase02.class})
        @DisplayName("Validar o retorno 405 - Validar o status code informando um método não suportado no endpoint de backoffice/outage")
        public void testMetodoNaoSuportado() throws Exception {
                getBackofficeOutagesRequest.metodoNaoSuportado()
                        .then()
                        .statusCode(405)
                        .body("errors.title", hasItem("Ocorreu um erro inesperado ao processar sua requisição."))
                        .body("errors.detail", hasItem("Request method 'DELETE' not supported"));
        }
}
