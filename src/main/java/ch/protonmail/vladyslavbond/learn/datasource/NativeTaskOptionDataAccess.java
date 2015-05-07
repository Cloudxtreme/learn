package ch.protonmail.vladyslavbond.learn.datasource;

import java.sql.ResultSet;

import ch.protonmail.vladyslavbond.learn.domain.TaskOption;
import ch.protonmail.vladyslavbond.learn.domain.DescriptorFactory;
import ch.protonmail.vladyslavbond.learn.domain.Identificator;
import ch.protonmail.vladyslavbond.learn.domain.Task;

import java.util.List;
import java.util.ArrayList;

enum NativeTaskOptionDataAccess implements TaskOptionDataAccess
{
	INSTANCE ( );

	private NativeTaskOptionDataAccess ( )
	{

	}

	@Override
	public List<TaskOption> read (Identificator<Task> taskId) throws DataAccessException
	{
		try
		{
			ResultSet resultSet = Query.newInstance("SELECT * FROM task_option_read(?::domain_task_id);")
				.setInt(taskId)
				.executeQuery( );
			List<TaskOption> listOfTaskOptions = new ArrayList<TaskOption> ( );
			while (resultSet.next( ))
			{
				listOfTaskOptions.add(DescriptorFactory.newTaskOptionDescriptor(resultSet).newInstance( ));
			}
			resultSet.close( );
			return listOfTaskOptions;
		} catch (Exception e) {
			throw new DataAccessException ("Failed to read a list of task options in task from the database.", e);
		}
	}
}