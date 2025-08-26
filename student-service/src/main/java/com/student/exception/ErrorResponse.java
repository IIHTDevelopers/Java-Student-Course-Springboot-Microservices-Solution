package com.student.exception;

import java.time.LocalDateTime;

public class ErrorResponse {

	private LocalDateTime timestamp;
	private int status;
	private String message;
	private String path;

	public ErrorResponse(LocalDateTime t, int s, String m, String p) {
		timestamp = t;
		status = s;
		message = m;
		path = p;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public String getPath() {
		return path;
	}
}
