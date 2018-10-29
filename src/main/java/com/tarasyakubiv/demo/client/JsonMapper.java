package com.tarasyakubiv.demo.client;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tarasyakubiv.demo.domain.Image;

public class JsonMapper {
	
	static public Set<Image> readJson(String jsonData, String postNumber) {
    	ObjectMapper objectMapper = new ObjectMapper();
    	Set<Image> images = new HashSet<>();
    	try {
    		JsonNode rootNode = objectMapper.readTree(jsonData).path(postNumber);
        	JsonNode opNode = rootNode.path("op");
        	JsonNode opMediaNode = opNode.path("media");
        	Image opImage = encapsulateImage(opNode, opMediaNode);
        	images.add(opImage);
        	JsonNode postsNode = rootNode.path("posts");
        	Iterator<Entry<String, JsonNode>> posts = postsNode.fields();

        	while (posts.hasNext()) {
        	  Map.Entry<String, JsonNode> post = (Map.Entry<String, JsonNode>) posts.next();
        	  JsonNode mediaNode = post.getValue().get("media");
        	  if(!mediaNode.isNull()) {
        		  Image postImage = encapsulateImage(post.getValue(), mediaNode);
        		  images.add(postImage);
        	  }
        	}
    	} 
    	catch (Exception e) {
    		e.printStackTrace();
    	}
		return images;
	}
	
	static private Image encapsulateImage(JsonNode postNode, JsonNode mediaNode) {
		String postNumber = postNode.get("num").asText();
		String threadNumber = postNode.get("thread_num").asText();
		String image = mediaNode.get("media_link").asText();
		String thumbImage = mediaNode.get("thumb_link").asText();
		String imageHash = mediaNode.get("media_hash").asText();
		String name = mediaNode.get("media_filename").asText();
		Long timestamp = postNode.get("timestamp").asLong() * 1000;
		LocalDateTime time = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), 
                TimeZone.getDefault().toZoneId());
		return new Image(postNumber, threadNumber, image, thumbImage, imageHash, name, time);
		
	}

}
