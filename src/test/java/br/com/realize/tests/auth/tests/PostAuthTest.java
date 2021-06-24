package br.com.realize.tests.auth.tests;

import br.com.realize.tests.auth.requests.PostAuthRequest;

public class PostAuthTest
{
    PostAuthRequest postAuthRequest = new PostAuthRequest();

    public String geraToken() throws Exception {
        String token = postAuthRequest.tokenPrivate()
                .then()
                .statusCode(200)
        .extract().path("access_token");
        return token;
    }
}
