package com.tarasyakubiv.demo.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarasyakubiv.demo.client.ApiRequestClient;
import com.tarasyakubiv.demo.client.dto.UserRequest;
import com.tarasyakubiv.demo.domain.Image;
import com.tarasyakubiv.demo.repository.ImageRepository;

@Service
public class ImageServiceImpl implements ImageService {

	private ImageRepository imageRepository;
	private ApiRequestClient apiRequestClient;
	
	@Autowired
	public ImageServiceImpl(ImageRepository imageRepository, ApiRequestClient apiRequestClient) {
		this.imageRepository = imageRepository;
		this.apiRequestClient = apiRequestClient;
	}
	
	@Override
	public Set<Image> getRequestedImages(UserRequest req) {
		Set<String> threadNumbers = apiRequestClient.getThreadNumbers(req);
		Set<Image> savedImages = imageRepository.findByThreadNumberInOrderByTimeAsc(threadNumbers);
		savedImages.forEach(image -> threadNumbers.remove(image.getThreadNumber()));
		Set<Image> images = apiRequestClient.getImagesFromThreads(threadNumbers);
        images.forEach(imageRepository::save);
        images.addAll(savedImages);
		return images;
	}

	@Override
	public void pushImages(UserRequest req) {
		Set<String> threadNumbers = apiRequestClient.getThreadNumbers(req);
		Set<Image> savedImages = imageRepository.findByThreadNumberInOrderByTimeAsc(threadNumbers);
		apiRequestClient.pushImagesToApp(savedImages);
	}

}
