package com.ninza.hrm.api.genericUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtility 
{
	static Connection connection = null;
	static ResultSet result = null;
	public void getConnection(String url, String username, String password) throws Exception
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url,username,password);   
		}
		catch(Exception e)
		{

		}
	}
	public void getConnection() throws Exception
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","root");   
		}catch(Exception e)
		{

		}
	}
	public void closeDbConnection() throws SQLException
	{
		connection.close();
	}
	public ResultSet executeSelectQuery(String query)
	{
		try
		{
			Statement statement = connection.createStatement();
			result=statement.executeQuery(query);


		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	public int executeNonSelectQuery(String query)
	{
		int result = 0;
		try
		{
			Statement statement = connection.createStatement();
			result=statement.executeUpdate(query);


		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
}