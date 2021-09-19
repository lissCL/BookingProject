package com.booking;

import io.restassured.http.ContentType;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.nio.charset.StandardCharsets;
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


}
