package ch.protonmail.vladyslavbond.learn.domain;

import freemarker.template.TemplateNumberModel;
import freemarker.template.TemplateModelException;

class NumberWrapper implements TemplateNumberModel
{
	private Number n;

	public NumberWrapper (Number n)
	{
		this.n = n;
	}

	@Override
	public Number getAsNumber ( )
	throws TemplateModelException
	{
		return this.n;
	}
}