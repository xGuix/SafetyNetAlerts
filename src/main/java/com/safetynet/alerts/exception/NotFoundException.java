package com.safetynet.alerts.exception;

public class NotFoundException extends RuntimeException
{
	private static final long serialVersionUID = -5612897368064835022L;

	/**
	 * Super constructor
	 * 
	 * @param notFound message 
	 */
	public NotFoundException(String notFound) 
	{
		super(notFound);
	}
}