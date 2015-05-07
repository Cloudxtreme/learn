package ch.protonmail.vladyslavbond.learn.datasource;

public class DataAccessProvider
{
	private DataAccessProvider ( )
	{
		throw new AssertionError ( );
	}

	public static AccountDataAccess getAccountDataAccess ( )
	{
		return NativeAccountDataAccess.INSTANCE;
	}

	public static AssessmentDataAccess getAssessmentDataAccess ( )
	{
		return NativeAssessmentDataAccess.INSTANCE;
	}

	public static ExamDataAccess getExamDataAccess ( )
	{
		return NativeExamDataAccess.INSTANCE;
	}

	public static TaskDataAccess getTaskDataAccess ( )
	{
		return NativeTaskDataAccess.INSTANCE;
	}

	public static TaskOptionDataAccess getTaskOptionDataAccess ( )
	{
		return NativeTaskOptionDataAccess.INSTANCE;
	}
}