package com.booking.clientapi;

import com.booking.setup.BaseApi;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;

public class DeleteBooking extends BaseApi {
    int countBooking;

    public int getCountBooking() {
        return countBooking;
    }
    @Test
    public void getBookings(){
        //String bookingId= given().get("/booking").path("[0].bookingid").toString();


//
//        System.out.println(bookingId);
//        System.out.println("-----------------");
//        System.out.println(responseBooking);
//        List<Map> bookingMap= from(responseBooking).getList("bookingid");  //.get("bookingid.findAll");


//
//        System.out.println("-----------------");
//        System.out.println(bookingMap);
//        System.out.println("-----------------");

        //return Integer.parseInt(bookingId);


       // int statusResponse= given().get("/booking").then().extract().statusCode();
        String responseBooking= given().get("/booking").then().extract().body().asString();
        countBooking= from(responseBooking).getList("bookingid").size();
        List<Integer> bookingMapint= from(responseBooking).getList("bookingid");
        System.out.println("--------bookingMapint---------");
        System.out.println(countBooking);
        System.out.println(bookingMapint);
        System.out.println("-----------------");
        for(Integer element : bookingMapint){
           Assert.assertEquals(getStatusCode(element),HttpStatus.SC_OK);
        }

    }

//    public void existBooking(){
//
//
//        for(Map element : bookingMap){
//            System.out.println(element);
//        }
//
//    }


    public int getStatusCode(int id){
        int statusResponse = get("/booking/"+id).then().extract().statusCode();
        System.out.println("User: "+id+"  Status code:  "+statusResponse);
        return statusResponse;
    }


}
