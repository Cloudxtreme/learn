package ch.protonmail.vladyslavbond.learn.datasource;

import ch.protonmail.vladyslavbond.learn.domain.Task;
import ch.protonmail.vladyslavbond.learn.domain.Assessment;
import ch.protonmail.vladyslavbond.learn.domain.Identificator;

import java.util.List;

public interface TaskDataAccess
{
	public abstract List<Task> read (Identificator<Assessment> assessmentId) throws DataAccessException;
}