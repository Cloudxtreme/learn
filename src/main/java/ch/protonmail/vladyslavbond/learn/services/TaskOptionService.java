package ch.protonmail.vladyslavbond.learn.services;

import ch.protonmail.vladyslavbond.learn.domain.Task;
import ch.protonmail.vladyslavbond.learn.domain.TaskOption;
import ch.protonmail.vladyslavbond.learn.domain.Identificator;

import java.util.List;

public interface TaskOptionService
{
	public abstract List<TaskOption> read (Identificator<Task> taskId) throws TaskOptionServiceException;
}