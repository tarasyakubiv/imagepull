package com.tarasyakubiv.demo.client;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.tarasyakubiv.demo.client.dto.Request;
import com.tarasyakubiv.demo.client.dto.UserRequest;
import com.tarasyakubiv.demo.domain.Image;

@Component
public class ApiRequestClientImpl implements ApiRequestClient {

	static final String REGEX_THREAD = "(?<=\"num\":\")[0-9]+(?=\")";
	private final RestTemplate restTemplate;
	private ApiRequestUtil apiRequestUtil;

	@Autowired
	public ApiRequestClientImpl(final RestTemplate restTemplate, ApiRequestUtil apiRequestUtil) {
		this.restTemplate = restTemplate;
		this.apiRequestUtil = apiRequestUtil;

	}

	@Override
	public Set<String> getThreadNumbers(UserRequest req) {
		Set<String> allMatches = new HashSet<String>();
		boolean check = true;
		HttpEntity<String> response;
		Request request = apiRequestUtil.buildSearchRequest(req);
		while (check) {
			response = restTemplate.exchange(request.toString(), HttpMethod.GET, apiRequestUtil.getRequestHeader(),
					String.class);
			if (!response.getBody().contains("error")) {
				Matcher m = Pattern.compile(REGEX_THREAD).matcher(response.getBody());
				while (m.find()) {
					allMatches.add(m.group());
				}
				request.nextPage();
			} else {
				try {
					if (response.getBody().contains("Search limit exceeded.")) {
						Thread.sleep(1000);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (response.getBody().contains("No results found")) {
					check = false;
				}
			}
		}
		return allMatches;
	}

	@Override
	public Set<Image> getImagesFromThreads(Set<String> threadNumbers) {
		Set<Image> images = new HashSet<>();
		HttpEntity<String> response;
		Request request;
		for (String postNumber : threadNumbers) {
			request = apiRequestUtil.buildThreadRequest(postNumber);
			System.out.print(request);
			response = restTemplate.exchange(request.toString(), HttpMethod.GET, apiRequestUtil.getRequestHeader(), String.class);
			images.addAll(JsonMapper.readJson(response.getBody(), postNumber));
		}
		return images;
	}

}
