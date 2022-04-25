package com.cydeo.utilities;

import com.cydeo.utilitiesDB.DBUtils;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class SpartansTestBase {


    //beforeAll is the same thing with beforeClass in testng
    @BeforeAll
    public static void init(){
        RestAssured.baseURI ="http://54.234.53.165:8000";

        String urlSP = "jdbc:oracle:thin:@54.234.53.165:1521:XE";
        String usernameSP = "SP";
        String passwordSP = "SP";

        DBUtils.createConnection(urlSP,usernameSP,passwordSP);




    }

    @AfterAll
    public static void close(){
        DBUtils.destroy();
    }





}
