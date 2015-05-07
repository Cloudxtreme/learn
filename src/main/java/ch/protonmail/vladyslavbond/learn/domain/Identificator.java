package ch.protonmail.vladyslavbond.learn.domain;

import java.lang.Comparable;

import freemarker.template.TemplateNumberModel;
import freemarker.template.TemplateModelException;

public class Identificator<T> extends Number implements Comparable, TemplateNumberModel
{
	private Integer identificatorValue;

	public Identificator
	(
		Integer identificatorValue
	)
	{
		this.identificatorValue = identificatorValue;
	}

	public Identificator
	(
		int identificatorValue
	)
	{
		this(Integer.valueOf(identificatorValue));
	}

	public Identificator
	(
		String identificatorValue
	)
	{
		this(Integer.valueOf(identificatorValue));
	}

	@Override
	public int intValue ( )
	{
		return this.identificatorValue.intValue( );
	}

	@Override
	public double doubleValue ( )
	{
		return this.identificatorValue.doubleValue( );
	}

	@Override
	public float floatValue ( )
	{
		return this.identificatorValue.floatValue( );
	}

	@Override
	public long longValue ( )
	{
		return this.identificatorValue.longValue( );
	}

	@Override
	public short shortValue ( )
	{
		return this.identificatorValue.shortValue( );
	}

	@Override
	public byte byteValue ( )
	{
		return this.identificatorValue.byteValue( );
	}

	@Override
	public String toString ( )
	{
		return this.identificatorValue.toString( );
	}

	@Override
	public int hashCode ( )
	{
		return this.identificatorValue.hashCode( );
	}

	@Override
	public boolean equals (Object objectToCheckEqualityTo)
	{
		try
		{
			Identificator<T> identificatorToCheckEqualityTo = (Identificator<T>)objectToCheckEqualityTo;
			return this.identificatorValue.equals(identificatorToCheckEqualityTo.intValue( ));
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public int compareTo (Object objectToCompareTo)
	{
		try
		{
			Identificator<T> identificatorToCompareTo = (Identificator<T>)objectToCompareTo;
			return this.identificatorValue.compareTo(identificatorToCompareTo.intValue( ));
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public Number getAsNumber ( )
	throws TemplateModelException
	{
		return this.identificatorValue;
	}
}