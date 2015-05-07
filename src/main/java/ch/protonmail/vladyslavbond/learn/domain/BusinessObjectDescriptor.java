package ch.protonmail.vladyslavbond.learn.domain;

interface BusinessObjectDescriptor<T> extends Descriptor<T>
{
	public abstract Identificator<T> getId ( );
}