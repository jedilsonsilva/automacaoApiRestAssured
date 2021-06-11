package br.com.realize.tests.fase01.CanaisAtendimento.AtendimentoTelefonico.tests;

import br.com.realize.runners.fase02;
import br.com.realize.suites.Contract;
import br.com.realize.runners.fase01;
import br.com.realize.suites.AllTests;
import br.com.realize.suites.Healthcheck;
import br.com.realize.tests.fase01.CanaisAtendimento.AtendimentoTelefonico.requests.GetAtendimentoTelefonicoRequest;
import br.com.realize.tests.base.tests.BaseTest;
import br.com.realize.utils.Utils;
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import java.io.File;
import java.util.concurrent.TimeUnit;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.*;



@Epic("Fase 01")
@Feature("Canais de Atendimento")
@DisplayName("Atendimento Telefônico")
public class GetAtendimentoTelefonicoTest extends BaseTest {

    GetAtendimentoTelefonicoRequest getAtendimentoTelefonicooRequest = new GetAtendimentoTelefonicoRequest();
    String linkSelf = getAtendimentoTelefonicooRequest.obterLinkSelfAtendimentoTelefonico();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase01.class})
    @DisplayName("Validar o retorno das informações gerais do atendimento telefônico")
    public void testAtendimentoTelefonico() throws Exception {
        getAtendimentoTelefonicooRequest.obterInformacoesAtendimentoTelefonico()
                .then()
                .statusCode(200)
                .body("data.companies[0].cnpjNumber", equalTo("27351731"))
                .body("data.companies[0].name", equalTo("REALIZE CRÉDITO, FINANCIAMENTO E INVESTIMENTO S.A."))
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body("meta.totalPages", greaterThan(0))
                .body("meta.totalRecords", greaterThan(0))
                .body("links.self", is(linkSelf));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase01.class})
    @DisplayName("Validar o retorno das informações do tipo de atendimento Central telefônica.")
    public void testInformacoesCentralTelefonica() throws Exception {
        getAtendimentoTelefonicooRequest.canaisTelefonicos()
                .then()
                .log().all()
                .statusCode(200)
                .rootPath("data.companies.phoneChannels")
                .body("identification[0].additionalInfo[0]", equalTo("Central de atendimento"))
                .body("identification[0].phones[0].number", hasItems("30045060", "0800 073 6637", "40042900", "39215464", "0800 727 9505", "0800 722 9855"))
                .body("services[0].name[0]", hasItems("CARTAO_CREDITO", "SEGUROS"))
                .body("services[0].code[0]", hasItems("CARTAO_CREDITO", "SEGUROS"));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase01.class})
    @DisplayName("Validar o retorno das informações do tipo de atendimento SAC.")
    public void testInformacoesSAC() throws Exception {
        getAtendimentoTelefonicooRequest.canaisTelefonicos()
                .then()
                .log().all()
                .statusCode(200)
                .rootPath("data.companies.phoneChannels")
                .body("identification[0].additionalInfo[1]", equalTo("Canal exclusivo para reclamações, informações e cancelamentos."))
                .body("identification[0].phones[1].number[0]", equalTo("0800 600 6601"))
                .body("services[0].name[1]", hasItems("RECLAMACOES", "INFORMACOES", "CANCELAMENTO"))
                .body("services[0].code[1]", hasItems("RECLAMACOES", "INFORMACOES", "CANCELAMENTO"));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase01.class})
    @DisplayName("Validar o retorno das informações do tipo de atendimento Ouvidoria.")
    public void testInformacoesOuvidoria() throws Exception {
        getAtendimentoTelefonicooRequest.canaisTelefonicos()
                .then()
                .log().all()
                .statusCode(200)
                .rootPath("data.companies.phoneChannels")
                .body("identification[0].additionalInfo[2]", equalTo("Atuação na mediação de conflitos."))
                .body("identification[0].phones[2].number[0]", equalTo("0800 727 0127"))
                .body("services[0].name[2]", hasItem("CARTAO_CREDITO"))
                .body("services[0].code[2]", hasItem("CARTAO_CREDITO"));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase01.class})
    @DisplayName("Validar o retorno das informações do tipo de atendimento Outros.")
    public void testInformacoesOutros() throws Exception {
        getAtendimentoTelefonicooRequest.canaisTelefonicos()
                .then()
                .log().all()
                .statusCode(200)
                .rootPath("data.companies.phoneChannels")
                .body("identification[0].additionalInfo[3]", equalTo("Assitente virtual cartões RENNER"))
                .body("identification[0].phones[3].number[0]", equalTo("39214004"))
                .body("services[0].name[3]", hasItem("CARTAO_CREDITO"))
                .body("services[0].code[3]", hasItem("CARTAO_CREDITO"))
                .body("services[0].additionalInfo[3]", hasItem("Verificação de saldos, carnês e faturas e geração de boletos para pagamento via WhatsApp."));

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({Contract.class, AllTests.class, fase01.class})
    @DisplayName("Garantir o contrato do retorno da lista de atendimento telefônico")
    public void testGarantirContratosInformacoesConta(){
        getAtendimentoTelefonicooRequest.obterInformacoesAtendimentoTelefonico()
                .then()
                .statusCode(200)
                .assertThat().body(matchesJsonSchema(
                    new File(Utils.getContractsBasePath(
                    "fase01/CanaisAtendimento/AtendimentoTelefonico","AtendimentoTelefonico"))));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase01.class})
    @DisplayName("Validar o retorno 404 - Número da página não localizado do endpoint de atendimento telefônico")
    public void testNumeroPaginaNaoLocalizado() throws Exception {
        getAtendimentoTelefonicooRequest.numeroPaginaNaoLocalizado()
                .then()
                .log().all()
                .statusCode(404)
                .body("errors[0].title", equalTo("O recurso solicitado está acima do permitido."))
                .body("errors[0].detail", equalTo("O número da página (parâmetro page) é maior do que o permitido na consulta (1)."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase01.class})
    @DisplayName("Validar o retorno 404 - Path da API inválido do endpoint de atendimento telefônico")
    public void testPathInvalido() throws Exception {
        getAtendimentoTelefonicooRequest.pathInvalido()
                .then()
                .log().all()
                .statusCode(404)
                .body("errors[0].title", equalTo("O recurso solicitado não existe."))
                .body("errors[0].detail", equalTo("O endereço informado para esse endpoint está incorreto."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase01.class})
    @DisplayName("Validar o retorno 400 - Número da página é zero do endpoint de atendimento telefônico")
    public void testNumeroPaginaZero() throws Exception {
        getAtendimentoTelefonicooRequest.numeroPaginaZero()
                .then()
                .log().all()
                .statusCode(400)
                .body("errors[0].title", equalTo("Número da página inválido."))
                .body("errors[0].detail", equalTo("O número da página (parâmetro page) informado é inválido. São permitidos valores numéricos com valor mínimo igual a 1."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase01.class})
    @DisplayName("Validar o retorno 400 - Número da página inválido do endpoint de atendimento telefônico")
    public void testNumeroPaginaInvalido() throws Exception {
        getAtendimentoTelefonicooRequest.numeroPaginaInvalido()
                .then()
                .log().all()
                .statusCode(400)
                .body("errors[0].title", equalTo("Número da página inválido."))
                .body("errors[0].detail", equalTo("O número da página (parâmetro page) informado é inválido. São permitidos valores numéricos com valor mínimo igual a 1."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase01.class})
    @DisplayName("Validar o retorno 400 - Tamanho da página é zero do endpoint de atendimento telefônico")
    public void testTamanhoPaginaZero() throws Exception {
        getAtendimentoTelefonicooRequest.tamanhoPaginaZero()
                .then()
                .log().all()
                .statusCode(400)
                .body("errors[0].title", equalTo("Tamanho da página inválido."))
                .body("errors[0].detail", equalTo("O tamanho da página (parâmetro page-size) informado é inválido. São permitidos valores numéricos de 10 a 1000."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase01.class})
    @DisplayName("Validar o retorno 400 - Tamanho da página inválido do endpoint de atendimento telefônico")
    public void testTamanhoPaginaInvalido() throws Exception {
        getAtendimentoTelefonicooRequest.tamanhoPaginaInvalido()
                .then()
                .log().all()
                .statusCode(400)
                .body("errors[0].title", equalTo("Tamanho da página inválido."))
                .body("errors[0].detail", equalTo("O tamanho da página (parâmetro page-size) informado é inválido. São permitidos valores numéricos de 10 a 1000."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase01.class})
    @DisplayName("Validar o retorno 422 - Tamanho da página superior ao permitido do endpoint de atendimento telefônico")
    public void testTamanhoPaginaSuperior() throws Exception {
        getAtendimentoTelefonicooRequest.tamanhoPaginaSuperior()
                .then()
                .log().all()
                .statusCode(422)
                .body("errors[0].title", equalTo("O recurso solicitado está acima do permitido."))
                .body("errors[0].detail", equalTo("O tamanho da página (parâmetro page-size) informado é superior ao limite previsto (1000)."));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("405 - Validar o status code informando um método não suportado do endpoint de atendimento telefônico")
    public void testMetodoNaoSuportado() throws Exception {
        getAtendimentoTelefonicooRequest.metodoNaoSuportado()
                .then()
                .log().all()
                .statusCode(405)
                .body("errors.title", hasItem("Ocorreu um erro inesperado ao processar sua requisição."))
                .body("errors.detail", hasItem("Request method 'POST' not supported"));
    }
}
