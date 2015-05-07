package ch.protonmail.vladyslavbond.learn.datasource;

import ch.protonmail.vladyslavbond.learn.domain.Exam;
import java.util.List;

public interface ExamDataAccess
{
	public abstract List<Exam> read ( ) throws DataAccessException;
}
