package ch.protonmail.vladyslavbond.learn.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

class NativeAccountDescriptor extends NativeBusinessObjectDescriptor<Account> implements AccountDescriptor
{
	private String name;
	private String hash;

	public NativeAccountDescriptor (ResultSet resultSet)
	throws SQLException
	{
		this.id = new Identificator<Account> (resultSet.getInt(1));
		this.name = resultSet.getString(2);
		this.hash = resultSet.getString(3);
	}

	@Override
	public String getName ( )
	{
		return this.name;
	}

	@Override
	public String getPasswordHash ( )
	{
		return this.hash;
	}

	@Override
	public Account newInstance ( )
	{
		return (Account)new NativeAccount(this);
	}
}