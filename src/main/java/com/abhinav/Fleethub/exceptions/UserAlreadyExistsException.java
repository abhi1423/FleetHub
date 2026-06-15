package com.abhinav.Fleethub.exceptions;

public class UserAlreadyExistsException extends RuntimeException
{
	public UserAlreadyExistsException() {
		super("UserAlreadyExists");
		
	}
	public UserAlreadyExistsException(String msg)
	{
		super(msg);
	}
}
