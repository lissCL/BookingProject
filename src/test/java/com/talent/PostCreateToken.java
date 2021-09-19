package com.talent;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class PostCreateToken {
    @BeforeEach
    public void setup(){
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.filters(new RequestLoggingFilter(),new ResponseLoggingFilter());
        RestAssured.requestSpecification = new RequestSpecBuilder().setContentType(ContentType.JSON).build();
    }
    @Test
    public void loginTest (){

        given()
                .body("{\n" +
                        "    \"username\" : \"admin\",\n" +
                        "    \"password\" : \"password123\"\n" +
                        "}")
                .post("/auth")
                .then()
                //verificando el status code
                .statusCode(200)
                //verificando que el token no sea null
                .body("token",notNullValue());


    }
    @Test
    public void errorLoginTest (){

        given()
                .body("{\n" +
                        "    \"username\" : \"admin1\",\n" +
                        "    \"password\" : \"password1234\"\n" +
                        "}")
                .post("/auth")
                .then()
                //verificando el status code
                //reportar bug
                .statusCode(HttpStatus.SC_NOT_ACCEPTABLE)
                .and()
                //verificar el mensaje de malas credenciales
                .body("reason",equalTo("Bad credentials"));

    }
}
