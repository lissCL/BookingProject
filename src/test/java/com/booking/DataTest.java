package com.booking;


import com.booking.setup.BaseApi;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DataTest extends BaseApi {

    //TODO CRISTHIAN - METHOD GET - GET BOOKIN BY ID
    @Test
    public void getBookingsId() {
        given()
                .when()
                .get("/booking/2")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("bookingdates.checkin", equalTo("2020-11-04"));
    }

    //TODO Cristhian get booking by ID -- in progress TEST2---> ID doesnt exist

    @Test
    public void getBookingsId2() {
        given()
                .get("/booking/100")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }


}
