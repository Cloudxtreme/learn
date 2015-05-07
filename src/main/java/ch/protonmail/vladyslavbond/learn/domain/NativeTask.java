package ch.protonmail.vladyslavbond.learn.domain;

import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

import java.util.List;

abstract class NativeTask extends BusinessObject<Task> implements Task
{
	private static String DESCRIPTION_EMPTY = "The description of the task is missing.";

	private String description;
	private List<TaskOption> taskOptions;

	public static Task newInstance (TaskDescriptor descriptor)
	{
		switch (descriptor.getTaskCategory( ))
		{
			case TaskSelectionMultiple:
				return NativeTaskSelectionMultiple.newInstance(descriptor);

			case TaskSelectionSingle:
				return NativeTaskSelectionSingle.newInstance(descriptor);

			default:
				throw new RuntimeException ("Uknown task category: " + descriptor.getTaskCategory( ).getTitle( ));
		}
	}

	protected NativeTask (Identificator<Task> id, String description, List<TaskOption> taskOptions)
	{
		super(id);
		if (description != null && !description.isEmpty( ))
		{
			this.description = description;
		} else {
			this.description = NativeTask.DESCRIPTION_EMPTY;
		}
		this.taskOptions = taskOptions;
	}

	protected NativeTask (TaskDescriptor descriptor)
	{
		this(descriptor.getId( ), descriptor.getDescription( ), descriptor.getTaskOptions( ));
	}

	@Override
	public String getDescription ( )
	{
		return this.description;
	}

	@Override
	public TaskOption getTaskOption (Identificator<TaskOption> taskOptionId)
	{
		for (TaskOption taskOption : this.taskOptions)
		{
			if (taskOption.getId( ).equals(taskOptionId))
			{
				return taskOption;
			}
		}
		return null;
	}

	@Override
	public List<TaskOption> getTaskOptions ( )
	{
		return this.taskOptions;
	}

	public abstract float getMaxReward ( );

	@Override
	public TemplateModel get (String key)
	throws TemplateModelException
	{
		try
		{
			return super.get(key);
		} catch (TemplateModelException e) {
			if (key.equals("options"))
			{
				return new CollectionWrapper(this.taskOptions);
			}
			if (key.equals("description"))
			{
				return new StringWrapper(this.description);
			}
			if (key.equals("taskCategory"))
			{
				return new StringWrapper(this.getTaskCategory( ));
			}
		}
		throw new TemplateModelException ("Unknown field: " + key);
	}
}