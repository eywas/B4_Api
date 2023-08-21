package apiTest.day03;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
public class PathMethod {
    @BeforeClass
    public void beforeClass() {
        baseURI="https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void testWithPathMethod() {
        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("id", 111)
                .when()//.log().all()
                .get("/allusers/getbyid/{id}");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json; charset=UTF-8");

        //print each value
        System.out.println("response.body().path(\"name\").toString() = " + response.body().path("name").toString());
        System.out.println("response.body().path(\"email\").toString() = " + response.body().path("email").toString());
        System.out.println("response.body().path(\"id\").toString() = " + response.body().path("id").toString());

        int id=response.path("id[0]");
        String name=response.path("name[0]");
        String email=response.path("email[0]");

        //verify each value
        assertEquals(id,111);
        assertEquals(name,"Thomas Eduson");
        assertEquals(email,"thomas@test.com");
    }
    /*

    TASK
    Given accept type json
    And query  parameter value pagesize 50
    And query  parameter value page 1
    When user sends GET request to /allusers/alluser
    Then response status code should be 200
    And response content-type application/json; charset=UTF-8
    Verify the first id is 1
    Verify the first name is MercanS
    Verify the last id is 102
    Verify the last name is GHAN
     */

    @Test
    public void testAllUsersWithPathMth() {
        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParams("pagesize", 50)
                .queryParams("page", 1)
                .when()//.log().all()
                .get("/allusers/alluser");
        //verify status code
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json; charset=UTF-8");
        assertEquals(response.getHeader("Content-Type"),"application/json; charset=UTF-8");

        int id=response.path("id[0]");
        System.out.println("id = " + id);
        assertEquals(id,1);

        String name=response.path("name[0]");
        System.out.println("name = " + name);
        assertEquals(name,"MercanS");

        int lastID=response.path("id[-1]");
        System.out.println("lastID = " + lastID);
        String lastName=response.path("name[-1]");
        System.out.println("lastName = " + lastName);

        assertEquals(lastID,102);
        assertEquals(lastName,"GHAN");

        //skills[0][1]=> MercanS's skills ->java
        //education[0].school[0]=> MercanS's school-> School or Bootcamp

    }
}
