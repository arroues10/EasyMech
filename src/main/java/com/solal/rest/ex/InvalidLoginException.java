package com.solal.rest.ex;

@SuppressWarnings("serial")
public class InvalidLoginException extends Exception {
	public InvalidLoginException(String message) {
		super(message);
	}
}
