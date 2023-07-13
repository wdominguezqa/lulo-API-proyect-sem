package com.lulobank.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static com.lulobank.utils.Constants.API_KEY_VALUE;


public class GetBreeds implements Task {

    private final String pathApi;

    public GetBreeds(String pathApi) {
        this.pathApi = pathApi;
    }

    public static Performable requestGetMethod (String pathApi){
        return Tasks.instrumented(GetImage.class, pathApi);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Get.resource(pathApi)
                .with(requestSpecification
                        -> requestSpecification.header("x-api-key", API_KEY_VALUE)));
    }
}
