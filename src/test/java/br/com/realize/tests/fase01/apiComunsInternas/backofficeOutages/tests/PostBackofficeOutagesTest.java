package br.com.realize.tests.fase01.apiComunsInternas.backofficeOutages.tests;

import br.com.realize.runners.fase01;
import br.com.realize.suites.AllTests;
import br.com.realize.suites.Contract;
import br.com.realize.suites.Healthcheck;
import br.com.realize.tests.base.tests.BaseTest;
import br.com.realize.tests.fase01.apiComunsInternas.backofficeOutages.requests.DelBackofficeOutagesRequest;
import br.com.realize.tests.fase01.apiComunsInternas.backofficeOutages.requests.PostBackofficeOutagesRequest;
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
import java.util.concurrent.TimeUnit;

import static br.com.realize.tests.fase01.apiComunsInternas.backofficeOutages.factory.IndisponibilidadeDataFactory.idParaDeletar;
import static br.com.realize.tests.fase01.apiComunsInternas.backofficeOutages.factory.IndisponibilidadeDataFactory.urlOutages;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.*;

@Epic("Fase 01")
@Feature("API - Comuns e Internas")
@DisplayName("Backoffice/Outages")
public class PostBackofficeOutagesTest extends BaseTest{

        PostBackofficeOutagesRequest postBackofficeOutagesRequest = new PostBackofficeOutagesRequest();
        DelBackofficeOutagesRequest delBackofficeOutagesRequest = new DelBackofficeOutagesRequest();

        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar a inclusão de indisponibilidade")
        public void testIncluirIndisponibilidade() throws Exception {
                String id;
                Response response = postBackofficeOutagesRequest.inserirIndisponibilidade()
                    .then().log().all()
                    .statusCode(200)
                    .time(lessThan(4L), TimeUnit.SECONDS)
                    .body("id", not(empty()))
 //FOI INCLUIDO A EXCLUSAO DA INDISPONIBILIDADE INCLUÍDA PARA NÃO TER PROBLEMA DE INDISPONIBILIDADE JÁ EXISTENTE NOS OUTROS CASOS DE TESTE.
                    .extract().response();
                JsonPath extractor = response.jsonPath();
                id = extractor.get("id");
                given()
                        .when().log().all()
                        .delete(urlOutages + id);
        }
        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar a inclusão de indisponibilidade quando informado uma data inferior a data atual no campo 'outageTime' ")
        public void testIncluirIndisponibilidadeDataInferiorAtual() throws Exception {
                postBackofficeOutagesRequest.inserirOutageInferiorDataHoraAtual()
                        .then()
                        .log().all()
                        .statusCode(400)
                        .time(lessThan(4L), TimeUnit.SECONDS)
                        .body("errors.title", hasItem("A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL."))
                        .body("errors.detail", hasItem("A outageTime não pode ser inferior ou igual a data/hora atual."));
        }
        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar a inclusão de indisponibilidade quando a flag 'isPartial' é 'true' e o UnavailableEndpoints é preenchido")
        public void testIncluirIndisponibilidadeIsPartialTrueComUnavailableEndpointsPreenchido() throws Exception {
                String id;
                Response response = postBackofficeOutagesRequest.inserirIsPartialTrueComUnavailableEndpointsPreenchido()
                        .then()
                        .log().all()
                        .statusCode(200)
                        .time(lessThan(4L), TimeUnit.SECONDS)
//FOI INCLUIDO A EXCLUSAO DA INDISPONIBILIDADE INCLUÍDA PARA NÃO TER PROBLEMA DE INDISPONIBILIDADE JÁ EXISTENTE NOS OUTROS CASOS DE TESTE.
                        .extract().response();
                JsonPath extractor = response.jsonPath();
                id = extractor.get("id");
                given()
                        .when().log().all()
                        .delete(urlOutages + id);
        }
        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar a inclusão de indisponibilidade quando a flag 'isPartial' é 'true' e o UnavailableEndpoints é vazio")
        public void testIncluirIndisponibilidadeIsPartialTrueComUnavailableEndpointsVazio() throws Exception {
                postBackofficeOutagesRequest.inserirIsPartialTrueComUnavailableEndpointsVazio()
                        .then()
                        .statusCode(400)
                        .time(lessThan(4L), TimeUnit.SECONDS)
                        .body("errors[0].title", equalTo("A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL."))
                        .body("errors[0].detail", equalTo("É preciso informar no mínimo um item na lista unavailableEndpoints quando o parâmetro isPartial for true."));;
        }
        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar a inclusão de indisponibilidade quando a flag 'isPartial' é 'false' e o UnavailableEndpoints é preenchido")
        public void testIncluirIndisponibilidadeIsPartialFalseComUnavailableEndpointsPreenchido() throws Exception {
                String id;
                Response response = postBackofficeOutagesRequest.inserirIsPartialFalseComUnavailableEndpointsPreenchido()
                        .then()
                        .log().all()
                        .statusCode(200)
                        .time(lessThan(4L), TimeUnit.SECONDS)
//FOI INCLUIDO A EXCLUSAO DA INDISPONIBILIDADE INCLUÍDA PARA NÃO TER PROBLEMA DE INDISPONIBILIDADE JÁ EXISTENTE NOS OUTROS CASOS DE TESTE.
                        .extract().response();
                JsonPath extractor = response.jsonPath();
                id = extractor.get("id");
                given()
                        .when().log().all()
                        .delete(urlOutages + id);
        }
        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar a inclusão de indisponibilidade quando a flag 'isPartial' é 'false' e o UnavailableEndpoints é vazio")
        public void testIncluirIndisponibilidadeIsPartialFalseComUnavailableEndpointsVazio() throws Exception {
                String id;
                Response response = postBackofficeOutagesRequest.inserirIsPartialFalseComUnavailableEndpointsVazio()
                        .then()
                        .log().all()
                        .statusCode(200)
                        .time(lessThan(4L), TimeUnit.SECONDS)
//FOI INCLUIDO A EXCLUSAO DA INDISPONIBILIDADE INCLUÍDA PARA NÃO TER PROBLEMA DE INDISPONIBILIDADE JÁ EXISTENTE NOS OUTROS CASOS DE TESTE.
                        .extract().response();
                JsonPath extractor = response.jsonPath();
                id = extractor.get("id");
                given()
                        .when().log().all()
                        .delete(urlOutages + id);
        }
        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno 404 - Path da API inválido no endpoint de inclusão de indisponibilidade")
        public void testPathInvalido() throws Exception {
                postBackofficeOutagesRequest.pathInvalido()
                        .then()
                        .statusCode(404)
                        .time(lessThan(4L), TimeUnit.SECONDS)
                        .body("errors[0].title", equalTo("O recurso solicitado não existe."))
                        .body("errors[0].detail", equalTo("O endereço informado para esse endpoint está incorreto."));
        }

        @Test
        @Severity(SeverityLevel.BLOCKER)
        @Category({Contract.class, AllTests.class, fase01.class})
        @DisplayName("Garantir o contrato de inclusão de indisponibilidade")
        public void testGarantirContratosInclusaoIndisponibilidade() throws Exception {
                String id;
                Response response = postBackofficeOutagesRequest.inserirIndisponibilidadeContrato()
                    .then().log().all()
                    .statusCode(200)
                    .time(lessThan(4L), TimeUnit.SECONDS)
                        .assertThat().body(matchesJsonSchema(
                    new File(Utils.getContractsBasePath("fase01/apiComunsInternas/backofficeOutages", "PostBackofficeOutages"))))
                        .extract().response();
                JsonPath extractor = response.jsonPath();
                id = extractor.get("id");
                given()
                        .when().log().all()
                        .delete(urlOutages + id);
        }

}
