package com.weconnect.utils;

public class NoFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NoFoundException(String message) {
		super(message);
	}
}
