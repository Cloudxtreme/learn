package ch.protonmail.vladyslavbond.learn.services;

import ch.protonmail.vladyslavbond.learn.domain.Assessment;
import ch.protonmail.vladyslavbond.learn.domain.Account;
import ch.protonmail.vladyslavbond.learn.domain.Exam;
import ch.protonmail.vladyslavbond.learn.domain.Task;
import ch.protonmail.vladyslavbond.learn.domain.Identificator;

import java.util.List;

public interface AssessmentService
{
	public abstract Assessment create 
	(
		  Identificator<Account> accountId
		, Identificator<Exam>    examId
	) throws AssessmentServiceException
	;

	public abstract Assessment read 
	(
		  Identificator<Account> accountId
	) throws AssessmentServiceException
	;

	public abstract boolean update 
	(
	   	  Identificator<Account> accountId
		, Identificator<Task>    taskId
		, List<String>           answers
	) throws AssessmentServiceException
	;

	public abstract boolean destroy 
	(
	   	  Identificator<Account> accountId
	) throws AssessmentServiceException
	;
}