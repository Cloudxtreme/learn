package ch.protonmail.vladyslavbond.learn.domain;

import freemarker.template.TemplateCollectionModel;
import freemarker.template.TemplateModelIterator;
import freemarker.template.TemplateModelException;

import java.util.Collection;

class CollectionWrapper implements TemplateCollectionModel
{
	private Collection c;

	public CollectionWrapper (Collection c)
	{
		this.c = c;
	}

	@Override
	public TemplateModelIterator iterator ( )
	throws TemplateModelException
	{
		return new IteratorWrapper(this.c.iterator( ));
	}
}