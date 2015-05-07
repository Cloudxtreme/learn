package ch.protonmail.vladyslavbond.learn.handlers;

import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ch.protonmail.vladyslavbond.learn.domain.Account;

import ch.protonmail.vladyslavbond.learn.services.ServiceProvider;

class LogInRequestHandler extends HttpServlet
{
	@Override
	public void doPost (HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		HttpSession session = null;
		Account account = null;
		String username = null, password = null;
		
		try
		{
			username = (String)request.getParameter("username");
			password = (String)request.getParameter("password");
			account = ServiceProvider.getAccountService( ).logIn(username, password);
			if (account != null)
			{
				session = request.getSession( );
				session.setAttribute("account", account);
				request.getRequestDispatcher(Page.SESSION_DESTROY.toString( )).forward(request, response);
			} else {
				request.getRequestDispatcher(Page.ACCOUNT_CREATE.toString( )).forward(request, response);
			}
		} catch (Exception e) {
			throw new ServletException ("Failed to process a request to log in.", e);
		}
	}

	@Override
	public void doGet (HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		boolean loggedIn = ((Account)request.getSession( ).getAttribute("account") != null);
		if (!loggedIn)
		{
			request.getRequestDispatcher(Page.SESSION_CREATE.toString( )).forward(request, response);
		} else {
			request.getRequestDispatcher(Page.SESSION_DESTROY.toString( )).forward(request, response);
		}
	}
}