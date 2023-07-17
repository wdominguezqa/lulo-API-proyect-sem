package com.lulobank.tasks;


import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Post;

import java.io.File;

import static com.lulobank.utils.Constants.API_KEY_VALUE;

public class UploadImage implements Task {

    private final String uploadData;

    public UploadImage(String uploadData) {
        this.uploadData = uploadData;
    }

    public static Performable infoUpload(String uploadData){
        return Tasks.instrumented(UploadImage.class, uploadData);
    }


    @Override
    public <T extends Actor> void performAs(T actor) {
        File file = new File(uploadData);
        actor.attemptsTo(Post.to("images/upload")
                .with(requestSpecification
                        -> requestSpecification
                        .header("x-api-key", API_KEY_VALUE)
                        .contentType(ContentType.MULTIPART)
                        .multiPart("file", file, "image/jpeg").log().all()
                ));
    }
}
