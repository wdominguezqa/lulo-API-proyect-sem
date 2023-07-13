package com.lulobank.tasks;


import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Post;

import java.io.File;

import static com.lulobank.utils.Constants.API_KEY_VALUE;

public class PostApi implements Task {

    private final String uploadData;

    public PostApi(String uploadData) {
        this.uploadData = uploadData;
    }

    public static Performable infoUpload(String uploadData){
        return Tasks.instrumented(PostApi.class, uploadData);
    }


    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Post.to("upload")
                .with(requestSpecification
                        -> requestSpecification.header("x-api-key", API_KEY_VALUE)
                        .contentType(ContentType.MULTIPART)
                        .multiPart("file", new File(uploadData), "multipart/form-data")
                ));
    }
}
