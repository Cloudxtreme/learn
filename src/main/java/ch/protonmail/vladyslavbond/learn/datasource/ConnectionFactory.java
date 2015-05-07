package ch.protonmail.vladyslavbond.learn.datasource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory
{
    private ConnectionFactory ( )
    {
        throw new AssertionError ( );
    }

    public static Connection getConnection ( )
    throws SQLException
    {
        return NativeConnectionFactory.INSTANCE.getConnection( );
    }
}