package ch.protonmail.vladyslavbond.learn.datasource;

import java.sql.ResultSet;

import ch.protonmail.vladyslavbond.learn.domain.Assessment;
import ch.protonmail.vladyslavbond.learn.domain.DescriptorFactory;
import ch.protonmail.vladyslavbond.learn.domain.Identificator;
import ch.protonmail.vladyslavbond.learn.domain.Task;

import java.util.List;
import java.util.ArrayList;

enum NativeTaskDataAccess implements TaskDataAccess
{
	INSTANCE ( );

	private NativeTaskDataAccess ( )
	{

	}

	@Override
	public List<Task> read (Identificator<Assessment> assessmentId) throws DataAccessException
	{
		try
		{
			ResultSet resultSet = Query.newInstance("SELECT * FROM task_read(?::domain_task_suite_id);")
				.setInt(assessmentId)
				.executeQuery( );
			List<Task> listOfTasks = new ArrayList<Task> ( );
			while (resultSet.next( ))
			{
				listOfTasks.add(DescriptorFactory.newTaskDescriptor(resultSet).newInstance( ));
			}
			resultSet.close( );
			return listOfTasks;
		} catch (Exception e) {
			throw new DataAccessException ("Failed to read a list of tasks in task suite from the database.", e);
		}
	}
}