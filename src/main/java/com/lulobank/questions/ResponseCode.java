package com.lulobank.questions;

import io.restassured.path.json.JsonPath;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Question;

public class ResponseCode{

    public static Question <Integer> getStatusCode(){
        return Question.about("Respuesta servicio").answeredBy(actor ->
                SerenityRest.lastResponse().statusCode());
    }

    public static Question <String> getIdImage(){
        return Question.about("ID de la imagen consultada").answeredBy(actor ->
                JsonPath.from(SerenityRest.lastResponse().body().asString()).get("id"));
    }

    public static Question <String> getIdFavouriteImage(){
        return Question
                .about("ID del primer registro de imagen encontrado marcada como favorita")
                .answeredBy(actor -> SerenityRest.lastResponse().jsonPath()
                        .getString("[0].id"));

    }

    public static Question<String> getMessageNotExistFavouriteRecord() {
        return Question.about("No existe un registro de la imagen marcada como favorita")
                .answeredBy(actor -> (SerenityRest.lastResponse().getBody().asString()));
    }
}
