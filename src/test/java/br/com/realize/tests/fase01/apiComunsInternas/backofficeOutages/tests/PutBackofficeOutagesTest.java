package br.com.realize.tests.fase01.apiComunsInternas.backofficeOutages.tests;

import br.com.realize.runners.fase01;
import br.com.realize.suites.AllTests;
import br.com.realize.suites.Contract;
import br.com.realize.suites.Healthcheck;
import br.com.realize.tests.base.tests.BaseTest;
import br.com.realize.tests.fase01.apiComunsInternas.backofficeOutages.requests.PutBackofficeOutagesRequest;
import br.com.realize.utils.Utils;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;

import static br.com.realize.tests.fase01.apiComunsInternas.backofficeOutages.factory.IndisponibilidadeDataFactory.urlOutages;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.equalTo;

@Epic("Fase 01")
@Feature("API - Comuns e Internas")
@DisplayName("Backoffice/Outages")
public class PutBackofficeOutagesTest extends BaseTest{

        PutBackofficeOutagesRequest putBackofficeOutagesRequest = new PutBackofficeOutagesRequest();

        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar a alteração de indisponibilidade")
        public void testAlterarIndisponibilidade() throws Exception {
                String id;
                Response response = putBackofficeOutagesRequest.alterarIndisponibilidade()
                        .then()
                        .statusCode(200)
//FOI INCLUIDO A EXCLUSAO DA INDISPONIBILIDADE INCLUÍDA PARA NÃO TER PROBLEMA DE INDISPONIBILIDADE JÁ EXISTENTE NOS OUTROS CASOS DE TESTE.
                        .extract().response();
                JsonPath extractor = response.jsonPath();
                id = extractor.get("id");
                given()
                        .when()
                        .delete(urlOutages + id);
        }
        @Test
        @Severity(SeverityLevel.BLOCKER)
        @Category({Contract.class, AllTests.class, fase01.class})
        @DisplayName("Garantir o contrato do retorno da alteração de indisponibilidade")
        public void testGarantirContratosAlteracaoIndisponibilidade() throws Exception {
                String id;
                Response response = putBackofficeOutagesRequest.alterarIndisponibilidadeContract()
                        .then()
                        .statusCode(200)
                        .assertThat().body(matchesJsonSchema(
                        new File(Utils.getContractsBasePath("fase01/apiComunsInternas/backofficeOutages", "PutBackofficeOutages"))))
                        //FOI INCLUIDO A EXCLUSAO DA INDISPONIBILIDADE INCLUÍDA PARA NÃO TER PROBLEMA DE INDISPONIBILIDADE JÁ EXISTENTE NOS OUTROS CASOS DE TESTE.
                        .extract().response();
                JsonPath extractor = response.jsonPath();
                id = extractor.get("id");
                given()
                        .when()
                        .delete(urlOutages + id);
        }
        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar a alteração de indisponibilidade informando uma data inválida")
        public void testAlterarIndisponibilidadeComDataOutageInvalida() throws Exception {
                putBackofficeOutagesRequest.alterarIndisponibilidadeComDataInvalida()
                        .then()
                        .statusCode(400)
                        .body("errors[0].title", equalTo("A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL."))
                        .body("errors[0].detail", equalTo("Text '30/10/2021' could not be parsed at index 0"));
        }
        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar a alteração de indisponibilidade informando uma data anterior à data de inclusão da indisponibilidade")
        public void testAlterarIndisponibilidadeDataInferiorADataDaInclusaoDaIndisponibilidade() throws Exception {
                putBackofficeOutagesRequest.alterarIndisponibilidadeDataInferiorADataDaInclusaoDaIndisponibilidade()
                        .then()
                        .statusCode(400)
                        .body("errors[0].title", equalTo("A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL."));
        }
        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar a alteração de indisponibilidade alterando a flag IsPartial de true para false com o campo UnavailableEndpoints vazio")
        public void testAlterarIndisponibilidadeFlagIsPartialDeTrueParaFalseComUnavailableEndpointsVazio() throws Exception {
                String id;
                Response response = putBackofficeOutagesRequest.alterarIndisponibilidadeFlagIsPartialDeTrueParaFalseComUnavailableEndpointsVazio()
                        .then()
                        .statusCode(200)
                        //FOI INCLUIDO A EXCLUSAO DA INDISPONIBILIDADE INCLUÍDA PARA NÃO TER PROBLEMA DE INDISPONIBILIDADE JÁ EXISTENTE NOS OUTROS CASOS DE TESTE.
                        .extract().response();
                JsonPath extractor = response.jsonPath();
                id = extractor.get("id");
                given()
                        .when()
                        .delete(urlOutages + id);
        }
        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar a alteração de indisponibilidade alterando a flag IsPartial de true para false com o campo UnavailableEndpoints preenchido")
        public void testAlterarIndisponibilidadeFlagIsPartialDeTrueParaFalseComUnavailableEndpointsPreenchido() throws Exception {
                String id;
                Response response = putBackofficeOutagesRequest.alterarIndisponibilidadeFlagIsPartialDeTrueParaFalseComUnavailableEndpointsPreenchido()
                        .then()
                        .statusCode(200)
                        //FOI INCLUIDO A EXCLUSAO DA INDISPONIBILIDADE INCLUÍDA PARA NÃO TER PROBLEMA DE INDISPONIBILIDADE JÁ EXISTENTE NOS OUTROS CASOS DE TESTE.
                        .extract().response();
                JsonPath extractor = response.jsonPath();
                id = extractor.get("id");
                given()
                        .when()
                        .delete(urlOutages + id);
        }
        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar a alteração de indisponibilidade alterando a flag IsPartial de false para true com o campo UnavailableEndpoints vazio")
        public void testAlterarIndisponibilidadeFlagIsPartialDeFalseParaTrueComUnavailableEndpointsVazio() throws Exception {
                putBackofficeOutagesRequest.alterarIndisponibilidadeFlagIsPartialDeFalseParaTrueComUnavailableEndpointsVazio()
                        .then()
                        .statusCode(400)
                        .body("errors[0].title", equalTo("A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL."))
                        .body("errors[0].detail", equalTo("É preciso informar no mínimo um item na lista unavailableEndpoints quando o parâmetro isPartial for true."));;
        }
        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar a alteração de indisponibilidade alterando a flag IsPartial de false para true com o campo UnavailableEndpoints preenchido")
        public void testAlterarIndisponibilidadeFlagIsPartialDeFalseParaTrueComUnavailableEndpointsPreenchido() throws Exception {
                String id;
                Response response = putBackofficeOutagesRequest.alterarIndisponibilidadeFlagIsPartialDeFalseParaTrueComUnavailableEndpointsPreenchido()
                        .then()
                        .statusCode(200)
                        //FOI INCLUIDO A EXCLUSAO DA INDISPONIBILIDADE INCLUÍDA PARA NÃO TER PROBLEMA DE INDISPONIBILIDADE JÁ EXISTENTE NOS OUTROS CASOS DE TESTE.
                        .extract().response();
                JsonPath extractor = response.jsonPath();
                id = extractor.get("id");
                given()
                        .when()
                        .delete(urlOutages + id);;
        }
}
