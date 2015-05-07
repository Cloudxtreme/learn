package ch.protonmail.vladyslavbond.learn.datasource;

import ch.protonmail.vladyslavbond.learn.domain.Assessment;
import ch.protonmail.vladyslavbond.learn.domain.Account;
import ch.protonmail.vladyslavbond.learn.domain.Exam;
import ch.protonmail.vladyslavbond.learn.domain.Task;
import ch.protonmail.vladyslavbond.learn.domain.Identificator;

public interface AssessmentDataAccess
{
	public abstract Assessment create 
	(
		  Identificator<Account> accountId
		, Identificator<Exam>    examId
	) throws DataAccessException
	;

	public abstract Assessment read 
	(
		  Identificator<Account> accountId
	) throws DataAccessException
	;

	public abstract boolean update 
	(
	   	  Identificator<Account> accountId
		, Identificator<Task>    taskId
		, String                 answer
	) throws DataAccessException
	;

	public abstract boolean destroy 
	(
	   	  Identificator<Account> accountId
	) throws DataAccessException
	;
}