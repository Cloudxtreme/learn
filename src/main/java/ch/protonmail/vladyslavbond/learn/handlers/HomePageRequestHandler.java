package ch.protonmail.vladyslavbond.learn.handlers;

import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ch.protonmail.vladyslavbond.learn.services.ExamServiceException;
import ch.protonmail.vladyslavbond.learn.services.ServiceProvider;

import java.util.List;

import ch.protonmail.vladyslavbond.learn.domain.Account;
import ch.protonmail.vladyslavbond.learn.domain.Assessment;
import ch.protonmail.vladyslavbond.learn.domain.Exam;

/*import freemarker.template.SimpleCollection;*/
/*import freemarker.template.TemplateCollectionModel;*/

class HomePageRequestHandler extends HttpServlet
{
	@Override
	public void doGet (HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		try
		{
			HttpSession session = request.getSession( );
			Account account = (Account)session.getAttribute("account");
			boolean loggedIn = (account != null);
			boolean currentlyTaskingAssessment = false;
			if (loggedIn)
			{
				Assessment assessment = (Assessment)session.getAttribute("assessment");
				currentlyTaskingAssessment = (assessment != null);
				if (!currentlyTaskingAssessment)
				{
					assessment = ServiceProvider.getAssessmentService( ).read(account.getId( ));
				}
				if (assessment != null)
				{
					session.setAttribute("assessment", assessment);
					currentlyTaskingAssessment = true;
				} else {
					currentlyTaskingAssessment = false;
				}
			}
			if (!currentlyTaskingAssessment)
			{
				List<Exam> listOfExams = ServiceProvider.getExamService( ).read( );
				request.setAttribute("exams", listOfExams);
				request.getRequestDispatcher(Page.HOME.toString( )).forward(request, response);
			} else {
				request.getRequestDispatcher(Page.ASSESSMENT_UPDATE.toString( )).forward(request, response);
			}
		} catch (Exception e) {
			throw new ServletException ("Failed to display home page.", e);
		}
	}
}