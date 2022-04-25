package com.cydeo.day5;

import com.cydeo.utilities.StudentCydeoBase;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMatcherCydeoStudent extends StudentCydeoBase {


    @Test
    public void test1() {

        given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id", 10423)
                .when()
                .get("/teacher/{id}")
                .then()
                .statusCode(200)
                .and()
                .contentType("application/json;charset=UTF-8")
                .and()
                .header("Content-Encoding", "gzip")
                .and()
                .header("Data", notNullValue())
                .body("teachers[0].firstName", is("Alexander"),
                "teachers[0].lastName", is("Syrup"),
                "teachers[0].gender", equalTo("male"));

    }


}
