package com.cydeo.day5;

import com.cydeo.utilities.SpartansTestBase;
import com.cydeo.utilitiesDB.DB_Util;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class SpartansAPIvsDB extends SpartansTestBase {

    /**
     * when I  see response on Postman
     * are you sure that I have same on DB?  NO
     * when I got result on Postman
     *  I need to create SQL- query
     *  you need Rest Assured Library + JDBC
     *  you need to write method to do it
     *
     *
     */


    @DisplayName("Compare one spartan info api vs db")
    @Test
    public void test1() {

        Response response =
                given()
                        .accept(ContentType.JSON)
                        .pathParam("id", 15)
                        .when()
                        .get("/api/spartans/{id}")
                        .then()
                        .extract().response();
        // convert JSON response to MAP

        Map<String, Object> apiMAP = response.as(Map.class);
        System.out.println("apiMAP = " + apiMAP);

        // we need to get information from DB
        // ADD  dependency  from  Oracle
        // ADD  DB_utilities

       String query = "select SPARTAN_ID, name, GENDER,PHONE\n" +
               "from SPARTANS\n" +
               "where SPARTAN_ID = 15";

       DB_Util.runQuery(query);






    }


}
