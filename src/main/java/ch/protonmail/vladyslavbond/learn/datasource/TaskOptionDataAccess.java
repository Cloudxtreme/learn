package ch.protonmail.vladyslavbond.learn.datasource;

import ch.protonmail.vladyslavbond.learn.domain.TaskOption;
import ch.protonmail.vladyslavbond.learn.domain.Task;
import ch.protonmail.vladyslavbond.learn.domain.Identificator;

import java.util.List;

public interface TaskOptionDataAccess
{
	public abstract List<TaskOption> read (Identificator<Task> taskId) throws DataAccessException;
}