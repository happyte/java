package com.zs.javaweb.listener;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.zs.javaweb.properities.FileUploadProperities;


@WebListener
public class FileUploadListener implements ServletContextListener {

    public FileUploadListener() {
        
    }

    public void contextDestroyed(ServletContextEvent arg0)  { 
   
    }

    public void contextInitialized(ServletContextEvent arg0)  { 
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("/upload.properities");
        Properties properties = new Properties();
        try {
			properties.load(inputStream);
			for(Map.Entry<Object, Object> prop:properties.entrySet()){
				String propertyName = (String)prop.getKey();
				String propertyValue = (String)prop.getValue();
//				System.out.println("propertyName:"+propertyName);
//				System.out.println("propertyValue:"+propertyValue);
				FileUploadProperities.getInstance().addProperty(propertyName, propertyValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
}
