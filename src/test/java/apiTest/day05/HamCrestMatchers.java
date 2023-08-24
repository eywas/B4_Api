package apiTest.day05;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.request;

public class HamCrestMatchers {

    @BeforeClass
    public void beforeClass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }


    @Test
    public void hamcrest1() {
        /**
         TASK
         given accept type is JSON
         And path param id is 111
         When user send a get request to /allusers/getbyid/{id}
         Then status code should be 200
         And content type  should be application/json; charset=UTF-8
         */

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get("allusers/getbyid/111")
                .then()
                .statusCode(200)
                .contentType("application/json; charset=UTF-8");
    }

    @Test
    public void hamcrest2(){
        /**

         TASK
         given accept type is JSON
         And path param id is 111
         When user send a get request to /allusers/getbyid/{id}
         Then status code 200
         And content Type application/json; charset=UTF-8
         And response header Content-Length should be 606
         And response header date is not null
         */

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get("allusers/getbyid/111")
                .then()
                .statusCode(200)
                .log().all()
                .contentType("application/json; charset=UTF-8")
                //.header("Content-Length", Matchers.equalTo("606"))
                //.header("Content-Encoding", Matchers.equalTo("gzip"))
                .header("Date", Matchers.notNullValue());

    }
    @Test
    public void hamcrest3() {
        /**
         * TASK
         * given accept type is JSON
         * And path param id is 111
         * When user send a get request to /allusers/getbyid/{id}
         * Then status code should be 200
         * And content type  should be application/json; charset=UTF-8
         * make some verification inside the response body
         */

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get("allusers/getbyid/111")
                .then()
                .statusCode(200)
                .contentType("application/json; charset=UTF-8")
                .body("id[0]", Matchers.equalTo(111),
                        "name[0]", Matchers.equalTo("Thomas Eduson"),
                        "job[0]", Matchers.equalTo("Developer"),
                        "skills[0][4]", Matchers.equalTo("SQL"),
                        "education[0].school[0]", Matchers.equalTo("ODTU"));

    }

    @Test
    public void hamcrest4() {

        /**

         given accept type is JSON
         And query param pagesize is 50
         And query param page is 1
         When user sends a get request to /allusers/alluser
         Then status code 200
         And content Type application/json; charset=UTF-8
         Verify that one of the email is "ghan@krafttechexlab.com"
         */

        RestAssured
                .given()
                .accept(ContentType.JSON)
                .queryParam("page", 1)
                .queryParam("pagesize", 50)
                .when()
                .get("allusers/alluser")
                .then()
                .statusCode(200)
                .contentType("application/json; charset=UTF-8")
                .body("email", Matchers.hasItem("ghan@krafttechexlab.com"));
    }

    @Test
    public void hamcrest5() {

        /**
         given accept type is JSON
         And query param pagesize is 50
         And query param page is 1
         When user sends a get request to /allusers/alluser
         Then status code 200
         And content Type application/json; charset=UTF-8
         Verify that email has "ghan@krafttechexlab.com",
         "blackuncle9599@gmail.com","user33@test.com",
         "sekercikefe@gmail.com","eddiem@kraft.com","qateam@test.com"

         */

        RestAssured
                .given()
                .accept(ContentType.JSON)
                .queryParam("page", 1)
                .queryParam("pagesize", 50)
                .when()
                .get("allusers/alluser")
                .then()
                .statusCode(200)
                .contentType("application/json; charset=UTF-8")
                .body("email", Matchers.hasItems("ghan@krafttechexlab.com","blackuncle9599@gmail.com","user33@test.com",
                        "sekercikefe@gmail.com","eddiem@kraft.com","qateam@test.com"));
    }





}
