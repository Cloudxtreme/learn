package ch.protonmail.vladyslavbond.learn.datasource;

import ch.protonmail.vladyslavbond.learn.domain.Account;

public interface AccountDataAccess
{
	public abstract Account logIn (String username, String password) throws DataAccessException;

	public abstract Boolean signUp (String username, String password) throws DataAccessException;
}