package com.booking.clientapi;

import com.booking.setup.BaseApi;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasKey;

public class RequestGetBooking extends BaseApi {

    @Test(testName = "Get all bookings ID")
    public void testBookingList(){
        given()
                .when()
                .get(BOOKING)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("bookingid",notNullValue());
    }



    @Test
    public void testBookingListID(){
        given()
                .when()
                .get(BOOKING)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("[0]", hasKey("bookingid"))
                .body("[0].bookingid", instanceOf(Integer.class))
        ;
    }




}
