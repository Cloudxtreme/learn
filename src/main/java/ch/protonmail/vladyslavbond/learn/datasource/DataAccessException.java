package ch.protonmail.vladyslavbond.learn.datasource;

public class DataAccessException extends Exception
{
	public DataAccessException (String message)
	{
		super(message);
	}

	public DataAccessException (String message, Throwable cause)
	{
		super(message, cause);
	}

	public DataAccessException (Throwable cause)
	{
		this(cause.getMessage( ), cause);
	}
}