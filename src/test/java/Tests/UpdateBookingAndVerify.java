package Tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UpdateBookingAndVerify {
    
    @BeforeClass
    public void setupAuthentication() {
        RestAssured.authentication = basic("admin", "password123");
    }

    @Test
    public void testUpdateBookingAndVerify() {
        baseURI = "https://restful-booker.herokuapp.com";

        //Update booking
        JSONObject updateRequest = new JSONObject();
        updateRequest.put("firstname", "John");
        updateRequest.put("lastname", "Wick");
        updateRequest.put("totalprice", 555);
        updateRequest.put("depositpaid", false);

        JSONObject updatedBookingDates = new JSONObject();
        updatedBookingDates.put("checkin", "2023-10-10");
        updatedBookingDates.put("checkout", "2023-10-15");

        updateRequest.put("bookingdates", updatedBookingDates);
        updateRequest.put("additionalneeds", "Lunch");

        //Send the PUT request
        given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
            .header("Accept", "application/json")
            .body(updateRequest.toJSONString())
        .when()
            .put("/booking/3650")
        .then()
            .statusCode(200)
            .log().all();

        //Verify the updates by getting the booking details
        Response verifyResponse = given()
            .header("Accept", "application/json") 
        .when()
            .get("/booking/3650");

        verifyResponse.then()
            .statusCode(200)
            .body("firstname", equalTo("John"))
            .body("lastname", equalTo("Wick"))
            .body("totalprice", equalTo(555))
            .body("depositpaid", equalTo(false))
            .body("bookingdates.checkin", equalTo("2023-10-10"))
            .body("bookingdates.checkout", equalTo("2023-10-15"))
            .body("additionalneeds", equalTo("Lunch"))
            .log().all();
    }
}
