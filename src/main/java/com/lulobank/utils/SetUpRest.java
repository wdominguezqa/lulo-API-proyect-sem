package com.lulobank.utils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;

import static com.lulobank.utils.Constants.BASE_PATH_CATAPI;
import static com.lulobank.utils.Constants.BASE_URI_CATAPI;

public class SetUpRest {

    public static void restConfig() {
        RestAssured.baseURI = BASE_URI_CATAPI;
        RestAssured.basePath = BASE_PATH_CATAPI;
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setRelaxedHTTPSValidation()
                .build();
    }

}
