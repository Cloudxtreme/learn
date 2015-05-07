package ch.protonmail.vladyslavbond.learn.domain;

import java.util.List;

public interface Task extends GenericTask<String, String, Object>
{
	public abstract Identificator<Task> getId ( );

	public abstract List<TaskOption> getTaskOptions ( );

	public abstract TaskOption getTaskOption (Identificator<TaskOption> taskOptionId);

	public abstract TaskCategory getTaskCategory ( );

	public abstract float grade ( );

	public abstract boolean isAnswerGiven ( );
}