package com.cydeo.day3;

import com.cydeo.utilities.SpartansTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpartanTestsWithPath extends SpartansTestBase {



        /**
         * /*
         *      Given accept type is json
         *      And path param id is 10
         *      When user sends a get request to "api/spartans/{id}"
         *      Then status code is 200
         *      And content-type is "application/json"
         *      And response payload values match the following:
         *           id is 10,
         *           name is "Lorenza",
         *           gender is "Female",
         *           phone is 3312820936
         *    */

        @DisplayName("GET request to \"api/spartans/{id}\"")
        @Test
        public void test1 (){
            Response response = given()
                    .accept(ContentType.JSON)
                    .and()
                    .pathParam("id",10)
                    .when()
                    .get("api/spartans/{id}");

           assertEquals(200,response.statusCode());
           assertEquals("application/json", response.contentType());

            System.out.println("response.path(\"id\") = " + response.path("id"));
            System.out.println("response.path(\"name\") = " + response.path("name"));
            System.out.println("response.path(\"gender\") = " + response.path("gender"));
            System.out.println("response.path(\"phone\") = " + response.path("phone"));

            int id = response.path("id");
            String name = response.path("name");
            String gender = response.path("gender");
            long phone = response.path("phone");

            //verify each value
            assertEquals(10,id);
            assertEquals("Lorenza",name);
            assertEquals("Female",gender);
            assertEquals(3312820936l,phone);
        }
    @DisplayName("GET all spartan and navigate with Path()")
    @Test
    public void test2(){

        Response response = given()
                .accept(ContentType.JSON)
                .when().get("/api/spartans");

        int firstID = response.path("id[0]");
        System.out.println("firstID = " + firstID);

        String name = response.path("name[1]");
        System.out.println("name = " + name);

        String lastFirstName = response.path("name[-1]");
        System.out.println(lastFirstName);

        //how to get all names and store inside the list
        List<String> names = response.path("name");
        System.out.println(names);
        //print names one by one
        for (String s : names) {
            System.out.println(s);
        }

    }

    }



