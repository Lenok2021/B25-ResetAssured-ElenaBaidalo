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

public class SpartansAPIvsDB extends SpartansTestBase {

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





    }


}
