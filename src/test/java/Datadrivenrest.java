import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Datadrivenrest {
	@SuppressWarnings("unchecked")
	@Test(dataProvider="empdata1")
	void getdata(String ename , String esal , String eage) {

		RestAssured.baseURI="https://dummy.restapiexample.com/api/v1/";
		RequestSpecification httprequest = RestAssured.given();

		JSONObject  requestparams = new JSONObject();
		requestparams.put("name", ename);
		requestparams.put("salary", esal);
		requestparams.put("age", eage);
		httprequest.header("Content-Type","application/json");
		httprequest.body(requestparams.toJSONString());

		Response response = httprequest.request(Method.POST,"/create");
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


		@DataProvider(name="empdata1")
		String[][] getempdata(){

			String empdata[][] = {{"kiran" ,"123" ,"23"} ,{"bharatg" ,"345" ,"456"}};
			return empdata;
		}
	}
