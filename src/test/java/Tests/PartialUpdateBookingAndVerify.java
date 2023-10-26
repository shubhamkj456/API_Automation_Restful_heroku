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

public class PartialUpdateBookingAndVerify {
    
    @BeforeClass
    public void setupAuthentication() {
        RestAssured.authentication = basic("admin", "password123");
    }

    @Test
    public void PartialUpdateBookingAndVerify() {
        baseURI = "https://restful-booker.herokuapp.com";

        //Update booking partially
        JSONObject partialUpdateRequest = new JSONObject();
        
        // update the booking dates
        JSONObject updatedBookingDates = new JSONObject();
        updatedBookingDates.put("checkin", "2023-10-10");
        updatedBookingDates.put("checkout", "2023-10-15");

        partialUpdateRequest.put("bookingdates", updatedBookingDates);

        //Send the PATCH request 
        given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
            .header("Accept", "application/json")
            .body(partialUpdateRequest.toJSONString())
        .when()
            .patch("/booking/3106") 
        .then()
            .statusCode(200)
            .log().all();

        //Verify the updates by getting the booking details
        Response verifyResponse = given()
            .header("Accept", "application/json")
        .when()
            .get("/booking/3106");

        verifyResponse.then()
            .statusCode(200)
            .body("bookingdates.checkin", equalTo("2023-10-10"))
            .body("bookingdates.checkout", equalTo("2023-10-15"))
            .log().all();
    }
}
