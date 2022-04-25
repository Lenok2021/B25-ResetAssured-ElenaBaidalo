package com.cydeo.day5;


import com.cydeo.utilitiesDB.DBUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import com.cydeo.utilities.SpartansTestBase;
import org.junit.jupiter.api.DisplayName;

public class SpartansAPIvsDB extends SpartansTestBase {

    /**
     * when I  see response on Postman
     * are you sure that I have same on DB?  NO
     * when I got result on Postman
     * I need to create SQL- query
     * you need Rest Assured Library + JDBC
     * you need to write method to do it
     */


    @DisplayName("Compare one spartan info api vs db")
    @Test
    public void test1() {

        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id", 15)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200).extract().response();


        Map<String, Object> apiMap = response.as(Map.class);

        System.out.println(apiMap.toString());

        String query = "SELECT SPARTAN_ID,NAME,GENDER,PHONE\n" +
                "FROM SPARTANS\n" +
                "WHERE SPARTAN_ID = 15";

        Map<String, Object> dbMap = DBUtils.getRowMap(query);

        System.out.println(dbMap);

        assertThat(apiMap.get("id").toString(), is(dbMap.get("SPARTAN_ID").toString()));
        assertThat(apiMap.get("name").toString(), is(dbMap.get("NAME").toString()));
        assertThat(apiMap.get("gender").toString(), is(dbMap.get("GENDER").toString()));
        assertThat(apiMap.get("phone").toString(), is(dbMap.get("PHONE").toString()));


    }
}



