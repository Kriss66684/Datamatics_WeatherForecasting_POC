package com.dm.bdd.web.step_definitions;

import java.util.List;
import java.util.Map;
import com.dm.bdd.web.pages.GetWeatherHomePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class GetWeatherReport_StepDefinations {

	GetWeatherHomePage weatherHomePage = new GetWeatherHomePage();
	
	@Given("^user should navigate to entered 'Weather' URL$")
	public void user_should_navigate_to_entered_url() throws Throwable {
		weatherHomePage.the_user_has_entered_the_Weather_url();
	}

	@When("^user should enter the city name (.+) in searchTextBox to get weather report$")
    public void user_should_enter_cityname_to_getweatherReport(String cityName) throws Throwable {
		weatherHomePage.user_should_enter_the_cityname(cityName);
    }
	
	@And("^get the Weekdays Max WeatherForeCast report$")
    public void get_the_weekdays_max_weatherForecastReport() throws Throwable {
		weatherHomePage.getDailyWeatherMaxForecastReport();
    }
	
	@And("^get the hourly report for the day (.+)$")
    public void get_the_hourlyReport_for_theDay(String day) throws Throwable {
		weatherHomePage.getHourlyWeatherMaxForecastReportForADay(day);
    }
	
	@And("^click the day and get hourlyreport and again hide the day (.+)$")
    public void clickThe_day_get_the_hourlyReport_hideback_again(String day) throws Throwable {
		weatherHomePage.hideTheDayAfterExpanding(day);
    }
	
	@And("^get Daily forecast for three hour data for a day (.+)$")
    public void gethourlyRainfallfor_the_day(String day) throws Throwable {
		weatherHomePage.getHourlyDaTaRainFallForDay(day);
		weatherHomePage.getHourlyWindSpeedDaTaForDay(day);
		weatherHomePage.getHourlyMinAndMaxDaTaForDay(day);
    }
	
	@When("^user should enter the city name in searchTextBox to get weather report for following day$")
    public void user_should_enter_the_cityname_in_searchTextBox_to_get_weather_report_for_followingday(DataTable dataTable) throws Throwable {
		List<Map<String, String>> Table = dataTable.asMaps();
		for (int i = 0; i < Table.size(); i++) {
			String city_value = Table.get(i).get("city");
			String day_value = Table.get(i).get("day");
			weatherHomePage.user_should_enter_the_cityname(city_value);
			weatherHomePage.getDailyWeatherMaxForecastReport();
			weatherHomePage.getHourlyDaTaRainFallForDay(day_value);
			weatherHomePage.getHourlyWindSpeedDaTaForDay(day_value);
			weatherHomePage.getHourlyMinAndMaxDaTaForDay(day_value);
		}
    }
	
	@And("^validate error message$")
    public void validateErrorMessage(DataTable dataTable) throws Throwable {
		List<Map<String, String>> Table = dataTable.asMaps();
		for (int i = 0; i < Table.size(); i++) {
			String errro_message = Table.get(i).get("errorMessage");
			weatherHomePage.validateErrorMessage(errro_message);
		}
    }
}




























