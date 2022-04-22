package com.cydeo.day3;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HRApiWithPath extends HrTestBase {


    @DisplayName("GET request to countries with Path method")
    @Test
    public void test1() {

        Response response = given()
                .accept(ContentType.JSON)
                .queryParam("q", "{\"region_id\":2}")
                .when()
                .get("/countries");

        //response.prettyPrint();
        //print limit result
        System.out.println(response.path("limit").toString());
        //print hasMore
        System.out.println(response.path("hasMore").toString());
        //print second country id
        System.out.println(response.path("items[1].country_id").toString());
        //print 4th element country name
        System.out.println(response.path("items[3].country_name").toString());

        //get me all country names
        List<String> countryNames = response.path("items.country_name") ;
        System.out.println(countryNames);

        // assert that ALL regions ID = 2

        List<Integer> allRegions_ID = response.path("items.region_id") ;
        for (Integer eachRegion : allRegions_ID) {
            assertEquals(2,eachRegion);
        }



    }


    @Test
    public void test2() {
     /*
        send a GET request o employees endpoint, filter only Job_id IT_PROG
        then assert that all job_ids are IT_PROG
     */
        Response response = given()
                .accept(ContentType.JSON)
                .queryParam("q", "{\"job_id\":\"IT_PROG\"}")
                .when()
                .get("/employees");


        List<String> jobID = response.path("items.job_id");

        System.out.println("jobID = " + jobID);
        //assert one by one that they are equal to 2
        for (String eachJOBID : jobID) {
            assertEquals("IT_PROG", eachJOBID);

        }
        int amount = jobID.size();
        System.out.println("amount = " + amount);

    }


    }


