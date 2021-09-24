package com.booking.clientapi;

import com.booking.data.Data;
import com.booking.model.Booking;
import com.booking.setup.BaseApi;
import org.apache.http.ParseException;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasKey;

public class RequestPut extends BaseApi {

    //Request Put updateBookin by Id with BASIC AUTH
    @Test(dataProvider = "bookingParameters", dataProviderClass = Data.class)
    public void updateBookingBasicAuth(String firstname, String lastname, int totalprice, boolean depositpaid, String bookingdates_checkin, String bookingdates_checkout, String additionalneeds, int status) throws ParseException {
        Booking bookigpost = new Booking(firstname, lastname, totalprice, depositpaid, bookingdates_checkin, bookingdates_checkout, additionalneeds);
//
        given()
                .header(BASIC_AUTHENTICATION_HEADER, BASIC_AUTHENTICATION)
                .when()
                .body(bookigpost)
                .put(BOOKING + "2")
                .then()
                .body("$", hasKey("firstname"))
                .body(containsString("firstname"))
                .body("$", hasKey("lastname"))
                .body(containsString("lastname"))
                .body("$", hasKey("totalprice"))
                .body("$", hasKey("depositpaid"))
                .body("$", hasKey("bookingdates"))
                .body("firstname", Matchers.equalTo(firstname))
                .body("lastname", Matchers.equalTo(lastname))
                .body("totalprice", Matchers.equalTo(totalprice))
                .body("depositpaid", Matchers.equalTo(depositpaid))
                .body("bookingdates.checkin", Matchers.not(Matchers.isEmptyOrNullString()))
                .body("bookingdates.checkout", Matchers.not(Matchers.isEmptyOrNullString()))
                .body("additionalneeds", Matchers.equalTo(additionalneeds))
                .statusCode(status);
    }

    //Request Put update Booking by Id with TOKEN
    @Test(dataProvider = "bookingParameters", dataProviderClass = Data.class)
    public void updateBookingToken(String firstname, String lastname, int totalprice, boolean depositpaid,
                                   String bookingdates_checkin, String bookingdates_checkout, String additionalneeds,
                                   int status) throws ParseException {
        Booking bookigpost = new Booking(firstname, lastname, totalprice, depositpaid, bookingdates_checkin, bookingdates_checkout, additionalneeds);

        given()
                .header("Cookie", "token=" + getToken())
                .body(bookigpost)
                .put(BOOKING + "1")
                .then()
                .body("$", hasKey("firstname"))
                .body(containsString("firstname"))
                .body("$", hasKey("lastname"))
                .body(containsString("lastname"))
                .body("$", hasKey("totalprice"))
                .body("$", hasKey("depositpaid"))
                .body("$", hasKey("bookingdates"))
                .body("firstname", Matchers.equalTo(firstname))
                .body("lastname", Matchers.equalTo(lastname))
                .body("totalprice", Matchers.equalTo(totalprice))
                .body("depositpaid", Matchers.equalTo(depositpaid))
                .body("bookingdates.checkin", Matchers.not(Matchers.isEmptyOrNullString()))
                .body("bookingdates.checkout", Matchers.not(Matchers.isEmptyOrNullString()))
                .body("additionalneeds", Matchers.equalTo(additionalneeds))
                .statusCode(status);
    }
}
