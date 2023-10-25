package Tests;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;


public class CreateBooking {
	
	
//	@Test
//	public void testGet() {
//		baseURI = "https://restful-booker.herokuapp.com/booking";
//		given().get("/65").then().statusCode(200).body("firstname", equalTo("Jane"));
//		
//	}
	@Test
	public void testPost() {
		
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("firstname", "Arthur");
//		map.put("lastname", "Morgan");
//		System.out.println(map);
		
		JSONObject request = new JSONObject();
		
		request.put("firstname", "Arthur");
		request.put("lastname", "Morgan");
		request.put("totalprice", "777");
		request.put("checkin", "2018-01-01");
		request.put("checkout", "2019-01-01");
		request.put("additionalneeds", "Lunch");
		System.out.println(request.toJSONString());
		
		baseURI = "https://restful-booker.herokuapp.com";
		
		given().
		 header("Content-Type", "application/json").
		 contentType(ContentType.JSON).
		 body(request.toJSONString()).
		when().
		 post("/booking").
		then().statusCode(201).log().all();
		
		
	}

}
