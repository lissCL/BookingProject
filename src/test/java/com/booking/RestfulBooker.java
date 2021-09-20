package com.booking;

import io.restassured.http.ContentType;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.notNullValue;

public class RestfulBooker extends BaseApi {

//    @Test
//    public void createToken(){
//
//        RestAssured
//                .given()
//                .log().all()
//                .contentType(ContentType.JSON)
//                .body("{\n" +
//                        "    \"username\" : \"admin\",\n" +
//                        "    \"password\" : \"password123\"\n" +
//                        "}")
//                .post("/auth")
//                .then()
//                .log().all()
//                .statusCode(HttpStatus.SC_OK)
//                .body("token", notNullValue());
//    }

    @Test
    public void GetBooking(){
        String response= given().get("/booking").path("[0].bookingid").toString();
        given()
                .get("/booking/")
                .then()
                .assertThat()
                .header("Content-Type", "application/json; charset=utf-8")
                .statusCode(HttpStatus.SC_OK)
                .body("[0].bookingid",equalTo(6));
        System.out.println(response);
    }
    @Test
    public void getBookingId(){
        given()

                .get("/booking/"+getFirstIdBooking())
                .then()
                .assertThat()
                .header("Content-Type", "application/json; charset=utf-8")
                .statusCode(HttpStatus.SC_OK);
                //.body("[0].bookingid",equalTo(6))
    }

    @Test
    public void createBooking(){

        given()

                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"firstname\" : \"Jim\",\n" +
                        "    \"lastname\" : \"Brown\",\n" +
                        "    \"totalprice\" : 20,\n" +
                        "    \"depositpaid\" : true,\n" +
                        "    \"bookingdates\" : {\n" +
                        "        \"checkin\" : \"2019-02-31\",\n" +
                        "        \"checkout\" : \"2019-01-01\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\" : \"Breakfast\"\n" +
                        "}")
                .post("/booking")
                .then()

                .header("Content-Type", "application/json; charset=utf-8")
                .statusCode(HttpStatus.SC_OK)
                .body("bookingid", notNullValue());

    }

    @Test(testName = "deleteBooking")
    public void deleteBooking(){
        int id= getFirstIdBooking();
        //if the booking exist should pass the next
        getBookingId();
        //deleted a booking ID
        given()
               .header("Cookie","token="+getToken())
               //.param("id",getFirstIdBooking())
               .when()
               .delete("/booking/"+id)
               .then()
               .statusCode(HttpStatus.SC_CREATED);

        //if the booking was deleted should pass the next assert
        Assert.assertEquals(getResponse(id),"Not Found");

    }
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

    //TODO get booking by ID -- in progress TEST2---> ID doesnt exist

    @Test
    public void getBookingsId2() {
        given()
                .get("/booking/100")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }
    //TODO Get filters ByDate -- in progress

    @DataProvider(name = "TestDate")

    public Object[][] testForPost() {

        List<LocalDate> checkIn = new ArrayList<>();

        checkIn.add(LocalDate.of(2015, 2, 12));
        checkIn.add(LocalDate.of(2017, 6, 12));

        Object[][] obj = new Object[1][2];

        obj[0][0] = checkIn.get(0).toString();
        obj[0][1] = checkIn.get(1).toString();

        return obj;
    }

    //TODO Get filters ByDate -- in progress

    @Test(dataProvider = "TestDate")
    public void getFiltersByDate(String checkin, String checkout) {
        System.out.println(checkin);
        System.out.println(checkout);
        given()
                .when()
                .get("booking?checkin=" + checkin + "&checkout=" + checkout) //.param("id",getFirstIdBooking())
                .then()
                .statusCode(HttpStatus.SC_OK);

    }

    //TODO PUT Update Booking -- basic auth
    @Test
    public void updateBookinggetBasicAtuh() {
        ResponsePut requestPut = given()
                .auth()
                .preemptive()
                .basic("admin", "password123")
                .when()
                .get("/booking/2")
                .as(ResponsePut.class);

        requestPut.setAdditionalneeds("desayuno 1.0");

        ResponsePut responseAdditionalNeeds =
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
                        .as(ResponsePut.class);
//
//        assertThat(responseAdditionalNeeds.getAdditionalneeds(), equalTo("desayuno"));
    }

    //TODO PUT Update Booking -- Token
    @Test
    public void updateBookinWithToken() {
//        Cookie cookie2 = new Cookie.Builder("token", "2c92a420b5a006e").setComment("comment 2").build();
//        Cookies cookies1 = new Cookies(cookie2);
        given()
                .body("{\n" +
                        "    \"firstname\" : \"CRIS\",\n" +
                        "    \"lastname\" : \"LOPEZ\",\n" +
                        "    \"totalprice\" : 111,\n" +
                        "    \"depositpaid\" : true,\n" +
                        "    \"bookingdates\" : {\n" +
                        "        \"checkin\" : \"2018-01-01\",\n" +
                        "        \"checkout\" : \"2019-01-01\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\" : \"Breakfast\"\n" +
                        "}")
                .when()
                .put("/booking/1")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }


}
