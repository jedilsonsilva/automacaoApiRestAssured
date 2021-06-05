package br.com.realize.tests.fase01.API_ComunsInternas.BackofficeOutages.tests;

import br.com.realize.runners.fase01;
import br.com.realize.runners.fase02;
import br.com.realize.suites.AllTests;
import br.com.realize.suites.Contract;
import br.com.realize.suites.Healthcheck;
import br.com.realize.tests.base.tests.BaseTest;
import br.com.realize.tests.fase01.API_ComunsInternas.BackofficeOutages.requests.DelBackoffideOutagesRequest;
import br.com.realize.tests.fase01.API_ComunsInternas.BackofficeOutages.requests.GetBackoffideOutagesRequest;
import br.com.realize.tests.fase01.CanaisAtendimento.AtendimentoEletronico.requests.GetAtendimentoEletronicoRequest;
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

@Epic("Fase 01")
@Feature("API - Comuns e Internas")
@DisplayName("Backoffice/Outages")
public class DelBackoffideOutagesTest extends BaseTest{

        DelBackoffideOutagesRequest delBackoffideOutagesRequest = new DelBackoffideOutagesRequest();

        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar a exclusão de indisponibilidade")
        public void testDeletarIndisponibilidade() throws Exception {
            delBackoffideOutagesRequest.deletarIndisponibilidade()
                    .then()
                    .log().all()
                    .statusCode(204);
        }
}