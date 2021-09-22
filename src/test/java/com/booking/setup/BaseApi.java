package com.booking.setup;

import com.booking.clientapi.RequestGetBooking;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.*;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class BaseApi {
    public static final String BASIC_AUTHENTICATION = "Basic YWRtaW46cGFzc3dvcmQxMjM=";
    public static final String BASIC_AUTHENTICATION_HEADER = "Authorization";
    public static final String TOKEN_HEADER = "Token";
    public static final String BOOKING = "/booking/";
    public static final String AUTH = "/auth";
    public static final Logger logger = LogManager.getLogger(BaseApi.class);

    @BeforeClass
    public static void setUp() {
        logger.info("setUp");
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
//        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();
    }

    public static void getTokenBuild() {
        Cookie cookie2 = new Cookie.Builder(TOKEN_HEADER, getToken()).setComment("comment 2").build();
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .addCookie(cookie2)
                .build();
    }

    //Liseth
    public static String getToken() {
        Response response = RestAssured.given().body("{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}").contentType("application/json")
                .when().post("/auth");
        String token = response.path("token");
        return token;
    }


}