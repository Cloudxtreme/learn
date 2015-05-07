package ch.protonmail.vladyslavbond.learn.datasource;

import org.mindrot.BCrypt;

import ch.protonmail.vladyslavbond.learn.domain.Account;
import ch.protonmail.vladyslavbond.learn.domain.DescriptorFactory;
import ch.protonmail.vladyslavbond.learn.domain.AccountDescriptor;

import java.sql.ResultSet;
import java.sql.SQLException;

enum NativeAccountDataAccess implements AccountDataAccess
{
	INSTANCE ( );

	private NativeAccountDataAccess ( )
	{

	}

	@Override
	public Account logIn (String username, String password)
	throws DataAccessException
	{
		try
		{
			ResultSet resultSet = Query.newInstance("SELECT * FROM account_read(?::VARCHAR(100));")
				.setString(username)
				.executeQuery( );
			while (resultSet.next( ))
			{
				AccountDescriptor accountDescriptor = DescriptorFactory.newAccountDescriptor(resultSet);
				String hash = accountDescriptor.getPasswordHash( );
				if (BCrypt.checkpw(password, hash))
				{
					return (Account)accountDescriptor.newInstance( );
				} else {
					throw new DataAccessException ("Wrong password.");
				}
			}
			return null;
		} catch (SQLException e) {
			throw new DataAccessException (e);
		}
	}

	@Override
	public Boolean signUp (String username, String password)
	throws DataAccessException
	{
		try
		{
			password = BCrypt.hashpw(password, BCrypt.gensalt( ));
			ResultSet resultSet = Query.newInstance("SELECT * FROM account_create (?::VARCHAR(100), ?::TEXT);")
				.setString(username)
				.setString(password)
				.executeQuery( );
			while (resultSet.next( ))
			{
				return resultSet.getBoolean(1);
			}
			return false;
		} catch (SQLException e) {
			throw new DataAccessException (e);
		}
	}
}