package br.com.realize.tests.fase01.API_ComunsInternas.BackofficeOutages.tests;

import br.com.realize.runners.fase01;
import br.com.realize.runners.fase02;
import br.com.realize.suites.AllTests;
import br.com.realize.suites.Contract;
import br.com.realize.suites.Healthcheck;
import br.com.realize.tests.base.tests.BaseTest;
import br.com.realize.tests.fase01.API_ComunsInternas.BackofficeOutages.requests.PostBackoffideOutagesRequest;
import br.com.realize.tests.fase01.API_ComunsInternas.BackofficeOutages.requests.PutBackoffideOutagesRequest;
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
public class PutBackoffideOutagesTest extends BaseTest{

        PutBackoffideOutagesRequest putBackoffideOutagesRequest = new PutBackoffideOutagesRequest();

        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar a alteração de indisponibilidade")
        public void testAlterarIndisponibilidade() throws Exception {
                putBackoffideOutagesRequest.alterarIndisponibilidade()
                        .then()
                        .log().all()
                        .statusCode(200);
        }
        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar a alteração de indisponibilidade quando informado uma data inferior a data atual no campo 'outageTime' ")
        public void testAlterarIndisponibilidadeDataInferiorAtual() throws Exception {
                putBackoffideOutagesRequest.alterarOutageInferiorDataHoraAtual()
                        .then()
                        .log().all()
                        .statusCode(400);
        }
        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar a alteração de indisponibilidade quando informado uma data inferior a data atual no campo 'outageTime' ")
        public void testAlterarIndisponibilidadeIsPartialTrue() throws Exception {
                putBackoffideOutagesRequest.alterarIsPartialTrue()
                        .then()
                        .log().all()
                        .statusCode(200);
        }
        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar a alteração de indisponibilidade com a flag 'isPartial' igual a 'true' e o campo 'unavailableEndpoints' não preenchido.")
        public void testAlterarIndisponibilidadeCampoObrigatorioNaoPreenchido() throws Exception {
                putBackoffideOutagesRequest.alterarCampoObrigatorioNaoPreenchido()
                        .then()
                        .log().all()
                        .statusCode(400);
        }

        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno 404 - Path da API inválido no endpoint de alteração de indisponibilidade")
        public void testPathInvalido() throws Exception {
                putBackoffideOutagesRequest.pathInvalido()
                        .then()
                        .log().all()
                        .statusCode(404)
                        .body("errors[0].title", equalTo("O recurso solicitado não existe."))
                        .body("errors[0].detail", equalTo("O endereço informado para esse endpoint está incorreto."));
        }

        @Test
        @Severity(SeverityLevel.BLOCKER)
        @Category({Contract.class, AllTests.class, fase01.class})
        @DisplayName("Garantir o contrato do retorno da alteração de indisponibilidade")
        public void testGarantirContratosAlteracaoIndisponibilidade() throws Exception {
                putBackoffideOutagesRequest.alterarIndisponibilidade()
                        .then()
                        .statusCode(200)
                        .assertThat().body(matchesJsonSchema(
                        new File(Utils.getContractsBasePath("fase01/API_ComunsInternas/BackofficeOutages", "PutBackofficeOutages"))));
        }

}
