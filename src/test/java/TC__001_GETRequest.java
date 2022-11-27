import java.util.Iterator;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC__001_GETRequest {
	@SuppressWarnings("unchecked")
	@Test
	void getweatherdetails() {
		RestAssured.baseURI="https://dummy.restapiexample.com/api/v1/";
		RequestSpecification httprequest = RestAssured.given();
		
		JSONObject  requestparams = new JSONObject();
		requestparams.put("name", "bharath");
		requestparams.put("salary", "123");
		requestparams.put("age", "23");
		httprequest.header("Content-Type","application/json");
		httprequest.body(requestparams.toJSONString());
		
		Response response = httprequest.request(Method.POST,"/create");
		//Response response = httprequest.request(Method.GET, "/users/2");
		String responsebody = response.getBody().asString();
		System.out.println(responsebody);
		//Assert.assertEquals(responsebody.contains("id"), true);
		//JsonPath jsonpath = response.jsonPath();
		//System.out.println(jsonpath.get("data.id"));
		int statuscode = response.getStatusCode();
		System.out.println("Response code is" +statuscode);
		String statusline = response.getStatusLine();
		System.out.println("status line is " +statusline);
		String head = response.header("content-type");
		System.out.println(head);
		//Assert.assertEquals(head, "application/json; charset=utf-8");
		Headers headers = response.getHeaders();
		for (Header header : headers) {
			System.out.println(header.getName()+"  "+ header.getValue());
			
		}
		

}
}
