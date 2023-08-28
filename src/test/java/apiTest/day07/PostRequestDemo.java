package apiTest.day07;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class PostRequestDemo {

    @BeforeClass
    public void beforeClass() {
        baseURI="https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void postNewUser() {

        String jsonBody="{\n" +
                "  \"name\": \"Sebastian\",\n" +
                "  \"email\": \"szymanski8@krafttechexlab.com\",\n" +
                "  \"password\": \"Test1234\",\n" +
                "  \"about\": \"About Me\",\n" +
                "  \"terms\": \"10\"\n" +
                "}";

        Response response = given().accept(ContentType.JSON) // hey api send me body as json format
                .and()
                .contentType(ContentType.JSON)// hey api I am sending json body
                .and()
                .body(jsonBody)
                .when()
                .post("/allusers/register");

        assertEquals(response.statusCode(),200);
        response.prettyPrint();
        assertTrue(response.body().asString().contains("token"));

    }

    @Test
    public void postNewUser2() {

        Map<String,Object> requestMap=new HashMap<>();
        requestMap.put("name","Sebastian");
        requestMap.put("email","szymanski1@krafttechexlab.com");
        requestMap.put("password","Test1234");
        requestMap.put("about","About Me");
        requestMap.put("terms","5");

        Response response = given().accept(ContentType.JSON) // hey api send me body as json format
                .and()
                .contentType(ContentType.JSON)// hey api I am sending json body
                .and()
                .body(requestMap)
                .when()
                .post("/allusers/register");

        assertEquals(response.statusCode(),200);
        response.prettyPrint();
        assertTrue(response.body().asString().contains("token"));
    }

    @Test
    public void postNewUser3() {
        NewUserInfo newUserInfo=new NewUserInfo();
        newUserInfo.setName("Sebastian");
        newUserInfo.setEmail("szymanski2@krafttechexlab.com");
        newUserInfo.setPassword("Test1234");
        newUserInfo.setAbout("About me");
        newUserInfo.setTerms("5");


        Response response = given().accept(ContentType.JSON) // hey api send me body as json format
                .and()
                .contentType(ContentType.JSON)// hey api I am sending json body
                .and()
                .body(newUserInfo) // Serialization
                .when()
                .post("/allusers/register");

        assertEquals(response.statusCode(),200);
        response.prettyPrint();
        assertTrue(response.body().asString().contains("token"));
    }

    @Test
    public void postNewUser4() {
        NewUserInfo newUserInfo=new NewUserInfo("Sebastian","szymanski3@krafttechexlab.com"
        ,"Test1234","SDET","3");

        Response response = given().accept(ContentType.JSON) // hey api send me body as json format
                .and()
                .contentType(ContentType.JSON)// hey api I am sending json body
                .and()
                .body(newUserInfo) // Serialization
                .when()
                .post("/allusers/register");

        assertEquals(response.statusCode(),200);
        response.prettyPrint();
        assertTrue(response.body().asString().contains("token"));
    }


}
