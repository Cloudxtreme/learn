package ch.protonmail.vladyslavbond.learn.domain;

import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

class NativeAccount extends BusinessObject<Account> implements Account
{
	private String name;

	public NativeAccount (Identificator<Account> id, String name)
	{
		super(id);
		this.name = name;
	}

	public NativeAccount (AccountDescriptor accountDescriptor)
	{
		this(accountDescriptor.getId( ), accountDescriptor.getName( ));
	}

	@Override
	public String getName ( )
	{
		return this.name;
	}
}