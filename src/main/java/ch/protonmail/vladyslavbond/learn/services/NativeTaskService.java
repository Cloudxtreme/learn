package ch.protonmail.vladyslavbond.learn.services;

import ch.protonmail.vladyslavbond.learn.datasource.TaskDataAccess;
import ch.protonmail.vladyslavbond.learn.datasource.DataAccessProvider;

import ch.protonmail.vladyslavbond.learn.domain.Task;
import ch.protonmail.vladyslavbond.learn.domain.Assessment;
import ch.protonmail.vladyslavbond.learn.domain.Identificator;

import java.util.List;

enum NativeTaskService implements TaskService
{
	INSTANCE ( );

	private NativeTaskService ( )
	{

	}

	private TaskDataAccess getTaskDataAccess ( )
	{
		return DataAccessProvider.getTaskDataAccess( );
	}

	@Override
	public List<Task> read (Identificator<Assessment> assessmentId)
	throws TaskServiceException
	{
		try
		{
			return this.getTaskDataAccess( ).read(assessmentId);
		} catch (Exception e) {
			throw new TaskServiceException ("Failed to read a list of tasks in the assessment.", e);
		}
	}
}