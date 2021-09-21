package com.booking;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.*;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.basic;


public abstract class BaseApi {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        //Content type abstract
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();

        //Cookie abstract
        Cookie cookie2 = new Cookie.Builder("token", "9ca0f97553fc7c0").setComment("comment 2").build();
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .addCookie(cookie2)
                .build();
        //Auth Abstrac
//        RestAssured.authentication = basic("admin","password123");
    }

    public String getToken() {
        Response response = RestAssured.given().body("{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}").contentType("application/json")
                .when().post("/auth");
        String token = response.path("token");
        return token;
    }

    public int getFirstIdBooking() {
        String bookingId = RestAssured.given().get("/booking").path("[0].bookingid").toString();
        return Integer.parseInt(bookingId);
    }

    public String getResponse(int id) {
        String response = RestAssured.given().get("/booking/" + id).asString();
        return response;
    }
}