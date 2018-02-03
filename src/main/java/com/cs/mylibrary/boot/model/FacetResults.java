package com.cs.mylibrary.boot.model;

import java.util.Collection;

public class FacetResults {

	private String name;

	private Collection<FacetResult> result;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<FacetResult> getResult() {
		return result;
	}

	public void setResult(Collection<FacetResult> result) {
		this.result = result;
	}

	public static class FacetResult {
		private String facet;
		private int frequency;

		public String getFacet() {
			return facet;
		}

		public void setFacet(String facet) {
			this.facet = facet;
		}

		public int getFrequency() {
			return frequency;
		}

		public void setFrequency(int frequency) {
			this.frequency = frequency;
		}

		public String getText() {
			return facet;
		}
	}

}
