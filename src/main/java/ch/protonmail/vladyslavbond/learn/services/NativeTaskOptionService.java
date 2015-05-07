package ch.protonmail.vladyslavbond.learn.services;

import ch.protonmail.vladyslavbond.learn.datasource.TaskOptionDataAccess;
import ch.protonmail.vladyslavbond.learn.datasource.DataAccessProvider;

import ch.protonmail.vladyslavbond.learn.domain.Task;
import ch.protonmail.vladyslavbond.learn.domain.TaskOption;
import ch.protonmail.vladyslavbond.learn.domain.Identificator;

import java.util.List;

enum NativeTaskOptionService implements TaskOptionService
{
	INSTANCE ( );

	private NativeTaskOptionService ( )
	{

	}

	private TaskOptionDataAccess getTaskOptionDataAccess ( )
	{
		return DataAccessProvider.getTaskOptionDataAccess( );
	}

	@Override
	public List<TaskOption> read (Identificator<Task> taskId)
	throws TaskOptionServiceException
	{
		try
		{
			return this.getTaskOptionDataAccess( ).read(taskId);
		} catch (Exception e) {
			throw new TaskOptionServiceException ("Failed to read a list of task options in the task.", e);
		}
	}
}