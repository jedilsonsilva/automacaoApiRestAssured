package br.com.realize.tests.base.massaOrbi;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GerarTokenOrbi {
    public Response gerarToken(){

        Response response = (Response) given().config(RestAssured.config()
                .encoderConfig(EncoderConfig.encoderConfig()
                        .encodeContentTypeAs("x-www-form-urlencoded", ContentType.URLENC)))
                .contentType(ContentType.URLENC.withCharset("UTF-8"))
                .formParam("grant_type", "client_credentials")
                .formParam("client_id","orbi-backend")
                .formParam("client_secret", "c20e7f8d-df46-4d16-bf96-891a166d5460")
                .when()
                .post("https://keycloak-dev.realizecfi.io/auth/realms/orbi-backend/protocol/openid-connect/token")
                .then()
                .statusCode(200)
                .extract().response();

        return response;
    }
    public Response gerarTokenSaldo(){

        Response response = (Response) given().config(RestAssured.config()
                .encoderConfig(EncoderConfig.encoderConfig()
                        .encodeContentTypeAs("x-www-form-urlencoded", ContentType.URLENC)))
                .contentType(ContentType.URLENC.withCharset("UTF-8"))
                .formParam("grant_type", "client_credentials")
                .formParam("client_id","7p3trtaroh0pv1p7fsai19lmhj")
                .formParam("client_secret", "dmm5qr5gnaq59ins2qju9qnsatchs7m7i61s555ern3tusafn5q")
                .when()
                .post("https://auth.hml.caradhras.io/oauth2/token")
                .then()
                .statusCode(200)
                .extract().response();

        return response;
    }
}
