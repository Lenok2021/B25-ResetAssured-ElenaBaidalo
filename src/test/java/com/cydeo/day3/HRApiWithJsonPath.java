package com.cydeo.day3;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class HRApiWithJsonPath {
    @DisplayName("GET request to / employess with path")
    @Test
    public void test3() {

        Response response = given().queryParams("limit", 150)
                .when().get("/employees");
        // Json Object

        JsonPath jsonPath = response.jsonPath();
        // get me ALL e-mails who is working as IT_PROG

        List<String> list = jsonPath.getList("items.findAll {it.job_it==\"IT_PROG\"}.email");
        System.out.println(list);

        // get we first name of employees who is making more than 10000
        List<Integer> employeesName = jsonPath.getList("items.findAll{it.salary >= 10000}.first_name");
        System.out.println(employeesName);


        // get me employee who is making the highest sally

        String maxFirstName = jsonPath.getString("items.max{it.salary.first_name");

    }


}
