package ch.protonmail.vladyslavbond.learn.domain;

public interface AccountDescriptor extends BusinessObjectDescriptor<Account>
{
	public abstract String getName ( );

	public abstract String getPasswordHash ( );
}