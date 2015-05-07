package ch.protonmail.vladyslavbond.learn.datasource;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import ch.protonmail.vladyslavbond.learn.domain.Identificator;

class Query
{
	public static Query newInstance (String sql)
	throws SQLException
	{
		return new Query(ConnectionFactory.getConnection( ).prepareCall(sql));
	}

	private CallableStatement callableStatement       = null;
	private int               indexOfTheLastParameter = 0;

	private Query (CallableStatement callableStatement)
	throws SQLException
	{
		this.callableStatement = callableStatement;
	}

	public ResultSet executeQuery ( )
	throws SQLException
	{
		try
		{
			ResultSet resultSet = this.callableStatement.executeQuery( );
			return resultSet;
		} catch (SQLException e) {
			throw e;
		} finally {
			this.callableStatement.getConnection( ).close( );
		}
	}

	public Query setInt (int value)
	throws SQLException
	{
		return this.setInt(this.indexOfTheLastParameter + 1, value);
	}

	public Query setInt (int index, int value)
	throws SQLException
	{
		this.callableStatement.setInt(index, value);
		this.indexOfTheLastParameter = index;
		return this;
	}
	
	public Query setInt (Identificator value) throws SQLException
	{
		return this.setInt(value.intValue( ));
	}

	public Query setString (String value)
	throws SQLException
	{
		return this.setString(this.indexOfTheLastParameter + 1, value);
	}

	public Query setString (int index, String value)
	throws SQLException
	{
		this.callableStatement.setString(index, value);
		this.indexOfTheLastParameter = index;
		return this;
	}
}