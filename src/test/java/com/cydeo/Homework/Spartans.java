package com.cydeo.Homework;

import com.cydeo.utilities.SpartansTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;

public class Spartans extends SpartansTestBase {


    @Test
    public void test1() {
/*
        Given Accept type is application/json
        When user send GET request to  /api/spartans end point
        Then status code must be 200
        And response content type must be application/json
     */

        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/spartans");

        String contentType = response.contentType();
        System.out.println(contentType);

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());

    }

    @Test
    public void test2() {

        Response response = given()
                .accept(ContentType.XML)
                .when()
                .get("/api/spartans");

        String contentType = response.contentType();
        int statusCode = response.statusCode();
        System.out.println(contentType);
        System.out.println(statusCode);

        assertEquals(200, response.statusCode());
        assertEquals("application/xml", response.contentType());
        assertTrue(response.body().asString().contains("Meade"));

    }

    @Test
    public void test3() {
        // provide path parameters
        Response response = given()
                .accept(ContentType.JSON)
                .pathParam("id", 76)
                .when()
                .get("/api/spartans/{id}");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("Arnie"));

    }

    @Test
    public void test4() {

        Response response = given()
                .accept(ContentType.JSON)
                .pathParam("id", 33)
                .when()
                .get("/api/spartans/{id}");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("Wilek"));

        System.out.println("response.prettyPrint() = " + response.prettyPrint());
    }

    @Test
    public void test5() {

        Response response = given()
                .accept(ContentType.JSON)
                .pathParam("id", 66)
                .when()
                .get("/api/spartans/{id}");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("Quentin"));


    }

    @Test
    public void test6() {

        Response response = given()
                .accept(ContentType.JSON)
                .queryParams("gender", "Male")
                .when()
                .get("/api/spartans");


        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("Nels"));

    }

    @Test
    public void test7() {

        Response response = given()
                .accept(ContentType.JSON)
                .queryParam("gender", "Female")
                .when().get("/api/spartans/search");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("Bunnie"));


    }

    @Test
    public void test8() {

/*
        Given accept type is Json
        And query parameter values are:
        gender | Female
        nameContains | e
        When user sends GET request to /api / spartans / search
        Then response status code should be 200
        And response content - type:application / json
        And "Female" should be in response payload
        And "Janette" should be in response payload
         */

        Response response = given()
                .accept(ContentType.JSON)
                .queryParam("gender", "Female")
                .queryParams("nameContains", "e")
                .when()
                .get("/api/spartans/search");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));
        assertTrue(response.body().asString().contains("Paige"));

    }

    @Test
    public void test9() {
        /*
        Given except type is Json
        And query parameter values are:
        gender | Female
        nameContains | e
        When user sends GET request to /api / spartans / search
        Then response status code should be 200
        And response content - type:application / json
        And "Female" should be in response payload
        And "Janette" should be in response payload
         */


    }

    @Test
    public void test10() {
        //  let's add  as Map
        Map<String, String> mapQuery = new HashMap<>();
        mapQuery.put("gender", "Male");

        Response response = given()
                .accept(ContentType.JSON)
                .queryParams(mapQuery)
                .when()
                .get("/api/spartans/search");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());

        assertTrue(response.body().asString().contains("Rodolfo"));
        assertTrue(response.body().asString().contains("Franky"));

    }

    @Test
    public void test11() {

        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id", 10)
                .when()
                .get("api/spartans/{id}");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());

        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");

        //verify each value
        assertEquals(10, id);
        assertEquals("Lorenza", name);
        assertEquals("Female", gender);
        assertEquals(3312820936l, phone);
    }

    @Test
    public void test12() {
        // let's get the first id
        // first name
        Response response = given().log().all()
                .accept(ContentType.JSON)
                .when()
                .get("/api/spartans");

        int first_ID = response.path("id[0]");
        System.out.println(first_ID);

        String firstName = response.path("name[0]");
        System.out.println("firstName = " + firstName);

        // find the last name

        String lastName = response.path("name[-1]");
        System.out.println("lastName = " + lastName);


    }

    @Test
    public void test13() {


        /*
        /**
     * /*
     * Given accept type is json
     * And path param id is 10
     * When user sends a get request to "api/spartans/{id}"
     * Then status code is 200
     * And content-type is "application/json"
     * And response payload values match the following:
     * id is 10,
     * name is "Lorenza",
     * gender is "Female",
     * phone is 3312820936
     */

        Response response = given()
                .accept(ContentType.JSON)
                .and().pathParam("id", 10)
                .when().get("api/spartans/{id}");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        System.out.println("response.body().prettyPrint() = " + response.body().prettyPrint());

        JsonPath jsonPath = response.jsonPath();

        int id = jsonPath.getInt("id");
        String name = jsonPath.getString("name");
        String gender = jsonPath.getString("gender");
        long phone = jsonPath.getLong("phone");

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        assertEquals(10, id);

    }

    @Test
    public void test14() {

        /*
         * Given accept type is json
         * And path param id is 10
         * When user sends a get request to "api/spartans/{id}"
         * Then status code is 200
         * And content-type is "application/json"
         * And response payload values match the following:
         * id is 10,
         * name is "Lorenza",
         * gender is "Female",
         * phone is 3312820936
         */

        given()
                .accept(ContentType.JSON)
                .pathParam("id", 23)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json")
                .header("Date", notNullValue())
                .header("Transfer-Encoding", "chunked")
                .and().body("id", is(23))
                .and().body("name", is("Mervin"))
                .and().body("gender", is("Male"))
                .and().body("phone", is(9098436816L));

    }


    @Test
    public void test15() {
        Response response =
                given()
                        .accept(ContentType.JSON)
                        .when()
                        .get("/api/spartans")
                        .then()
                        .statusCode(200)
                        .contentType("application/json")
                        .and().header("Transfer-Encoding", "chunked")
                        .and().header("Keep-Alive", "timeout=60")
                        .and().header("Date", notNullValue())
                        .and().header("Connection", notNullValue())
                        .extract().response();


        List<Map<String, Object>> map = response.as(List.class);

        System.out.println("map.get(7).get(\"name\") = " + map.get(7).get("name"));





    }


}











