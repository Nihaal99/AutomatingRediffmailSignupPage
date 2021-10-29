package com.Dropdown.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
//**********This class is used to generate report**********
public class ExtendReportManager {
	// For designing report in HTML.

	// This class object report will actually create report.
	public static ExtentReports report;

	public static ExtentReports getReportInstance() {
		if (report == null) {
			String reportName = DateUtils.getTimeStamp() + ".html";
			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(
					System.getProperty("user.dir") + "\\test-output" + reportName);
			report = new ExtentReports();
			report.attachReporter(htmlReporter);

			report.setSystemInfo("OS", "Windows 10");
			report.setSystemInfo("Environment", "UAT");
			report.setSystemInfo("IDE", "Eclipse");
			report.setSystemInfo("Browser", "Chrome");

			htmlReporter.config().setDocumentTitle("Automation Results");
			htmlReporter.config().setReportName("Dropdown test results");
			htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		}
		return report;
	}

}
