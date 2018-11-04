package com.tarasyakubiv.demo.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Tag {
	
    @Id
    @GeneratedValue
    @Column(name = "TAG_ID")
    private Long id;
    
    private String name;
    
    @ManyToMany(cascade = { CascadeType.ALL})
    @JoinTable(
            name = "Tag_Image", 
            joinColumns = { @JoinColumn(name = "tag_id") }, 
            inverseJoinColumns = { @JoinColumn(name = "image_id") }
        )
    Set<Image> images = new HashSet<>();
    
	public Tag(String name, Set<Image> images) {
		super();
		this.name = name;
		this.images = images;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Image> getImages() {
		return images;
	}

	public void setImages(Set<Image> images) {
		this.images = images;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Tag other = (Tag) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tag [" + (id != null ? "id=" + id + ", " : "") + (name != null ? "name=" + name + ", " : "")
				+ (images != null ? "images=" + images : "") + "]";
	}
	
	
    
}
