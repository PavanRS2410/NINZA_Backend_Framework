package com.ninza.hrm.api.genericUtility;

import static io.restassured.RestAssured.given;

import java.util.List;

import com.jayway.jsonpath.JsonPath;

import io.restassured.response.Response;


public class JsonUtility extends FileUtility
{
	/**
	 * get the JsonData based on json complex xpath
	 * @param resp
	 * @param jsonXpath
	 * @return
	 */
	public String getDataOnJsonPath(Response resp,String jsonXpath)
	{
		List<Object> list = JsonPath.read(resp.asString(), jsonXpath);
		return list.get(0).toString();
		
	}
	/**
	 * get the xmldata based on xml complex xpath
	 * @param resp
	 * @param xmlPath
	 * @return
	 */
	public String getDataOnXmlPath(Response resp,String xmlPath)
	{
		return resp.xmlPath().get(xmlPath);
	}
	/**
	 * verify the data in jsonbody based on jsonpath
	 * @param resp
	 * @param jsonXpath
	 * @return
	 */
	public boolean verifyDataOnJsonPath(Response resp,String jsonXpath,String expectedData)
	{
		List<String> list = JsonPath.read(resp.asString(), jsonXpath);
		boolean flag = false;
		for(String s:list)
		{
			if(s.equals(expectedData))
			{
				System.out.println(expectedData+" is available--->pass");
				flag = true;
			}
		}
		if(flag == false)
		{
			System.out.println(expectedData+" is not available--->fail");
		}
		return flag;
	}
	
	public String getAccessToken() throws Throwable 
	{
		//ninza hrm application
		//first get the authorised token
		Response resp = given()
							.formParam("client_id", getDataFromPropFile("client_id"))
							.formParam("client_secret", getDataFromPropFile("client_secret"))
							.formParam("grant_type", "client_credentials")
						.when()
							.post("http://49.249.28.218:8180/auth/realms/ninza/protocol/openid-connect/token");
						resp.then()
							.log().all();
		//capture the access token
		String token = resp.jsonPath().get("access_token");
		return token;
		
	}
}
