package ch.protonmail.vladyslavbond.learn.domain;

public interface Account
{
	public abstract String getName ( );

	public abstract Identificator<Account> getId ( );
}