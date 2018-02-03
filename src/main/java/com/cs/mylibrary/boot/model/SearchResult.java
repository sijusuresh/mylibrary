package com.cs.mylibrary.boot.model;

import java.util.List;

public class SearchResult<I, F> {

	private long total;
	private String queryCriteria;
	private String uri;

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getQueryCriteria() {
		return queryCriteria;
	}

	public void setQueryCriteria(String queryCriteria) {
		this.queryCriteria = queryCriteria;
	}

	private List<I> items;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<I> getItems() {
		return items;
	}

	public void setItems(List<I> items) {
		this.items = items;
	}

	public List<F> getFacets() {
		return facets;
	}

	public void setFacets(List<F> facets) {
		this.facets = facets;
	}

	private List<F> facets;
}
