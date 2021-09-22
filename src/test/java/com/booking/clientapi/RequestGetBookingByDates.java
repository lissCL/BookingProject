package com.booking.clientapi;

import com.booking.model.Booking;
import com.booking.model.Bookingdates;
import com.booking.setup.BaseApi;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.nio.file.LinkOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class RequestGetBookingByDates extends BaseApi {

    @Test(dataProvider = "bookingParameters")
    public void getFiltersByDate(String bookingdates_checkin, String bookingdates_checkout, int status) throws ParseException {
        Bookingdates bookingGetFiltersDates = new Bookingdates(bookingdates_checkin, bookingdates_checkout);

        given()
                .when()
                .get(BOOKING + "?" + "checkin=" + bookingGetFiltersDates + "&" + "checkout=" + bookingGetFiltersDates) //.param("id",getFirstIdBooking())
                .then()
                .statusCode(status);

    }

    @DataProvider
    public static Object[][] bookingParameters() {
        List<LocalDate> checkIn = new ArrayList<>();

        checkIn.add(LocalDate.of(2012, 2, 12));
        checkIn.add(LocalDate.of(2021, 6, 12));
        checkIn.add(LocalDate.of(05, 06, 21));
        checkIn.add(LocalDate.of(12, 12, 12));
        return new Object[][]{
                {checkIn.get(0), checkIn.get(1), 200}, //boundary values
                {checkIn.get(2), checkIn.get(3), 400},
                {"2015-06-12", "2020-06-12", 400}
        };
    }
}
