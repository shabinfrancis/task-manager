package com.example.app.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TaskRequest {
	@NotBlank(message = "Title is required")
	@Size(min=3, max=100, message="Title must be between 3 and 100 characters")
	private String title;
	
	@Size(max=500, message="Title must not be more than 500 characters")
	private String description;
	
	@Min(value=1, message="Priority must be at least 1")
	private int priority;

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

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
}
