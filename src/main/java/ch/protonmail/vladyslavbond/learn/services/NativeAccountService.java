package ch.protonmail.vladyslavbond.learn.services;

import ch.protonmail.vladyslavbond.learn.datasource.AccountDataAccess;
import ch.protonmail.vladyslavbond.learn.datasource.DataAccessProvider;

import ch.protonmail.vladyslavbond.learn.domain.Account;

enum NativeAccountService implements AccountService
{
	INSTANCE ( );

	private NativeAccountService ( )
	{

	}

	private AccountDataAccess getAccountDataAccess ( )
	{
		return DataAccessProvider.getAccountDataAccess( );
	}

	@Override
	public Account logIn (String username, String password)
	throws AccountServiceException
	{
		try
		{
			return this.getAccountDataAccess( ).logIn(username, password);
		} catch (Exception e) {
			throw new AccountServiceException ("Failed to log in.", e);
		}
	}

	@Override
	public Boolean signUp (String username, String password)
	throws AccountServiceException
	{
		try
		{
			return this.getAccountDataAccess( ).signUp(username, password);
		} catch (Exception e) {
			throw new AccountServiceException ("Failed to sign up.", e);
		}
	}
}