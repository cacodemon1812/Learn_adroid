package jmaster.io.evnloyalty.model;

import java.util.List;

public class Notification {
	private Long id;
	private String content;
	private String createdDate;

	public Notification() {
	}

	public Notification(Long id, String content, String createdDate) {
		this.id = id;
		this.content = content;
		this.createdDate = createdDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
}
