package com.dm.bdd.web.pages;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.dm.bdd.ccl.WebActions;
import com.dm.bdd.integrations.common_utils.ConfigReader;
import com.dm.bdd.integrations.common_utils.DriverFactory;
import com.dm.bdd.integrations.report_utils.ReportManager;

public class GetWeatherHomePage {

	WebActions webActions = new WebActions();

	// ==========================Login============================
	By enterCity = By.xpath("//input[@id='city']");
	
	public void the_user_has_entered_the_Weather_url() throws InterruptedException {
		webActions.openURL(ConfigReader.getValue("URL"));
		webActions.waitToLoad(4000);
	}

	public void user_should_enter_the_cityname(String cityName)
			throws InterruptedException {
		webActions.clearAndSendKeys(enterCity, cityName);
		webActions.waitToLoad(5000);
	}
	
	public void getDailyWeatherMaxForecastReport() {
		ReportManager.logInfo("=====Weather Max Forecast Report====");
		List<WebElement> elem=DriverFactory.getInstance().getWebDriver().findElements(By.xpath("//body/div[@id='root']/div[1]/div"));
		ReportManager.logInfo("Total Days : "+ elem.size());
		for(int i=0;i<elem.size();i++) {
			int index=i+1;
			String weekDays=DriverFactory.getInstance().getWebDriver().findElement(By.xpath("//body/div[@id='root']/div[1]/div["+index+"]/div/span/span[@class='name']")).getText();
			String weatherMaxForecast=DriverFactory.getInstance().getWebDriver().findElement(By.xpath("//body/div[@id='root']/div[1]/div["+index+"]/div/span/span[@class='max']")).getText();
			ReportManager.logInfo("Max WeatherForeCast for Day :" + weekDays + " is :" + weatherMaxForecast);
		}
	}
	
	public void getHourlyWeatherMaxForecastReportForADay(String day) {
		ReportManager.logInfo("=====Hourly Weather Max Forecast Report====");
		DriverFactory.getInstance().getWebDriver().findElement(By.xpath("//body/div[@id='root']/div[1]/div/div/span/span[contains(text(),'"+day+"')]")).click();
		List<WebElement> hours_elem=DriverFactory.getInstance().getWebDriver().findElements(By.xpath("//span[contains(text(),'"+day+"')]//following::div[1]/div/span/span[@class='hour']"));
		for(int i=0;i<hours_elem.size();i++) {
			int index=i+1;
			String hour=DriverFactory.getInstance().getWebDriver().findElement(By.xpath("//span[contains(text(),'"+day+"')]//following::div[1]/div["+index+"]/span/span[@class='hour']")).getText();
			String hourMaxWeatherReport=DriverFactory.getInstance().getWebDriver().findElement(By.xpath("//span[contains(text(),'"+day+"')]//following::div[1]/div["+index+"]/span/span[@class='max']")).getText();
			ReportManager.logInfo("WeatherForeCast for Day :" + day + " with Hourly is :" + hour + " is :"+hourMaxWeatherReport);
		}
	}
	
	public void hideTheDayAfterExpanding(String day) throws InterruptedException {
		DriverFactory.getInstance().getWebDriver().findElement(By.xpath("//body/div[@id='root']/div[1]/div/div/span/span[contains(text(),'"+day+"')]")).click();
		List<WebElement> hours_elem=DriverFactory.getInstance().getWebDriver().findElements(By.xpath("//span[contains(text(),'"+day+"')]//following::div[1]/div/span/span[@class='hour']"));
		for(int i=0;i<hours_elem.size();i++) {
			int index=i+1;
			String hour=DriverFactory.getInstance().getWebDriver().findElement(By.xpath("//span[contains(text(),'"+day+"')]//following::div[1]/div["+index+"]/span/span[@class='hour']")).getText();
			String hourMaxWeatherReport=DriverFactory.getInstance().getWebDriver().findElement(By.xpath("//span[contains(text(),'"+day+"')]//following::div[1]/div["+index+"]/span/span[@class='max']")).getText();
			ReportManager.logInfo("WeatherForeCast for Day :" + day + " with Hourly is :" + hour + " is :"+hourMaxWeatherReport);
		}
		webActions.waitToLoad(3000);
		DriverFactory.getInstance().getWebDriver().findElement(By.xpath("//body/div[@id='root']/div[1]/div/div/span/span[contains(text(),'"+day+"')]")).click();
		webActions.waitToLoad(4000);
		ReportManager.logInfo("Hided the day back again after getting report");
	}
	
	public void getHourlyDaTaRainFallForDay(String day) throws InterruptedException {
		ReportManager.logInfo("=======HourlyRainFall==========");
		DriverFactory.getInstance().getWebDriver().findElement(By.xpath("//body/div[@id='root']/div[1]/div/div/span/span[contains(text(),'"+day+"')]")).click();
		List<WebElement> hours_elem=DriverFactory.getInstance().getWebDriver().findElements(By.xpath("//span[contains(text(),'"+day+"')]//following::div[1]/div/span/span[@class='hour']"));
		for(int i=0;i<hours_elem.size();i++) {
			int index=i+1;
			String hour=DriverFactory.getInstance().getWebDriver().findElement(By.xpath("//span[contains(text(),'"+day+"')]//following::div[1]/div["+index+"]/span/span[@class='hour']")).getText();
			String hourlyRainFall=DriverFactory.getInstance().getWebDriver().findElement(By.xpath("//span[contains(text(),'"+day+"')]//following::div[1]/div["+index+"]/span/span[@class='rainfall']")).getText();
			ReportManager.logInfo("Hourly rainFall for the Day :" + day + " for hour :" + hour + " is :"+hourlyRainFall);
		}
	}
	
	public void getHourlyWindSpeedDaTaForDay(String day) throws InterruptedException {
		ReportManager.logInfo("=======HourlyWindSpeed==========");
		List<WebElement> hours_elem=DriverFactory.getInstance().getWebDriver().findElements(By.xpath("//span[contains(text(),'"+day+"')]//following::div[1]/div/span/span[@class='hour']"));
		for(int i=0;i<hours_elem.size();i++) {
			int index=i+1;
			String hour=DriverFactory.getInstance().getWebDriver().findElement(By.xpath("//span[contains(text(),'"+day+"')]//following::div[1]/div["+index+"]/span/span[@class='hour']")).getText();
			String hourlyWindSpeed=DriverFactory.getInstance().getWebDriver().findElement(By.xpath("//span[contains(text(),'"+day+"')]//following::div[1]/div["+index+"]/span/span[@class='speed']")).getText();
			ReportManager.logInfo("Hourly Wind speed for the Day :" + day + " for hour :" + hour + " is :"+hourlyWindSpeed);
		}
	}
	
	public void getHourlyMinAndMaxDaTaForDay(String day) throws InterruptedException {
		ReportManager.logInfo("=======HourlyMaxAndMinTemp==========");
		List<WebElement> hours_elem=DriverFactory.getInstance().getWebDriver().findElements(By.xpath("//span[contains(text(),'"+day+"')]//following::div[1]/div/span/span[@class='hour']"));
		for(int i=0;i<hours_elem.size();i++) {
			int index=i+1;
			String hour=DriverFactory.getInstance().getWebDriver().findElement(By.xpath("//span[contains(text(),'"+day+"')]//following::div[1]/div["+index+"]/span/span[@class='hour']")).getText();
			String hourlyMaxTemp=DriverFactory.getInstance().getWebDriver().findElement(By.xpath("//span[contains(text(),'"+day+"')]//following::div[1]/div["+index+"]/span/span[@class='max']")).getText();
			String hourlyMinTemp=DriverFactory.getInstance().getWebDriver().findElement(By.xpath("//span[contains(text(),'"+day+"')]//following::div[1]/div["+index+"]/span/span[@class='rmq-5ea3c959 min']")).getText();
			ReportManager.logInfo("Hourly Max and Min Temperature for the Day :" + day + " for Hour :"+hour+ " Max temp :" + hourlyMaxTemp + " and Min Temp is :"+hourlyMinTemp);
		}
	}
	
	public void validateErrorMessage(String actualErrorMsg) {
		String expectedErrorMsg=DriverFactory.getInstance().getWebDriver().findElement(By.xpath("//div[contains(text(),'Error retrieving')]")).getText();
		ReportManager.logInfo("Actual Error Text - " + actualErrorMsg);
		ReportManager.logInfo("Expected Error Text - " + expectedErrorMsg);
		assertEquals(actualErrorMsg, expectedErrorMsg);
	}
	
}