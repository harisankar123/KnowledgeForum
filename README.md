# KnowledgeForum
This is backend part of the application. User should have spring extension added , If they have eclipse IDE in their system. Otherwise they can run this application in Spring tool Suite. 
Requirements
For building and running the application you need:
JDK 1.8
Maven 3


Changes to be done as part of running the application:
Go to application properties file and update the required sql related configurations like path,username and password.Kindly find the below lines to be changed.
spring.datasource.url="SQL path"
spring.datasource.username=username
spring.datasource.password=password

Build the Project:
Right Click on the project, and run as maven build .
Type Clean install the text box asked for.
This will add all JAR files required for the project as mentioned in pom.xml file.

Running the application locally
There are several ways to run a Spring Boot application on your local machine. 
One way is to execute the main method in the package com.knowledge.KnowledgeApplication class from your IDE.
(src\main\java\com\Knowledge\web)
Other way to run the application , is to naviage to spring explorer and configure the poject path to run tha application directly.





