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
import static org.hamcrest.Matchers.notNullValue;

public class GetFilterByName {
    @BeforeEach
    public void setup(){
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.filters(new RequestLoggingFilter(),new ResponseLoggingFilter());
        RestAssured.requestSpecification = new RequestSpecBuilder().setContentType(ContentType.JSON).build();
    }
    @Test
    public void filterName(){

        given()
                .get("/booking?firstname=Jim&lastname=Ericsson")
        .then()
                //verificando el status code
                .statusCode(HttpStatus.SC_OK)
                .body("[0].bookingid",equalTo(3));

    }

    //.param("id",getFirstIdBooking())
}
