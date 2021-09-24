package com.booking.clientapi;

import com.booking.model.ResponseItem;
import com.booking.model.TestModelGetDate;
import com.booking.setup.BaseApi;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RequestGetBookingByDates extends BaseApi {

    @Test(dataProvider = "testModelProvider")
    public void getFiltersByDate2(LocalDate checkIn, LocalDate checkOut, int status) {
        TestModelGetDate testModel = new TestModelGetDate(checkIn, checkOut);
        ResponseItem response = new ResponseItem();
        given()
                .when()
                .get(BOOKING + "?" + "checkin=" + testModel.getCheckin() + "&" + "checkout=" + testModel.getCheckout()) //.param("id",getFirstIdBooking())
                .then()
                .statusCode(status)
                .body("[0]", hasKey("bookingid"))
                .body("[0]",notNullValue());
    }

    @DataProvider
    public static Object[][] testModelProvider() {
        List<LocalDate> checkIn = new ArrayList<>();

        checkIn.add(LocalDate.of(2012, 02, 12));
        checkIn.add(LocalDate.of(2021, 06, 12));
        checkIn.add(LocalDate.of(05, 06, 21));
        checkIn.add(LocalDate.of(12, 12, 12));
        checkIn.add(LocalDate.of(2010, 12, 12));
        checkIn.add(LocalDate.of(2020, 12, 12));

        return new Object[][]{
                {checkIn.get(0), checkIn.get(1), 200}, //boundary values
                {checkIn.get(2), checkIn.get(3), 612},//612
                {checkIn.get(4), checkIn.get(5), 200}
        };
    }
}
