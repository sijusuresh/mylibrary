package com.cs.mylibrary.boot.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.admin.QueryOptionsManager;
import com.marklogic.client.io.Format;

@Component
public class ConfigService {

	@Autowired
	protected DatabaseClient databaseClient;
	
	@Autowired
	@Qualifier("getAllSearchOptions")
	private String getAllSearchOptions;
	
	@Autowired
	@Qualifier("searchTermOptions")
	private String searchTermOptions;
	
	@PostConstruct
	public void init() {
		System.out.println("Initializing Options persisting .............");
		QueryOptionsManager optionsMgr = databaseClient.newServerConfigManager().newQueryOptionsManager();
	    optionsMgr.deleteOptions("getAllSearchOptions");
	    optionsMgr.writeOptionsAs("getAllSearchOptions", Format.XML, getAllSearchOptions);
	    optionsMgr.deleteOptions("searchTermOptions");
	    optionsMgr.writeOptionsAs("searchTermOptions", Format.XML, searchTermOptions);
	}
	
}
