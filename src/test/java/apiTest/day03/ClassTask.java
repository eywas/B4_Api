package apiTest.day03;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ClassTask {

    String bookStoreBaseURL = "https://bookstore.toolsqa.com/BookStore/v1";

    @Test
    public void bookstoreTest1(){
        /**
         *  Given accept type is JSON
         *  When user send GET request to "/Books"
         *  Then verify that response status code 200
         *  and body is JSON format
         *  and response body has "Eric Elliott"
         */
        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get(bookStoreBaseURL + "/Books");
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");
        Assert.assertTrue(response.body().asString().contains("Eric Elliot"));

    }
}
