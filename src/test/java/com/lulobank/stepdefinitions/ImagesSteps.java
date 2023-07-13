package com.lulobank.stepdefinitions;

import com.lulobank.questions.ResponseCode;
import com.lulobank.tasks.GetApi;
import com.lulobank.tasks.PostApi;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

import static com.lulobank.exceptions.ErrorAssertions.CODES_DO_NOT_MATCH;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ImagesSteps {

    @Before
    public static void actor(){
        OnStage.setTheStage(new Cast());
        theActorCalled("david");
    }

    @Before
    public static void setUpRest(){
        RestAssured.baseURI = "https://api.thecatapi.com";
        RestAssured.basePath = "/v1/images";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setRelaxedHTTPSValidation()
                .build();
    }
    @Dado("se tiene un usuario con permisos del APICAT")
    public void seTieneUnUsuarioConPermisosDelAPICAT() {
        theActorInTheSpotlight().whoCan(CallAnApi.at("/"));
    }

    @Cuando("Realizo la peticion al servicio {string}")
    public void realizoLaPeticionAlServicio(String pathApi) {
        theActorInTheSpotlight().attemptsTo(GetApi.requestGetMethod(pathApi));
    }

    @Entonces("Obtengo un codigo de repuesta {int}")
    public void obtengoUnCodigoDeRepuesta(int statusCode) {
        theActorInTheSpotlight().should(seeThat("El codigo de respuesta", ResponseCode.getStatusCode()
                , equalTo(statusCode)));
    }

    @Cuando("se envia la peticion con la informacion necesario para subir la imagen")
    public void seEnviaLaPeticionConLaInformacionNecesarioParaSubirLaImagen() {
        String uploadDataInfo = "{\n" +
                "    \"file\": \"/src/main/java/com/lulobank/files/cat_2.jpeg\"\n" +
                "}";
        theActorInTheSpotlight().attemptsTo(PostApi.infoUpload(uploadDataInfo));
    }

    @Entonces("se obtiene una respuesta exitosa para la peticion realizada")
    public void seObtieneUnaRespuestaExitosaParaLaPeticionRealizada() {
    }

    @Y("se valida que el registro de la imagen existe en el repositorio del APICAT")
    public void seValidaQueElRegistroDeLaImagenExisteEnElRepositorioDelAPICAT() {
    }
}
