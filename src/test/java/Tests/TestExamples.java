package Tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;


public class TestExamples {

	@Test
	public void test_1(){
		
		
	Response response = get("https://restful-booker.herokuapp.com/booking");
	

	System.out.println(response.getStatusCode());
	System.out.println(response.getTime());
	System.out.println(response.getBody().asString());
	System.out.println(response.getStatusLine());
	System.out.println(response.getHeader("content-type"));
	
	int statusCode = response.statusCode(); 
    
	Assert.assertEquals(statusCode, 200);
	
	}
	
	@Test
	public void test_2() {
		
		baseURI = "https://restful-booker.herokuapp.com";
		given().get("/booking").then().statusCode(200).log().all();
				
		//body("booking.[2]totalprice",equalTo(111)):
		
	}

	
}
