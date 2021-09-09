package br.com.realize.tests.fase01.apiComunsInternas.backofficeOutages.factory;

import br.com.realize.tests.fase01.apiComunsInternas.backofficeOutages.pojo.BodyPostIndisponibilidade;
import br.com.realize.tests.fase01.apiComunsInternas.backofficeOutages.pojo.BodyPutIndisponibilidade;
import br.com.realize.utils.DataUtils;
import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class IndisponibilidadeDataFactory {

    static Faker fake = new Faker(new Locale("pt-br"));
    public static String urlOutages = "backoffice/v1/outages/";
    public static String idAlteracao;
    public static String idParaDeletar;
    public static String durationAlteracao;
    public static String explanationAlteracao;

//DADOS PARA TESTES DE INCLUSÃO DE INDISPONIBILIDADE - POST
    public static BodyPostIndisponibilidade dadosIncluirIndisponibilidade(){
         int qtdDias = fake.number().numberBetween(01, 5);
         int qtdHoras = fake.number().numberBetween(01, 10);
         String explanation = fake.backToTheFuture().character();
         String unavailableEndpoints = fake.internet().password();

        BodyPostIndisponibilidade bodyPostIndisponibilidade = new BodyPostIndisponibilidade();
        bodyPostIndisponibilidade.setDuration("P"+qtdDias+"DT"+qtdHoras+"H");
        bodyPostIndisponibilidade.setExplanation(explanation);
        bodyPostIndisponibilidade.setOutageTime(DataUtils.getDataIndisponibilidade(1));
        bodyPostIndisponibilidade.setPartial("true");
        bodyPostIndisponibilidade.setUnavailableEndpoints(unavailableEndpoints + DataUtils.getDataIndisponibilidade(+1));
        return bodyPostIndisponibilidade;
    }
    public static BodyPostIndisponibilidade dadosIncluirIndisponibilidadeContrato(){
        int qtdDias = fake.number().numberBetween(01, 5);
        int qtdHoras = fake.number().numberBetween(01, 10);
        String explanation = fake.backToTheFuture().character();
        String unavailableEndpoints = fake.internet().password();

        BodyPostIndisponibilidade bodyPostIndisponibilidade = new BodyPostIndisponibilidade();
        bodyPostIndisponibilidade.setDuration("P"+qtdDias+"DT"+qtdHoras+"H");
        bodyPostIndisponibilidade.setExplanation(explanation);
        bodyPostIndisponibilidade.setOutageTime(DataUtils.getDataIndisponibilidade(+4));
        bodyPostIndisponibilidade.setPartial("true");
        bodyPostIndisponibilidade.setUnavailableEndpoints(unavailableEndpoints + DataUtils.getDataIndisponibilidade(0));
        return bodyPostIndisponibilidade;
    }
    public static BodyPostIndisponibilidade dadosIncluirIndisponibilidadeinserirOutageInferiorDataHoraAtual(){
        int qtdDias = fake.number().numberBetween(01, 5);
        int qtdHoras = fake.number().numberBetween(01, 10);
        String explanation = fake.backToTheFuture().character();
        String unavailableEndpoints = fake.internet().password();

        BodyPostIndisponibilidade bodyPostIndisponibilidade = new BodyPostIndisponibilidade();
        bodyPostIndisponibilidade.setDuration("P"+qtdDias+"DT"+qtdHoras+"H");
        bodyPostIndisponibilidade.setExplanation(explanation);
        bodyPostIndisponibilidade.setOutageTime(DataUtils.getDataIndisponibilidade(-1));
        bodyPostIndisponibilidade.setPartial("true");
        bodyPostIndisponibilidade.setUnavailableEndpoints(unavailableEndpoints + DataUtils.getDataIndisponibilidade(0));
        return bodyPostIndisponibilidade;

    }
    public static BodyPostIndisponibilidade dadosIncluirIndisponibilidadeInserirIsPartialTrueComUnavailableEndpointsPreenchido(){
        int qtdDias = fake.number().numberBetween(01, 2);
        int qtdHoras = fake.number().numberBetween(01, 2);
        String explanation = fake.backToTheFuture().character();
        String unavailableEndpoints = fake.internet().password();

        BodyPostIndisponibilidade bodyPostIndisponibilidade = new BodyPostIndisponibilidade();
        bodyPostIndisponibilidade.setDuration("P"+qtdDias+"DT"+qtdHoras+"H");
        bodyPostIndisponibilidade.setExplanation(explanation);
        bodyPostIndisponibilidade.setOutageTime(DataUtils.getDataIndisponibilidade(+1));
        bodyPostIndisponibilidade.setPartial("true");
        bodyPostIndisponibilidade.setUnavailableEndpoints(unavailableEndpoints + DataUtils.getDataIndisponibilidade(0));
        return bodyPostIndisponibilidade;
    }
    public static BodyPostIndisponibilidade dadosIncluirIndisponibilidadeInserirIsPartialFalseComUnavailableEndpointsPreenchido(){
        int qtdDias = fake.number().numberBetween(01, 5);
        int qtdHoras = fake.number().numberBetween(01, 10);
        String explanation = fake.backToTheFuture().character();
        String unavailableEndpoints = fake.internet().password();

        BodyPostIndisponibilidade bodyPostIndisponibilidade = new BodyPostIndisponibilidade();
        bodyPostIndisponibilidade.setDuration("P"+qtdDias+"DT"+qtdHoras+"H");
        bodyPostIndisponibilidade.setExplanation(explanation);
        bodyPostIndisponibilidade.setOutageTime(DataUtils.getDataIndisponibilidade(+40));
        bodyPostIndisponibilidade.setPartial("false");
        bodyPostIndisponibilidade.setUnavailableEndpoints(unavailableEndpoints + DataUtils.getDataIndisponibilidade(0));
        return bodyPostIndisponibilidade;
    }
    public static BodyPostIndisponibilidade dadosIncluirIndisponibilidadeInserirIsPartialFalseComUnavailableEndpointsVazio(){
        int qtdDias = fake.number().numberBetween(01, 5);
        int qtdHoras = fake.number().numberBetween(01, 10);
        String explanation = fake.backToTheFuture().character();
        String unavailableEndpoints = fake.internet().password();

        BodyPostIndisponibilidade bodyPostIndisponibilidade = new BodyPostIndisponibilidade();
        bodyPostIndisponibilidade.setDuration("P"+qtdDias+"DT"+qtdHoras+"H");
        bodyPostIndisponibilidade.setExplanation(explanation);
        bodyPostIndisponibilidade.setOutageTime(DataUtils.getDataIndisponibilidade(+30));
        bodyPostIndisponibilidade.setPartial("false");
        return bodyPostIndisponibilidade;
    }
    public static BodyPostIndisponibilidade dadosIncluirIndisponibilidadeInserirIsPartialTrueComUnavailableEndpointsVazio(){
        int qtdDias = fake.number().numberBetween(01, 2);
        int qtdHoras = fake.number().numberBetween(01, 2);
        String explanation = fake.backToTheFuture().character();
        String unavailableEndpoints = fake.internet().password();

        BodyPostIndisponibilidade bodyPostIndisponibilidade = new BodyPostIndisponibilidade();
        bodyPostIndisponibilidade.setDuration("P"+qtdDias+"DT"+qtdHoras+"H");
        bodyPostIndisponibilidade.setExplanation(explanation);
        bodyPostIndisponibilidade.setOutageTime(DataUtils.getDataIndisponibilidade(+4));
        bodyPostIndisponibilidade.setPartial("true");
        return bodyPostIndisponibilidade;
    }
//DADOS PARA TESTES DE CONSULTA DE INDISPONIBILIDADE - GET

//DADOS PARA TESTES DE ALTERAÇÃO DE INDISPONIBILIDADE - PUT
public static BodyPostIndisponibilidade dadosInclusaoDisponibilidadeParaAlteracao(){
    int qtdDias = fake.number().numberBetween(01, 5);
    int qtdHoras = fake.number().numberBetween(01, 10);
    String explanation = fake.backToTheFuture().character();
    String unavailableEndpoints = fake.internet().password();

    BodyPostIndisponibilidade bodyPostIndisponibilidade = new BodyPostIndisponibilidade();
    bodyPostIndisponibilidade.setDuration("P"+qtdDias+"DT"+qtdHoras+"H");
    bodyPostIndisponibilidade.setExplanation(explanation);
    bodyPostIndisponibilidade.setOutageTime(DataUtils.getDataIndisponibilidade(+15));
    bodyPostIndisponibilidade.setPartial("true");
    bodyPostIndisponibilidade.setUnavailableEndpoints(unavailableEndpoints + DataUtils.getDataIndisponibilidade(+1));
    return bodyPostIndisponibilidade;
}
    public static Response obterDadosParaAlterarIndisponibilidade() {

        BodyPostIndisponibilidade bodyPostIndisponibilidade = IndisponibilidadeDataFactory.dadosInclusaoDisponibilidadeParaAlteracao();
        Response response = (Response)   given().log().all()
                .body(bodyPostIndisponibilidade)
                .when()
                .post(urlOutages)
                .then().log().all()
                .extract().response();
        JsonPath extractor = response.jsonPath();
        idAlteracao = extractor.get("id");
        durationAlteracao = extractor.get("duration");
        explanationAlteracao = extractor.get("explanation");
        System.out.println(idAlteracao);
        System.out.println(durationAlteracao);
        System.out.println(explanationAlteracao);
        return response;
    }
    public static BodyPutIndisponibilidade dadosAlterarIndisponibilidade(){
        String unavailableEndpoints = fake.internet().password();
        obterDadosParaAlterarIndisponibilidade();
        BodyPutIndisponibilidade bodyPutIndisponibilidade = new BodyPutIndisponibilidade();
        bodyPutIndisponibilidade.setDuration(durationAlteracao);
        bodyPutIndisponibilidade.setExplanation(explanationAlteracao);
        bodyPutIndisponibilidade.setId(idAlteracao);
        bodyPutIndisponibilidade.setOutageTime(DataUtils.getDataDiferencaDias(1) + "T17:39:35.265Z");
        bodyPutIndisponibilidade.setPartial("false");
        bodyPutIndisponibilidade.setUnavailableEndpoints(unavailableEndpoints + DataUtils.getDataIndisponibilidade(0));
        return bodyPutIndisponibilidade;
    }
    public static BodyPutIndisponibilidade dadosAlterarIndisponibilidadeContract(){
        String unavailableEndpoints = fake.internet().password();
        obterDadosParaAlterarIndisponibilidade();
        BodyPutIndisponibilidade bodyPutIndisponibilidade = new BodyPutIndisponibilidade();
        bodyPutIndisponibilidade.setDuration(durationAlteracao);
        bodyPutIndisponibilidade.setExplanation(explanationAlteracao);
        bodyPutIndisponibilidade.setId(idAlteracao);
        bodyPutIndisponibilidade.setOutageTime(DataUtils.getDataDiferencaDias(1) + "T17:39:35.265Z");
        bodyPutIndisponibilidade.setPartial("true");
        bodyPutIndisponibilidade.setUnavailableEndpoints(unavailableEndpoints + DataUtils.getDataIndisponibilidade(0));
        return bodyPutIndisponibilidade;
    }
    public static BodyPutIndisponibilidade dadosAlterarIndisponibilidadeDataInvalida(){
        obterDadosParaAlterarIndisponibilidade();
        BodyPutIndisponibilidade bodyPutIndisponibilidade = new BodyPutIndisponibilidade();
        bodyPutIndisponibilidade.setDuration("P1DT3H");
        bodyPutIndisponibilidade.setExplanation("Teste");
        bodyPutIndisponibilidade.setId(idAlteracao);
        bodyPutIndisponibilidade.setOutageTime("30/10/2021");
        bodyPutIndisponibilidade.setPartial("true");
        bodyPutIndisponibilidade.setUnavailableEndpoints("Teste de Endpoints");
        return bodyPutIndisponibilidade;
    }
    public static BodyPutIndisponibilidade dadosAlterarIndisponibilidadeDataInferiorADataDaInclusaoDaIndisponibilidade(){
        String unavailableEndpoints = fake.internet().password();
        obterDadosParaAlterarIndisponibilidade();
        BodyPutIndisponibilidade bodyPutIndisponibilidade = new BodyPutIndisponibilidade();
        bodyPutIndisponibilidade.setDuration("P1DT3H");
        bodyPutIndisponibilidade.setExplanation("Teste");
        bodyPutIndisponibilidade.setId(idAlteracao);
        bodyPutIndisponibilidade.setOutageTime(DataUtils.getDataIndisponibilidade(-10));
        bodyPutIndisponibilidade.setPartial("true");
        bodyPutIndisponibilidade.setUnavailableEndpoints(unavailableEndpoints + DataUtils.getDataIndisponibilidade(0));
        return bodyPutIndisponibilidade;
    }
    public static BodyPutIndisponibilidade dadosAlterarIndisponibilidadeFlagIsPartialDeTrueParaFalseComUnavailableEndpointsVazio(){
        obterDadosParaAlterarIndisponibilidade();
        BodyPutIndisponibilidade bodyPutIndisponibilidade = new BodyPutIndisponibilidade();
        bodyPutIndisponibilidade.setDuration("P1DT3H");
        bodyPutIndisponibilidade.setExplanation("Teste");
        bodyPutIndisponibilidade.setId(idAlteracao);
        bodyPutIndisponibilidade.setOutageTime(DataUtils.getDataDiferencaDias(1) + "T17:39:35.265Z");
        bodyPutIndisponibilidade.setPartial("false");
        bodyPutIndisponibilidade.setUnavailableEndpoints("");
        return bodyPutIndisponibilidade;
    }
    public static BodyPutIndisponibilidade dadosAlterarIndisponibilidadeFlagIsPartialDeTrueParaFalseComUnavailableEndpointsPreenchido(){
        String unavailableEndpoints = fake.internet().password();
        obterDadosParaAlterarIndisponibilidade();
        BodyPutIndisponibilidade bodyPutIndisponibilidade = new BodyPutIndisponibilidade();
        bodyPutIndisponibilidade.setDuration("P1DT3H");
        bodyPutIndisponibilidade.setExplanation("Teste");
        bodyPutIndisponibilidade.setId(idAlteracao);
        bodyPutIndisponibilidade.setOutageTime(DataUtils.getDataDiferencaDias(1) + "T17:39:35.265Z");
        bodyPutIndisponibilidade.setPartial("false");
        bodyPutIndisponibilidade.setUnavailableEndpoints(unavailableEndpoints + DataUtils.getDataIndisponibilidade(0));
        return bodyPutIndisponibilidade;
    }
    public static BodyPutIndisponibilidade dadosAlterarIndisponibilidadeFlagIsPartialDeFalseParaTrueComUnavailableEndpointsVazio(){
        dadosAlterarIndisponibilidade();
        BodyPutIndisponibilidade bodyPutIndisponibilidade = new BodyPutIndisponibilidade();
        bodyPutIndisponibilidade.setDuration("P1DT3H");
        bodyPutIndisponibilidade.setExplanation("Teste");
        bodyPutIndisponibilidade.setId(idAlteracao);
        bodyPutIndisponibilidade.setOutageTime(DataUtils.getDataDiferencaDias(1) + "T17:39:35.265Z");
        bodyPutIndisponibilidade.setPartial("true");
        return bodyPutIndisponibilidade;
    }
    public static BodyPutIndisponibilidade dadosAlterarIndisponibilidadeFlagIsPartialDeFalseParaTrueComUnavailableEndpointsPreenchido(){
        String unavailableEndpoints = fake.internet().password();
        dadosAlterarIndisponibilidade();
        BodyPutIndisponibilidade bodyPutIndisponibilidade = new BodyPutIndisponibilidade();
        bodyPutIndisponibilidade.setDuration("P1DT3H");
        bodyPutIndisponibilidade.setExplanation("Teste");
        bodyPutIndisponibilidade.setId(idAlteracao);
        bodyPutIndisponibilidade.setOutageTime(DataUtils.getDataDiferencaDias(1) + "T17:39:35.265Z");
        bodyPutIndisponibilidade.setPartial("true");
        bodyPutIndisponibilidade.setUnavailableEndpoints(unavailableEndpoints + DataUtils.getDataIndisponibilidade(0));
        return bodyPutIndisponibilidade;
    }
//DADOS PARA TESTES DE EXCLUSÃO DE INDISPONIBILIDADE - DELETE
public static Response obterDadosParaDeletarIndisponibilidade() {
    BodyPostIndisponibilidade bodyPostIndisponibilidade = IndisponibilidadeDataFactory.dadosIncluirIndisponibilidade();
    Response response = (Response)   given()
            .body(bodyPostIndisponibilidade)
            .when()
            .post(urlOutages)
            .then()
            .extract().response();
    JsonPath extractor = response.jsonPath();
    idParaDeletar = extractor.get("id");
    durationAlteracao = extractor.get("duration");
    return response;
}
}
