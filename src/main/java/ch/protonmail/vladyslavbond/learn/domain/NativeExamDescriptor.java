package ch.protonmail.vladyslavbond.learn.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

class NativeExamDescriptor extends NativeBusinessObjectDescriptor<Exam> implements ExamDescriptor
{
	private static String TITLE_EMPTY = "Title of the exam is missing.";
	private String title;

	public NativeExamDescriptor (ResultSet resultSet)
	throws SQLException
	{
		this.id = new Identificator<Exam> (resultSet.getInt(1));
		this.title = resultSet.getString(2);
		if (this.title == null || this.title.isEmpty( ))
		{
			this.title = NativeExamDescriptor.TITLE_EMPTY;
		}
	}

	@Override
	public String getTitle ( )
	{
		return this.title;
	}

	@Override
	public Exam newInstance ( )
	{
		return (Exam)new NativeExam (this);
	}
}