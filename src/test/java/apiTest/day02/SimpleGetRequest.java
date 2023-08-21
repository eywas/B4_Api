package apiTest.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleGetRequest {

    String petStoreURL="https://petstore.swagger.io/v2";
    String bookCartURL="https://bookcart.azurewebsites.net";
    @Test
    public void basicGetRequest() {
        Response response = RestAssured.get(petStoreURL+"/store/inventory");
        //print status code
        System.out.println("response.statusCode() = " + response.statusCode());
        //print body
        response.prettyPrint();
    }

    @Test
    public void getRequestWithHeader() {
        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get(petStoreURL + "/store/inventory");

        // response.prettyPrint();
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json");
    }

    @Test
    public void getRequest_VerificationWithRestAssured() {
        //Verify test case with using RestAssured Library
        RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get(petStoreURL + "/store/inventory")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType("application/json");
    }

    @Test
    public void getRequestWithContainsMethod(){
        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get(petStoreURL + "/store/inventory");
        Assert.assertEquals(response.statusCode(),200);
        System.out.println("response.body().asString() = " + response.body().asString());
        Assert.assertTrue(response.body().asString().contains("available"));
    }
    @Test
    public void getRequestWithContainsMethod2(){
        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get(bookCartURL + "/api/Book");
        System.out.println("response.body().asString() = " + response.body().asString());
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");
        Assert.assertTrue(response.body().asString().contains("Jonathan Maberry"));
    }
}

