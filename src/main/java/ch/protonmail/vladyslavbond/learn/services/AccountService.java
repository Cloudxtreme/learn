package ch.protonmail.vladyslavbond.learn.services;

import ch.protonmail.vladyslavbond.learn.domain.Account;

public interface AccountService
{
	public abstract Account logIn (String username, String password) throws AccountServiceException;

	public abstract Boolean signUp (String username, String password) throws AccountServiceException;
}