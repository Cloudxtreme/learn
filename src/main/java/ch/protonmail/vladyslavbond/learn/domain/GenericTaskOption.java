package ch.protonmail.vladyslavbond.learn.domain;

interface GenericTaskOption<T>
{
	public abstract T getContent ( );
}