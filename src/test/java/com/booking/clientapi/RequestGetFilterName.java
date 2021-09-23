package com.booking.clientapi;
import static com.booking.setup.Methods.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import com.booking.setup.BaseApi;
import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import org.apache.http.HttpStatus;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RequestGetFilterName extends BaseApi{

    @Test(testName = "filter a name that exists")
    public void filterName(){

         given()
                .queryParam("firstname","Mark")
                .queryParam("lastname","Ericsson")
         .when()
                .get(BOOKING)
         .then()
                .statusCode(HttpStatus.SC_OK).log().all()
                 .body("[1].bookingid",notNullValue())
                 .and()
                 .body("[1].bookingid",equalTo(3))
                .contentType(equalTo("application/json; charset=utf-8"));

    }
    //
    @Test(testName = "if it doesn't exist, send it empty")
    public void filterNameError(){

        given()
                .queryParam("firstname","Erick")
                .queryParam("lastname","Brown")
        .when()
                .get(BOOKING)
        .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .body("[0].bookingid",emptyOrNullString())
                .contentType(equalTo("application/json; charset=utf-8"));

    }
    @Test(testName = "If it does not exist, send not found")
    public void filterNameBug(){

        given()
                .queryParam("firstname","Erick")
                .queryParam("lastname","Brown")
                .log().all()
        .when()
                .get(BOOKING)
        .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .log().all()
                .contentType(equalTo("application/json; charset=utf-8"));


    }
}
