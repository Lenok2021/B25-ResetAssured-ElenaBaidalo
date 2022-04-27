package com.cydeo.Homework;

import com.cydeo.pojoZipCode.Place;
import com.cydeo.pojoZipCode.ZipCodeMain;
import com.cydeo.utilities.ZipCodeBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ZipCode extends ZipCodeBase {
    /*
        Given Accept application/json
        And path zipcode is 22031
        When I send a GET request to /us endpoint
        Then status code must be 200
        And content type must be application/json
        And Server header is cloudflare
        And Report-To header exists
        And body should contains following information
        post code is 22031
        country  is United States
        country abbreviation is US
        place name is Fairfax
        state is Virginia
        latitude is 38.8604
    */
    @Test
    public void test_path() {



    }


    @Test
    public void test_HarmCrest() {
      /*
        Given Accept application/json
        And path zipcode is 22031
        When I send a GET request to /us endpoint
        Then status code must be 200
        And content type must be application/json
        And Server header is cloudflare
        And Report-To header exists
        And body should contains following information
        post code is 22031
        country  is United States
        country abbreviation is US
        place name is Fairfax
        state is Virginia
        latitude is 38.8604
    */
        given()
                .accept(ContentType.JSON)
                .pathParam("zipcode", 22031)
                .when()
                .get("/us/{zipcode}")
                .then()
                .and()
                .statusCode(200)
                .and()
                .contentType("application/json")
                .and()
                .header("Server", "cloudflare")
                .and()
                .header("Report-To", notNullValue())
                .assertThat()
                .body("'post code'", is(22031 + ""), "country", is("United States")
                        , "'country abbreviation'", is("US"), "places[0].'place name'", is("Fairfax")
                        , "places[0].state", is("Virginia"), "places[0].latitude", is(38.8604 + ""));


    }

    @Test
    public void Response() {

        Response response = given()
                .accept(ContentType.JSON)
                .pathParam("zipcode", 22031)
                .when()
                .get("/us/{zipcode}")
                .then()
                .and()
                .statusCode(200)
                .and()
                .contentType("application/json")
                .and()
                .header("Server", "cloudflare")
                .and()
                .header("Report-To", notNullValue())
                .extract().response();

        ZipCodeMain zipCodeMain = response.as(ZipCodeMain.class);


        // post code is 22031
        assertEquals("22031", zipCodeMain.getPostCode());

        // country  is United States
        assertEquals("United States", zipCodeMain.getCountry());
        // country abbreviation is US
        assertEquals("US", zipCodeMain.getCountryAbbreviation());


        Place place = zipCodeMain.getPlaces().get(0);
        assertEquals("Fairfax", place.getPlaceName());
        assertEquals("Virginia", place.getState());
        assertEquals("38.8604", place.getLatitude());

    }

    @Test
    public void negativeTest() {
     /*
     Given Accept application/json
And path zipcode is 50000
When I send a GET request to /us endpoint
Then status code must be 404
And content type must be application/json

      */
    }


    @Test
    public void test3(){
        /*
        Given Accept application/json
        And path state is va
        And path city is farifax
        When I send a GET request to /us endpoint
        Then status code must be 200
        And content type must be application/json
        And payload should contains following information
        country abbreviation is US
        country  United States
        place name  Fairfax
        each places must contain fairfax as a value
        each post code must start with 22
        */

        given()
                .pathParam("state","VA")
                .and()
                .pathParam("city", "Fairfax")
                .when()
                .get("/us/{state}/{city}")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .and().body("'country abbreviation'",is("US"),
                        "'place name'", is("Fairfax"),
                        "places.'place name'",everyItem(containsString("Fairfax")),
                        "places.'post code'",everyItem(startsWith("22")));



    }


}
