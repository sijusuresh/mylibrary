package com.cs.mylibrary.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cs.mylibrary.boot.model.MovieModel;
import com.cs.mylibrary.boot.model.SearchResult;
import com.cs.mylibrary.boot.service.SearchService;
import com.marklogic.client.query.FacetResult;


@Controller
public class AppController {
	
	@Autowired
	public SearchService searchService;

	@RequestMapping("/index")
    public String index() {
        return "welcome";
    }
	
	@RequestMapping("/")
    public String welcome() {
        return "welcome";
    }
	
	@RequestMapping("/top")
    public String top() {
        return "top";
    }
	
	@RequestMapping("/left")
    public String left() {
        return "left";
    }
	
	@RequestMapping("/right")
    public String right() {
        return "right";
    }
	
	@RequestMapping("/searchText")
    public @ResponseBody SearchResult<MovieModel,FacetResult> searchText(@RequestParam(value="query",required=true) String queryString,@RequestParam(value="start",required=false,defaultValue="1") Long start) {
        return searchService.searchText(queryString,start);
    }
	
	@RequestMapping("/getAllMovies")
    public @ResponseBody SearchResult<MovieModel,FacetResult> getAllMovies(@RequestParam(value="start",required=false,defaultValue="1") Long start) {
        return searchService.getAllMovies(start);
    }
	
	@RequestMapping("/getMovieDetails")
    public @ResponseBody String getMovieDetails(@RequestParam(value="uri",required=false) String uri) {
        return searchService.getMovieDetails(uri);
    }
}
