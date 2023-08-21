package apiTest.day03;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;


public class UserGetRequestWithPathParam {

    @BeforeClass
    public void beforeClass() {
        baseURI="https://www.krafttechexlab.com/sw/api/v1";

    }

    @Test
    public void getUserByID() {
        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("id",111)
                .when()
                .get("allusers/getbyid/{id}");
        assertEquals(response.statusCode(), 200);
    }

    @Test
    public void getUserByIDWithNegativeScenario() {
        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("id",444)
                .when()
                .get("allusers/getbyid/{id}");
        assertEquals(response.statusCode(), 404);
        assertEquals(response.contentType(), "application/json; charset=UTF-8");
        assertTrue(response.body().asString().contains("No User Record Found..."));
    }

}
