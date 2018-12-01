package com.tarasyakubiv.demo.client;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.tarasyakubiv.demo.client.dto.Request;
import com.tarasyakubiv.demo.client.dto.UserRequest;

@Component
public class ApiRequestUtil {
	
	private final String apiHost;
	private final String apiSearch;
	private final String apiThread;
	private final String appHost;
	
	
	@Autowired
	public ApiRequestUtil(@Value("${apiHost}") final String apiHost,
			@Value("${apiSearch}") final String apiSearch,
			@Value("${apiThread}") final String apiThread,
			@Value("${appHost}") final String appHost) {
		this.apiHost = apiHost;
		this.apiSearch = apiSearch;
		this.apiThread = apiThread;
		this.appHost = appHost;
	}
	
	public HttpEntity<String> getRequestHeader() {
		HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        return new HttpEntity<String>("parameters", headers);
	}
	
	public Request buildSearchRequest(UserRequest req) {
		return new Request.Builder(apiHost, apiSearch)
		.withSubject("big brother")
		.start(req.getStart())
		.end(req.getEnd())
		.byThreads()
		.paginated(1)
		.ascending()
		.build();
	}
	
	public Request buildThreadRequest(String postNumber) {
		return new Request.Builder(apiHost, apiThread)
		.withPostNumber(postNumber)
		.build();
	}

	public String getImagePostRequest() {
		return appHost + "/image";
	}

	public String getLinkToPost(String threadNumber, String postNumber) {
		return "https://archive.4plebs.org/tv/thread/"+threadNumber+"/#"+postNumber;
	}
	
	
}
