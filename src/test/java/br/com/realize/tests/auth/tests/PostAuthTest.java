package br.com.realize.tests.auth.tests;

import br.com.realize.runners.fase02;
import br.com.realize.suites.AllTests;
import br.com.realize.suites.Healthcheck;
import br.com.realize.tests.auth.requests.PostAuthRequest;
import br.com.realize.tests.fase02.CartaoCredito.ApiPrivateConta.requests.GetPrivateContaCartaoCompraCreditoRequest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.*;

public class PostAuthTest
{
  /*  PostAuthRequest postAuthRequest = new PostAuthRequest();

    @Test
    public void geraToken() throws Exception {
        postAuthRequest.tokenPrivate()
                .then()
                .log().all()
                .statusCode(200)
        .extract().path("access_token");
    }*/
}
