package com.cydeo.Homework;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HR extends HrTestBase {
    @Test
    public void test1() {

 /*
  Q1:
- Given accept type is Json
- Path param value- US
- When users sends request to /countries
- Then status code is 200
- And Content - Type is Json
- And country_id is US
- And Country_name is United States of America
- And Region_id is 2
  */

        Response response = given()
                .accept(ContentType.JSON)
                .pathParam("id", "US")
                .when()
                .get("/countries/{id}");


        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());

        JsonPath jsonPath = response.jsonPath();

        String countryID = jsonPath.getString("country_id");
        String countryName = jsonPath.getString("country_name");
        int regionID = jsonPath.getInt("region_id");

        assertEquals("US", countryID);
        assertEquals("United States of America", countryName);
        assertEquals(2, regionID);

    }

    @Test
    public void test2() {
        /*
        Given except type is Json
- Query param value - q={"department_id":80}
- When users sends request to /employees
- Then status code is 200
- And Content - Type is Json
- And all job_ids start with 'SA'
- And all department_ids are 80
- Count is 25
         */


        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"department_id\":80}")
                .when()
                .get("/employees");

        JsonPath jsonPath = response.jsonPath();

        //- Then status code is 200
        assertEquals(200, response.statusCode());

        //- And Content - Type is Json
        assertEquals("application/json", response.contentType());

        //- And all job_ids start with 'SA'
        List<String> list = jsonPath.getList("items.job_id");
        for (String each : list) {
            assertTrue(each.startsWith("SA"));
        }

        //- And all department_ids are 80
        List<Integer> department_ids = jsonPath.getList("items.department_id");
        for (Integer each : department_ids) {
            assertTrue(each == 80);
        }
        //- Count is 25
        assertEquals(25, jsonPath.getInt("count"));


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

        // And all regions_id is 3
        JsonPath jsonPath = response.jsonPath();
        List<Integer> allRegionsID = jsonPath.getList("items.region_id");
        for (Integer each : allRegionsID) {
            assertTrue(each == 3);
        }

        //  - And count is 6
        int count = jsonPath.getInt("count");
        assertTrue(count == 6);
        //- And hasMore is false

        boolean hasMore = jsonPath.getBoolean("hasMore");
        assertTrue(hasMore == false);

        //all countries
        //Country_name are;
        //Australia,China,India,Japan,Malaysia,Singapore
        List<String> country_names = jsonPath.getList("items.country_name");
        System.out.println("country_names = " + country_names);

        List<String> expectedCountryNames = new ArrayList<>(Arrays.asList("Australia", "China", "India", "Japan", "Malaysia", "Singapore"));
        System.out.println("expectedCountryNames = " + expectedCountryNames);

        assertEquals(expectedCountryNames, country_names);


    }

    @Test
    public void test4() {
     /*
        ORDS API:
Q1:
- Given accept type is Json
- Path param value- US
- When users sends request to /countries
- Then status code is 200
- And Content - Type is Json
- And country_id is US
- And Country_name is United States of America
- And Region_id is
      */
        Response response = given()
                .accept(ContentType.JSON)
                .pathParam("id", "US")
                .when()
                .get("/countries/{id}");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertEquals(response.header("Transfer-Encoding"), "chunked");
        assertTrue(response.headers().exist(), "Date");
        assertTrue(response.headers().hasHeaderWithName("ETag"));

        assertTrue(response.body().asString().contains("United States of America"));


    }


    @Test
    public void test5() {
/*
        ORDS API:
        Q1:
        - Given accept type is Json
        - Path param value- US
                - When users sends request to /countries
                - Then status code is 200
                - And Content - Type is Json
        - And country_id is US
                - And Country_name is United States of America
        - And Region_id is
      */

        Response response = given()
                .accept(ContentType.JSON)
                .pathParam("id", "US")
                .when()
                .get("/countries/{id}");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        System.out.println(response.body().prettyPrint());

        JsonPath jsonPath = response.jsonPath();

        String countryId = jsonPath.getString("country_id");
        System.out.println("countryId = " + countryId);
        String countryName = jsonPath.getString("country_name");
        System.out.println("countryName = " + countryName);
        int regionId = jsonPath.getInt("region_id");
        System.out.println("regionId = " + regionId);

    }

    @Test
    public void test6(){
        /*
        ORDS API:
        Q1:
        - Given accept type is Json
        - Path param value- US
                - When users sends request to /countries
                - Then status code is 200
                - And Content - Type is Json
        - And country_id is US
                - And Country_name is United States of America
        - And Region_id is
      */

        given()
                .accept(ContentType.JSON)
                .pathParam("id","US")
                .when().get("/countries/{id}")
                .then()
                .and()
                .statusCode(200)
                .and()
                .contentType("application/json")
                .and().body("country_id",is("US"))
                .and().body("country_name", is("United States of America"))
                .and().body("region_id",is(2))
                .and().body("country_name", startsWith("United"))
                .and().body("country_name", containsString("of"));




    }

    @Test
    public void test7(){
          Response response = given()
                  .accept(ContentType.JSON)
                  .pathParam("id",10)
                  .when()
                  .get("/departments/{id}")
                  .then()
                  .statusCode(200)
                  .contentType("application/json")
                  .extract().response();

        System.out.println(response.prettyPrint());

        Map<String, Object> map = response.as(Map.class) ;


        // i meant to print all keys

        for (String eachKey : map.keySet()) {

            System.out.print(eachKey);

        }

        // I want to print all value

        for (Object eachValue : map.values()) {
            System.out.println("eachValue = " + eachValue);
        }

        // I want to print value=kye

        for (Map.Entry<String, Object> stringObjectEntry : map.entrySet()) {
            System.out.println(stringObjectEntry);

        }


        
        
        










    }


}
