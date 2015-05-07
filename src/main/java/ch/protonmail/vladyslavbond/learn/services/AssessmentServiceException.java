package ch.protonmail.vladyslavbond.learn.services;

public class AssessmentServiceException extends ServiceException
{
	public AssessmentServiceException (String message, Throwable cause)
	{
		super(message, cause);
	}

	public AssessmentServiceException (String message)
	{
		super(message);
	}
}