package br.com.realize.tests.fase01.apiComunsInternas.backofficeOutages.requests;

import br.com.realize.tests.fase01.apiComunsInternas.backofficeOutages.factory.IndisponibilidadeDataFactory;
import br.com.realize.tests.fase01.apiComunsInternas.backofficeOutages.pojo.BodyPostIndisponibilidade;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static br.com.realize.tests.fase01.apiComunsInternas.backofficeOutages.factory.IndisponibilidadeDataFactory.urlOutages;
import static io.restassured.RestAssured.given;

public class PostBackofficeOutagesRequest {

    @Step("Inserir indisponibilidade.")
    public Response inserirIndisponibilidade() {
        BodyPostIndisponibilidade bodyPostIndisponibilidade = IndisponibilidadeDataFactory.dadosIncluirIndisponibilidade();
        return given()
                .body(bodyPostIndisponibilidade)
                .when()
                .post(urlOutages);
    }
    @Step("Garantir o contrato de inclusão de indisponibilidade")
    public Response inserirIndisponibilidadeContrato() {
        BodyPostIndisponibilidade bodyPostIndisponibilidade = IndisponibilidadeDataFactory.dadosIncluirIndisponibilidadeContrato();
        return given()
                .body(bodyPostIndisponibilidade)
                .when()
                .post(urlOutages);
        //Obs.: Quando o registro foi incluído com sucesso, retornar a mensagem: "Inclusão do registro realizada com sucesso".
    }
    @Step("Inserir indisponibilidade informando a outageTime inferior a data atual.")
    public Response inserirOutageInferiorDataHoraAtual() {
        BodyPostIndisponibilidade bodyBodyPostIndisponibilidadeDataInferiorDataAtual = IndisponibilidadeDataFactory.dadosIncluirIndisponibilidadeinserirOutageInferiorDataHoraAtual();
        return given()
                .body(bodyBodyPostIndisponibilidadeDataInferiorDataAtual)
                .when()
                .post(urlOutages);
        /*4) "Data e hora da indisponibilidade inferior a data atual - campo outageTime" (inclusão/alteração):
         Quando ocorrer este erro, retornar a mensagem "A data e hora da indisponibilidade deve ser maior que a data/hora atual." e o código de erro "400".*/
    }

    @Step("Inserir indisponibilidade com a flag 'isPartial' igual a 'true' Com UnavailableEndpoints preenchido.")
    public Response inserirIsPartialTrueComUnavailableEndpointsPreenchido() {
        BodyPostIndisponibilidade bodyBodyPostIndisponibilidadeIspartialTrue = IndisponibilidadeDataFactory.dadosIncluirIndisponibilidadeInserirIsPartialTrueComUnavailableEndpointsPreenchido();
        return given()
                .body(bodyBodyPostIndisponibilidadeIspartialTrue)
                .when()
                .post(urlOutages);
    }
    @Step("Inserir indisponibilidade com a flag 'isPartial' igual a 'true' Com UnavailableEndpoints vazio.")
    public Response inserirIsPartialTrueComUnavailableEndpointsVazio() {
        BodyPostIndisponibilidade bodyBodyPostIndisponibilidadeIspartialTrue = IndisponibilidadeDataFactory.dadosIncluirIndisponibilidadeInserirIsPartialTrueComUnavailableEndpointsVazio();
        return given()
                .body(bodyBodyPostIndisponibilidadeIspartialTrue)
                .when()
                .post(urlOutages);
    }
    @Step("Inserir indisponibilidade com a flag 'isPartial' igual a 'true' Com UnavailableEndpoints preenchido.")
    public Response inserirIsPartialFalseComUnavailableEndpointsPreenchido() {
        BodyPostIndisponibilidade bodyBodyPostIndisponibilidadeIspartialTrue = IndisponibilidadeDataFactory.dadosIncluirIndisponibilidadeInserirIsPartialFalseComUnavailableEndpointsPreenchido();
        return given()
                .body(bodyBodyPostIndisponibilidadeIspartialTrue)
                .when()
                .post(urlOutages);
    }
    @Step("Inserir indisponibilidade com a flag 'isPartial' igual a 'true' Com UnavailableEndpoints preenchido.")
    public Response inserirIsPartialFalseComUnavailableEndpointsVazio() {
        BodyPostIndisponibilidade bodyBodyPostIndisponibilidadeIspartialTrue = IndisponibilidadeDataFactory.dadosIncluirIndisponibilidadeInserirIsPartialFalseComUnavailableEndpointsVazio();
        return given()
                .body(bodyBodyPostIndisponibilidadeIspartialTrue)
                .when()
                .post(urlOutages);
    }
    @Step("O endpoint foi informado com algum caracter que não está de acordo com a chamada da API")
    public Response pathInvalido() {
        BodyPostIndisponibilidade bodyPostIndisponibilidade = (BodyPostIndisponibilidade) IndisponibilidadeDataFactory.dadosIncluirIndisponibilidade();
        return given()
                .body(bodyPostIndisponibilidade)
                .when()
                .post("backoffice/v3/outages/");
    }
}

