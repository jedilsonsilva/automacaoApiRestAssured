package br.com.realize.tests.fase01.apiComunsInternas.backofficeOutages.requests;

import br.com.realize.tests.fase01.apiComunsInternas.backofficeOutages.factory.IndisponibilidadeDataFactory;
import br.com.realize.tests.fase01.apiComunsInternas.backofficeOutages.pojo.BodyPutIndisponibilidade;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import static br.com.realize.tests.fase01.apiComunsInternas.backofficeOutages.factory.IndisponibilidadeDataFactory.urlOutages;
import static io.restassured.RestAssured.given;


public class PutBackofficeOutagesRequest {

    @Step("Alterar indisponibilidade.")
    public Response alterarIndisponibilidade() {

    BodyPutIndisponibilidade bodyPutIndisponibilidade = IndisponibilidadeDataFactory.dadosAlterarIndisponibilidade();
        return given()
                .body(bodyPutIndisponibilidade)
                .when()
                .put(urlOutages);
    }
    @Step("Validar o contrato da alteração de Indisponibilidade")
    public Response alterarIndisponibilidadeContract() {

        BodyPutIndisponibilidade bodyPutIndisponibilidade = IndisponibilidadeDataFactory.dadosAlterarIndisponibilidadeContract();
        return given()
                .body(bodyPutIndisponibilidade)
                .when()
                .put(urlOutages);
    }
    @Step("Alterar indisponibilidade informando uma data inválida")
    public Response alterarIndisponibilidadeComDataInvalida() {

        BodyPutIndisponibilidade bodyPutIndisponibilidade = IndisponibilidadeDataFactory.dadosAlterarIndisponibilidadeDataInvalida();
        return given()
                .body(bodyPutIndisponibilidade)
                .when()
                .put(urlOutages);
    }
    @Step("Alterar indisponibilidade informando uma data de Outages inferior a data de inclusão.")
    public Response alterarIndisponibilidadeDataInferiorADataDaInclusaoDaIndisponibilidade() {

        BodyPutIndisponibilidade bodyPutIndisponibilidade = IndisponibilidadeDataFactory.dadosAlterarIndisponibilidadeDataInferiorADataDaInclusaoDaIndisponibilidade();
        return given()
                .body(bodyPutIndisponibilidade)
                .when()
                .put(urlOutages);
    }
    @Step("Alterar a indisponibilidade alterando a flag IsPartial de true para false com o campo UnavailableEndpoints vazio.")
    public Response alterarIndisponibilidadeFlagIsPartialDeTrueParaFalseComUnavailableEndpointsVazio() {

        BodyPutIndisponibilidade bodyPutIndisponibilidade = IndisponibilidadeDataFactory.dadosAlterarIndisponibilidadeFlagIsPartialDeTrueParaFalseComUnavailableEndpointsVazio();
        return given()
                .body(bodyPutIndisponibilidade)
                .when()
                .put(urlOutages);
    }
    @Step("Alterar a indisponibilidade alterando a flag IsPartial de true para false com o campo UnavailableEndpoints preenchido.")
    public Response alterarIndisponibilidadeFlagIsPartialDeTrueParaFalseComUnavailableEndpointsPreenchido() {

        BodyPutIndisponibilidade bodyPutIndisponibilidade = IndisponibilidadeDataFactory.dadosAlterarIndisponibilidadeFlagIsPartialDeTrueParaFalseComUnavailableEndpointsPreenchido();
        return given()
                .body(bodyPutIndisponibilidade)
                .when()
                .put(urlOutages);
    }
    @Step("Alterar a indisponibilidade alterando a flag IsPartial de false para true com o campo UnavailableEndpoints vazio.")
    public Response alterarIndisponibilidadeFlagIsPartialDeFalseParaTrueComUnavailableEndpointsVazio() {

        BodyPutIndisponibilidade bodyPutIndisponibilidade = IndisponibilidadeDataFactory.dadosAlterarIndisponibilidadeFlagIsPartialDeFalseParaTrueComUnavailableEndpointsVazio();
        return given()
                .body(bodyPutIndisponibilidade)
                .when()
                .put(urlOutages);
    }
    @Step("Alterar a indisponibilidade alterando a flag IsPartial de false para true com o campo UnavailableEndpoints preenchido.")
    public Response alterarIndisponibilidadeFlagIsPartialDeFalseParaTrueComUnavailableEndpointsPreenchido() {

        BodyPutIndisponibilidade bodyPutIndisponibilidade = IndisponibilidadeDataFactory.dadosAlterarIndisponibilidadeFlagIsPartialDeFalseParaTrueComUnavailableEndpointsPreenchido();
        return given()
                .body(bodyPutIndisponibilidade)
                .when()
                .put(urlOutages);
    }
}

