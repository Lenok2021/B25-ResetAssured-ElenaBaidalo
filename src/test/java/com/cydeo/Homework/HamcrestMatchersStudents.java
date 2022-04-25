package com.cydeo.Homework;

import com.cydeo.utilities.StudentCydeoBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;

public class HamcrestMatchersStudents extends StudentCydeoBase {


   @Test
   public void test1() {


     given()
             .accept(ContentType.JSON)
             .pathParam("id", 24661)
             .when()
             .get("/student/{id}")
             .then()
             .assertThat()
             .statusCode(200)
             .and().assertThat()
             .contentType("application/json;charset=UTF-8")
             .and().assertThat()
             .body("students[0].studentId", equalTo(24661),
              "students[0].lastName",is("Wick"),
                     "students[0].firstName", equalTo("Athena"));








   }



}
