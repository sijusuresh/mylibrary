package com.cs.mylibrary.boot.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cs.mylibrary.boot.model.MovieModel;
import com.cs.mylibrary.boot.model.SearchResult;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marklogic.client.DatabaseClient;
import com.marklogic.client.document.JSONDocumentManager;
import com.marklogic.client.io.SearchHandle;
import com.marklogic.client.io.StringHandle;
import com.marklogic.client.query.ExtractedItem;
import com.marklogic.client.query.ExtractedResult;
import com.marklogic.client.query.FacetResult;
import com.marklogic.client.query.MatchDocumentSummary;
import com.marklogic.client.query.QueryManager;
import com.marklogic.client.query.StringQueryDefinition;

@Component
public class SearchService {

	@Autowired
	protected DatabaseClient databaseClient;

	@Value("${pageLimit:10}")
    private long pageLimit;
	
	public SearchResult<MovieModel, FacetResult> searchText(String queryString,long start) {
		//Set start position based on page when page is not first page
		if(start > 1){
			start = (start-1)*pageLimit+1;
		}
		QueryManager queryManager = databaseClient.newQueryManager();
		queryManager.setPageLength(pageLimit);
		StringQueryDefinition queryDefinition = queryManager.newStringDefinition();
		if(queryString != null && queryString.length() > 0){
			queryDefinition.setCriteria(queryString);
		}
		queryDefinition.setOptionsName("searchTermOptions");
		SearchHandle handle = queryManager.search(queryDefinition, new SearchHandle(), start);
		FacetResult[] facetResult = handle.getFacetResults();
		List<MovieModel> movieModelList = new ArrayList<MovieModel>();
		MatchDocumentSummary matches[] = handle.getMatchResults();
		for (MatchDocumentSummary match : matches) {
			ExtractedResult extracts = match.getExtracted();
			MovieModel movieModel = new MovieModel();
			movieModel.setUri(match.getUri());
			for (ExtractedItem extract : extracts) {
				try {
					JSONObject obj = new JSONObject(extract.getAs(String.class));
					if (obj.has("Title")) {
						movieModel.setTitle(obj.getString("Title"));
					} else if (obj.has("Poster")) {
						movieModel.setPoster(obj.getString("Poster"));
					}
				} catch (Exception je) {
				}
			}
			movieModelList.add(movieModel);
		}
		SearchResult<MovieModel, FacetResult> result = new SearchResult<MovieModel, FacetResult>();
		result.setFacets(Arrays.asList(facetResult));
		result.setItems(movieModelList);
		result.setTotal(handle.getTotalResults());
		result.setQueryCriteria(queryString);
		return result;
	}

	/*
	 * Get All Movies. This will be triggered on application load/welcome page
	 */
	public SearchResult<MovieModel, FacetResult> getAllMovies(Long start) {
		//Set start position based on page when page is not first page
		if(start > 1){
			start = (start-1)*pageLimit+1;
		}
		QueryManager queryManager = databaseClient.newQueryManager();
		queryManager.setPageLength(10);
		StringQueryDefinition queryDefinition = queryManager.newStringDefinition();
		queryDefinition.setOptionsName("getAllSearchOptions");
		SearchHandle handle = queryManager.search(queryDefinition, new SearchHandle(), start);
		FacetResult[] facetResult = handle.getFacetResults();
		List<MovieModel> movieModelList = new ArrayList<MovieModel>();
		MatchDocumentSummary matches[] = handle.getMatchResults();
		for (MatchDocumentSummary match : matches) {
			ExtractedResult extracts = match.getExtracted();
			MovieModel movieModel = new MovieModel();
			movieModel.setUri(match.getUri());
			for (ExtractedItem extract : extracts) {
				try {
					JSONObject obj = new JSONObject(extract.getAs(String.class));
					if (obj.has("Title")) {
						movieModel.setTitle(obj.getString("Title"));
					} else if (obj.has("Poster")) {
						movieModel.setPoster(obj.getString("Poster"));
					}
				} catch (Exception je) {
				}
			}
			movieModelList.add(movieModel);
		}
		SearchResult<MovieModel, FacetResult> result = new SearchResult<MovieModel, FacetResult>();
		result.setFacets(Arrays.asList(facetResult));
		result.setItems(movieModelList);
		result.setTotal(handle.getTotalResults());
		return result;
	}

	/*
	 * Read Movie details based on the document uri passed
	 */
	public String getMovieDetails(String uri) {
		JSONDocumentManager documentManager = databaseClient.newJSONDocumentManager();
		StringHandle strhandle = new StringHandle();
		documentManager.read(uri, strhandle);
		return strhandle.get();
	}

}
