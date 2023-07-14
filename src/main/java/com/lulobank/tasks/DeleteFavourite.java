package com.lulobank.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Delete;

import static com.lulobank.utils.Constants.API_KEY_VALUE;

public class DeleteFavourite implements Task {
    private final String idFavourite;

    public DeleteFavourite(String idFavourite) {
        this.idFavourite = idFavourite;
    }

    public static Performable idFavouriteToDelete(String idFavourite){
        return Tasks.instrumented(DeleteFavourite.class, idFavourite);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Delete.from("favourites"+"/"+idFavourite)
                .with(requestSpecification ->
                        requestSpecification.header("x-api-key", API_KEY_VALUE)));
    }
}
