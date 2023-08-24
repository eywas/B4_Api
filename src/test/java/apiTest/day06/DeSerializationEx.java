package apiTest.day06;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;

public class DeSerializationEx {

    @BeforeClass
    public void beforeClass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void testName() {

        /** Task 1
         *  end  point /allusers/alluser
         *  pagesize  50
         *  page       1
         *  The company in the 10. user's experience  part
         *
         */


        Response response = RestAssured.given().accept(ContentType.JSON)
                .queryParam("pagesize", 50)
                .queryParam("page", 1)
                .when()
                .get("/allusers/alluser");
        Assert.assertEquals(response.statusCode(), 200);

        //de-serialization Json to java collection
        List<Map<String, Object>> allUsers = response.body().as(List.class);
        System.out.println("allUsers = " + allUsers);
        List<Map<String, Object>> experienceUsers = (List<Map<String, Object>>) allUsers.get(10).get("experience");
        System.out.println("experienceUsers = " + experienceUsers);

        String job1= (String) experienceUsers.get(0).get("job");
        System.out.println("job1 = " + job1);
        Assert.assertEquals(job1,"Tester");

        String job2= (String) experienceUsers.get(1).get("job");
        System.out.println("job2 = " + job2);
        Assert.assertEquals(job2,"QA Engineer");

    }
}
