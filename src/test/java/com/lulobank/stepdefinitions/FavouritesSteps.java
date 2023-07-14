package com.lulobank.stepdefinitions;

import com.lulobank.models.RegisterFavouriteInfo;
import com.lulobank.questions.ResponseCode;
import com.lulobank.tasks.DeleteFavourite;
import com.lulobank.tasks.GetFavourites;
import com.lulobank.tasks.PostFavourites;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Y;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;

import static com.lulobank.exceptions.ErrorAssertions.INVALID_STATUS_RECORD;
import static com.lulobank.utils.Constants.*;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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

    @Y("se consulta un registro de imagen favorita en el servicio {string}")
    public void seConsultaUnRegistroDeImagenFavoritaEnElServicio(String pathApi) {

        theActorInTheSpotlight().attemptsTo(GetFavourites.requestGetFavourite(pathApi));
        theActorInTheSpotlight().remember("id_favourite", ResponseCode.getIdFavouriteImage());
    }

    @Cuando("se envia la peticion con el registro consultado para eliminarlo")
    public void seEnviaLaPeticionConElRegistroConsultadoParaEliminarlo() {
        theActorInTheSpotlight().attemptsTo(DeleteFavourite.idFavouriteToDelete(
                theActorInTheSpotlight().recall("id_favourite")));
    }

    @Y("se consulta el registro")
    public void seConsultaElRegistro() {
        String idFavouriteDeleted= theActorInTheSpotlight().recall("id_favourite");
        theActorInTheSpotlight().attemptsTo(GetFavourites.requestGetFavourite(
                "favourites"+"/"+idFavouriteDeleted));
    }

    @Y("el registro no debe existir")
    public void elRegistroNoDebeExistir() {
        assertThat(INVALID_STATUS_RECORD, theActorInTheSpotlight().asksFor(
                ResponseCode.getMessageNotExistFavouriteRecord()
        ), equalTo("NOT_FOUND"));
    }
}
