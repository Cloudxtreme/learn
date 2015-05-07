package ch.protonmail.vladyslavbond.learn.domain;

import java.sql.ResultSet;

abstract class NativeDescriptor<T>
{
	public abstract T newInstance( );
}