package com.booking.clientapi;

import com.booking.data.Data;
import com.booking.model.Booking;
import com.booking.setup.BaseApi;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.hamcrest.Matchers;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.util.Date;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;



public class RequestPost extends BaseApi {
    DateFormat formatter = null;
    Date convertedDate1 = null;
    Date convertedDate2 = null;

//    Data data = new Data();
//    Object[][] bookingParameters= data.bookingParameters();


    @Test(testName = "Create Booking ")
    public void createBooking() {


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
                .post(BOOKING)
                .then()

                .header("Content-Type", "application/json; charset=utf-8")
                .statusCode(HttpStatus.SC_OK)
                .body("bookingid", notNullValue());
    }


    @Test(dataProvider = "bookingParameters")
    public void setBooking(String firstname, String lastname, int totalprice, boolean depositpaid, String bookingdates_checkin, String bookingdates_checkout,String additionalneeds, int status) throws ParseException {
        //RestAssured.defaultParser = Parser.JSON;
        //System.out.println("\nTest "+Thread.currentThread().getStackTrace()[1].getMethodName()+":\n");
        Booking bookigpost = new Booking(firstname,lastname,totalprice,depositpaid, bookingdates_checkin,bookingdates_checkout,additionalneeds);


        given()
                .contentType(ContentType.JSON).log().all()
                .header("Accept", "application/json")
                .body(bookigpost)
                .post(BOOKING)
                .then()
                .assertThat()
                .log().all()
                .body("isEmpty()", Matchers.is(false)).
                body("booking.firstname", Matchers.equalTo(firstname)).
                body("booking.lastname", Matchers.equalTo(lastname)).
                body("booking.totalprice", Matchers.equalTo(totalprice)).
                body("booking.depositpaid", Matchers.equalTo(depositpaid)).
                body("booking.bookingdates.checkin", Matchers.not(Matchers.isEmptyOrNullString())).
                body("booking.bookingdates.checkout", Matchers.not(Matchers.isEmptyOrNullString())).
                body("booking.additionalneeds", Matchers.equalTo(additionalneeds))
                .statusCode(status)
                .body("bookingid", notNullValue());

	      /*  String yyyyMMdd1 = "1988-05-12";
	        String yyyyMMdd2 = "1754-01-25";
	        formatter =new SimpleDateFormat("yyyy-MM-dd");

	        convertedDate1 =(Date) formatter.parse(yyyyMMdd1);
	        convertedDate2 =(Date) formatter.parse(yyyyMMdd2);
	        System.out.println("Date from yyyyMMdd1 and yyyyMMdd2 Strings in Java : " + convertedDate1 + " "+convertedDate2);
	*/    }


    @DataProvider
    public static Object[][] bookingParameters() {
        return new Object[][] {
                {"Joe",	    "Juarez",   -10,    true,	"2021-10-01",	"2021-10-15",	"Breakfast",400},
                {"li",	    "juarez",   100,    false,	"2021-10-02",	"2021-10-16",	"all",	    400},
                {"Joe",	    "Jua",	    200,	true,	"2021-10-03",	"2021-10-17",	"meal",	    200},
                {"Juana",	"la",	    300,	false,	"2021-10-04",	"2021-10-18",	"drinks",	400},
                { "",       "perez",	200,	true,"	2021-10-05",	"2021-10-19",	""	,   400},
                {"Juana",   "	"	,   100,	false,	"2021-10-06",	"2021-10-20",		"",  400},
                {"Alicia",	"Juarez",	0,	    true,	"2021-10-07",	"2021-10-21",		"",  400},
                {"Juana",	"mendoza",	1,	    false,	"2021-10-08",	"2021-10-22",	"Breakfast",200},
                {"Anahi",	"Cardenaz",	"",     true,	"2021-10-09",	"2021-10-23",	"all",	    400},
                {"Julio",	"Melendrez",50,	    false,	"2021-10-10",	"2021-10-24",	"meal",	    200},
                {"Gabriela","Pancha",  "veinte",false,	"2021-10-11",	"2021-10-25",	"drinks",	400},
                {"Anahi",	"banana",	100,    "",  "2021-10-05",	"2021-10-19",	""	,   400},
                {"julian",	"perez",	100,	"no",	"2021-10-06",	"2021-10-20",	""	,   400},
                {"Anahi",	"banana",	100,	5,	    "2021-10-07",	"2021-10-21",	""	,   400},
                {"julian",	"perez",	100,	true,	"2021-10-08",	"2021-10-22",	"Breakfast",200},
                {"Anahi",	"banana",	100,	false,	"2021-10-09",	"2021-10-23",	"all",	    200},
                {"julian",	"perez",	100,	0,	    "2021-10-10",	"2021-10-24",	"meal",	    200},
                {"Anahi",	"banana",	100,	1,	    "2021-10-11",	"2021-10-25",	"drinks",	200},
                {"julian",	"perez",	100,	true,	"2020-10-11",	"2021-10-26",	"meal",	    400},
                {"Anahi",	"banana",	100,	false,	"2021-10-11",	"2020-10-11",	"drinks",	400},
                {"julian",	"perez",	100,	true,	"2021-10-10",	"2021-10-09",	"	"	,   400},
                {"Anahi",	"banana",	100,	false,	"2021-10-09",	"	",          " "	,       400},
                {"julian",	"perez",	100,	true,	"2021-10-09",	"	"	,	    " ",        400},
                {"Anahi",	"banana",	100,	false,	"hi",	        "2021-10-09",	"Breakfast",400},
                {"julian",	"perez",	100,	true,	"2021-10-09",	"hi",	        "all",	    400},
                {"Anahi",	"banana",	100,	false,	"2021-10-09",	"2021-10-25",	"meal",	    400},
                {"julian",	"perez",	100,	true,	"2021-10-02",	"2021-10-09",	"drinks",	400},
                {"Anahi",	"banana",	100,	false,	"2021-02",	    "2021-10-09",	    "	",  400},
                {"julian",	"perez",	100,	true,	"2021-10-09",	"2021-05",	    "	"	,   400},
        };
    }


}
