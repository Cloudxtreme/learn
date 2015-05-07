package ch.protonmail.vladyslavbond.learn.handlers;

import java.io.IOException;

import java.util.Arrays;
import java.util.List;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ch.protonmail.vladyslavbond.learn.domain.Account;
import ch.protonmail.vladyslavbond.learn.domain.Assessment;
import ch.protonmail.vladyslavbond.learn.domain.Exam;
import ch.protonmail.vladyslavbond.learn.domain.Task;
import ch.protonmail.vladyslavbond.learn.domain.Identificator;

import ch.protonmail.vladyslavbond.learn.services.ServiceProvider;

class AssessmentUpdateRequestHandler extends HttpServlet
{
	@Override
	public void doPost (HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		try
		{
			HttpSession session = request.getSession( );
			Assessment assessment = (Assessment)session.getAttribute("assessment");
			Account account = (Account)session.getAttribute("account");
			boolean loggedIn = (account != null);
			boolean currentlyTakingAssessment = (assessment != null);
			if (currentlyTakingAssessment && loggedIn)
			{
				Identificator<Task> taskId = new Identificator<Task> (request.getParameter("task_id"));
				List<String> listOfAnswers = Arrays.asList(request.getParameterValues("answer"));
				boolean success = ServiceProvider.getAssessmentService( ).update(account.getId( ), taskId, listOfAnswers);
				if (success)
				{
					for (String answer : listOfAnswers)
					{
						assessment.answer(taskId, answer);
					}
					assessment.next( );
					session.setAttribute("assessment", assessment);
				}
			}
			/*request.setAttribute("assessment", assessment);*/
			request.getRequestDispatcher(Page.ASSESSMENT_UPDATE.toString( )).forward(request, response);
		} catch (Exception e) {
			throw new ServletException ("Failed to process the request.", e);
		}
	}

	@Override
	public void doGet (HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		HttpSession session = request.getSession( );
		Assessment assessment = (Assessment)session.getAttribute("assessment");
		Account account = (Account)session.getAttribute("account");
		boolean loggedIn = (account != null);
		boolean currentlyTakingAssessment = (assessment != null);
		if (currentlyTakingAssessment && loggedIn)
		{
	        String requestURL = request.getRequestURL( ).toString( );
			Matcher matcher = Pattern.compile(".*/+(\\d+)/?").matcher(requestURL);
			Identificator<Task> taskId = null;
			if (matcher.matches( ))
			{
			    taskId = new Identificator<Task> ((String)matcher.group(1));
			} else {
				throw new ServletException("Doesn't match: " + requestURL);
			}
			if (taskId != null)
			{
				assessment.jumpTo(taskId);
				session.setAttribute("assessment", assessment);
			}
			request.getRequestDispatcher(Page.ASSESSMENT_UPDATE.toString( )).forward(request, response);
		} else {
			request.getRequestDispatcher(Page.ASSESSMENT_CREATE.toString( )).forward(request, response);
		}
	}
}