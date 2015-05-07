package ch.protonmail.vladyslavbond.learn.handlers;

import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ch.protonmail.vladyslavbond.learn.domain.Account;

class TerminateSessionRequestHandler extends HttpServlet
{
	@Override
	public void doPost (HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		request.getSession( ).invalidate( );
		request.getRequestDispatcher(Page.SESSION_CREATE.toString( )).forward(request, response);
	}

	@Override
	public void doGet (HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		boolean loggedIn = ((Account)request.getSession( ).getAttribute("account") != null);
		if (loggedIn)
		{
			request.getRequestDispatcher(Page.SESSION_DESTROY.toString( )).forward(request, response);
		} else {
			request.getRequestDispatcher(Page.SESSION_CREATE.toString( )).forward(request, response);
		}
	}
}