package Tests;


import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;


public class Getbooking {
	
	@Test
	public void test_2() {
		
		baseURI = "https://restful-booker.herokuapp.com";
		given().get("/booking/1829").then().statusCode(200).log().all();
				
	  
		
	}

}
