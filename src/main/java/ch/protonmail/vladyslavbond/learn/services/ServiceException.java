package ch.protonmail.vladyslavbond.learn.services;

abstract class ServiceException extends Exception
{
	public ServiceException (String message, Throwable cause)
	{
		super(message, cause);
	}

	public ServiceException (String message)
	{
		super(message);
	}
}