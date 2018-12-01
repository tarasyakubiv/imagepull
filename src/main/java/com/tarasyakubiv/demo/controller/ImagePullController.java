package com.tarasyakubiv.demo.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tarasyakubiv.demo.client.dto.UserRequest;
import com.tarasyakubiv.demo.domain.Image;
import com.tarasyakubiv.demo.service.ImageService;

@RestController
@RequestMapping("/imagepull")
public class ImagePullController {
	
	private final ImageService imageService;
	
	@Autowired
	ImagePullController(final ImageService imageService) {
		this.imageService = imageService;
	}
	
	@PostMapping
    public ResponseEntity<Set<Image>> postResult(@RequestBody UserRequest request) {
        Set<Image> images = imageService.getRequestedImages(request);
        return ResponseEntity.ok(images);
	}
	

	@PostMapping("/push")
    public void pushToPicsApp(@RequestBody UserRequest request) {
        imageService.pushImages(request);
    }
	

}
