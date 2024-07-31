package com.ninza.hrm.api.projecttest;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ninza.hrm.api.BaseClassUtility.BaseAPIClass;
import com.ninza.hrm.api.POJOclassUtility.ProjectPojo;
import com.ninza.hrm.api.constants.endpoints.IEndPoints;

import io.restassured.response.Response;

public class ProjectTest extends BaseAPIClass
{
	ProjectPojo pojo_obj;          //make it global so that it can be used in next test case also
	@Test
	public void addSingleProjectWithCreatedTest() throws Throwable
	{
		String act_success_msg = "Successfully Added";
		String act_projectName = "Abb_"+j.getRandonNumber();                
		pojo_obj = new ProjectPojo(act_projectName,"Pavan","Created", 0);
		Response resp = given()
							.spec(req_spec_obj)
							.body(pojo_obj)
						.when()
							.post(IEndPoints.ADD_PROJ);
					resp.then()
							.assertThat().statusCode(201)
							.assertThat().time(Matchers.lessThan(3000L))
							.spec(resp_spec_obj)
							.log().all();
		String resp_success_msg = resp.jsonPath().get("msg");
		Assert.assertEquals(resp_success_msg, act_success_msg);
		String resp_projectName = resp.jsonPath().get("projectName");
		Assert.assertEquals(resp_projectName, act_projectName);
		//verification in API layer is done
		//now verification in DB layer is to be done
		/*
		 * boolean flag = false; Driver driverRef = new Driver();
		 * DriverManager.registerDriver(driverRef); Connection con =
		 * DriverManager.getConnection(""); ResultSet result =
		 * con.createStatement().executeQuery("select * from project");
		 * while(result.next()) { if(result.getString(4).equals(act_projectName)) { flag
		 * = true; break; } } con.close();
		 * Assert.assertTrue(flag,"Project in dB is not verified");
		 */
	}
	
	//because creating duplicate project depends on already created project, use dependsOnMethods
	//this is a negative scenario
	//just pass pojo_obj for body here also as project is already created using pojo_obj
	//without executing 1st tc only executing 2nd tc will throw exception as pojo_obj which is a global variable will be pointing to null
	@Test(dependsOnMethods = "addSingleProjectWithCreatedTest")         
	public void addDuplicateProjectTest() throws Throwable
	{
		given()
			.spec(req_spec_obj)
			.body(pojo_obj)
		.when()
			.post(IEndPoints.ADD_PROJ)
		.then()
			.assertThat().statusCode(409)
			.spec(resp_spec_obj)
			.log().all();
	}
}
