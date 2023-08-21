package apiTest.day03;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
public class UserGetRequestWithQueryParam {
    @BeforeClass
    public void beforeClass() {
        baseURI="https://www.krafttechexlab.com/sw/api/v1";
    }
     /*
    TASK
    Given accept type json
    And query  parameter value name Thomas Eduson
    And query  parameter value skills Cypress
    And query  parameter value pagesize 50
    And query  parameter value page 1
    When user sends GET request to /allusers/alluser
    Then response status code should be 200
    And response content-type application/json; charset=UTF-8
    And response body contains "Developer" message
     */

    @Test
    public void queryParams() {
        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParam("name", "Thomas Eduson")
                .queryParams("skills", "Cypress")
                .queryParams("pagesize", 50)
                .queryParams("page", 1)
                .when().log().all()
                .get("/allusers/alluser");
        //verify status code
        assertEquals(response.statusCode(),200);
        //verify content type

    }

    @Test
    public void requestWithMap() {
        Map<String, Object> mapBody=new HashMap<>();
        mapBody.put("name","Thomas Eduson");
        mapBody.put("skills","Cypress");
        mapBody.put("pagesize",50);
        mapBody.put("page",1);

        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParams(mapBody)
                .when().log().all()
                .get("/allusers/alluser");
        //verify status code
        assertEquals(response.statusCode(),200);

    }
}
