package br.com.realize.tests.base.tests;

import io.restassured.http.ContentType;

public interface Constantes {
    String APP_Base_URL = "http://10.120.192.16/open-banking/";
    ContentType APP_CONTENT_TYPE = ContentType.JSON;
    Long MAX_TIMEOUT = 50000L;
}
