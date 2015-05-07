package ch.protonmail.vladyslavbond.learn.services;

public class TaskServiceException extends ServiceException
{
	public TaskServiceException (String message, Throwable cause)
	{
		super(message, cause);
	}

	public TaskServiceException (String message)
	{
		super(message);
	}
}