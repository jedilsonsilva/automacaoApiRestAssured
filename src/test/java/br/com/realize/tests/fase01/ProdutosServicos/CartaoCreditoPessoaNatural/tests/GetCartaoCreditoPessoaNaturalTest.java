package br.com.realize.tests.fase01.ProdutosServicos.CartaoCreditoPessoaNatural.tests;

import br.com.realize.runners.fase01;
import br.com.realize.suites.AllTests;
import br.com.realize.suites.Contract;
import br.com.realize.suites.Healthcheck;
import br.com.realize.tests.base.tests.BaseTest;
import br.com.realize.tests.fase01.ProdutosServicos.CartaoCreditoPessoaNatural.requests.GetCartaoCreditoPessoaNaturalRequest;
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
public class GetCartaoCreditoPessoaNaturalTest extends BaseTest{

        GetCartaoCreditoPessoaNaturalRequest getCartaoCreditoPessoaNaturalRequest = new GetCartaoCreditoPessoaNaturalRequest();

        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Obter informações do cartão de crédito pessoa natural com a bandeira VISA.")
        public void testCartaoCreditoPessoaNaturalBandeiraVisa() throws Exception {
            String linkSelf = getCartaoCreditoPessoaNaturalRequest.obterLinkSelfCartaoCreditoPessoaNatural();
            getCartaoCreditoPessoaNaturalRequest.obterInformacoesCartaoCreditoPessoaNatural()
                    .then()
                    .log().all()
                    .statusCode(200)
                    .rootPath("data.companies[0].personalCreditCards.identification.product")
                    .body("type[0]", equalTo("STANDARD_INTERNACIONAL"))
                    .noRootPath()
                    .rootPath("data.companies[0].personalCreditCards.identification.creditCard")
                    .body("network[0]", equalTo("VISA"))
                    .noRootPath()
                    .body("links.self", equalTo(linkSelf))
                    .body("meta.totalRecords", equalTo(3));
        }
        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Obter informações do cartão de crédito pessoa natural com a bandeira MASTERCARD.")
        public void testCartaoCreditoPessoaNaturalBandeiraMastercard() throws Exception {
                String linkSelf = getCartaoCreditoPessoaNaturalRequest.obterLinkSelfCartaoCreditoPessoaNatural();
                getCartaoCreditoPessoaNaturalRequest.obterInformacoesCartaoCreditoPessoaNatural()
                        .then()
                        .log().all()
                        .statusCode(200)
                        .rootPath("data.companies[0].personalCreditCards.identification.product")
                        .body("type[1]", equalTo("STANDARD_INTERNACIONAL"))
                        .noRootPath()
                        .rootPath("data.companies[0].personalCreditCards.identification.creditCard")
                        .body("network[1]", equalTo("MASTERCARD"))
                        .noRootPath()
                        .body("links.self", equalTo(linkSelf))
                        .body("meta.totalRecords", equalTo(3));
        }
        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Obter informações do cartão de crédito pessoa natural com a bandeira própria.")
        public void testCartaoCreditoPessoaNaturalBandeiraPropria() throws Exception {
                String linkSelf = getCartaoCreditoPessoaNaturalRequest.obterLinkSelfCartaoCreditoPessoaNatural();
                getCartaoCreditoPessoaNaturalRequest.obterInformacoesCartaoCreditoPessoaNatural()
                        .then()
                        .log().all()
                        .statusCode(200)
                        .rootPath("data.companies[0].personalCreditCards.identification.product")
                        .body("type[2]", equalTo("COMPRAS"))
                        .noRootPath()
                        .rootPath("data.companies[0].personalCreditCards.identification.creditCard")
                        .body("network[2]", equalTo("BANDEIRA_PROPRIA"))
                        .noRootPath()
                        .body("links.self", equalTo(linkSelf))
                        .body("meta.totalRecords", equalTo(3));
        }

        @Test
        @Severity(SeverityLevel.BLOCKER)
        @Category({Contract.class, AllTests.class, fase01.class})
        @DisplayName("Garantir o contrato do retorno da lista de atendimento eletrônico")
        public void testGarantirContratosCreditoPessoaNatural() throws Exception {
                getCartaoCreditoPessoaNaturalRequest.obterInformacoesCartaoCreditoPessoaNatural()
                    .then()
                    .statusCode(200)
                    .assertThat().body(matchesJsonSchema(
                    new File(Utils.getContractsBasePath("fase01/ProdutosServicos/CartaoCreditoPessoaNatural", "CartaoCreditoPessoaNatural"))));
        }

        //VALIDAÇÕES DOS STATUS CODE DE ERRO
        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno 404 - Número da página não localizado.")
        public void testNumeroPaginaNaoLocalizado() throws Exception {
                getCartaoCreditoPessoaNaturalRequest.numeroPaginaNaoLocalizado()
                    .then()
                    .log().all()
                    .statusCode(404)
                    .body("errors[0].title", equalTo("O recurso solicitado está acima do permitido."))
                    .body("errors[0].detail", equalTo("O número da página (parâmetro page) é maior do que o permitido na consulta (1)."));
        }

        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno 404 - Path da API inválido")
        public void testPathInvalido() throws Exception {
                getCartaoCreditoPessoaNaturalRequest.pathInvalido()
                    .then()
                    .log().all()
                    .statusCode(404)
                    .body("errors[0].title", equalTo("O recurso solicitado não existe."))
                    .body("errors[0].detail", equalTo("O endereço informado para esse endpoint está incorreto."));
        }

        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno 400 - Número da página é zero.")
        public void testNumeroPaginaZero() throws Exception {
                getCartaoCreditoPessoaNaturalRequest.numeroPaginaZero()
                    .then()
                    .log().all()
                    .statusCode(400)
                    .body("errors[0].title", equalTo("Número da página inválido."))
                    .body("errors[0].detail", equalTo("O número da página (parâmetro page) informado é inválido. São permitidos valores numéricos com valor mínimo igual a 1."));
        }

        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno 400 - Número da página inválido.")
        public void testNumeroPaginaInvalido() throws Exception {
                getCartaoCreditoPessoaNaturalRequest.numeroPaginaInvalido()
                    .then()
                    .log().all()
                    .statusCode(400)
                    .body("errors[0].title", equalTo("Número da página inválido."))
                    .body("errors[0].detail", equalTo("O número da página (parâmetro page) informado é inválido. São permitidos valores numéricos com valor mínimo igual a 1."));
        }

        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno 400 - Tamanho da página é zero.")
        public void testTamanhoPaginaZero() throws Exception {
                getCartaoCreditoPessoaNaturalRequest.tamanhoPaginaZero()
                    .then()
                    .log().all()
                    .statusCode(400)
                    .body("errors[0].title", equalTo("Tamanho da página inválido."))
                    .body("errors[0].detail", equalTo("O tamanho da página (parâmetro page-size) informado é inválido. São permitidos valores numéricos de 10 a 1000."));
        }

        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno 400 - Tamanho da página inválido.")
        public void testTamanhoPaginaInvalido() throws Exception {
                getCartaoCreditoPessoaNaturalRequest.tamanhoPaginaInvalido()
                    .then()
                    .log().all()
                    .statusCode(400)
                    .body("errors[0].title", equalTo("Tamanho da página inválido."))
                    .body("errors[0].detail", equalTo("O tamanho da página (parâmetro page-size) informado é inválido. São permitidos valores numéricos de 10 a 1000."));
        }

        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno 422 - Tamanho da página superior ao permitido.")
        public void testTamanhoPaginaSuperior() throws Exception {
                getCartaoCreditoPessoaNaturalRequest.tamanhoPaginaSuperior()
                    .then()
                    .log().all()
                    .statusCode(422)
                    .body("errors[0].title", equalTo("O recurso solicitado está acima do permitido."))
                    .body("errors[0].detail", equalTo("O tamanho da página (parâmetro page-size) informado é superior ao limite previsto (1000)."));
        }
        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("405 - Validar o status code informando um método não suportado.")
        public void testMetodoNaoSuportado() throws Exception {
                getCartaoCreditoPessoaNaturalRequest.metodoNaoSuportado()
                        .then()
                        .log().all()
                        .statusCode(405)
                        .body("errors.title", hasItem("Ocorreu um erro inesperado ao processar sua requisição."))
                        .body("errors.detail", hasItem("Request method 'POST' not supported"));
        }

}
