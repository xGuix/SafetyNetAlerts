package com.safetynet.alerts.exception;

public class AlreadyExistingException extends RuntimeException
{
	private static final long serialVersionUID = -1186880060067215368L;

	/**
	 * Super constructor
	 * 
	 * @param alreadyExists message 
	 */
	public AlreadyExistingException(String alreadyExists) 
	{
		super(alreadyExists);
	}
}