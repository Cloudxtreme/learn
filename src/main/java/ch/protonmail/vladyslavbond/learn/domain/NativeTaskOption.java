package ch.protonmail.vladyslavbond.learn.domain;

import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

class NativeTaskOption extends BusinessObject<TaskOption> implements TaskOption
{
	private static String CONTENT_EMPTY = "The content of the task option is missing.";
	private Integer reward;
	private String content;

	public static TaskOption newInstance (TaskOptionDescriptor descriptor)
	{
		return (TaskOption)new NativeTaskOption(descriptor);
	}

	protected NativeTaskOption (Identificator<TaskOption>id, String content, Integer reward)
	{
		super(id);
		if (content != null && !content.isEmpty( ))
		{
			this.content = content;
		} else {
			this.content = NativeTaskOption.CONTENT_EMPTY;
		}
		this.reward = reward;
	}

	protected NativeTaskOption (TaskOptionDescriptor descriptor)
	{
		this(descriptor.getId( ), descriptor.getContent( ), descriptor.getReward( ));
	}

	@Override
	public String getContent ( )
	{
		return this.content;
	}

	@Override
	public Integer getReward ( )
	{
		return this.reward;
	}

	@Override
	public TemplateModel get (String key)
	throws TemplateModelException
	{
		try
		{
			return super.get(key);
		} catch (TemplateModelException e) {
			if (key.equals("content"))
			{
				return new StringWrapper(this.content);
			}
			if (key.equals("reward"))
			{
				return new NumberWrapper(this.reward);
			}
		}
		throw new TemplateModelException ("Unknown field: " + key);
	}
}