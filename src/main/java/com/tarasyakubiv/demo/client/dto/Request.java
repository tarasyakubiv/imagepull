package com.tarasyakubiv.demo.client.dto;


public class Request {
	
	private String host;
	private String requestType;
	private String postNumber;
	private String subject;
	private int page;
	private String startDate;
	private String endDate;
	private String results;
	private String order;
	
	public static class Builder {
		
		private String host;
		private String requestType;
		private String postNumber;
		private String subject;
		private int page;
		private String startDate;
		private String endDate;
		private String results;
		private String order;
		
		public Builder(String host, String requestType) {
			this.host = host;
			this.requestType = requestType;
		}
		
		public Builder withSubject(String subject) {
			this.subject = subject;
			return this;
		}
		
		public Builder withPostNumber(String postNumber) {
			this.postNumber = postNumber;
			return this;
		}
		
		public Builder paginated(int page) {
			this.page = page;
			return this;
		}
		
		public Builder start(String startDate) {
			this.startDate = startDate;
			return this;
		}
		
		public Builder end(String endDate) {
			this.endDate = endDate;
			return this;
		}
		
		public Builder byThreads() {
			this.results = "threads";
			return this;
		}
		
		public Builder ascending() {
			this.order = "asc";
			return this;
		}
		
		public Builder descending() {
			this.order = "desc";
			return this;
		}
		
		public Request build() {
			Request request = new Request();
			request.host = this.host;
			request.requestType = this.requestType;
			request.postNumber = this.postNumber;
			request.subject = this.subject;
			request.page = this.page;
			request.startDate = this.startDate;
			request.endDate = this.endDate;
			request.results = this.results;
			request.order = this.order;
			return request;
		}
		
	}

	private Request() {
		
	}
	
	public void nextPage() {
		this.page++;
	}
	
	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	@Override
	public String toString() {
		return host + requestType
				+ (postNumber != null ? "&num=" + postNumber : "")
				+ (subject != null ? "&subject=" + subject : "") 
				+ (page!= 0 ? "&page=" + page : "")
				+ (startDate != null ? "&start=" + startDate : "")
				+ (endDate != null ? "&end=" + endDate: "")
				+ (results != null ? "&results=" + results : "") 
				+ (order != null ? "&order=" + order : "");
	}

	
	
	
}
