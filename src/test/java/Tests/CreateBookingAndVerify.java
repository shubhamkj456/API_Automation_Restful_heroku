package Tests;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CreateBookingAndVerify {


    @Test
    public void testCreateBookingAndVerify() {
    	
    	baseURI = "https://restful-booker.herokuapp.com";
        // Step 1: Create a new booking
        JSONObject request = new JSONObject();
        request.put("firstname", "Arthur");
        request.put("lastname", "Morgan");
        request.put("totalprice", 444);
        request.put("depositpaid", true);
        
        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", "2021-01-12");
        bookingDates.put("checkout", "2021-01-16");
        request.put("bookingdates", bookingDates);
        request.put("additionalneeds", "Dinner");

        Response createResponse = given()
                .contentType(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .post("/booking");

        createResponse.then()
                .statusCode(200)
                .log().all();

        // Step 2: Get the booking ID from the response
       int bookingId = createResponse.jsonPath().getInt("bookingid");
     
   

        //Step 3 : Get booking id and verify response
        Response verifyResponse = given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking/" + bookingId);

        verifyResponse.then()
                .statusCode(200)
                .body("firstname", equalTo("Arthur"))
                .body("lastname", equalTo("Morgan"))
                .body("totalprice", equalTo(444))
                .body("depositpaid", equalTo(true))
                .body("bookingdates.checkin", equalTo("2023-10-04"))
                .body("bookingdates.checkout", equalTo("2023-10-05"))
                .body("additionalneeds", equalTo("Dinner"))
                .log().all();
    }
   
    }
