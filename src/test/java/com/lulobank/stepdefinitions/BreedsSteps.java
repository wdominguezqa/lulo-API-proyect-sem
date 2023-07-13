package com.lulobank.stepdefinitions;

import com.lulobank.questions.ResponseCode;
import com.lulobank.tasks.GetBreeds;
import com.lulobank.tasks.GetImage;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;

import static com.lulobank.exceptions.ErrorAssertions.CODES_DO_NOT_MATCH;
import static com.lulobank.utils.Constants.*;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class BreedsSteps {

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
    @Cuando("se envia la peticion al servicio {string} para traer todo el listado de razas")
    public void seEnviaLaPeticionAlServicioParaTraerTodoElListadoDeRazas(String pathApi) {
        theActorInTheSpotlight().attemptsTo(GetBreeds.requestGetMethod(pathApi));
    }
}
