package ch.protonmail.vladyslavbond.learn.handlers;

enum Page
{
	  ACCOUNT_CREATE     ("/WEB-INF/views/sign_up.htm")
	, ASSESSMENT_CREATE  ("/WEB-INF/views/assessment_taking.htm")
	, ASSESSMENT_UPDATE  ("/WEB-INF/views/assessment_taking.htm")
	, ASSESSMENT_DESTROY ("/WEB-INF/views/grade_assessment.htm")
	, EXAM_READ          ("/index.htm")
	, SESSION_CREATE     ("/WEB-INF/views/log_in.htm")
	, SESSION_DESTROY    ("/WEB-INF/views/terminate_session.htm")
	, HOME               ("/index.htm")
	;

	private String pagePath;

	private Page (String pagePath)
	{
		this.pagePath = pagePath;
	}

	@Override
	public String toString ( )
	{
		return this.pagePath;
	}
}