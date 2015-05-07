package ch.protonmail.vladyslavbond.learn.services;

import ch.protonmail.vladyslavbond.learn.domain.Exam;
import java.util.List;

public interface ExamService
{
	public abstract List<Exam> read ( ) throws ExamServiceException;
}