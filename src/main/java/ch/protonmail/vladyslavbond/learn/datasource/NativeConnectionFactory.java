package ch.protonmail.vladyslavbond.learn.datasource;

import org.mindrot.BCrypt;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.DriverManager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

enum NativeConnectionFactory
{
    INSTANCE ( );

    private String dbURL;
    private String dbUser;
    private String dbPassword;
    /*private String dbDefaultSchema;*/

    private NativeConnectionFactory ( )
    {
        Context initialContext     = null;
        Context environmentContext = null;

        try
        {
            initialContext     = new InitialContext( );
            environmentContext = (Context)initialContext.lookup("java:comp/env");

            this.dbURL      = (String)environmentContext.lookup("dbURL");
            this.dbUser     = (String)environmentContext.lookup("dbUser");
            this.dbPassword = (String)environmentContext.lookup("dbPassword");
            /*this.dbDefaultSchema = (String)environmentContext.lookup("dbDefaultSchema");*/
        } catch (NamingException e) {
            throw new AssertionError("Failed to lookup for database credentials.", e);
        }
    }

    public Connection getConnection ( )
    throws SQLException
    {
            return DriverManager.getConnection(this.dbURL, this.dbUser, this.dbPassword);
    }
}