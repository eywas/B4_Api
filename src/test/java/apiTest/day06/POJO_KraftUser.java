package apiTest.day06;

import apiPOJOTemplates.Education;
import apiPOJOTemplates.Experience;
import apiPOJOTemplates.KraftUser;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class POJO_KraftUser {

    @Test
    public void testName() {
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", "102")
                .when()
                .get("https://www.krafttechexlab.com/sw/api/v1/allusers/getbyid/{id}");

        //verify status code
        Assert.assertEquals(response.statusCode(),200);

        //de-serialization
        //JSON to Custom Java Class(POJO)
        KraftUser[] kraftUser=response.as(KraftUser[].class);

        //get the length of array
        System.out.println("kraftUser.length = " + kraftUser.length);
        //get id
        System.out.println("kraftUser[0].getId() = " + kraftUser[0].getId());
        //get name
        System.out.println("kraftUser[0].getName() = " + kraftUser[0].getName());
        //get the second skill
        List<String> skills = kraftUser[0].getSkills();
        System.out.println("skills.get(1) = " + skills.get(1));
        //get the whole education
        List<Education> education = kraftUser[0].getEducation();
        Education education1 = education.get(0);
        //get the id of first education json
        System.out.println("education1.getId() = " + education1.getId());
        //get the study of first education
        System.out.println("education1.getStudy() = " + education1.getStudy());
        //get the experience
        List<Experience> experience = kraftUser[0].getExperience();
        //get the first json experience
        Experience experience1 = experience.get(0);
        System.out.println("experience1.getDate() = " + experience1.getDate());


    }
}
