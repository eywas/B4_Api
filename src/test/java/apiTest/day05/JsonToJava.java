package apiTest.day05;

import com.beust.ah.A;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;

public class JsonToJava {

    String kraftBaseUrl="https://www.krafttechexlab.com/sw/api/v1";
    String bookStoreBaseUrl="https://bookstore.toolsqa.com";

    @Test
    public void putAllUsersToList(){
        /**
         given accept type is JSON
         And query param pagesize is 50
         And query param page is 1
         When user sends a get request to /allusers/alluser
         Then status code 200
         put all response body inside a list by as() method
         make several verifications
         */

        Response response = RestAssured
                .given()
                .accept(ContentType.JSON)
                .queryParam("page", 1)
                .queryParam("pagesize", 50)
                .when()
                .get(kraftBaseUrl + "/allusers/alluser");

        Assert.assertEquals(response.statusCode(),200);
        List <Map<String,Object>> allUser = response.as(List.class);

        String actualEmail = (String) allUser.get(0).get("email");
        String expectedEmail = "afmercan@gmail.com";
        Assert.assertEquals(actualEmail,expectedEmail);

        String actualJob = (String)  allUser.get(0).get("job");
        String expectedJob = "Manual Tester";
        Assert.assertEquals(actualJob,expectedJob);

        List<String> skills = (List<String>) allUser.get(0).get("skills");
        String actualSkill = skills.get(1);
        String expectedSkill = "Java";
        Assert.assertEquals(actualSkill,expectedSkill);

        List<Map<String,Object>> experience = (List<Map<String,Object>>) allUser.get(0).get("experience");
        String actualJobOfExperience = (String) experience.get(2).get("job");
        String expectedJobOfExperience = "Junior Developer";
        Assert.assertEquals(actualJobOfExperience,expectedJobOfExperience);

    }

    @Test
    public void bookStoreUserTest_NegativeCase(){
        Response response = RestAssured
                .when()
                .get(bookStoreBaseUrl+"/Account/v1/User/1");

        Assert.assertEquals(response.statusCode(), 401);

        Map<String, Object> map = response.as(Map.class);
        String actualCode = (String) map.get("code");
        String expectedCode = "1200";

        Assert.assertEquals(actualCode,expectedCode);

        String actualMessage = (String) map.get("message");
        String expectedMessage = "User not authorized!";

        Assert.assertEquals(actualMessage,expectedMessage);
    }

}
