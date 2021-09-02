package br.com.realize.tests.fase01.API_ComunsInternas.BackofficeOutages.requests;

import br.com.realize.tests.fase01.API_ComunsInternas.BackofficeOutages.factory.IndisponibilidadeDataFactory;
import br.com.realize.tests.fase01.API_ComunsInternas.BackofficeOutages.pojo.Indisponibilidade;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class PostBackoffideOutagesRequest {
    String url = "backoffice/v1/outages";

    @Step("Inserir indisponibilidade.")
    public Response inserirIndisponibilidade() {
        Indisponibilidade bodyIndisponibilidade = IndisponibilidadeDataFactory.dadosIndisponibilidade();
        return given()
                .body(bodyIndisponibilidade)
                .when()
                .post(url);
        //Obs.: Quando o registro foi incluído com sucesso, retornar a mensagem: "Inclusão do registro realizada com sucesso".
    }
    @Step("Inserir indisponibilidade.")
    public Response inserirIndisponibilidadeContrato() {
        Indisponibilidade bodyIndisponibilidade = IndisponibilidadeDataFactory.dadosIndisponibilidadeContrato();
        return given()
                .body(bodyIndisponibilidade)
                .when()
                .post(url);
        //Obs.: Quando o registro foi incluído com sucesso, retornar a mensagem: "Inclusão do registro realizada com sucesso".
    }
    @Step("Inserir indisponibilidade informando a outageTime inferior a data atual.")
    public Response inserirOutageInferiorDataHoraAtual() {
        Indisponibilidade bodyIndisponibilidadeDataInferiorDataAtual = IndisponibilidadeDataFactory.dadosIndisponibilidadeinserirOutageInferiorDataHoraAtual();
        return given()
                .body(bodyIndisponibilidadeDataInferiorDataAtual)
                .when()
                .post(url);
        /*4) "Data e hora da indisponibilidade inferior a data atual - campo outageTime" (inclusão/alteração):
         Quando ocorrer este erro, retornar a mensagem "A data e hora da indisponibilidade deve ser maior que a data/hora atual." e o código de erro "400".*/
    }

    @Step("Inserir indisponibilidade com a flag 'isPartial' igual a 'true'.")
    public Response inserirIsPartialTrue() {
        Indisponibilidade bodyIndisponibilidadeIspartialTrue = IndisponibilidadeDataFactory.dadosIndisponibilidadeInserirIsPartialTrue();
        return given()
                .body(bodyIndisponibilidadeIspartialTrue)
                .when()
                .post(url);
        // Deve-se informar quais são os endpoints indisponíveis para este agendamento no campo "unavailableEndpoints".
    }
    @Step("Inserir indisponibilidade com a flag 'isPartial' igual a 'true' e o campo 'unavailableEndpoints' não preenchido.")
    public Response inserirCampoObrigatorioNaoPreenchido() {
        Indisponibilidade bodyIndisponibilidadeCamposObrigatorios = IndisponibilidadeDataFactory.dadosIndisponibilidadeInserirCampoObrigatorioNaoPreenchido();
        return given()
                .body(bodyIndisponibilidadeCamposObrigatorios)
                .when()
                .post(url);
    }
    /*1) "Campo obrigatório não encontrado" (inclusão/alteração): Quando um campo "obrigatório" não for informado,
    retornar a mensagem de erro "O campo obrigatório <nome do campo> não foi preenchido." e o código de erro "400" para o endpoint.
     */

    @Step("O endpoint foi informado com algum caracter que não está de acordo com a chamada da API")
    public Response pathInvalido() {
        Indisponibilidade bodyIndisponibilidade = (Indisponibilidade) IndisponibilidadeDataFactory.dadosIndisponibilidade();
        return given()
                .body(bodyIndisponibilidade)
                .when()
                .post(url + "s");
    }
}

