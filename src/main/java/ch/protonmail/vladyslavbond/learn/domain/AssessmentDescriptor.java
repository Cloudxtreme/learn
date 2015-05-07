package ch.protonmail.vladyslavbond.learn.domain;

import java.util.List;

public interface AssessmentDescriptor extends BusinessObjectDescriptor<Assessment>
{
	public abstract List<Task> getTasks ( );
}