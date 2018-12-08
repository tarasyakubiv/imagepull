package com.tarasyakubiv.demo.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.tarasyakubiv.demo.client.ApiRequestClient;
import com.tarasyakubiv.demo.client.dto.UserRequest;
import com.tarasyakubiv.demo.domain.Image;
import com.tarasyakubiv.demo.repository.ImageRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class ImageServiceImplTest {
	
	private ImageServiceImpl imageServiceImpl;
	
	@Mock
	private ImageRepository imageRepository;
	
	@Mock
	private ApiRequestClient apiRequestClient;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		imageServiceImpl = new ImageServiceImpl(imageRepository, apiRequestClient);
	}
	
	@Test
	public void checkCorrectResults() {
		//given
		UserRequest request = new UserRequest("2018-04-26", "2018-04-28");
		Image image = new Image("97746203", "97746203", "http://i.4pcdn.org/tv/1524723078914.png", 
				"http://i.4pcdn.org/tv/1524723078914s.jpg", 	"IpadLjco7y01NCPk+Kabug==",	"bb.png", LocalDateTime.parse("2018-04-26T01:11:18"));
		Set<Image> images = new HashSet<>();
		images.add(image);
		Set<String> foundThreads = new HashSet<>();
		foundThreads.add("97746203");
		given(apiRequestClient.getThreadNumbers(request)).willReturn(foundThreads);
		given(imageRepository.findByThreadNumberInOrderByTimeAsc(foundThreads)).willReturn(images);
		
		//when
		Set<Image> requestedImages = imageServiceImpl.getRequestedImages(request);
		
		//then
		assertThat(images).isEqualTo(requestedImages);
		
	}
}
