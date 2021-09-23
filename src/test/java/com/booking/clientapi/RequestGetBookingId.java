package com.booking.clientapi;

import com.booking.model.Booking;
import com.booking.setup.BaseApi;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RequestGetBookingId extends BaseApi {

    //TODO get booking by ID -- in progress TEST1
    @Test
    public void getBookingsId() {
        given()
                .when()
                .get(BOOKING + "2")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("$", hasKey("firstname"))
                .body(containsString("firstname"))
                .body("$", hasKey("lastname"))
                .body(containsString("lastname"))
                .body("$", hasKey("totalprice"))
                .body("$", hasKey("depositpaid"))
                .body("$", hasKey("bookingdates"));
    }

    //TODO get booking by ID -- in progress TEST2 ID doesnt exist
    @Test
    public void getBookingsId2() {
        given()
                .get(BOOKING + "100")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
