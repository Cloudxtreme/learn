package ch.protonmail.vladyslavbond.learn.domain;

public interface TaskOption extends GenericTaskOption<String>
{
	public abstract Identificator<TaskOption> getId ( );

	public abstract Integer getReward ( );
}