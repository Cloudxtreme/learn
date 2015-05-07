package ch.protonmail.vladyslavbond.learn.datasource;

import java.sql.ResultSet;

import ch.protonmail.vladyslavbond.learn.domain.Assessment;
import ch.protonmail.vladyslavbond.learn.domain.Account;
import ch.protonmail.vladyslavbond.learn.domain.DescriptorFactory;
import ch.protonmail.vladyslavbond.learn.domain.Exam;
import ch.protonmail.vladyslavbond.learn.domain.Task;
import ch.protonmail.vladyslavbond.learn.domain.Identificator;

enum NativeAssessmentDataAccess implements AssessmentDataAccess
{
	INSTANCE ( );

	private NativeAssessmentDataAccess ( )
	{

	}

	@Override
	public Assessment create 
	(
		  Identificator<Account> accountId
		, Identificator<Exam>    examId
	) throws DataAccessException
	{
		try
		{
			ResultSet resultSet = Query.newInstance("SELECT * FROM task_suite_create(?::domain_account_id, ?::domain_exam_id);")
				.setInt(accountId)
				.setInt(examId)
				.executeQuery( );
			while (resultSet.next( ))
			{
				return DescriptorFactory.newAssessmentDescriptor(resultSet).newInstance( );
			}
			throw new DataAccessException ("The result set is empty.");
		} catch (Exception e) {
			throw new DataAccessException ("Failed to add new assessment to the database.", e);
		}
	}

	@Override
	public Assessment read 
	(
		  Identificator<Account> accountId
	) throws DataAccessException
	{
		try
		{
			ResultSet resultSet = Query.newInstance("SELECT * FROM task_suite_read(?::domain_account_id);")
				.setInt(accountId)
				.executeQuery( );
			while (resultSet.next( ))
			{
				return DescriptorFactory.newAssessmentDescriptor(resultSet).newInstance( );
			}
			return null;
		} catch (Exception e) {
			throw new DataAccessException ("Failed to fetch an assessment from the database.", e);
		}
	}

	@Override
	public boolean update 
	(
	   	  Identificator<Account> accountId
		, Identificator<Task>    taskId
		, String                 answer
	) throws DataAccessException
	{
		try
		{
			ResultSet resultSet = Query.newInstance("SELECT * FROM task_suite_update(?::domain_account_id, ?::domain_task_id, ?::TEXT);")
				.setInt(accountId)
				.setInt(taskId)
				.setString(answer)
				.executeQuery( );
			while (resultSet.next( ))
			{
				return resultSet.getBoolean(1);
			}
			throw new DataAccessException ("The result set is empty.");
		} catch (Exception e) {
			throw new DataAccessException ("Failed to add an answer to the assessment in the database.", e);
		}
	}

	@Override
	public boolean destroy 
	(
	   	  Identificator<Account> accountId
	) throws DataAccessException
	{
		try
		{
			ResultSet resultSet = Query.newInstance("SELECT * FROM task_suite_destroy(?::domain_account_id);")
				.setInt(accountId)
				.executeQuery( );
			while (resultSet.next( ))
			{
				return resultSet.getBoolean(1);
			}
			throw new DataAccessException ("The result set is empty.");
		} catch (Exception e) {
			throw new DataAccessException ("Failed to mark assessment as completed in the database.", e);
		}
	}
}