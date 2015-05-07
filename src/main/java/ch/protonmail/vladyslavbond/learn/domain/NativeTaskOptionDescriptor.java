package ch.protonmail.vladyslavbond.learn.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

class NativeTaskOptionDescriptor extends NativeBusinessObjectDescriptor<TaskOption> implements TaskOptionDescriptor
{
	private Identificator<Task> taskId;
	private String content;
	private Integer reward;

	public NativeTaskOptionDescriptor (ResultSet resultSet)
	throws SQLException
	{
		this.taskId = new Identificator<Task> (resultSet.getInt(1));
		this.id = new Identificator<TaskOption> (resultSet.getInt(2));
		this.content = resultSet.getString(3);
		this.reward = resultSet.getInt(4);
	}

	@Override
	public String getContent ( )
	{
		return this.content;
	}

	@Override
	public Integer getReward ( )
	{
		return this.reward;
	}

	@Override
	public TaskOption newInstance ( )
	{
		return NativeTaskOption.newInstance(this);
	}
}