package com.booking.clientapi;

import com.booking.model.Booking;
import com.booking.model.RequestAuth;
import com.booking.model.ResponseAuth;
import com.booking.setup.BaseApi;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class RequestPostAuth extends BaseApi {


    @Test(testName = "create a user and generate a token")
    public void authUser(){

        RequestAuth user = new RequestAuth();
        user.setUsername("admin");
        user.setPassword("password123");

        ResponseAuth userResponse = given()
                .when()
                .body(user)
                .post(AUTH)
                .then()
                //verificando el status code
                .statusCode(HttpStatus.SC_OK)
                //verificando que el token no sea null
                .body("token",notNullValue())
                .log().all()
                .contentType(equalTo("application/json; charset=utf-8"))
                .extract()
                .body()
                .as(ResponseAuth.class);

        //assertThat(userResponse.getToken(),equalTo(getToken()));
    }
    @Test(testName = "the credentials are wrong")
    public void errorLoginTest (){

        RequestAuth user = new RequestAuth();
        user.setUsername("admin1");
        user.setPassword("password1231");

                given()
                .when()
                .body(user)
                .post(AUTH)
                .then()
                .statusCode(HttpStatus.SC_OK)
                //verificar el mensaje de malas credenciales
                .body("reason",equalTo("Bad credentials"))
                .log().all()
                .contentType(equalTo("application/json; charset=utf-8"))
                .extract()
                .body();
    }
    @Test(testName = "status code equal 406")
    public void errorLoginTest1(){
        //bug deberia mandar un status code 406
        RequestAuth user = new RequestAuth();
        user.setUsername("admin1");
        user.setPassword("password1231");

        given()
                .when()
                .body(user)
                .post(AUTH)
                .then()
                .statusCode(HttpStatus.SC_NOT_ACCEPTABLE)
                //verificar el mensaje de malas credenciales
                .log().all()
                .contentType(equalTo("application/json; charset=utf-8"))
                .extract()
                .body();
    }
}
