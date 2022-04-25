package com.cydeo.Homework;


import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class HR1 extends HrTestBase {

   /*
    Q1:
- Given accept type is Json
- Path param value- US
- When users send request to /countries
- Then status code is 200
- And Content - Type is Json
- And country_id is US
- And Country_name is United States of America
- And Region_id is 2
    */


    @Test
    public void test1() {

        Response response = given()
                .accept(ContentType.JSON)
                .pathParam("id", "US")
                .when()
                .get("/countries/{id}");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());

        System.out.println(response.prettyPrint());

        String actualCountry_id = response.path("country_id");
        String expectedCountry_id = "US";
        assertEquals(expectedCountry_id, actualCountry_id);

        //And Country_name is United States of America
        String expectedCountryName = "United States of America";
        String actualCountryName = response.path("country_name");
        assertEquals(expectedCountryName, actualCountryName);

        //And Region_id is 2
        JsonPath jsonPath = response.jsonPath();
        int expectedRegion_id = 2;
        int actualRegion_id = jsonPath.getInt("region_id");
        assertEquals(expectedRegion_id, actualRegion_id);

    }

    @Test
    public void test2() {

      /*
        - Given accept type is Json
- Query param value - q={"department_id":80}
- When users sends request to /employees
- Then status code is 200
- And Content - Type is Json
- And all job_ids start with 'SA'
- And all department_ids are 80
- Count is 25
Q3:
       */

        Response response = given()
                .accept(ContentType.JSON)
                .queryParam("q", "{\"department_id\":80}")
                .when()
                .get("/employees");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());

        //And all job_ids start with 'SA'

        JsonPath jsonPath = response.jsonPath();
        List<String> allJob_id = jsonPath.getList("items.job_id");
        for (String each : allJob_id) {
            assertTrue(each.startsWith("SA"));
        }

        // And all department_ids are 80

        List<Integer> allDepartments_id = jsonPath.getList("items.department_id");
        for (Integer each : allDepartments_id) {
            assertTrue(each == 80);
        }

   }


    @Test
    public void test3() {
/*
        Q3:
        - Given accept type is Json
        -Query param value q= region_id 3
                - When users sends request to /countries
                - Then status code is 200
                - And all regions_id is 3
                - And count is 6
                - And hasMore is false
                - And Country_name are;
        Australia,China,India,Japan,Malaysia,Singapore

        */
        Response response = given()
                .accept(ContentType.JSON)
                .queryParam("q", "{\"region_id\":3}")
                .when()
                .get("/countries");
        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());

      //  - And all regions_id is 3
        JsonPath jsonPath = response.jsonPath() ;
        List<Integer> allReqion_id =  jsonPath.getList("items.region_id");
        for (Integer each : allReqion_id) {
            assertTrue(each ==3);
        }

        //And Country_name are;
        // Australia,China,India,Japan,Malaysia,Singapore

        List<String> expectedCountryName =new ArrayList<>(Arrays.asList("Australia","China","India","Japan","Malaysia","Singapore")) ;
        System.out.println("expectedCountryName = " + expectedCountryName);
        List<String> actualCountryName = jsonPath.getList("items.country_name");
        System.out.println("actualCountryName = " + actualCountryName);

        Assertions.assertEquals(expectedCountryName, actualCountryName);


    }


}
