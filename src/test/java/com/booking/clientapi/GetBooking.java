package com.booking.clientapi;

import com.booking.setup.BaseApi;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetBooking extends BaseApi {


    @Test(testName = "GetAllBookings")
    public void GetBooking(){
        //String response= given().get("/booking").path("[0].bookingid").toString();
        given()
                .get("/booking/")
                .then()
                .assertThat()
                .header("Content-Type", "application/json; charset=utf-8")
                .statusCode(HttpStatus.SC_OK)
                .body("[0].bookingid",equalTo(6));
        System.out.println(response);
    }



}
