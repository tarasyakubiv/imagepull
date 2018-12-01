package com.tarasyakubiv.demo.client;

import java.util.Set;

import com.tarasyakubiv.demo.client.dto.UserRequest;
import com.tarasyakubiv.demo.domain.Image;

public interface ApiRequestClient {

	Set<String> getThreadNumbers(UserRequest req);
	Set<Image> getImagesFromThreads(Set<String> threadNumbers);
	void pushImagesToApp(Set<Image> images);
}
