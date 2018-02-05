package com.cs.mylibrary.boot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.admin.QueryOptionsManager;
import com.marklogic.client.io.StringHandle;



@SpringBootApplication
@ImportResource(value={"classpath:search-options-config.xml","classpath:search-term-options-config.xml","classpath:search-suggest-options-config.xml"})
public class MainApp extends SpringBootServletInitializer{

	@Value("${marklogic.host}")
    private String host;

    @Value("${marklogic.port}")
    private int port;

    @Value("${marklogic.username}")
    private String username;

    @Value("${marklogic.password}")
    private String password;
    
    @Value("${marklogic.database}")
    private String database;
    
	@Bean
    public DatabaseClient getDatabaseClient() {
        return DatabaseClientFactory.newClient(host, port, database, new DatabaseClientFactory.DigestAuthContext(username, password));
    }
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MainApp.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(MainApp.class, args);
	}
}
