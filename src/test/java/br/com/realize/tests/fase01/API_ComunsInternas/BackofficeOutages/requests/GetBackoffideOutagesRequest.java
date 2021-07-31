package br.com.realize.tests.fase01.API_ComunsInternas.BackofficeOutages.requests;

import br.com.realize.utils.DataUtils;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Date;
import java.util.Locale;

import static io.restassured.RestAssured.given;


public class GetBackoffideOutagesRequest {

    Faker fake = new Faker(new Locale("pt-br"));

    @Step("Obtém as indisponibilidades iniciando a partir da data atual até 7 dias após data de início.")
    public Response obterIndisponibilidadeSemPeriodoInformado() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "25")
                .when()
                .get("backoffice/v1/outage");
    }
    @Step("Obtém as indisponibilidades previstas a partir da data inicial informada e a data final até 7 dias após da data informada no início.")
    public Response obterIndisponibilidadeSomenteDataInicioInformada() {
        return given()
                .log().all()
                .queryParam("dateFrom", DataUtils.getDateTime())
                .queryParam("page", 1)
                .queryParam("page-size", "25")
                .when()
                .get("backoffice/v1/outage");
    }
    @Step("Obtém as indisponibilidades previstas a partir da data inicial informada e a data final até 7 dias após da data informada no início.")
    public Response obterIndisponibilidadeDataInicialInvalida() {
        return given()
                .log().all()
                .queryParam("dateFrom", "2021-05-32")
                .queryParam("page", 1)
                .queryParam("page-size", "25")
                .when()
                .get("backoffice/v1/outage");
    }
    @Step("Obtém as indisponibilidades previstas a partir da data inicial informada e a data final até 7 dias após da data informada no início.")
    public Response obterIndisponibilidadeDataFinalInvalida() {
        return given()
                .log().all()
                .queryParam("dateTo", "2021-05-32")
                .queryParam("page", 1)
                .queryParam("page-size", "25")
                .when()
                .get("backoffice/v1/outage");
    }
    @Step("Obtém as indisponibilidades a partir da data atual até a data final informada.")
    public Response obterIndisponibilidadeDataInicioFimInformadas() {
        return given()
                .log().all()
                .queryParam("dateFrom", "2021-05-20")
                .queryParam("dateTo", "2021-06-01")
                .queryParam("page", 1)
                .queryParam("page-size", "25")
                .when()
                .get("backoffice/v1/outage");
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
                .get("backoffice/v1/outage");
    }
    @Step("Obtém as indisponibilidades a partir da data atual até a data final informada.")
    public Response obterIndisponibilidadeSomenteDataFimInformada() {
        return given()
                .log().all()
                .queryParam("dateTo", DataUtils.getDataDiferencaDias(7))
                .queryParam("page", 1)
                .queryParam("page-size", "25")
                .when()
                .get("backoffice/v1/outage");
    }


    @Step("Número da página informado é maior que o número de páginas calculadas..")
    public Response numeroPaginaNaoLocalizado() {
        return given()
                .queryParam("page", "9rrtrt222")
                .queryParam("page-size", "10")
                .when()
                .get("backoffice/v1/outage");
    }
    @Step("O endpoint foi informado com algum caracter que não está de acordo com a chamada da API")
    public Response pathInvalido() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "10")
                .when()
                .get("backoffice/v1/outages");
    }
    @Step("O número da página informado é zero.")
    public Response numeroPaginaZero() {
        return given()
                .queryParam("page", 0)
                .queryParam("page-size", "10")
                .when()
                .get("backoffice/v1/outage");
    }
    @Step("O número da página informado contém letras ou caracteres especiais.")
    public Response numeroPaginaInvalido() {
        return given()
                .queryParam("page", -8)
                .queryParam("page-size", "10")
                .when()
                .get("backoffice/v1/outage");
    }
    @Step("O tamanho da página informado é zero.")
    public Response tamanhoPaginaZero() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "0")
                .when()
                .get("backoffice/v1/outage");
    }
    @Step("O tamanho da página informado contém letras ou caracteres especiais.")
    public Response tamanhoPaginaInvalido() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "1abc#")
                .when()
                .get("backoffice/v1/outage");
    }
    @Step("O tamanho da página informado é superior ao valor 1000.")
    public Response tamanhoPaginaSuperior() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "1001")
                .when()
                .get("backoffice/v1/outage");
    }
    @Step("Método não suportado para a o endpoint informado")
    public Response metodoNaoSuportado() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "25")
                .when()
                .post("backoffice/v1/outage");
    }

    /*public String obterLinkSelfBackofficeOutage() {
        return obterIndisponibilidadeSemPeriodoInformado()
                .then()
                .statusCode(200)
                .extract().path("links.self");
    }*/
}

