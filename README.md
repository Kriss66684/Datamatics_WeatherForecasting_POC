DataMatics BDD Cucumber with Selenium Webdriver Test Automation Framework
==================================================================================
This project is an test automation framework, which provides structured and standard way of 
creating automated test scripts for GUI, and API level tests(Extra Capabilaties). 

This framework supports automation of the following: - 
*Web Browsers (Firefox, Chrome, Internet Explorer)  
 
This framework incorporates design principle of BDD (Behaviour driven development). which promotes
 writing acceptance tests by describing user behaviour in the application using Gherkin Language (simple english language).
 Having tests written in common english language helps the Scrum Team 
 (Product Owners, Business Analysts, Development and QA team) to understand and track the requirements
 
Tools & libraries
=================
The test automation framework is comprised of following tools and libraries  
*Cucumber-JVM:- BDD Framework  
*Custom Page Object Pattern and utility functions  
*Selenium WebDriver: - Browser automation framework
*JAVA: - Programming language  
*TestNg: - TestNg Java testing framework  
*Maven: - Build tool  
*Reporting: - Testng , Cucumber Pretty , Cucumber Advanced & Eclipse Reporting 
*Eclipse: - Integrated Development Environment  
*AssertJ: - Matcher's  
*Log4j: - Simple Logging Facade for Java  

Features
=================
Following are the features of this Automation Framework

*Creating Objects & Methods in Page Model which can be "reused"

*CCL  (common code library) - CCL – Helper Methods - Waits , Document upload , Random Data Generator, Elements Finder etc.,​

*Externalizing the Test data either via .xml or Excel or json files - Easy of Maintenance

*Cross Browser Testing – Running scripts in multiple browsers

*Parallel Execution – Executing scripts parallel​

*Jenkins Integration – Running scripts Manually , Schedule or Automatic mode(Simple steps to configure)

*GIT  Repository – For Check-in and Check-out of Code​(Simple steps to configure)

*Flexible Framework and easy adaptability for any project in no time

*Improvised results view with Screenshots for failures​

*Random/Dynamic Test Data Generation – reduced 90% data storage​

*Smart Synchronization Faster Execution

Machine Configuration
====================
Configure Windows and setup: -   
*Java 8  
*Git  / SVN  
*Maven  
*TestNG

IDE Configuration
==================
Eclipse plugins  
----------------
Configure and Install Following Plugins  
File >> Setting >> Plugins >> Browser Repositories>

*Cucumber for Java
*Gherkin- Natural
*Git Integration/ SVN Integration  
*Maven Integration
*TestNg  
*Git Integration

Import Project and Run Tests
==========================

Eclipse >> File >> Import >> Maven >> Existing Maven Project >> Click Next >> Browser the Project Root Directory >> Finish

Right Click on Project >> Maven >> Update Project >> Select Force Update Of Snapshots/Releases >> OK

Expand NG_XMLs >> Web_UI_Testing.xml >> Right Click >> Run As >> TestNG Suite

If any TESTNG Error :
==========================
TestNG by default disables loading DTD from unsecure Urls

FIX: 

    Right Click on the class, select Run--> Run configuration
    By default one testNg class will be generated with same class name under testng option
    Select that class and go to Arguments tab
    In the VM arguments provide -Dtestng.dtd.http=true

Please refer - https://stackoverflow.com/questions/57299606/testng-by-default-disables-loading-dtd-from-unsecure-urls


Compile Build or Run Tests
==========================

Command Line

cd to root ot automationFramework project directory

To clean and compile the build
-----------------------------
mvn clean install -DskipTests

To run all tests on dev environment
--------------------------------------
mvn clean install  

OR

mvn clean install -P dev  

*Note -P dev is default profile hence doesn't need to be specified for every run

Report
======

Local reports
-------------
Date wise Extent-emailable Html Report  (Please refer this report)

A report will be generated at /Execution_Reports\Web_Reports\02.12.2020 

Standard HTML Report  
A report will be generated at /target/cucumber-report/index.html  

Pretty Cucumber-Html Report  
A report will be generated at /target/cucumber-report/cucumber-html-reports/feature-overview.html 

Feature Files
-------------------------------------------------------------------
These files contains the acceptance criteria which are written in Gherkin Language and contains various scenarios.  
The feature files are tagged with "@tagname" to group common feature files 
File Extension:  *.feature    


