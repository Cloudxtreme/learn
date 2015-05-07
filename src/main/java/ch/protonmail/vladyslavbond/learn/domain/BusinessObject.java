package ch.protonmail.vladyslavbond.learn.domain;

import freemarker.template.TemplateModel;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModelException;

import java.lang.Comparable;

abstract class BusinessObject<K> extends Object implements Comparable, TemplateHashModel
{
	protected Identificator<K> id;

	protected BusinessObject ( )
	{
		throw new AssertionError ("Bussiness object has to possess an identificator.");
	}

	protected BusinessObject (Identificator<K> id)
	{
		this.id = id;
		if (this.id == null)
		{
			throw new AssertionError ("No id given.");
		}
	}

	public Identificator<K> getId ( )
	{
		return this.id;
	}

	@Override
	public int hashCode ( )
	{
		return this.id.hashCode( );
	}

	@Override
	public boolean equals (Object objectToCheckEqualityTo)
	{
		try
		{
			BusinessObject<K> businessObjectCheckEqualityTo = (BusinessObject<K>)objectToCheckEqualityTo;
			return this.id.equals(businessObjectCheckEqualityTo.getId( ));
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public int compareTo (Object objectToCompareTo)
	{
		try
		{
			BusinessObject<K> businessObjectCompareTo = (BusinessObject<K>)objectToCompareTo;
			return this.id.compareTo(businessObjectCompareTo.getId( ));
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public TemplateModel get (String key)
	throws TemplateModelException
	{
		if (key.equals("id"))
		{
			return this.getId( );
		}
		throw new TemplateModelException ("Unknown field: " + key);
	}

	@Override
	public boolean isEmpty ( )
	throws TemplateModelException
	{
		return (this.id == null);
	}
}