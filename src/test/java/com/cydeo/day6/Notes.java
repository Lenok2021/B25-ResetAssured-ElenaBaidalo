package com.cydeo.day6;

public class Notes {
}


    /**
     * Hamcrest Matchers
     * we can  chain, we can use many methods
     * this pretty  much assertions
     * we just compare expected and actual result
     * WE  CAN USE it not only for API
     * we can use that everywhere when we do assertions
     * WE CAN USE THAT for COLLECTION as well (many methods)
     * EVERY ITEM - method we just iterate each item we DON'T need to create  for loop
     *
     *
     * when we create Hamcrest:
     * before then() = request
     * after then() = response
     * AFTER then() we start RESPONSE validation
     * and WE DON'T NEED  to create Response OBJECT
     * when you put status.code(200) = EXPECTED result
     * ACTUAL we get from API = but we don't  put that
     * assertThat():
     * we verify the body!!!!!! we use matchers (is, equal...ext.)
     *
     * how to get any header value =
     * how to check if header exist????
     *
     * when we do body verification:
     * WHEN  we deal with json collection (we want to get all teachers  =
     * we get ALLLLLL  first_name = ---->teachers.first_name
     * WHEN you want to get specific first_name from Json Object
     * you provide index =  --> students[0].firstName
     *
     * EXTRACT  method!!!!!!
     *
     * We need to verify Json body  we  need  EXTRACT response
     * then I store response in Json Object
     * and then I can to deserialization = BC you want to convert Json OBJECT to JAVA OBJECT
     * WITH EXTRACT method you can RETURN status code  ext.  what  ever you want
     *
     * WE  USE  AS  method !!!!!!  to deserialize Json body to Java Object
     *
     *  Map<String,Object> jsonMap = response.as(Map.class);
     *
     * In order to  do  that we Have to add dependency = Jackson ot Gson
     * we convert with Jackson Library!!!!!!!!!!!!!!!!
     * That allows us to CONVERT Json response to Java
     * Java can be Map, List of Map!!!!!!!
     *
     * And then you can apply Methods from Java
     *
     *   List<Map<String,Object>> jsonList = response.as(List.class);
     *
     *         System.out.println(jsonList.get(15).get("gender"));
     *
     *         here = since we have LIST of MAP = at first we use method
     *         from List (get)  and then we use get method from Map = we put key and get value
     *
     * WHAT  is SYNTAX SUGAR?????????
     *
     * Json Object {}
     * when we call students all
     * when you see {} = this is Json Object
     * when you see [] = is Array
     * it can be ARRAY of Json Objects
     *
     *
     * that depends on US
     * but
     * DB = expected, API = actual
     *
     *when you send request to Postman = I got result from DB
     * but did not connect result from DB
     * we have to verify DB information is matching with API info
     *
     * How  the API can be wrong???
     * api developed by developers they can do mistake
     * (let's say do we get all fly tickets??? available seats)
     * but when API take all that info from DB API build business logic
     * we test API to check how API build business logic
     *
     *From where UI take info from DB = through API
     * we have to check that UI take info from DB correctly
     *
     * Spartans app do not get directly info from DB,
     * that has connection through API
     *
     *
      */


/**
 * when we deserialize  Json = we still  need get  method.....
 * if you need to verify (students all = get company name)
 * you need to give index....  bla-bla-bla
 *
 * Json structure will be static
 *
 * you  gonna  have let's say Spartan's Object and use get method to get what do you want
 * you can Have design abd use just get method and you  want to get
 * whatever you want
 *
 *WE WANT  to create classes and archive feasibility
 *WE WANT  to store Json response in my custom classes
 * And I will have ready getter methods and get bi id, name, e-mail
 * bla-bla-bla
 *
 * LOG() ALL()  you  CAN add  to request  and response!!!!!!!!!!!!!!!!
 * LOG() BODY(),  STATUS.CODE()  you can print out what ever you want
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */





