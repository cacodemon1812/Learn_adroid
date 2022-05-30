package jmaster.io.evnloyalty.model;

import java.util.List;

public class Post {
	private Long id;
	private String title;
	private String description;
	private List<String> images;
	private String createdDate;

	public Post() {
	}

	public Post(Long id, String title, String description, List<String> images, String createdDate) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.images = images;
		this.createdDate = createdDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
}
