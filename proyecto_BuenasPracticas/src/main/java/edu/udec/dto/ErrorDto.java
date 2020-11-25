package edu.udec.dto;

import java.time.LocalDateTime;

public class ErrorDto {
	
	private LocalDateTime timestamp;
	
	private String status;
	
	private String error;
	
	private String message;
	
	private String path;

	public ErrorDto(String status, String error, String message, String path) {
		super();
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
		this.timestamp = LocalDateTime.now();
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
	
}
