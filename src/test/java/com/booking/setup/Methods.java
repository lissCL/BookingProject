package com.booking.setup;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.testng.Assert;

import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;

public class Methods {
int countBooking;
final String  Url= "https://restful-booker.herokuapp.com/";

    public int getCountBooking() {
        return countBooking;
    }

    public int countBookings(){
        String responseBooking= given().get(Url+"booking").then().extract().body().asString();
        return countBooking= from(responseBooking).getList("bookingid").size();
    }
    public void Booking(){
        String responseBooking= given().get(Url+"booking").then().extract().body().asString();
        countBooking= from(responseBooking).getList("bookingid").size();

        List<Integer> bookingMapint= from(responseBooking).getList("bookingid");

//        System.out.println("--------bookingMapint---------");
//        System.out.println(countBooking);
//        System.out.println(bookingMapint);
//        System.out.println("-----------------");
        for(Integer element : bookingMapint){
            Assert.assertEquals(getStatusCode(element), HttpStatus.SC_OK);
        }
    }
    public int getStatusCode(int id){
        int statusResponse = get(Url+"booking").then().extract().statusCode();
        //System.out.println("User: "+id+"  Status code:  "+statusResponse);
        return statusResponse;
    }

}
