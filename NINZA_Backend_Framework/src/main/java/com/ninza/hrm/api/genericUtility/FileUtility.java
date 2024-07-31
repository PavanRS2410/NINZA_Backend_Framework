package com.ninza.hrm.api.genericUtility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class FileUtility 
{

	public String getDataFromPropFile(String Key) throws Throwable
	{
		File f = new File("./config_env_data/config_env_data.properties");
		FileInputStream fileInputStream = new FileInputStream(f);
		Properties properties = new Properties();
		properties.load(fileInputStream);
		String data = properties.getProperty(Key);
		return data;
	}
}
