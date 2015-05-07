package ch.protonmail.vladyslavbond.learn.domain;

import freemarker.template.TemplateScalarModel;
import freemarker.template.TemplateModelException;

class StringWrapper implements TemplateScalarModel
{
	private String s;

	public StringWrapper (String s)
	{
		this.s = s;
	}

	public StringWrapper (Object o)
	{
		this(o.toString( ));
	}

	@Override
	public String getAsString ( )
	throws TemplateModelException
	{
		return this.s;
	}
}