package com.cydeo.day6;

import com.cydeo.pojoStudentsCydeo.Student;
import com.cydeo.pojoStudentsCydeo.Students;
import com.cydeo.utilities.StudentCydeoBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StudentCydeoPojoTest extends StudentCydeoBase {


    @Test
    public void test() {
        Response response = given().
                accept(ContentType.JSON)
                .and()
                .pathParam("abc",24103)
                .when()
                .get("/student/{abc}")
                .then()
                .statusCode(200)
                .and()
                .contentType("application/json;charset=UTF-8")
                .and()
                .header("Content-Encoding","gzip")
                .and()
                .header("Date",notNullValue())
                .body("students[0].firstName",is("Karole"))
                .extract().response();

        //payload/body verification
        /*
        firstName Karole                          --> students[0].firstName
        batch 7                                   --> students[0].batch
        major Master of Creative Arts             --> students[0].major
        emailAddress hassan.lebsack@hotmail.com   --> students[0].contact.emailAddress
        companyName Legacy Integration Analyst    --> students[0].company.companyName
        street 6253 Willard Place                 --> students[0].company.address.street
        zipCode 28524                             --> students[0].company.address.zipCode
         */

        Students students = response.as(Students.class);
        System.out.println(students);

        Student student1 = students.getStudents().get(0);

        JsonPath jsonPath = response.jsonPath();

        assertEquals("Karole",student1.getFirstName());
        assertEquals(7,student1.getBatch());
        assertEquals("Master of Creative Arts",student1.getMajor());
        assertEquals("hassan.lebsack@hotmail.com",student1.getContact().getEmailAddress());
        assertEquals("Legacy Integration Analyst",student1.getCompany().getCompanyName());
        assertEquals("6253 Willard Place",student1.getCompany().getAddress().getStreet());
        assertEquals(28524,student1.getCompany().getAddress().getZipCode());

    }

}



