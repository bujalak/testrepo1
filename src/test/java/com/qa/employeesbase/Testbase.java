package com.qa.employeesbase;

import java.lang.System.Logger;

import org.apache.log4j.Level;
import org.apache.log4j.PropertyConfigurator;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Testbase {

	public static RequestSpecification httpRequest;
	public static Response response;
	public String EMPID = "5";
	
	public org.apache.log4j.Logger logger;

	@BeforeClass
	public void setup() {
		logger = org.apache.log4j.Logger.getLogger("EmployessRestAPI");
		PropertyConfigurator.configure("Log4j.properties");
		logger.setLevel(Level.DEBUG);

	

}
	
}
