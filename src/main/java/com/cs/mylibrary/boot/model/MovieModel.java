package com.cs.mylibrary.boot.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieModel {

	private String title;
	private String poster;
	private String uri;
	private String released;
	private String runtime;
	private String genre;
	private String director;
	private String writer;
	private String actors;
	private String plot;
	private String language;
	private String awards;
	private String production;
	private String website;
	private String imdbRating;
	
	@JsonProperty("Released")
	public String getReleased() {
		return released;
	}
	public void setReleased(String released) {
		this.released = released;
	}
	@JsonProperty("Runtime")
	public String getRuntime() {
		return runtime;
	}
	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}
	@JsonProperty("Genre")
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	@JsonProperty("Director")
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	@JsonProperty("Writer")
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	@JsonProperty("Actors")
	public String getActors() {
		return actors;
	}
	public void setActors(String actors) {
		this.actors = actors;
	}
	@JsonProperty("Plot")
	public String getPlot() {
		return plot;
	}
	public void setPlot(String plot) {
		this.plot = plot;
	}
	@JsonProperty("Language")
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	@JsonProperty("Awards")
	public String getAwards() {
		return awards;
	}
	public void setAwards(String awards) {
		this.awards = awards;
	}
	@JsonProperty("Production")
	public String getProduction() {
		return production;
	}
	public void setProduction(String production) {
		this.production = production;
	}
	@JsonProperty("Website")
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	@JsonProperty("imdbRating")
	public String getImdbRating() {
		return imdbRating;
	}
	public void setImdbRating(String imdbRating) {
		this.imdbRating = imdbRating;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	//@JsonProperty("Title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	//@JsonProperty("Poster")
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	
}
