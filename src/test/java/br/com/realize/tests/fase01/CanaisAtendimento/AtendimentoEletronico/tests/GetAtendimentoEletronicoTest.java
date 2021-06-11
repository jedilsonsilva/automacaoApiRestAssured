package br.com.realize.tests.fase01.CanaisAtendimento.AtendimentoEletronico.tests;

import br.com.realize.runners.fase01;
import br.com.realize.runners.fase02;
import br.com.realize.suites.AllTests;
import br.com.realize.suites.Contract;
import br.com.realize.suites.Healthcheck;
import br.com.realize.tests.base.tests.BaseTest;
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
import java.util.concurrent.TimeUnit;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

@Epic("Fase 01")
@Feature("Canais de Atendimento")
@DisplayName("Atendimento Eletrônico")
public class GetAtendimentoEletronicoTest  extends BaseTest{

        GetAtendimentoEletronicoRequest getAtendimentoEletronicoRequest = new GetAtendimentoEletronicoRequest();
        String linkSelf = getAtendimentoEletronicoRequest.obterLinkSelfAtendimentoEletronico();

        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno de informações gerais do atendimento eletrônico")
        public void testAtendimentoEletronico() throws Exception {
            getAtendimentoEletronicoRequest.obterInformacoesAtendimentoEletronico()
                    .then()
                    .statusCode(200)
                    .time(lessThan(4L), TimeUnit.SECONDS)
                    .body("meta.totalPages", greaterThan(0))
                    .body("meta.totalRecords", greaterThan(0))
                    .body("links.self", is(linkSelf));
        }

        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno de informações do tipo de atendimento eletrônico Internet Banking")
        public void testInformacoesInternetBanking() throws Exception {
            getAtendimentoEletronicoRequest.obterInformacoesAtendimentoEletronico()
                    .then()
                    .statusCode(200)
                    .rootPath("data.companies.electronicChannels")
                    .body("identification[0].type[0]", equalTo("INTERNET_BANKING"))
                    .body("identification[0].additionalInfo[0]", equalTo("Pagamento de contas e visualização de limites."))
                    .body("identification[0].urls[0]", hasItems("https://www.realizesolucoesfinanceiras.com.br/cartoes-renner/login",
                            "https://www.realizesolucoesfinanceiras.com.br/cartoes-renner/login/data-nascimento"))
                    .body("services[0].name[0]", hasItem("CARTAO_CREDITO"))
                    .body("services[0].code[0]", hasItem("CARTAO_CREDITO"));
        }

        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno de informações do tipo de atendimento eletrônico Mobile Banking")
        public void testInformacoesMobileBanking() throws Exception {
            getAtendimentoEletronicoRequest.obterInformacoesAtendimentoEletronico()
                    .then()
                    .statusCode(200)
                    .rootPath("data.companies.electronicChannels")
                    .body("identification[0].type[1]", equalTo("MOBILE_BANKING"))
                    .body("identification[0].urls[1]", hasItems("https://play.google.com/store/apps/details?id=br.com.lojasrenner&hl=pt_BR",
                            "https://apps.apple.com/br/app/lojas-renner-roupas-e-sapatos/id567763947"))
                    .body("services[0].name[0]", hasItem("CARTAO_CREDITO"))
                    .body("services[0].code[0]", hasItem("CARTAO_CREDITO"));
        }

        @Test
        @Severity(SeverityLevel.BLOCKER)
        @Category({Contract.class, AllTests.class, fase01.class})
        @DisplayName("Garantir o contrato do retorno da lista de atendimento eletrônico")
        public void testGarantirContratosAtendimentoEletronico() throws Exception {
            getAtendimentoEletronicoRequest.obterInformacoesAtendimentoEletronico()
                    .then()
                    .statusCode(200)
                    .assertThat().body(matchesJsonSchema(
                    new File(Utils.getContractsBasePath("fase01/CanaisAtendimento/AtendimentoEletronico", "AtendimentoEletronico"))));
        }

        //VALIDAÇÕES DOS STATUS CODE DE ERRO
        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno 404 - Número da página não localizado no endpoint de atendimento eletrônico")
        public void testNumeroPaginaNaoLocalizado() throws Exception {
            getAtendimentoEletronicoRequest.numeroPaginaNaoLocalizado()
                    .then()
                    .statusCode(404)
                    .body("errors[0].title", equalTo("O recurso solicitado está acima do permitido."))
                    .body("errors[0].detail", equalTo("O número da página (parâmetro page) é maior do que o permitido na consulta (1)."));
        }

        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno 404 - Path da API inválido no endpoint de atendimento eletrônico")
        public void testPathInvalido() throws Exception {
            getAtendimentoEletronicoRequest.pathInvalido()
                    .then()
                    .statusCode(404)
                    .body("errors[0].title", equalTo("O recurso solicitado não existe."))
                    .body("errors[0].detail", equalTo("O endereço informado para esse endpoint está incorreto."));
        }

        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno 400 - Número da página é zero no endpoint de atendimento eletrônico")
        public void testNumeroPaginaZero() throws Exception {
            getAtendimentoEletronicoRequest.numeroPaginaZero()
                    .then()
                    .statusCode(400)
                    .body("errors[0].title", equalTo("Número da página inválido."))
                    .body("errors[0].detail", equalTo("O número da página (parâmetro page) informado é inválido. São permitidos valores numéricos com valor mínimo igual a 1."));
        }

        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno 400 - Número da página inválido no endpoint de atendimento eletrônico")
        public void testNumeroPaginaInvalido() throws Exception {
            getAtendimentoEletronicoRequest.numeroPaginaInvalido()
                    .then()
                    .statusCode(400)
                    .body("errors[0].title", equalTo("Número da página inválido."))
                    .body("errors[0].detail", equalTo("O número da página (parâmetro page) informado é inválido. São permitidos valores numéricos com valor mínimo igual a 1."));
        }

        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno 400 - Tamanho da página é zero no endpoint de atendimento eletrônico")
        public void testTamanhoPaginaZero() throws Exception {
            getAtendimentoEletronicoRequest.tamanhoPaginaZero()
                    .then()
                    .statusCode(400)
                    .body("errors[0].title", equalTo("Tamanho da página inválido."))
                    .body("errors[0].detail", equalTo("O tamanho da página (parâmetro page-size) informado é inválido. São permitidos valores numéricos de 10 a 1000."));
        }

        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno 400 - Tamanho da página inválido no endpoint de atendimento eletrônico")
        public void testTamanhoPaginaInvalido() throws Exception {
            getAtendimentoEletronicoRequest.tamanhoPaginaInvalido()
                    .then()
                    .statusCode(400)
                    .body("errors[0].title", equalTo("Tamanho da página inválido."))
                    .body("errors[0].detail", equalTo("O tamanho da página (parâmetro page-size) informado é inválido. São permitidos valores numéricos de 10 a 1000."));
        }

        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno 422 - Tamanho da página superior ao permitido no endpoint de atendimento eletrônico")
        public void testTamanhoPaginaSuperior() throws Exception {
            getAtendimentoEletronicoRequest.tamanhoPaginaSuperior()
                    .then()
                    .statusCode(422)
                    .body("errors[0].title", equalTo("O recurso solicitado está acima do permitido."))
                    .body("errors[0].detail", equalTo("O tamanho da página (parâmetro page-size) informado é superior ao limite previsto (1000)."));
        }
        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase02.class})
        @DisplayName("Validar o retorno 405 - Validar o status code informando um método não suportado no endpoint de atendimento eletrônico")
        public void testMetodoNaoSuportado() throws Exception {
                getAtendimentoEletronicoRequest.metodoNaoSuportado()
                        .then()
                        .statusCode(405)
                        .body("errors.title", hasItem("Ocorreu um erro inesperado ao processar sua requisição."))
                        .body("errors.detail", hasItem("Request method 'POST' not supported"));
        }
}
