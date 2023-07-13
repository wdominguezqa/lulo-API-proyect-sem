package com.lulobank.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static com.lulobank.utils.Constants.API_KEY_VALUE;

public class PostFavourites implements Task {

    private final Object favouriteInfo;

    public PostFavourites(Object favouriteInfo) {
        this.favouriteInfo = favouriteInfo;
    }

    public static Performable infoPostFavourite(Object favouriteInfo){
        return Tasks.instrumented(PostFavourites.class, favouriteInfo);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Post.to("favourites")
                .with(requestSpecification -> requestSpecification
                        .header("x-api-key", API_KEY_VALUE)
                        .body(favouriteInfo)));
    }
}
