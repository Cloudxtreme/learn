package ch.protonmail.vladyslavbond.learn.domain;

import freemarker.template.TemplateCollectionModel;
import freemarker.template.TemplateModelIterator;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

import java.util.Iterator;

class IteratorWrapper implements TemplateModelIterator
{
	private Iterator i;

	public IteratorWrapper (Iterator i)
	{
		this.i = i;
	}

	@Override
	public boolean hasNext ( )
	throws TemplateModelException
	{
		return this.i.hasNext( );
	}

	@Override
	public TemplateModel next ( )
	throws TemplateModelException
	{
		return (TemplateModel)this.i.next( );
	}
}