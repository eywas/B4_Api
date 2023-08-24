package apiTest.day06;

import apiPOJOTemplates.PetStoreUser;
import com.google.gson.Gson;
import org.testng.annotations.Test;

import java.util.Map;

public class GSON_Test {
   /*

{
  "id": 9223372036854776000,
  "username": "Miky",
  "firstName": "mike",
  "lastName": "masters",
  "email": "mike@gmail.com",
  "password": "Test1234",
  "phone": "55512345",
  "userStatus": 0
}
    */

    @Test
    public void jsonToMap() {
        Gson gson=new Gson();

        String myJsonBody="{\n" +
                "  \"id\": 9223372036854776000,\n" +
                "  \"username\": \"Miky\",\n" +
                "  \"firstName\": \"mike\",\n" +
                "  \"lastName\": \"masters\",\n" +
                "  \"email\": \"mike@gmail.com\",\n" +
                "  \"password\": \"Test1234\",\n" +
                "  \"phone\": \"55512345\",\n" +
                "  \"userStatus\": 0\n" +
                "}";
        System.out.println("myJsonBody = " + myJsonBody);
        //gson to converting to map
        Map<String,Object> dataMap=gson.fromJson(myJsonBody, Map.class);
        System.out.println("dataMap = " + dataMap);

        //gson converting to object class
        PetStoreUser oneUser=gson.fromJson(myJsonBody,PetStoreUser.class);
        System.out.println("oneUser = " + oneUser);

        //Serialization
        //Java collection or POJO to JSON

        PetStoreUser petStoreUser=new PetStoreUser(11,"mich","michael","master","mike1@gmail.com",
                "Test1234","55512345",22);
        String jsonUser=gson.toJson(petStoreUser);
        System.out.println("jsonUser = " + jsonUser);

    }
}
