package com.lulobank.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Delete;

import static com.lulobank.utils.Constants.API_KEY_VALUE;

public class DeleteImage implements Task {

    private final String idValue;

    public DeleteImage(String idValue) {
        this.idValue = idValue;
    }

    public static Performable deleteMethod(String idValue){
        return Tasks.instrumented(DeleteImage.class, idValue);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Delete.from(idValue)
                .with(requestSpecification
                        -> requestSpecification.header("x-api-key", API_KEY_VALUE)));
    }
}
