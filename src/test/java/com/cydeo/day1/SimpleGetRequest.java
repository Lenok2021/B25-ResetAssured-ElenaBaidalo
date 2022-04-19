package com.cydeo.day1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SimpleGetRequest {

    String url = "http://54.234.53.165:8000/api/spartans";


    @Test
    public void test1() {

        // send a get request and save response inside the Response Object
        Response response = RestAssured.get(url);

        /**
         * print response status code
         * through the Object we can get what ever your want
         */
        System.out.println("response.statusCode() = " + response.statusCode());

        // print response body
        response.prettyPrint();

        // verify status code is 200
        Assertions.assertEquals(200, response.statusCode());



    }


}
