package com.booking.clientapi;

import com.booking.model.Booking;
import com.booking.setup.BaseApi;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class RequestPatch extends BaseApi {

    @Test(dataProvider = "bookingParameters")
    public void setBooking(String firstname, String lastname, int status) throws ParseException {
        Booking bookingPatch = new Booking(firstname,lastname);

        given()

                .header(BASIC_AUTHENTICATION_HEADER, BASIC_AUTHENTICATION)
                .body(bookingPatch)
                .when()
                .patch(BOOKING + "1")
                .then()
                .assertThat()
                .log().all()
                .body("isEmpty()", Matchers.is(false))
                .body("firstname", is(firstname))
                .body("lastname", is(lastname))
                .statusCode(status)
                .log().all()
                //validando respuesta
                .contentType(equalTo("application/json; charset=utf-8"));

    }
    @DataProvider
    public static Object[][] bookingParameters() {
        return new Object[][] {
                {"Joe",	    "Juarez", 200},
                //no pueda editar datos incorrectos
                { "",       "perez", 400},
                {"Juana",   "	", 400},
                {"Juana132324",  "Lopez	", 400},
                {"Karen",   "28548254", 400},
                {"!@@#@%^%",   "/.,m[][*)(&%^", 400}

        };
    }
    @Test
    public void patchBooking(){
        //not found
        given()
                .body("{\n" +
                        "    \"firstname\" : \"James\",\n" +
                        "    \"lastname\" : \"Brown\"\n" +
                        "}")
                .header(BASIC_AUTHENTICATION_HEADER, BASIC_AUTHENTICATION)
                .when()
                .patch(BOOKING + "56")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .log().all();

    }
    @Test
    public void patchUserTest1() {

        given()
                .body("{\n" +
                        "    \"firstname\" : \"James\",\n" +
                        "    \"lastname\" : \"Brown\"\n" +
                        "}")
                .header(BASIC_AUTHENTICATION_HEADER, "Basic YWRtaW46cGFzc3dvcmQxMjMa=")
                .when()
                .patch(BOOKING + "1")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_FORBIDDEN)
                .log().all()
                .contentType(equalTo("text/plain; charset=utf-8"));

    }
}
