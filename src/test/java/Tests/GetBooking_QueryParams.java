package Tests;


import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;


public class GetBooking_QueryParams {
	
	@Test
	public void test_2() {
		
		baseURI = "https://restful-booker.herokuapp.com";
		given().get("/booking?firstname=Arthur&lastname=Morgan").then().statusCode(200).log().all();
		given().get("/booking?checkin=2021-01-12&checkout=2021-01-16").then().statusCode(200).log().all();		
	  
		
	}

}