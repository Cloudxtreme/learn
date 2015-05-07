package ch.protonmail.vladyslavbond.learn.domain;

import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

class NativeTaskSelectionSingle extends NativeTask
{
	private TaskOption storedAnswer = null;
	private TaskOption correctAnswer = null;

	public static Task newInstance (TaskDescriptor descriptor)
	{
		return (Task)new NativeTaskSelectionSingle (descriptor);
	}

	protected NativeTaskSelectionSingle (TaskDescriptor descriptor)
	{
		super(descriptor);
		this.correctAnswer = this.getTaskOptions( ).get(0);
		for (TaskOption taskOption : this.getTaskOptions( ))
		{
			if (taskOption.getReward( ) > this.correctAnswer.getReward( ))
			{
				this.correctAnswer = taskOption;
			}
		}
	}

	@Override
	public void answer (String givenAnswer)
	{
		Identificator<TaskOption> taskOptoinId = new Identificator<TaskOption> (givenAnswer);
		for (TaskOption taskOption : this.getTaskOptions( ))
		{
			if (taskOption.getId( ).equals(taskOptoinId))
			{
				this.storedAnswer = taskOption;
				return;
			}
		}
	}

	@Override
	public float getMaxReward ( )
	{
		return this.correctAnswer.getReward( );
	}

	@Override
	public boolean isAnswerGiven ( )
	{
		return (this.storedAnswer != null);
	}

	@Override
	public Object getAnswer ( )
	{
		return this.storedAnswer;
	}

	@Override
	public float grade ( )
	{
		if (this.storedAnswer != null)
		{
			if (this.correctAnswer.getReward( ) != 0)
			{
				return this.storedAnswer.getReward( ) / this.correctAnswer.getReward( );
			} else {
				return this.storedAnswer.getReward( );
			}
		} else {
			return 0;
		}
	}

	@Override
	public TaskCategory getTaskCategory ( )
	{
		return TaskCategory.TaskSelectionSingle;
	}

	@Override
	public TemplateModel get (String key)
	throws TemplateModelException
	{
		try
		{
			return super.get(key);
		} catch (TemplateModelException e) {
			if (key.equals("answer"))
			{
				return (TemplateModel)this.getAnswer( );
			}
		}
		throw new TemplateModelException ("Unknown field: " + key);
	}
}