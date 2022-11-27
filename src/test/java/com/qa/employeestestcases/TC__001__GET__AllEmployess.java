package com.qa.employeestestcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.employeesbase.Testbase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC__001__GET__AllEmployess extends Testbase{
	
	@BeforeClass
	void getallemployees() throws InterruptedException {
		
		logger.info("started Get employees");
		RestAssured.baseURI="https://dummy.restapiexample.com/api/v1";
		 httpRequest = RestAssured.given();
		 response = httpRequest.request(Method.GET, "/employees");
		 Thread.sleep(3);
		 
		 
		
	}
	
	@Test
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
