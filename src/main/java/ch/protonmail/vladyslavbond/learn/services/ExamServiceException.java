package ch.protonmail.vladyslavbond.learn.services;

public class ExamServiceException extends ServiceException
{
	public ExamServiceException (String message, Throwable cause)
	{
		super(message, cause);
	}

	public ExamServiceException (String message)
	{
		super(message);
	}
}