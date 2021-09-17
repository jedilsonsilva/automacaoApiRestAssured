package br.com.realize.tests.fase01.apiComunsInternas.backofficeOutages.requests;

import br.com.realize.utils.DataUtils;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import static br.com.realize.tests.fase01.apiComunsInternas.backofficeOutages.factory.IndisponibilidadeDataFactory.urlOutages;
import static io.restassured.RestAssured.given;

public class GetBackofficeOutagesRequest {


    @Step("Obtém as indisponibilidades iniciando a partir da data atual até 7 dias após data de início.")
    public Response obterIndisponibilidadeSemPeriodoInformado() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "25")
                .when()
                .get(urlOutages);
    }
    @Step("Obtém as indisponibilidades previstas a partir da data inicial informada e a data final até 7 dias após da data informada no início.")
    public Response obterIndisponibilidadeSomenteDataInicioInformada() {
        return given()
                .log().all()
                .queryParam("dateFrom", DataUtils.getDateTime())
                .queryParam("page", 1)
                .queryParam("page-size", "25")
                .when()
                .get(urlOutages);
    }
    @Step("Obtém as indisponibilidades previstas a partir da data inicial informada e a data final até 7 dias após da data informada no início.")
    public Response obterIndisponibilidadeDataInicialInvalida() {
        return given()
                .log().all()
                .queryParam("dateFrom", "30-05-2021")
                .queryParam("dateTo", DataUtils.getDataDiferencaDias(0))
                .queryParam("page", 1)
                .queryParam("page-size", "25")
                .when()
                .get(urlOutages);
    }
    @Step("Obtém as indisponibilidades previstas a partir da data inicial informada e a data final até 7 dias após da data informada no início.")
    public Response obterIndisponibilidadeDataFinalInvalida() {
        return given()
                .log().all()
                .queryParam("dateFrom", DataUtils.getDataDiferencaDias(0))
                .queryParam("dateTo", "30-05-2021")
                .queryParam("page", 1)
                .queryParam("page-size", "25")
                .when()
                .get(urlOutages);
    }
    @Step("Obtém as indisponibilidades a partir da data atual até a data final informada.")
    public Response obterIndisponibilidadeDataInicioFimInformadas() {
        return given()
                .log().all()
                .queryParam("dateFrom", "2001-05-20")
                .queryParam("dateTo", "2029-06-01")
                .queryParam("page", 1)
                .queryParam("page-size", "25")
                .when()
                .get(urlOutages);
    }
    @Step("Obtém as indisponibilidades a partir da data atual até a data final informada.")
    public Response obterIndisponibilidadeDataInicioMaiorDataFim() {
        return given()
                .log().all()
                .queryParam("dateFrom", "2021-05-31")
                .queryParam("dateTo", "2021-05-01")
                .queryParam("page", 1)
                .queryParam("page-size", "25")
                .when()
                .get(urlOutages);
    }
    @Step("Obtém as indisponibilidades a partir da data atual até a data final informada.")
    public Response obterIndisponibilidadeSomenteDataFimInformada() {
        return given()
                .log().all()
                .queryParam("dateTo", DataUtils.getDataDiferencaDias(7))
                .queryParam("page", 1)
                .queryParam("page-size", "25")
                .when()
                .get(urlOutages);
    }


    @Step("Número da página informado é maior que o número de páginas calculadas..")
    public Response numeroPaginaNaoLocalizado() {
        return given()
                .queryParam("page", 8)
                .queryParam("page-size", "10")
                .when()
                .get(urlOutages);
    }
    @Step("O endpoint foi informado com algum caracter que não está de acordo com a chamada da API")
    public Response pathInvalido() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "10")
                .when()
                .get("backoffice/v3/outagesssss/");
    }
    @Step("O número da página informado é zero.")
    public Response numeroPaginaZero() {
        return given()
                .queryParam("page", 0)
                .queryParam("page-size", "10")
                .when()
                .get(urlOutages);
    }
    @Step("O número da página informado contém letras ou caracteres especiais.")
    public Response numeroPaginaInvalido() {
        return given()
                .queryParam("page", -8)
                .queryParam("page-size", "10")
                .when()
                .get(urlOutages);
    }
    @Step("O tamanho da página informado é zero.")
    public Response tamanhoPaginaZero() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "0")
                .when()
                .get(urlOutages);
    }
    @Step("O tamanho da página informado contém letras ou caracteres especiais.")
    public Response tamanhoPaginaInvalido() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "1abc#")
                .when()
                .get(urlOutages);
    }
    @Step("O tamanho da página informado é superior ao valor 1000.")
    public Response tamanhoPaginaSuperior() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "1001")
                .when()
                .get(urlOutages);
    }
    @Step("Método não suportado para a o endpoint informado")
    public Response metodoNaoSuportado() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "25")
                .when()
                .delete(urlOutages);
    }

    /*public String obterLinkSelfBackofficeOutage() {
        return obterIndisponibilidadeSemPeriodoInformado()
                .then()
                .statusCode(200)
                .extract().path("links.self");
    }*/
}

