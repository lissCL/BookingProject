package com.booking.clientapi;

import com.booking.setup.BaseApi;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RequestGetBookingId extends BaseApi {
    //Request GET booking bu Id
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

    //Request get booking by Id doesnt exist
    @Test
    public void getBookingsId2() {
        given()
                .get(BOOKING + "100")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);

    }
}
