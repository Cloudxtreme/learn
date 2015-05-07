package ch.protonmail.vladyslavbond.learn.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import ch.protonmail.vladyslavbond.learn.services.ServiceProvider;
import ch.protonmail.vladyslavbond.learn.services.TaskOptionServiceException;

class NativeTaskDescriptor extends NativeBusinessObjectDescriptor<Task> implements TaskDescriptor
{
	private TaskCategory taskCategory;
	private String description;
	private List<TaskOption> taskOptions;

	public NativeTaskDescriptor (ResultSet resultSet)
	throws SQLException
	{
		this.id = new Identificator<Task> (resultSet.getInt(1));
		this.taskCategory = TaskCategory.getTaskCategory(new Identificator<TaskCategory>(resultSet.getInt(2)));
		this.description = resultSet.getString(3);
		try
		{
			this.taskOptions = ServiceProvider.getTaskOptionService( ).read(this.id);
		} catch (TaskOptionServiceException e) {
			throw new RuntimeException ("Failed to read task options for task descriptor.", e);
		}
	}

	@Override
	public String getDescription ( )
	{
		return this.description;
	}

	@Override
	public List<TaskOption> getTaskOptions ( )
	{
		return this.taskOptions;
	}

	@Override
	public TaskCategory getTaskCategory ( )
	{
		return this.taskCategory;
	}

	@Override
	public Task newInstance ( )
	{
		return NativeTask.newInstance(this);
	}
}