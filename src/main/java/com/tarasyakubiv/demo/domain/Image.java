package com.tarasyakubiv.demo.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Image {
    @Id
    @GeneratedValue
    private Long id;
	private String postNumber;
	private String threadNumber;
	private String image;
	private String thumbImage;
	private String imageHash;
	private String name;
	private LocalDateTime time;
	
	
	Image() {}
	
	public Image(String postNumber, String threadNumber, String image, String thumbImage, String imageHash, String name, LocalDateTime time) {
		this.postNumber = postNumber;
		this.threadNumber = threadNumber;
		this.image = image;
		this.thumbImage = thumbImage;
		this.imageHash = imageHash;
		this.name = name;
		this.time = time;
	}

	
	public String getPostNumber() {
		return postNumber;
	}
	public void setPostNumber(String postNumber) {
		this.postNumber = postNumber;
	}
	public String getThreadNumber() {
		return threadNumber;
	}
	public void setThreadNumber(String threadNumber) {
		this.threadNumber = threadNumber;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getThumbImage() {
		return thumbImage;
	}
	public void setThumbImage(String thumbImage) {
		this.thumbImage = thumbImage;
	}
	public String getImageHash() {
		return imageHash;
	}
	public void setImageHash(String imageHash) {
		this.imageHash = imageHash;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((imageHash == null) ? 0 : imageHash.hashCode());
		result = prime * result + ((postNumber == null) ? 0 : postNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Image other = (Image) obj;
		if (imageHash == null) {
			if (other.imageHash != null)
				return false;
		} else if (!imageHash.equals(other.imageHash))
			return false;
		if (postNumber == null) {
			if (other.postNumber != null)
				return false;
		} else if (!postNumber.equals(other.postNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Image [" + (id != null ? "id=" + id + ", " : "") + "postNumber=" + postNumber + ", threadNumber="
				+ threadNumber + ", " + (image != null ? "image=" + image + ", " : "")
				+ (thumbImage != null ? "thumbImage=" + thumbImage + ", " : "")
				+ (imageHash != null ? "imageHash=" + imageHash + ", " : "")
				+ (name != null ? "name=" + name + ", " : "") + (time != null ? "time=" + time : "") + "]";
	}



	
	
	

	

}
