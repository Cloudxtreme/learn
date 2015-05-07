package ch.protonmail.vladyslavbond.learn.domain;

import java.util.List;

public interface Assessment
{
	public abstract Identificator<Assessment> getId ( );

	public abstract List<Task> getTasks ( );

	public abstract Task getTask (Identificator<Task> taskId);

	public abstract void answer (Identificator<Task> taskId, String answerGiven);

	public abstract float grade ( );

	public abstract void next ( );

	public abstract void jumpTo (Identificator<Task> taskId);
}