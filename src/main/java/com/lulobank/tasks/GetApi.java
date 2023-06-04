package com.lulobank.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class GetApi implements Task {

    private final String pathApi;

    public GetApi(String pathApi) {
        this.pathApi = pathApi;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Get.resource(pathApi)
                .with(requestSpecification
                        -> requestSpecification.header("x-api-key", "live_UY1bNWX0qtDAY7V3i2cT7P94YxfrZNXuGDvcgU6Azq8C4clEV2wd5FJzuFFgDLUP")));
    }

    public static Performable requestGetMethod (String pathApi){
        return Tasks.instrumented(GetApi.class, pathApi);
    }
}
