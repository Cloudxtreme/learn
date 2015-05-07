package ch.protonmail.vladyslavbond.learn.domain;

public interface TaskOptionDescriptor extends BusinessObjectDescriptor<TaskOption>
{
	public abstract String getContent ( );

	public abstract Integer getReward ( );
}