package com.talent;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PatchBooking {
    @BeforeEach
    public void setup(){
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.filters(new RequestLoggingFilter(),new ResponseLoggingFilter());
        RestAssured.requestSpecification = new RequestSpecBuilder().setContentType(ContentType.JSON).build();
    }
    @Test
    public void patchUserTest() {
        given()
                .body("{\n" +
                        "    \"firstname\" : \"James\",\n" +
                        "    \"lastname\" : \"Brown\"\n" +
                        "}")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
        .when()
                .patch("/booking/1")
        .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                //validando respuesta
                .contentType(equalTo("application/json; charset=utf-8"))
                .body("totalprice",equalTo(838));

    }
    @Test
    public void patchBooking(){
        //not found
        given()
                .body("{\n" +
                        "    \"firstname\" : \"James\",\n" +
                        "    \"lastname\" : \"Brown\"\n" +
                        "}")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .when()
                .patch("/booking/56")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND);

    }

       @Test
       public void patchUserTest1() {
           given()
                   .body("{\n" +
                           "    \"firstname\" : \"James\",\n" +
                           "    \"lastname\" : \"Brown\"\n" +
                           "}")
                   .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMj=")
                   .when()
                   .patch("/booking/1")
                   .then()
                   .assertThat()
                   .statusCode(HttpStatus.SC_FORBIDDEN)
                   .contentType(equalTo("text/plain; charset=utf-8"));

       }
}