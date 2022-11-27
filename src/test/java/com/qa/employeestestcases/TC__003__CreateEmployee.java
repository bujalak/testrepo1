package com.qa.employeestestcases;

import org.json.simple.JSONObject;
import org.junit.Before;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.employeesbase.Testbase;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC__003__CreateEmployee extends Testbase {
	
	
	@BeforeClass
	void getempldata() {
		
		logger.info("Started creation of employee data");
	}
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
	}
		@Test(dependsOnMethods = "getdata")
		void checkResponseBody() {
			
			String responsebody = response.getBody().asString();
			logger.info(responsebody);
			Assert.assertTrue(responsebody!=null);
		}
		
		@Test
		void checkstatuscode() {
			int statuscode = response.getStatusCode();
			logger.info("Response code is" +statuscode);
			Assert.assertEquals(statuscode, 200);
		}
		
		@Test
		void checkstatusline() {
			
			String statusline = response.getStatusLine();
			logger.info("status line is " +statusline);
			Assert.assertEquals(statusline, "HTTP/1.1 200 OK");
		}
		
		@Test
		void checkresponsetime() {
			long responsetime = response.getTime();
			logger.info("Response time is " +responsetime);
			Assert.assertTrue(responsetime<3000);
			
			
		}
		
		@Test
		void contenttype() throws InterruptedException {
			String contenttype = response.header("content-Type");
			logger.info("content type is " +contenttype);
			Assert.assertEquals(contenttype, "application/json");
		}
		@Test
	      void servertype() {
			
			String servertype = response.header("server");
			logger.info("server type is " +servertype);
			Assert.assertEquals(servertype, "nginx/1.21.6");
		}
		
		@AfterClass
	void teardown() {
		
		logger.info("Test case 1 completed");
	}
		

		
	}
