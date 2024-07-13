# Overview

This project has been designed to serve all API test cases for all sites with reusable functionality. It’s Scalable, Robust and easy to maintain. It provides automation testing both pruction and staging version.

## Tools and Technologies

  - IntelliJ IDEA 
  - Java
  - testng (v 7.1.0)
  - rest-assured (v 4.3.0)
  - assertj-core (v 3.9.1)
  - json (v 20171018)
  
# Conceptual API testing project

## Prerequisites
To setup the project, you will need the following tools and machine configurations:
 - Minimum windows 10
 - Mac OS

## Project Clone
* Clone this repository from GitLab
* Go to the cloned project directory using,

        cd api-testing-automation

## Setup Configuration
1. To open this project, you have to open the folder api-testing-automation in IntelliJ IDEA.

2. Run the api test case using testing.xml file.

## Features
1. It has an environment variable to run automation for production and staging version

2. It has a common function where we write redundant code

3. More details about test case status(True or False) is in report

## Test Case Detail
1. TC001_CacheStatus - Using rest-assured, for a specific property_id, it's status is checked and include it in all.txt file

2. Functions 
	- CommonFunction : to get status code, to get value of the required key
			 
	- CommonReport : to write statement in a specific txt file as a report
			 
3. GenerateExtentReport - To generate extent report from all.txt file
