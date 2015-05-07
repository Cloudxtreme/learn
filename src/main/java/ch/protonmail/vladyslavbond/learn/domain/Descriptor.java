package ch.protonmail.vladyslavbond.learn.domain;

interface Descriptor<T>
{
	public abstract T newInstance ( );
}