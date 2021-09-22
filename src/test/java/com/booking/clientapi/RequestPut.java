package com.booking.clientapi;

import com.booking.model.Booking;
import com.booking.setup.BaseApi;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasKey;

public class RequestPut extends BaseApi {
    //TODO PUT Update Booking -- basic auth
    @Test
    public void updateBookinggetBasicAtuh() {
        Booking requestPut = given()
                .header(BASIC_AUTHENTICATION_HEADER, BASIC_AUTHENTICATION)
                .when()
                .get("/booking/2")
                .as(Booking.class);

        requestPut.setAdditionalneeds("desayuno 1.0");

        Booking responseAdditionalNeeds =
                given()
                        .auth()
                        .preemptive()
                        .basic("admin", "password123")
                        .when()
                        .body(requestPut)
                        .put("/booking/2")
                        .then()
                        .contentType(equalTo("application/json; charset=utf-8"))
                        .extract()
                        .body()
                        .as(Booking.class);
//
        assertThat(responseAdditionalNeeds.getAdditionalneeds(), equalTo("desayuno 1.0"));
        System.out.println("hola");
    }

    //TODO PUT Update Booking -- Token
    @Test(dataProvider = "bookingParameters")
    public void updateBookingToken(String firstname, String lastname, int totalprice, boolean depositpaid, String bookingdates_checkin, String bookingdates_checkout, String additionalneeds, int status) throws ParseException {
        Booking bookigpost = new Booking(firstname, lastname, totalprice, depositpaid, bookingdates_checkin, bookingdates_checkout, additionalneeds);

//        for (int i = 0; i < bookingParameters().length; i++) {
//            for (int j = 0; j < bookingParameters().length; j++) {
//                if (bookingParameters()[i][j] == "sda") {
//                }
//            }
//        }
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

    @DataProvider
    public static Object[][] bookingParameters() {
        return new Object[][]{
                {"Joe", "Juarez", -10, true, "2021-10-01", "2021-10-15", "Breakfast", 400},
                {"li", "juarez", 100, false, "2021-10-02", "2021-10-16", "all", 400},
                {"Joe", "Jua", 200, true, "2021-10-03", "2021-10-17", "meal", 200},
                {"Juana", "la", 300, false, "2021-10-04", "2021-10-18", "drinks", 400},
                {"", "perez", 200, true, "	2021-10-05", "2021-10-19", "", 400},
                {"Juana", "	", 100, false, "2021-10-06", "2021-10-20", "", 400},
                {"Alicia", "Juarez", 0, true, "2021-10-07", "2021-10-21", "", 400},
                {"Juana", "mendoza", 1, false, "2021-10-08", "2021-10-22", "Breakfast", 200},
//                {"Anahi", "Cardenaz", "", true, "2021-10-09", "2021-10-23", "all", 400},
//                {"Julio", "Melendrez", 50, false, "2021-10-10", "2021-10-24", "meal", 200},
//                {"Gabriela", "Pancha", "veinte", false, "2021-10-11", "2021-10-25", "drinks", 400},
//                {"Anahi", "banana", 100, "", "2021-10-05", "2021-10-19", "", 400},
//                {"julian", "perez", 100, "no", "2021-10-06", "2021-10-20", "", 400},
//                {"Anahi", "banana", 100, 5, "2021-10-07", "2021-10-21", "", 400},
                {"julian", "perez", 100, true, "2021-10-08", "2021-10-22", "Breakfast", 200},
                {"Anahi", "banana", 100, false, "2021-10-09", "2021-10-23", "all", 200},
//                {"julian", "perez", 100, 0, "2021-10-10", "2021-10-24", "meal", 200},
//                {"Anahi", "banana", 100, 1, "2021-10-11", "2021-10-25", "drinks", 200},
                {"julian", "perez", 100, true, "2020-10-11", "2021-10-26", "meal", 400},
                {"Anahi", "banana", 100, false, "2021-10-11", "2020-10-11", "drinks", 400},
                {"julian", "perez", 100, true, "2021-10-10", "2021-10-09", "	", 400},
                {"Anahi", "banana", 100, false, "2021-10-09", "	", " ", 400},
                {"julian", "perez", 100, true, "2021-10-09", "	", " ", 400},
                {"Anahi", "banana", 100, false, "hi", "2021-10-09", "Breakfast", 400},
                {"julian", "perez", 100, true, "2021-10-09", "hi", "all", 400},
                {"Anahi", "banana", 100, false, "2021-10-09", "2021-10-25", "meal", 400},
                {"julian", "perez", 100, true, "2021-10-02", "2021-10-09", "drinks", 400},
                {"Anahi", "banana", 100, false, "2021-02", "2021-10-09", "	", 400},
                {"julian", "perez", 100, true, "2021-10-09", "2021-05", "	", 400},
        };
    }
}
