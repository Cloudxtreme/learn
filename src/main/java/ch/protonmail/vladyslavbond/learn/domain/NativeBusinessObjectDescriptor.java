package ch.protonmail.vladyslavbond.learn.domain;

import java.sql.ResultSet;

abstract class NativeBusinessObjectDescriptor<T> extends NativeDescriptor<T>
{
	protected Identificator<T> id;

	public Identificator<T> getId( )
	{
		if (this.id != null)
		{
			return this.id;
		} else {
			throw new AssertionError ("No id fetched.");
		}
	}
}