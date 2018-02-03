This is a web application developed on Spring Boot and Java for maintaining Movie details.
Project instructions will help lead you to install and run application in windows environment, can run in any platform with minimal changes.

Project Setup

Pre-Requisite softwares : gradle,maven,java 1.8

1. Setup Environment

	1.1 Setup MarkLogic
		a. Download Mark Logic latest version and intsall. Start the MarkLogic server.
		b. Edit gradle.properties to add port, username & password

	1.2 Setup mlcp
		a. download mlcp from http://developer.marklogic.com/products/mlcp and set the path to bin folder in system environment variables
		
	1.3 Run ml-setup.bat
	
2.  Start Application
	a. To start application from project folder run $PROJECT_HOME/mvn spring-boot:run
	b. To start application as a service run java -jar $PROJECT_JAR_HOME/myproject-0.0.1-SNAPSHOT.jar