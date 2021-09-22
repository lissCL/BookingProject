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
        String apiURL = RestAssured.baseURI + BOOKING;
         given()
                .queryParam("firstname","Mark")
                .queryParam("lastname","Brown")
                .when()
                .get(apiURL)
                .then()
                .statusCode(HttpStatus.SC_OK)
                 .body("[0].bookingid",notNullValue())
                 .and()
                 .body("[0].bookingid",equalTo(3))
                .contentType(equalTo("application/json; charset=utf-8"));

    }
    //
    @Test(testName = "if it doesn't exist, send it empty")
    public void filterNameError(){
        //String apiURL = RestAssured.baseURI + BOOKING;
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
        String apiURL = RestAssured.baseURI + BOOKING;
        given()
                .queryParam("firstname","Erick")
                .queryParam("lastname","Brown")
                .log().all()
                .when()
                .get(apiURL)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .log().all()
                .contentType(equalTo("application/json; charset=utf-8"));


    }
}
