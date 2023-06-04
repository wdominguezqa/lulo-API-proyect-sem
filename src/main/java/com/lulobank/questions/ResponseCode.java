package com.lulobank.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ResponseCode implements Question{

    public static Question<Integer> getStatusCode(){
        return new ResponseCode();
    }

    @Override
    public Object answeredBy(Actor actor) {
        return SerenityRest
                .lastResponse().statusCode();
    }
}
