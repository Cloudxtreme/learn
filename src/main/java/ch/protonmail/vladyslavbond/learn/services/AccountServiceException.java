package ch.protonmail.vladyslavbond.learn.services;

public class AccountServiceException extends Exception
{
	public AccountServiceException (String message, Throwable cause)
	{
		super(message, cause);
	}
}