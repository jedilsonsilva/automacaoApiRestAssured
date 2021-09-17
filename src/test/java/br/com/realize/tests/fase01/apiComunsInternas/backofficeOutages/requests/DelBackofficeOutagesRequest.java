package br.com.realize.tests.fase01.apiComunsInternas.backofficeOutages.requests;

import br.com.realize.tests.fase01.apiComunsInternas.backofficeOutages.factory.IndisponibilidadeDataFactory;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import static br.com.realize.tests.fase01.apiComunsInternas.backofficeOutages.factory.IndisponibilidadeDataFactory.idParaDeletar;
import static br.com.realize.tests.fase01.apiComunsInternas.backofficeOutages.factory.IndisponibilidadeDataFactory.urlOutages;
import static io.restassured.RestAssured.given;

public class DelBackofficeOutagesRequest {

    @Step("Deletar indisponibilidade")
    public Response deletarIndisponibilidade() {
        IndisponibilidadeDataFactory.obterDadosParaDeletarIndisponibilidade();
               return given()
                .when()
                .delete(urlOutages + idParaDeletar);
    }
    @Step("Deletar indisponibilidade informando um Id de uma indisponibilidade já excluída")
    public Response deletarIndisponibilidadeJaExcluido() {
        IndisponibilidadeDataFactory.obterDadosParaDeletarIndisponibilidade();
         given()
                .when()
                .delete(urlOutages + idParaDeletar);
         return given()
                 .when()
                 .delete(urlOutages + idParaDeletar);
    }
    @Step("Deletar indisponibilidade informando um Id inválido")
    public Response deletarIndisponibilidadeComIdInvalido() {
        IndisponibilidadeDataFactory.obterDadosParaDeletarIndisponibilidade();
        return given()
                .when()
                .delete(urlOutages + "idInvalidoParaTeste");
    }
}

