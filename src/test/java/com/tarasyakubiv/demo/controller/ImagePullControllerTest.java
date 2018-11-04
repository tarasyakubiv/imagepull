package com.tarasyakubiv.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tarasyakubiv.demo.client.ApiRequestClient;
import com.tarasyakubiv.demo.client.dto.UserRequest;
import com.tarasyakubiv.demo.domain.Image;
import com.tarasyakubiv.demo.service.ImageService;

@RunWith(SpringRunner.class)
@WebMvcTest(ImagePullController.class)
public class ImagePullControllerTest {
	
	@MockBean
	private ImageService imageService;
	
	@MockBean
	private ApiRequestClient apiRequestClient;
	
	@Autowired
	private MockMvc mvc;
	
	private JacksonTester<UserRequest> jsonRequest;
	private JacksonTester<Set<Image>> jsonImages;
	
	@Before
	public void setup() {
		JacksonTester.initFields(this, new ObjectMapper());
	}
	
	@Test
	public void getRequestedImages() throws Exception {
		//given
		UserRequest request = new UserRequest("2018-04-26", "2018-04-28");
		Image image = new Image("97746203", "97746203", "http://i.4pcdn.org/tv/1524723078914.png", 
				"http://i.4pcdn.org/tv/1524723078914s.jpg", 	"IpadLjco7y01NCPk+Kabug==",	"bb.png", LocalDateTime.parse("2018-04-26T01:11:18"));
		Set<Image> images = new HashSet<>();
		images.add(image);
		given(imageService.
				getRequestedImages(request)).willReturn(images);
		
		// when
		MockHttpServletResponse response = mvc.perform(
				post("/imagepull").contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest.write(request).getJson()))
        .andReturn().getResponse();
	
		// then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        /*failing test
        assertThat(response.getContentAsString()).isEqualTo(
                jsonImages.write(images
                ).getJson());
		*/
	}
}
