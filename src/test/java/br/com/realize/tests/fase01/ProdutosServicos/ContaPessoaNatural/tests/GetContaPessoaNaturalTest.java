package br.com.realize.tests.fase01.ProdutosServicos.ContaPessoaNatural.tests;

import br.com.realize.runners.fase01;
import br.com.realize.suites.AllTests;
import br.com.realize.suites.Contract;
import br.com.realize.suites.Healthcheck;
import br.com.realize.tests.base.tests.BaseTest;
import br.com.realize.tests.fase01.ProdutosServicos.CartaoCreditoPessoaNatural.requests.GetCartaoCreditoPessoaNaturalRequest;
import br.com.realize.tests.fase01.ProdutosServicos.ContaPessoaNatural.requests.GetContaPessoaNaturalRequest;
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
@Feature("Produtos e Serviços")
@DisplayName("Conta Pessoa Natural")
public class GetContaPessoaNaturalTest extends BaseTest{

        GetContaPessoaNaturalRequest getContaPessoaNaturalRequest = new GetContaPessoaNaturalRequest();

        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno das informações de conta pessoa natural")
        public void testContaPessoaNatural() throws Exception {
                getContaPessoaNaturalRequest.obterInformacoesContaPessoaNatural()
                    .then()
                    .log().all()
                    .statusCode(200);
        }
        @Test
        @Severity(SeverityLevel.BLOCKER)
        @Category({Contract.class, AllTests.class, fase01.class})
        @DisplayName("Garantir o contrato do retorno do endpoint de conta pessoa natural")
        public void testGarantirContratosContaPessoaNatural() throws Exception {
                getContaPessoaNaturalRequest.obterInformacoesContaPessoaNatural()
                    .then()
                    .statusCode(200)
                    .assertThat().body(matchesJsonSchema(
                    new File(Utils.getContractsBasePath("fase01/ProdutosServicos/ContaPessoaNatural", "ContaPessoaNatural"))));
        }

        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno 404 - Número da página não localizado no endpoint de conta pessoa natural")
        public void testNumeroPaginaNaoLocalizado() throws Exception {
                getContaPessoaNaturalRequest.numeroPaginaNaoLocalizado()
                    .then()
                    .log().all()
                    .statusCode(404)
                    .body("errors[0].title", equalTo("O recurso solicitado está acima do permitido."))
                    .body("errors[0].detail", equalTo("O número da página (parâmetro page) é maior do que o permitido na consulta (1)."));
        }

        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno 404 - Path da API inválido no endpoint de conta pessoa natural")
        public void testPathInvalido() throws Exception {
                getContaPessoaNaturalRequest.pathInvalido()
                    .then()
                    .log().all()
                    .statusCode(404)
                    .body("errors[0].title", equalTo("O recurso solicitado não existe."))
                    .body("errors[0].detail", equalTo("O endereço informado para esse endpoint está incorreto."));
        }

        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno 400 - Número da página é zero no endpoint de conta pessoa natural")
        public void testNumeroPaginaZero() throws Exception {
                getContaPessoaNaturalRequest.numeroPaginaZero()
                    .then()
                    .log().all()
                    .statusCode(400)
                    .body("errors[0].title", equalTo("Número da página inválido."))
                    .body("errors[0].detail", equalTo("O número da página (parâmetro page) informado é inválido. São permitidos valores numéricos com valor mínimo igual a 1."));
        }

        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno 400 - Número da página inválido no endpoint de conta pessoa natural")
        public void testNumeroPaginaInvalido() throws Exception {
                getContaPessoaNaturalRequest.numeroPaginaInvalido()
                    .then()
                    .log().all()
                    .statusCode(400)
                    .body("errors[0].title", equalTo("Número da página inválido."))
                    .body("errors[0].detail", equalTo("O número da página (parâmetro page) informado é inválido. São permitidos valores numéricos com valor mínimo igual a 1."));
        }

        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno 400 - Tamanho da página é zero no endpoint de cartão de crédito pesso natural")
        public void testTamanhoPaginaZero() throws Exception {
                getContaPessoaNaturalRequest.tamanhoPaginaZero()
                    .then()
                    .log().all()
                    .statusCode(400)
                    .body("errors[0].title", equalTo("Tamanho da página inválido."))
                    .body("errors[0].detail", equalTo("O tamanho da página (parâmetro page-size) informado é inválido. São permitidos valores numéricos de 10 a 1000."));
        }

        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno 400 - Tamanho da página inválido no endpoint de conta pessoa natural")
        public void testTamanhoPaginaInvalido() throws Exception {
                getContaPessoaNaturalRequest.tamanhoPaginaInvalido()
                    .then()
                    .log().all()
                    .statusCode(400)
                    .body("errors[0].title", equalTo("Tamanho da página inválido."))
                    .body("errors[0].detail", equalTo("O tamanho da página (parâmetro page-size) informado é inválido. São permitidos valores numéricos de 10 a 1000."));
        }

        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno 422 - Tamanho da página superior ao permitido no endpoint de conta pessoa natural")
        public void testTamanhoPaginaSuperior() throws Exception {
                getContaPessoaNaturalRequest.tamanhoPaginaSuperior()
                    .then()
                    .log().all()
                    .statusCode(422)
                    .body("errors[0].title", equalTo("O recurso solicitado está acima do permitido."))
                    .body("errors[0].detail", equalTo("O tamanho da página (parâmetro page-size) informado é superior ao limite previsto (1000)."));
        }
        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno 404 - Tamanho da página é inferior a 11 no endpoint de conta pessoa natural")
        public void testTamanhoPaginaInferiorDez() throws Exception {
                getContaPessoaNaturalRequest.tamanhoPaginaInveriorDez()
                        .then()
                        .log().all()
                        .statusCode(400)
                        .body("errors[0].title", equalTo("Tamanho da página inválido."))
                        .body("errors[0].detail", equalTo("O tamanho da página (parâmetro page-size) informado é inválido. São permitidos valores numéricos de 10 a 1000."));
        }

        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("405 - Validar o status code informando um método não suportado no endpoint de conta pessoa natural")
        public void testMetodoNaoSuportado() throws Exception {
                getContaPessoaNaturalRequest.metodoNaoSuportado()
                        .then()
                        .log().all()
                        .statusCode(405)
                        .body("errors.title", hasItem("Ocorreu um erro inesperado ao processar sua requisição."))
                        .body("errors.detail", hasItem("Request method 'POST' not supported"));
        }

}
