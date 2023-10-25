package Tests;

import org.testng.annotations.Test;
import org.json.simple.JSONObject;
import org.junit.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UpdateBookingAndVerify {
	
	
	
	@Test
	public void BasicAuth() {
		RequestSpecification requestSpec = RestAssured.given();
		requestSpec.baseUri("https://restful-booker.herokuapp.com");
		requestSpec.basePath("/booking/4753");
		
		Response response = requestSpec.auth().basic("admin", "password123").get();
		
		System.out.println("Response status:" + response.statusLine());
		}
	
	 
	 @Test
	    public void testUpdateBookingAndVerify(){
		 
		 	baseURI = "https://restful-booker.herokuapp.com";
		 
	 JSONObject updateRequest = new JSONObject();
     updateRequest.put("firstname", "John");
     updateRequest.put("lastname", "Wick");
     updateRequest.put("totalprice", 555);
     updateRequest.put("depositpaid", false);
     JSONObject updatedBookingDates = new JSONObject();
     updatedBookingDates.put("checkin", "2023-10-10");
     updatedBookingDates.put("checkout", "2023-10-15");
     updateRequest.put("bookingdates", updatedBookingDates);
     updateRequest.put("additionalneeds", "Dinner");

     Response updateResponse = given()
             .contentType(ContentType.JSON)
             .body(updateRequest.toJSONString())
             .when()
             .put("/booking/4753");

     updateResponse.then()
             .statusCode(200)
             .log().all();

     // Step 4: Verify the updates by getting the booking details
     Response verifyResponse = given()
             .when()
             .get("/booking/4753");

     verifyResponse.then()
             .statusCode(200)
             .body("firstname", equalTo("John"))
             .body("lastname", equalTo("Wick"))
             .body("totalprice", equalTo(555))
             .body("depositpaid", equalTo(false))
             .body("bookingdates.checkin", equalTo("2023-10-10"))
             .body("bookingdates.checkout", equalTo("2023-10-15"))
             .body("additionalneeds", equalTo("Dinner"))
             .log().all();
 }
}
	
	


