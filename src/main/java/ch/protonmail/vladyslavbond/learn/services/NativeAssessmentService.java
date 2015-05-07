package ch.protonmail.vladyslavbond.learn.services;

import ch.protonmail.vladyslavbond.learn.datasource.DataAccessProvider;
import ch.protonmail.vladyslavbond.learn.datasource.AssessmentDataAccess;
import ch.protonmail.vladyslavbond.learn.datasource.DataAccessException;

import ch.protonmail.vladyslavbond.learn.domain.Assessment;
import ch.protonmail.vladyslavbond.learn.domain.Account;
import ch.protonmail.vladyslavbond.learn.domain.Exam;
import ch.protonmail.vladyslavbond.learn.domain.Task;
import ch.protonmail.vladyslavbond.learn.domain.Identificator;

import java.util.List;

enum NativeAssessmentService implements AssessmentService
{
	INSTANCE ( );

	private NativeAssessmentService ( )
	{

	}

	private AssessmentDataAccess getAssessmentDataAccess ( ) throws DataAccessException
	{
		return DataAccessProvider.getAssessmentDataAccess( );
	}

	@Override
	public Assessment create 
	(
		  Identificator<Account> accountId
		, Identificator<Exam>    examId
	) throws AssessmentServiceException
	{
		try
		{
			return this.getAssessmentDataAccess( ).create(accountId, examId);
		} catch (DataAccessException e) {
			throw new AssessmentServiceException (e.getMessage( ), e);
		}
	}

	@Override
	public Assessment read 
	(
		  Identificator<Account> accountId
	) throws AssessmentServiceException
	{
		try
		{
			return this.getAssessmentDataAccess( ).read(accountId);
		} catch (DataAccessException e) {
			throw new AssessmentServiceException (e.getMessage( ), e);
		}
	}

	@Override
	public boolean update 
	(
	   	  Identificator<Account> accountId
		, Identificator<Task>    taskId
		, List<String>           answers
	) throws AssessmentServiceException
	{
		try
		{
			/*TODO: Optimization required: there is no need in opening another database connection.*/
			boolean success = true;
			for (String answer : answers)
			{
				success = this.getAssessmentDataAccess( ).update(accountId, taskId, answer) && success;
			}
			return success;
		} catch (DataAccessException e) {
			throw new AssessmentServiceException (e.getMessage( ), e);
		}
	}

	@Override
	public boolean destroy 
	(
	   	  Identificator<Account> accountId
	) throws AssessmentServiceException
	{
		try
		{
			return this.getAssessmentDataAccess( ).destroy(accountId);
		} catch (DataAccessException e) {
			throw new AssessmentServiceException (e.getMessage( ), e);
		}
	}
}