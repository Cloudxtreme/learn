package ch.protonmail.vladyslavbond.learn.services;

import ch.protonmail.vladyslavbond.learn.domain.Task;
import ch.protonmail.vladyslavbond.learn.domain.Assessment;
import ch.protonmail.vladyslavbond.learn.domain.Identificator;

import java.util.List;

public interface TaskService
{
	public abstract List<Task> read (Identificator<Assessment> assessmentId) throws TaskServiceException;
}