package ch.protonmail.vladyslavbond.learn.handlers;

import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ch.protonmail.vladyslavbond.learn.domain.Account;
import ch.protonmail.vladyslavbond.learn.domain.Assessment;
import ch.protonmail.vladyslavbond.learn.domain.Exam;
import ch.protonmail.vladyslavbond.learn.domain.Identificator;

import ch.protonmail.vladyslavbond.learn.services.ServiceProvider;
import ch.protonmail.vladyslavbond.learn.services.AssessmentServiceException;

class AssessmentCreateRequestHandler extends HttpServlet
{
	@Override
	public void doPost (HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		try
		{
			HttpSession session = request.getSession( );
			Account account = (Account)session.getAttribute("account");
			boolean loggedIn = (account != null);
			if (loggedIn)
			{
				Assessment assessment = (Assessment)session.getAttribute("assessment");
				boolean alreadyTakingAssessment = (assessment != null);
				if (!alreadyTakingAssessment)
				{
					boolean assessmentResumedSuccessfully = false;
					assessment = ServiceProvider.getAssessmentService( ).read(account.getId( ));
					assessmentResumedSuccessfully = (assessment != null);
					
					boolean assessmentCreatedSuccessfully = false;
					if (!assessmentResumedSuccessfully)
					{
						Identificator<Exam> examId;
						try
						{
							examId = new Identificator<Exam> ((String)request.getParameter("exam_id"));
						} catch (RuntimeException e) {
							throw new ServletException ("Failed to retrive exam id from the request.", e);
						}
						assessment = ServiceProvider.getAssessmentService( ).create(account.getId( ), examId);
						assessmentCreatedSuccessfully = (assessment != null);
					}
					if (assessmentCreatedSuccessfully || assessmentResumedSuccessfully)
					{
						session.setAttribute("assessment", assessment);
					} else {
						throw new ServletException ("Failed to take assessment.");
					}
				}

				this.doGet(request, response);
			} else {
				request.getRequestDispatcher(Page.SESSION_CREATE.toString( )).forward(request, response);
			}
		} catch (Exception e) {
			throw new ServletException ("Failed to process the request.", e);
		}
	}

	@Override
	public void doGet (HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		try
		{
			Assessment assessment = (Assessment)request.getSession( ).getAttribute("assessment");
			boolean currentlyTakingAssessment = (assessment != null);
			if (currentlyTakingAssessment)
			{
				request.setAttribute("assessment", assessment);
				request.getRequestDispatcher(Page.ASSESSMENT_UPDATE.toString( )).forward(request, response);
			} else {
				request.getRequestDispatcher(Page.EXAM_READ.toString( )).forward(request, response);
			}
		} catch (Exception e) {
			throw new ServletException ("Failed to process the request.", e);
		}
	}
}