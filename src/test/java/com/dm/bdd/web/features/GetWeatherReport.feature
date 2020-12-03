Feature: Search weather Report

  Background: 
    Given user should navigate to entered 'Weather' URL


  @SmokeTest
  Scenario Outline: Get Weather Max report for weekDays
    When user should enter the city name <city> in searchTextBox to get weather report
    And get the Weekdays Max WeatherForeCast report
	Examples:
			|city						|
			|Edinburgh					|
   
  @SmokeTest
  Scenario Outline: Get hourly Weather report for day
    When user should enter the city name <city> in searchTextBox to get weather report
    And get the hourly report for the day <day>
	Examples:
			|city						|day 	|
			|Edinburgh					|Wed	|
   
  @SmokeTest
  Scenario Outline: Get hourly Weather report for day and hide the day
    When user should enter the city name <city> in searchTextBox to get weather report
	And click the day and get hourlyreport and again hide the day <day>
	Examples:
			|city						|day 	|
			|Edinburgh					|Tue	|
			

  @SmokeTest
  Scenario Outline: Daily forecast should summarize the 3 hour data
    When user should enter the city name <city> in searchTextBox to get weather report
	And get Daily forecast for three hour data for a day <day>
	
	Examples:
			|city						|day 	|
			|Edinburgh					|Tue	|

  @SmokeTest
  Scenario: Get all city weather report
    When user should enter the city name in searchTextBox to get weather report for following day
				|city						|day	|
	    		|Edinburgh					|Tue	|
	    		|Aberdeen					|Tue	|
				|Dundee						|Tue	|
				|Glasgow					|Tue	|
				|Perth						|Tue	|
				|Stirling					|Tue	|

  @SmokeTest
  Scenario Outline: Validate error message when user enter invalid city
    When user should enter the city name <city> in searchTextBox to get weather report
	And validate error message
				|errorMessage							|
	    		|Error retrieving the forecast			|
	    		
	Examples:
			|city						|
			|Mumbai						|