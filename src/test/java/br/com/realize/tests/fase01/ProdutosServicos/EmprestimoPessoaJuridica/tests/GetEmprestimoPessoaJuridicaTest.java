package br.com.realize.tests.fase01.ProdutosServicos.EmprestimoPessoaJuridica.tests;

import br.com.realize.runners.fase01;
import br.com.realize.runners.fase02;
import br.com.realize.suites.AllTests;
import br.com.realize.suites.Contract;
import br.com.realize.suites.Healthcheck;
import br.com.realize.tests.base.tests.BaseTest;
import br.com.realize.tests.fase01.ProdutosServicos.CartaoCreditoPessoaNatural.requests.GetCartaoCreditoPessoaNaturalRequest;
import br.com.realize.tests.fase01.ProdutosServicos.EmprestimoPessoaJuridica.requests.GetEmprestimoPessoaJuridicaRequest;
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

@Epic("Fase 01")
@Feature("Produtos e Serviços")
@DisplayName("Empréstimos Pessoa Jurídica")
public class GetEmprestimoPessoaJuridicaTest extends BaseTest{

        GetEmprestimoPessoaJuridicaRequest getEmprestimoPessoaJuridicaRequest = new GetEmprestimoPessoaJuridicaRequest();
        String linkSelf = getEmprestimoPessoaJuridicaRequest.obterLinkSelfEmprestimoPessoaJuridica();

        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno das informações do endpoint de empréstimo pessoa jurídica")
        public void testEmprestimoMicrocreditoPessoaJuridica() throws Exception {
                getEmprestimoPessoaJuridicaRequest.obterInformacoesEmprestimoPessoaJuridica()
                    .then()
                    .statusCode(200)
                    .rootPath("data.brand.companies[0].businessLoans")
                    .body("type[0]", equalTo("EMPRESTIMO_MICROCREDITO_PRODUTIVO_ORIENTADO"))
                    .body("type[1]", equalTo("EMPRESTIMO_CHEQUE_ESPECIAL"))
                    .body("type[2]", equalTo("EMPRESTIMO_CONTA_GARANTIDA"))
                    .body("type[3]", equalTo("EMPRESTIMO_CAPITAL_GIRO_PRAZO_VENCIMENTO_ATE_365_DIAS"))
                    .body("type[4]", equalTo("EMPRESTIMO_CAPITAL_GIRO_PRAZO_VENCIMENTO_SUPERIOR_365_DIAS"))
                    .body("type[5]", equalTo("EMPRESTIMO_CAPITAL_GIRO_ROTATIVO"))
                    .noRootPath()
                    .time(lessThan(4L), TimeUnit.SECONDS)
                    .body("meta.totalPages", greaterThan(0))
                    .body("meta.totalRecords", greaterThan(0))
                    .body("links.self", is(linkSelf));
        }
        @Test
        @Severity(SeverityLevel.BLOCKER)
        @Category({Contract.class, AllTests.class, fase01.class})
        @DisplayName("Garantir o contrato do retorno do endpoint de empréstimo pessoa jurídica.")
        public void testGarantirEmprestimoPessoaJuridica() throws Exception {
                getEmprestimoPessoaJuridicaRequest.obterInformacoesEmprestimoPessoaJuridica()
                    .then()
                    .statusCode(200)
                    .assertThat().body(matchesJsonSchema(
                    new File(Utils.getContractsBasePath("fase01/ProdutosServicos/EmprestimoPessoaJuridica", "EmprestimoPessoaJuridica"))));
        }

        //VALIDAÇÕES DOS STATUS CODE DE ERRO
        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno 404 - Número da página não localizado no endpoint de cartão de empréstipo pessoa jurídica")
        public void testNumeroPaginaNaoLocalizado() throws Exception {
                getEmprestimoPessoaJuridicaRequest.numeroPaginaNaoLocalizado()
                    .then()
                    .statusCode(404)
                    .body("errors[0].title", equalTo("O recurso solicitado está acima do permitido."))
                    .body("errors[0].detail", equalTo("O número da página (parâmetro page) é maior do que o permitido na consulta (1)."));
        }

        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno 404 - Path da API inválido no endpoint de cartão de empréstipo pessoa jurídica")
        public void testPathInvalido() throws Exception {
                getEmprestimoPessoaJuridicaRequest.pathInvalido()
                    .then()
                    .statusCode(404)
                    .body("errors[0].title", equalTo("O recurso solicitado não existe."))
                    .body("errors[0].detail", equalTo("O endereço informado para esse endpoint está incorreto."));
        }

        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno 400 - Número da página é zero no endpoint de cartão de empréstipo pessoa jurídica")
        public void testNumeroPaginaZero() throws Exception {
                getEmprestimoPessoaJuridicaRequest.numeroPaginaZero()
                    .then()
                    .statusCode(400)
                    .body("errors[0].title", equalTo("Número da página inválido."))
                    .body("errors[0].detail", equalTo("O número da página (parâmetro page) informado é inválido. São permitidos valores numéricos com valor mínimo igual a 1."));
        }

        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno 400 - Número da página inválido no endpoint de cartão de empréstipo pessoa jurídica")
        public void testNumeroPaginaInvalido() throws Exception {
                getEmprestimoPessoaJuridicaRequest.numeroPaginaInvalido()
                    .then()
                    .statusCode(400)
                    .body("errors[0].title", equalTo("Número da página inválido."))
                    .body("errors[0].detail", equalTo("O número da página (parâmetro page) informado é inválido. São permitidos valores numéricos com valor mínimo igual a 1."));
        }

        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno 400 - Tamanho da página é zero no endpoint de cartão de empréstipo pessoa jurídica")
        public void testTamanhoPaginaZero() throws Exception {
                getEmprestimoPessoaJuridicaRequest.tamanhoPaginaZero()
                    .then()
                    .statusCode(400)
                    .body("errors[0].title", equalTo("Tamanho da página inválido."))
                    .body("errors[0].detail", equalTo("O tamanho da página (parâmetro page-size) informado é inválido. São permitidos valores numéricos de 10 a 1000."));
        }

        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno 400 - Tamanho da página inválido no endpoint de cartão de empréstipo pessoa jurídica")
        public void testTamanhoPaginaInvalido() throws Exception {
                getEmprestimoPessoaJuridicaRequest.tamanhoPaginaInvalido()
                    .then()
                    .statusCode(400)
                    .body("errors[0].title", equalTo("Tamanho da página inválido."))
                    .body("errors[0].detail", equalTo("O tamanho da página (parâmetro page-size) informado é inválido. São permitidos valores numéricos de 10 a 1000."));
        }

        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase01.class})
        @DisplayName("Validar o retorno 422 - Tamanho da página superior ao permitido no endpoint de cartão de empréstipo pessoa jurídica")
        public void testTamanhoPaginaSuperior() throws Exception {
                getEmprestimoPessoaJuridicaRequest.tamanhoPaginaSuperior()
                    .then()
                    .statusCode(422)
                    .body("errors[0].title", equalTo("O recurso solicitado está acima do permitido."))
                    .body("errors[0].detail", equalTo("O tamanho da página (parâmetro page-size) informado é superior ao limite previsto (1000)."));
        }
        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category({Healthcheck.class, AllTests.class, fase02.class})
        @DisplayName("Validar retorno 405 -Método não suportado no endpoint de cartão de empréstipo pessoa jurídica")
        public void testMetodoNaoSuportado() throws Exception {
                getEmprestimoPessoaJuridicaRequest.metodoNaoSuportado()
                    .then()
                    .statusCode(405)
                    .body("errors.title", hasItem("Ocorreu um erro inesperado ao processar sua requisição."))
                    .body("errors.detail", hasItem("Request method 'POST' not supported"));
        }

}
