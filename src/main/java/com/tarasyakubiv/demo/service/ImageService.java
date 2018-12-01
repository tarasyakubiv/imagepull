package com.tarasyakubiv.demo.service;

import java.util.Set;

import com.tarasyakubiv.demo.client.dto.UserRequest;
import com.tarasyakubiv.demo.domain.Image;

public interface ImageService {
	
	Set<Image> getRequestedImages(UserRequest request);

	void pushImages(UserRequest request);

}
