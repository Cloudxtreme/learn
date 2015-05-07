package ch.protonmail.vladyslavbond.learn.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DescriptorFactory
{
	private DescriptorFactory ( )
	{
		throw new AssertionError ( );
	}

	public static AccountDescriptor newAccountDescriptor (ResultSet resultSet)
	throws SQLException
	{
		return new NativeAccountDescriptor(resultSet);
	}

	public static AssessmentDescriptor newAssessmentDescriptor (ResultSet resultSet)
	throws SQLException
	{
		return new NativeAssessmentDescriptor(resultSet);
	}

	public static ExamDescriptor newExamDescriptor (ResultSet resultSet)
	throws SQLException
	{
		return new NativeExamDescriptor(resultSet);
	}

	public static TaskDescriptor newTaskDescriptor (ResultSet resultSet)
	throws SQLException
	{
		return new NativeTaskDescriptor(resultSet);
	}

	public static TaskOptionDescriptor newTaskOptionDescriptor (ResultSet resultSet)
	throws SQLException
	{
		return new NativeTaskOptionDescriptor(resultSet);
	}
}