package com.qa.base;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	
	public Properties prop;
	
	public TestBase(){
			try {
				prop = new Properties();
				FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+
						"/src/main/java/com/qa/config/config.properties");
				prop.load(ip);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
