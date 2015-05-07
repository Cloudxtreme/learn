package ch.protonmail.vladyslavbond.learn.domain;

public interface Exam
{
	public abstract String getTitle ( );

	public abstract Identificator<Exam> getId ( );
}