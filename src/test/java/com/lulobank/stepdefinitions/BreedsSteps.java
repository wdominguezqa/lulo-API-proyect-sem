package com.lulobank.stepdefinitions;

import com.lulobank.questions.ResponseCode;
import com.lulobank.tasks.GetBreeds;
import com.lulobank.tasks.GetImage;
import com.lulobank.utils.SetUpRest;
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

    @Before ("@breeds")
    public static void initialStage(){
        SetUpRest.restConfig();
        OnStage.setTheStage(new Cast());
        theActorCalled("david");
    }
    @Cuando("se envia la peticion al servicio {string} para traer todo el listado de razas")
    public void seEnviaLaPeticionAlServicioParaTraerTodoElListadoDeRazas(String pathApi) {
        theActorInTheSpotlight().attemptsTo(GetBreeds.requestGetMethod(pathApi));
    }

    @Entonces("se obtiene una respuesta exitosa del servicio")
    public void seObtieneUnaRespuestaExitosaDelServicio() {
        assertThat(CODES_DO_NOT_MATCH, theActorInTheSpotlight().asksFor(
                ResponseCode.getStatusCode()
        ), equalTo(STATUS_CODE_SUCCESS));
    }
}
