package ch.protonmail.vladyslavbond.learn.handlers;

import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ch.protonmail.vladyslavbond.learn.domain.Account;
import ch.protonmail.vladyslavbond.learn.domain.Assessment;

import ch.protonmail.vladyslavbond.learn.services.ServiceProvider;

class AssessmentDestroyRequestHandler extends HttpServlet
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
				boolean currentlyTakingAssessment = (assessment != null);
				if (currentlyTakingAssessment)
				{
					boolean success = ServiceProvider.getAssessmentService( ).destroy(account.getId( ));
					if (success)
					{
						session.setAttribute("assessment", null);
						request.setAttribute("grade", Float.valueOf(assessment.grade( )));
						request.getRequestDispatcher(Page.ASSESSMENT_DESTROY.toString( )).forward(request, response);
					} else {
						throw new ServletException("Failed to grade assessment due to unknown database issues.");
					}
				} else {
					request.getRequestDispatcher(Page.ASSESSMENT_CREATE.toString( )).forward(request, response);
				}
			} else {
				request.getRequestDispatcher(Page.SESSION_CREATE.toString( )).forward(request, response);
			}
		} catch (Exception e) {
			throw new ServletException ("Failed to process request.", e);
		}
	}
}