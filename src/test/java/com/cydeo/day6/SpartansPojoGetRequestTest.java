package com.cydeo.day6;

import com.cydeo.pojoSpartans.SearchSpartans;
import com.cydeo.pojoSpartans.Spartan;
import com.cydeo.utilities.SpartansTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class SpartansPojoGetRequestTest extends SpartansTestBase {


    @DisplayName("GET one spartan and convert it to Spartan Object")
    @Test
    public void test1(){
        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id", 15)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200).log().body()
                .extract().response();

        //De serialize --> JSON to POJO(Java custom class)
        //2 different way to do this
        //1.using as() method
        //we convert json response to spartan object with the help of Jackson
        Spartan spartan15 = response.as(Spartan.class);
        System.out.println(spartan15);

        System.out.println(spartan15.getName());
        System.out.println(spartan15.getPhone());

        //second way of deserialize to java
        //2.using JsonPath to deserialize to custom class
        JsonPath jsonPath = response.jsonPath();

        Spartan s15 = jsonPath.getObject("",Spartan.class);
        System.out.println(s15.getName());
        System.out.println(s15.getGender());

    }

    @DisplayName("GET one spartan from search endpoint and use POJO")
    @Test
    public void test2(){

        JsonPath jsonPath = given()
                .accept(ContentType.JSON)
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .extract().jsonPath();

        //get the second spartan from the content list and put inside the spartan object
        Spartan spartan = jsonPath.getObject("content[1]", Spartan.class);
        System.out.println(spartan.getName());
        System.out.println(spartan);

    }

    @DisplayName("GET one spartan from search endpoint and use POJO")
    @Test
    public void test3() {

        Response response = given()
                .accept(ContentType.JSON)
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .extract().response();

        //get the full content json and convert it to Search object
        SearchSpartans searchResult = response.as(SearchSpartans.class);
        //  WHEN  YOU  USE RESPONSE AS WE CREATED ONE MORE SEARCH CLASS
        // AND STORE ALL SPARTANS AS  A LIST  AND THEN WE USE JAVA METHODS AND
        // GET ACCESS TO SPECIFIC JSON (SPARTAN)

        System.out.println(searchResult.getTotalElement());
        System.out.println(searchResult.getContent().get(1).getName());


    }
    @DisplayName("GET /api/spartans/search and save as List<Spartan>")
    @Test
    public void test4(){

        Response response = given()
                .accept(ContentType.JSON)
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();


        // WHEN  YOU  USE JSONPATH YOU CAN USE PATH= CONTENT AND SAVE AS A LIST
        // AND YOU DO NOT NEED TO CREAT A SEARCH CLASS
        // THAT DEPENDS ON YOU
        // WHEN YOU HAVE A DEAL WITH ARRAY AF JASON
        // YOU  CAN USE JSONPATH METHOD = GETLIST  AND CONVERT JSON TO JAVA LIST IN ONE STEP
        List<Spartan> content = jsonPath.getList("content", Spartan.class);

        System.out.println(content.get(1).getName());
    }







}
