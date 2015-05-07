package ch.protonmail.vladyslavbond.learn.services;

public class ServiceProvider
{
	private ServiceProvider ( )
	{
		throw new AssertionError ("ServiceProvider class must not be instantiated.");
	}

	public static AccountService getAccountService ( ) throws AccountServiceException
	{
		return NativeAccountService.INSTANCE;
	}

	public static AssessmentService getAssessmentService ( ) throws AssessmentServiceException
	{
		return NativeAssessmentService.INSTANCE;
	}

	public static TaskService getTaskService ( ) throws TaskServiceException
	{
		return NativeTaskService.INSTANCE;
	}

	public static TaskOptionService getTaskOptionService ( ) throws TaskOptionServiceException
	{
		return NativeTaskOptionService.INSTANCE;
	}

	public static ExamService getExamService ( ) throws ExamServiceException
	{
		return NativeExamService.INSTANCE;
	}
}