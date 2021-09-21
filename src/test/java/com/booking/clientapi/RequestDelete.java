package com.booking.clientapi;

import com.booking.setup.Methods;
import org.apache.http.HttpStatus;



import static com.booking.setup.Methods.*;
import static io.restassured.RestAssured.given;
import com.booking.setup.BaseApi;
import org.testng.Assert;
import org.testng.annotations.Test;


public class RequestDelete extends BaseApi {


    @Test(testName = "Delete Booking with Token")
    public void testDeleteBooking(){
        int testId = getValidIdBooking();
        given()
                .header("Cookie", "token="+ getToken())
                .when()
                .delete(BOOKING+testId)
                .then()
                .statusCode(HttpStatus.SC_CREATED);

        Assert.assertEquals(getResponse(testId), "Not Found","Status code should be 201, but it's not");

    }

    @Test(testName = "Delete Booking with Basic Auth")
    public void testDeleteBookingBasicAuth(){
        int testId = getValidIdBooking();
        given()
                .header(BASIC_AUTHENTICATION_HEADER, BASIC_AUTHENTICATION)
                .when()
                .delete(BOOKING+testId)
                .then()
                .statusCode(HttpStatus.SC_CREATED);

        Assert.assertEquals(getResponse(testId), "Not Found","Status code should be 201, but it's not");
    }

    @Test(testName = "Delete Booking with out Authorization")
    public void testDeleteBookingWithOutAuth(){
        int testId = getValidIdBooking();
        given()
                .when()
                .delete(BOOKING+testId)
                .then()
                .statusCode(HttpStatus.SC_FORBIDDEN);

    }

}
