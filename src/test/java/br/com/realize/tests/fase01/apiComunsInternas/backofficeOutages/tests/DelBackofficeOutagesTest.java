package br.com.realize.tests.fase01.apiComunsInternas.backofficeOutages.tests;

import br.com.realize.runners.fase01;
import br.com.realize.suites.AllTests;
import br.com.realize.suites.Healthcheck;
import br.com.realize.tests.base.tests.BaseTest;
import br.com.realize.tests.fase01.apiComunsInternas.backofficeOutages.requests.DelBackofficeOutagesRequest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.concurrent.TimeUnit;

import static br.com.realize.tests.fase01.apiComunsInternas.backofficeOutages.factory.IndisponibilidadeDataFactory.idParaDeletar;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

@Epic("Fase 01")
@Feature("API - Comuns e Internas")
@DisplayName("Backoffice/Outages")
public class DelBackofficeOutagesTest extends BaseTest{

        DelBackofficeOutagesRequest delBackofficeOutagesRequest = new DelBackofficeOutagesRequest();

        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar a exclusão de indisponibilidade")
        public void testDeletarIndisponibilidade() throws Exception {
                delBackofficeOutagesRequest.deletarIndisponibilidade()
                        .then()
                        .time(lessThan(4L), TimeUnit.SECONDS)
                        .statusCode(204);

        }
        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar a exclusão de indisponibilidade de um registro já excluído")
        public void testDeletarIndisponibilidadeJaExcluido() throws Exception {
                delBackofficeOutagesRequest.deletarIndisponibilidadeJaExcluido()
                        .then()
                        .time(lessThan(4L), TimeUnit.SECONDS)
                        .statusCode(404)
                        .body("errors[0].title", equalTo("Recurso indisponível."))
                        .body("errors[0].detail", equalTo("Nenhuma indisponibilidade encontrada com o id " + idParaDeletar + "."));
        }
        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar a exclusão de indisponibilidade")
        public void testDeletarIndisponibilidadeComIdInvalido() throws Exception {
                delBackofficeOutagesRequest.deletarIndisponibilidadeComIdInvalido()
                        .then()
                        .time(lessThan(4L), TimeUnit.SECONDS)
                        .statusCode(404)
                        .body("errors[0].title", equalTo("Recurso indisponível."))
                        .body("errors[0].detail", equalTo("Nenhuma indisponibilidade encontrada com o id idInvalidoParaTeste."));

        }
}