package Tests;


import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;


public class GetBooking_QueryParams {
	
	@Test
	public void test_2() {
		
		baseURI = "https://restful-booker.herokuapp.com";
		//given().get("/booking?firstname=Arthur&lastname=Morgan").then().statusCode(200).log().all();
		given().get("/booking?checkin=2018-01-01&checkout=2019-01-01").then().statusCode(200).log().all();		
	  
		
	}

}