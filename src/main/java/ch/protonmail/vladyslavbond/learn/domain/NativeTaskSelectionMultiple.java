package ch.protonmail.vladyslavbond.learn.domain;

import java.util.Set;
import java.util.HashSet;

import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

class NativeTaskSelectionMultiple extends NativeTask
{
	private Set<TaskOption> storedAnswer;

	public static Task newInstance (TaskDescriptor descriptor)
	{
		return (Task)new NativeTaskSelectionMultiple (descriptor);
	}

	protected NativeTaskSelectionMultiple (TaskDescriptor descriptor)
	{
		super(descriptor);
		this.storedAnswer = new HashSet<TaskOption> ( );
	}

	@Override
	public void answer (String givenAnswer)
	{
		Identificator<TaskOption> taskOptoinId = new Identificator<TaskOption> (givenAnswer);
		for (TaskOption taskOption : this.getTaskOptions( ))
		{
			if (taskOption.getId( ).equals(taskOptoinId))
			{
				this.storedAnswer.add(taskOption);
				return;
			}
		}
	}

	@Override
	public float grade ( )
	{
		float earned = (float)0.0;
		for (TaskOption storedTaskOption : this.storedAnswer)
		{
			earned = earned + storedTaskOption.getReward( );
		}
		if (this.getMaxReward( ) != 0)
		{
			return earned / this.getMaxReward( );
		} else {
			return earned;
		}
	}

	@Override
	public float getMaxReward ( )
	{
		float gradeMax = (float)0.0;
		for (TaskOption taskOption : this.getTaskOptions( ))
		{
			float taskOptionReward = taskOption.getReward( );
			if (taskOptionReward > 0)
			{
				gradeMax = gradeMax + taskOptionReward;
			}
		}
		return gradeMax;
	}

	@Override
	public boolean isAnswerGiven ( )
	{
		return !this.storedAnswer.isEmpty( );
	}

	@Override
	public Object getAnswer ( )
	{
		return this.storedAnswer;
	}

	@Override
	public TaskCategory getTaskCategory ( )
	{
		return TaskCategory.TaskSelectionMultiple;
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
				return new CollectionWrapper((Set<TaskOption>)this.getAnswer( ));
			}
		}
		throw new TemplateModelException ("Unknown field: " + key);
	}
}