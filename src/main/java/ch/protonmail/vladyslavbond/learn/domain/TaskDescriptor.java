package ch.protonmail.vladyslavbond.learn.domain;

import java.util.List;

public interface TaskDescriptor extends BusinessObjectDescriptor<Task>
{
	public abstract TaskCategory getTaskCategory ( );

	public abstract String getDescription ( );

	public abstract List<TaskOption> getTaskOptions ( );
}