package ch.protonmail.vladyslavbond.learn.services;

public class TaskOptionServiceException extends ServiceException
{
	public TaskOptionServiceException (String message, Throwable cause)
	{
		super(message, cause);
	}

	public TaskOptionServiceException (String message)
	{
		super(message);
	}
}