package com.lulobank.stepdefinitions;

import com.lulobank.models.RegisterFavouriteInfo;
import com.lulobank.tasks.PostFavourites;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;

import static com.lulobank.utils.Constants.*;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class FavouritesSteps {

    @Before
    public static void actor(){
        OnStage.setTheStage(new Cast());
        theActorCalled("david");
    }

    @Before
    public static void setUpRest(){
        RestAssured.baseURI = BASE_URI_CATAPI;
        RestAssured.basePath = BASE_PATH_CATAPI;
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setRelaxedHTTPSValidation()
                .build();
    }

    @Cuando("se envia la peticion con la informacion necesario para marcar como favorita una imagen")
    public void seEnviaLaPeticionConLaInformacionNecesarioParaMarcarComoFavoritaUnaImagen() {

        RegisterFavouriteInfo registerFavouriteInfo = new RegisterFavouriteInfo();
        registerFavouriteInfo.setImage_id("ZWvxuftCe");

        theActorInTheSpotlight().attemptsTo(PostFavourites.infoPostFavourite(registerFavouriteInfo));
    }
}
