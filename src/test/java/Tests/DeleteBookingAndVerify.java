package Tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

    public class DeleteBookingAndVerify {

       @BeforeClass
       public void setupAuthentication() {
        RestAssured.authentication = basic("admin", "password123");
    }

    @Test
    public void testDeleteBookingAndVerify() {
        baseURI = "https://restful-booker.herokuapp.com";

        int bookingIdToDelete = 3132; 

        // Send the DELETE request 
        given()
            .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
            .header("Accept", "application/json")
        .when()
            .delete("/booking/" + bookingIdToDelete)
        .then()
            .statusCode(201); 

        // Verify that the booking has been deleted by trying to get it again
        given()
        .header("Accept", "application/json")
            .when()
            .get("/booking/" + bookingIdToDelete)
        .then()
            .statusCode(404); 
    }
}