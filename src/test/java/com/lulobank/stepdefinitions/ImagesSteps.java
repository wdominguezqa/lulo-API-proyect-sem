package com.lulobank.stepdefinitions;

import com.lulobank.questions.ResponseCode;
import com.lulobank.tasks.DeleteImage;
import com.lulobank.tasks.GetImage;
import com.lulobank.tasks.UploadImage;
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
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import static com.lulobank.exceptions.ErrorAssertions.INVALID_DATA;
import static com.lulobank.utils.Constants.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

import static com.lulobank.exceptions.ErrorAssertions.CODES_DO_NOT_MATCH;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class ImagesSteps {

    @Before
    public static void actor(){
        OnStage.setTheStage(new Cast());
        theActorCalled("david");
    }

    @Before
    public static void setUpRest(){
        RestAssured.baseURI = BASE_URI_CATAPI;
        RestAssured.basePath = BASE_PATH_IMAGES_CATAPI;
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

    @Cuando("se envia la peticion al servicio {string} para traer todo el listado de imagenes aprobadas")
    public void seEnviaLaPeticionAlServicioParaTraerTodoElListadoDeImagenesAprobadas(String pathApi) {
        theActorInTheSpotlight().attemptsTo(GetImage.requestGetMethod(pathApi));
    }

    @Entonces("Obtengo un codigo de repuesta {int}")
    public void obtengoUnCodigoDeRepuesta(int statusCode) {
        assertThat(CODES_DO_NOT_MATCH, theActorInTheSpotlight().asksFor(
                ResponseCode.getStatusCode()
        ), equalTo(statusCode));
    }

    @Cuando("se envia la peticion con la informacion necesario para subir la imagen")
    public void seEnviaLaPeticionConLaInformacionNecesarioParaSubirLaImagen() {
        String uploadDataInfo = "{\n" +
                "    \"file\": \"/src/main/java/com/lulobank/files/cat_2.jpeg\"\n" +
                "}";
        theActorInTheSpotlight().attemptsTo(UploadImage.infoUpload(uploadDataInfo));
    }

    @Entonces("se obtiene una respuesta exitosa para la peticion realizada")
    public void seObtieneUnaRespuestaExitosaParaLaPeticionRealizada() {
        theActorInTheSpotlight().should(seeThat("El codigo de respuesta", ResponseCode.getStatusCode()
        , equalTo("201")));
    }

    @Y("se valida que el registro de la imagen existe en el repositorio del APICAT")
    public void seValidaQueElRegistroDeLaImagenExisteEnElRepositorioDelAPICAT() {

    }

    @Cuando("se envia la peticion con el id {string} de la imagen")
    public void seEnviaLaPeticionConElIdDeLaImagen(String idValue) {
        theActorInTheSpotlight().attemptsTo(GetImage.requestGetMethod(idValue));
    }

    @Entonces("se obtiene una respuesta exitosa")
    public void seObtieneUnaRespuestaExitosa() {
        assertThat(CODES_DO_NOT_MATCH, theActorInTheSpotlight().asksFor(
                ResponseCode.getStatusCode()
        ), equalTo(STATUS_CODE_SUCCESS));
    }

    @Y("la informacion recibida corresponde a la consultada segun el id {string}")
    public void laInformacionRecibidaCorrespondeALaConsultadaSegunElId(String idValue) {
        assertThat(INVALID_DATA, theActorInTheSpotlight().asksFor(
                ResponseCode.getIdImage()
        ), equalTo(idValue));
    }

    @Cuando("se envia la peticion para borrar el registro con el id {string} de la imagen")
    public void seEnviaLaPeticionParaBorrarElRegistroConElIdDeLaImagen(String idValue) {
        theActorInTheSpotlight().attemptsTo(DeleteImage.deleteMethod(idValue));
    }

    @Entonces("la imagen se elimina del repositorio de imagenes del APICAT")
    public void laImagenSeEliminaDelRepositorioDeImagenesDelAPICAT() {
        assertThat(CODES_DO_NOT_MATCH, theActorInTheSpotlight().asksFor(
                ResponseCode.getStatusCode()
        ), equalTo(204));
    }
}
