package ch.protonmail.vladyslavbond.learn.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import ch.protonmail.vladyslavbond.learn.services.ServiceProvider;
import ch.protonmail.vladyslavbond.learn.services.TaskServiceException;

class NativeAssessmentDescriptor extends NativeBusinessObjectDescriptor<Assessment> implements AssessmentDescriptor
{
	private List<Task> tasks;

	public NativeAssessmentDescriptor (ResultSet resultSet)
	throws SQLException
	{
		this.id = new Identificator<Assessment> (resultSet.getInt(1));
		try
		{
			this.tasks = ServiceProvider.getTaskService( ).read(this.id);
		} catch (TaskServiceException e) {
			throw new RuntimeException ("Failed to read tasks for the assessment descriptor.", e);
		}
	}

	@Override
	public List<Task> getTasks ( )
	{
		return this.tasks;
	}

	@Override
	public Assessment newInstance ( )
	{
		return NativeAssessment.newInstance(this);
	}
}