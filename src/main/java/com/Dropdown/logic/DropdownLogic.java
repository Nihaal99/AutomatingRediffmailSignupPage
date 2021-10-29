package com.Dropdown.logic;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.Dropdown.excel.ExcelInput;
import com.Dropdown.utils.DateUtils;
import com.Dropdown.utils.ExtendReportManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DropdownLogic {

	public WebDriver driver;
	public ExtentReports report = ExtendReportManager.getReportInstance();
	public ExtentTest logger;

	// ********Invoke The Browser********
	/*
	 * To reduce the file size less than 10MB for uploading in tekstac platform,
	 * drivers are not provided in project instead their zip file is attached
	 * seperately. To setup drivers read readme.text file
	 */
	public void invokeBrowser(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			System.out.println("Opening chrome");
			System.out.println("------------------------------------------------");
			// To read chrome driver location dynamically
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.out.println("Opening firefox");
			System.out.println("------------------------------------------------");
			// To read firefox driver location dynamically

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else {
			System.out.println("Invalid Input");
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

	}

	public void openURL(String URL) {
		driver.get(URL);
	}

	// *****Click on the link create new account******
	public void createNewAccountLinkClick(String xpath) {
		driver.findElement(By.xpath(xpath)).click();
	}

	// ******Method for taking input from Excel file**********
	public void Excel() {
		String feedData[] = null;
		try {
			feedData = ExcelInput.readExcelData("Book1.xlsx");
		} catch (Exception e) {
			System.out.println("Error");
		}
		driver.findElement(By.xpath("//*[@id='tblcrtac']/tbody/tr[3]/td[3]/input")).sendKeys(feedData[0]);
		driver.findElement(By.xpath("//*[@id='tblcrtac']/tbody/tr[7]/td[3]/input[1]")).sendKeys(feedData[1]);
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(feedData[2]);
		driver.findElement(By.xpath("//*[@id='tblcrtac']/tbody/tr[11]/td[3]/input")).sendKeys(feedData[2]);

	}

	// *****Checking availability and selecting one from autosuggested Ids*****
	public void checkAvailability(String xpath) {
		driver.findElement(By.xpath(xpath)).click();
	}

	public void autoSelectoption(String xpath) {
		driver.findElement(By.xpath(xpath)).click();
	}

	// *******Method to select checkbox********
	public void selectCheckbox(String xpath) {
		driver.findElement(By.xpath(xpath)).click();
	}

	// *******Method for DateDropDown*********
	public void dayDropDown(String xpath) {
		WebElement dayDropDown = driver.findElement(By.xpath(xpath));
		Select selectday = new Select(dayDropDown);
		selectday.selectByVisibleText("20");
	}

	// ********Method for MonthDropDown*********
	public void monthDropDown(String xpath) {
		WebElement monthDropDown = driver.findElement(By.xpath(xpath));
		Select selectmonth = new Select(monthDropDown);
		selectmonth.selectByIndex(6);
	}

	// ***********Method for YearDropDown***********
	public void yearDropDown(String xpath) {
		WebElement yearDropDown = driver.findElement(By.xpath(xpath));
		Select selectyear = new Select(yearDropDown);
		selectyear.selectByVisibleText("2000");
	}

	// ***********Method for CountryDropDown********
	public void countryDropDown(String xpath) {
		WebElement country = driver.findElement(By.xpath(xpath));
		Select select = new Select(country);

		// **********Printing all countries in list********
		List<WebElement> countryValues = select.getOptions();
		for (WebElement cntry : countryValues) {
			System.out.println(cntry.getText());
		}
		// **********Printing total countries count********
		System.out.println("The total count of countries is:" + countryValues.size());
		select.selectByVisibleText("India");

		// **********Printing the name of country selected on console***********
		System.out.println(select.getFirstSelectedOption().getText());

		// **********Validating the selected country against the expected********
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "India");

	}

	// ********Method for taking ScreenShot**********
	public void takeScreenShot() {
		TakesScreenshot takeScreenShot = (TakesScreenshot) driver;
		// sourceFile stores the file in binary format.
		File sourceFile = takeScreenShot.getScreenshotAs(OutputType.FILE);
		// For storing file in file format,file is saved in destFile.
		File destFile = new File(System.getProperty("user.dir") + "\\Screenshots" + DateUtils.getTimeStamp() + ".png");
		try {
			FileUtils.copyFile(sourceFile, destFile);
			logger.addScreenCaptureFromPath(
					System.getProperty("user.dir")+ "\\Screenshots" + DateUtils.getTimeStamp() + ".png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// *****Method to close browser*****
	public void tearDown() {
		driver.close();
	}

	// *****Method to quit browser*****
	public void quitBrowser() {
		driver.quit();
	}

}
