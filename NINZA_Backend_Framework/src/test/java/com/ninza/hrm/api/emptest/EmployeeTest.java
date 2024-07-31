package com.ninza.hrm.api.emptest;
import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.ninza.hrm.api.BaseClassUtility.BaseAPIClass;
import com.ninza.hrm.api.POJOclassUtility.EmployeePojo;
import com.ninza.hrm.api.POJOclassUtility.ProjectPojo;
import com.ninza.hrm.api.constants.endpoints.IEndPoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class EmployeeTest extends BaseAPIClass
{
	@Test
	public void addEmployeTest() throws Throwable
	{
		//request chaining
		//first add the project before adding employee to Project
		String projectName = "Cricket_"+j.getRandonNumber();
		ProjectPojo pojo_obj = new ProjectPojo(projectName,"Pavan R S","Created", 0);
						given()
							.spec(req_spec_obj)
							.body(pojo_obj)
						.when()
							.post(IEndPoints.ADD_PROJ)
						.then()
							.spec(resp_spec_obj)
				 			.log().all();
		
		//add an employee to the project
		String UN = "user_"+j.getRandonNumber(); 
		EmployeePojo pojo_obj1 = new EmployeePojo("Trainer", "23/09/1999", "dacotaexpress@gmail.com", UN, 2.2, "6785642341", projectName, "designer",UN);
		Response resp1 = given()
							.spec(req_spec_obj)
							.body(pojo_obj1)
						.when()
							.post(IEndPoints.ADD_EMP);
				resp1.then()
					.assertThat().statusCode(201)
					.assertThat().time(Matchers.lessThan(4000l))
					.spec(resp_spec_obj)
					.log().all();
		//verification in API layer is done
		//now verification in DB layer is to be done
				/*
				 * boolean flag = false; Driver driverRef = new Driver();
				 * DriverManager.registerDriver(driverRef); Connection con =
				 * DriverManager.getConnection(""); ResultSet result =
				 * con.createStatement().executeQuery("select * from employee");
				 * while(result.next()) { if(result.getString(5).equals(UN)) { flag
				 * = true; break; } } con.close();
				 * Assert.assertTrue(flag,"Employee in dB is not verified");
				 */		
	}
	
	@Test
	public void addEmployeWithOutEmail() throws Throwable
	{
		//request chaining
		//first add the project before adding employee to Project
		String projectName = "Cricket_"+j.getRandonNumber();;
		ProjectPojo pojo_obj = new ProjectPojo(projectName,"Pavan R S","Created", 0);
						given()
							.spec(req_spec_obj)
							.body(pojo_obj)
						.when()
							.post(IEndPoints.ADD_PROJ)
						.then()
							.spec(resp_spec_obj)
				 			.log().all();
		
		//add an employee to the project
		String UN = "user_"+j.getRandonNumber();; 
		EmployeePojo pojo_obj1 = new EmployeePojo("Trainer", "23/09/1999", "", UN, 2.2, "6785642341", projectName, "designer",UN);
						given()
							.spec(req_spec_obj)
							.body(pojo_obj1)
						.when()
							.post(IEndPoints.ADD_EMP)
						.then()
							.assertThat().statusCode(500)
							.spec(resp_spec_obj)
							.log().all();			
	}
}
