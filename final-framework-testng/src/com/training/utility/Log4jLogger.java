package com.training.utility;

import java.io.File;
import java.lang.reflect.Method;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4jLogger {
	private static Logger log;
	public static Logger getLog4jLog(String string) {
		log = Logger.getLogger(string.toString());
		String log4jConfigFile = System.getProperty("user.dir") + File.separator + "resources\\log4j.properties";
		System.out.println(log4jConfigFile);
        PropertyConfigurator.configure(log4jConfigFile);		
		return log;
	}
}
