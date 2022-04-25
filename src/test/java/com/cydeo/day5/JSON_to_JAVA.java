package com.cydeo.day5;

import com.cydeo.utilities.SpartansTestBase;
import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class JSON_to_JAVA  extends SpartansTestBase {

    @Test
    public void test1(){
        Response response =
        given()
                .accept(ContentType.JSON)
                .pathParam("id",15)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .extract().response();  // we save response in Response Object

        // get the json body and  convert to Map

        Map<String, Object> jsonMap = response.as(Map.class);




    }





}
