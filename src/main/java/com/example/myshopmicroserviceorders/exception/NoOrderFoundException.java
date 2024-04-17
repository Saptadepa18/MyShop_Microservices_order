package com.example.myshopmicroserviceorders.exception;

public class NoOrderFoundException extends RuntimeException{
	
	public NoOrderFoundException(String msg)
	{
		super(msg);
	}

}
