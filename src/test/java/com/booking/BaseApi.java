package com.booking;

import io.restassured.RestAssured;
import io.restassured.filter.log.*;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;


public abstract class BaseApi{
    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    public String getToken(){
        Response response = RestAssured.given().body("{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}").contentType("application/json")
                .when().post("/auth");
        String token= response.path("token");
        return token;
    }

    public int getFirstIdBooking(){
        String bookingId= RestAssured.given().get("/booking").path("[0].bookingid").toString();
        return Integer.parseInt(bookingId);
    }

    public String getResponse(int id){
        String response= RestAssured.given().get("/booking/"+id).asString();
        return response;
    }


}