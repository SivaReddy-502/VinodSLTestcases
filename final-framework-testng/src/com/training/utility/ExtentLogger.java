package com.training.utility;

import java.io.File;

import org.apache.log4j.Logger;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentLogger {
	private ExtentReports eReport;
	private ExtentTest logger;
	private static Logger Log;
	public ExtentLogger() {
		eReport=new ExtentReports(System.getProperty("user.dir")+"/test-output/Report.html", true);
		eReport.loadConfig(new File(System.getProperty("user.dir")+"/extent-config.xml"));
	}
	
	public ExtentTest startLogging(String methodName) {
		logger=eReport.startTest(methodName);
		return logger;
	}
	
	public void endLogging(ExtentTest logger) {
		eReport.endTest(logger);
		eReport.flush();
	}
	
	public void generateReport() {
		
		eReport.close();
	}	
}
