package com.talent;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class TestAuth {
    @BeforeEach
    public void setup(){
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.filters(new RequestLoggingFilter(),new ResponseLoggingFilter());
        RestAssured.requestSpecification = new RequestSpecBuilder().setContentType(ContentType.JSON).build();
    }

    @Test
    public void login(){

        given()
                .body("{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}")
                .post("/auth")
                .then()
                .statusCode(200)
                //verificar que el token no sea null
                .body("token",notNullValue());

    }
    @Test
    public void getBookingUserTest (){

        given()
                .get("/booking/2")
                .then()
                .statusCode(200)
                //verificar que el token no sea null
                .body("bookingdates.checkin",equalTo("2020-12-19"));
    }
//    @Test
//    public void patchBooking(){
//        //Unauthorized
//        String firstnameUpdated = given()
////                .body("{\n" +
////                        "    \"username\" : \"admin\",\n" +
////                        "    \"password\" : \"password123\"\n" +
////                        "}")
////                .get("/auth")
//                //.when()
//                .body("{\n" +
//                        "    \"firstname\" : \"Susan1\",\n" +
//                        "    \"lastname\" : \"Smith\"\n" +
//                        "}")
//                .patch("/booking/3")
//                .then()
//                .statusCode(200)
//                .extract()
//                .jsonPath()
//                .getString("firstname");
//        //.body("firstname",equalTo("Sally"))
//
//        assertThat(firstnameUpdated,equalTo("Susan1"));
//    }
}
