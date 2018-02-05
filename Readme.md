This is a web application developed on Spring Boot and Java for maintaining Movie details demonstrating some of the MarkLogic key features of search API.

Project instructions will help lead you to install and run application in windows environment, can run in any platform with minimal changes.

I	Project Setup

	Pre-Requisite softwares : gradle,maven,java 1.8, any IDE of your choice

	1. Setup Environment

		1.1 Setup MarkLogic
			a. Download Mark Logic latest version and intsall. Start the MarkLogic server.
			b. Edit gradle.properties to add port, username & password

		1.2 Setup mlcp
			a. download mlcp from http://developer.marklogic.com/products/mlcp and set the path to bin folder in system environment variables
			
		1.3 Edit ml-setup.bat file to set the dataset source path used in mlcp command.
		1.4 Run ml-setup.bat
		
	2.  Start Application
		a. Configure log path and log level as applicable in application.properties
		b. To start application from project folder run $PROJECT_HOME/mvn spring-boot:run
		C. To start application as a service run java -jar $PROJECT_JAR_HOME/myproject-0.0.1-SNAPSHOT.jar
	
II	Planned features in future releases

	- Enhance applicationton to ingest movie and tvseries datasets through UI
	- Enhance search to have multilevel searching based on term and facets
	- Enhance suggest to have suggestion based on user level filters applied
	- Add Test Module for stand alone testing
	
II	Recommendations

	- Run current version online to have images, styles and css to render with compatible versions.