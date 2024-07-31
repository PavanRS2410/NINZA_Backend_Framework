package com.ninza.hrm.api.BaseClassUtility;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.ninza.hrm.api.genericUtility.DBUtility;
import com.ninza.hrm.api.genericUtility.FileUtility;
import com.ninza.hrm.api.genericUtility.JavaUtility;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseAPIClass 
{
	public JavaUtility j = new JavaUtility();
	public FileUtility f = new FileUtility();
	public DBUtility d = new DBUtility();
	public static RequestSpecification req_spec_obj;
	public static ResponseSpecification resp_spec_obj;
	@BeforeSuite
	public void configBS() throws Throwable
	{
		//connect to dB
		System.out.println("----connect to dB----");
		RequestSpecBuilder req_builder = new RequestSpecBuilder();
		req_builder.setContentType(ContentType.JSON);
		//req_builder.setAuth(basic("UN", "PWD")); 
		//req_builder.addHeader("", "");
		req_builder.setBaseUri(f.getDataFromPropFile("BaseURI"));
		req_spec_obj = req_builder.build();
		
		ResponseSpecBuilder resp_builder = new ResponseSpecBuilder();
		resp_builder.expectContentType(ContentType.JSON);
		resp_spec_obj = resp_builder.build();
	}
	@AfterSuite
	public void configAS()
	{
		//close dB
		System.out.println("----close DB----");
	}
}
