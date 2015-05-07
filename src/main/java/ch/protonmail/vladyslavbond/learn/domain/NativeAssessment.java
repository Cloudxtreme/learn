package ch.protonmail.vladyslavbond.learn.domain;

import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

class NativeAssessment extends BusinessObject<Assessment> implements Assessment
{
	private static String CONTENT_EMPTY = "The content of the task option is missing.";
	private int currentTaskIndex = 0;
	private int quantityOfTasksCompleted = 0;
	private List<Task> tasks;
	private Map<Identificator<Task>, List<String>> answers;

	public static Assessment newInstance (AssessmentDescriptor descriptor)
	{
		return (Assessment)new NativeAssessment(descriptor);
	}

	protected NativeAssessment (Identificator<Assessment> id, List<Task> tasks)
	{
		super(id);
		this.tasks = tasks;
		this.answers = new HashMap<Identificator<Task>, List<String>> ( );
	}

	protected NativeAssessment (AssessmentDescriptor descriptor)
	{
		this(descriptor.getId( ), descriptor.getTasks( ));
	}

	@Override
	public List<Task> getTasks ( )
	{
		return this.tasks;
	}

	@Override
	public Task getTask (Identificator<Task> taskId)
	{
		for (Task task : this.tasks)
		{
			if (task.getId( ).equals(taskId))
			{
				return task;
			}
		}
		return null;
	}

	@Override
	public void answer (Identificator<Task> taskId, String answerGiven)
	{
		for (Task task : this.getTasks( ))
		{
			if (task.getId( ).equals(taskId))
			{
				task.answer(answerGiven);
				return;
			}
		}
	}

	@Override
	public void next ( )
	{
		this.currentTaskIndex++;
		if (this.currentTaskIndex >= this.tasks.size( ))
		{
			this.currentTaskIndex = 0;
		}
	}

	@Override
	public void jumpTo (Identificator<Task> taskId)
	{
		for (Task task : this.tasks)
		{
			if (task.getId( ).equals(taskId))
			{
				this.currentTaskIndex = this.tasks.indexOf(task);
				return;
			}
		}
	}

	@Override
	public float grade ( )
	{
		float earned = (float)0.0;
		for (Task task : this.getTasks( ))
		{
			earned = earned + task.grade( );
		}
		return earned / this.getMaxReward( );
	}

	private float getMaxReward ( )
	{
		float maxReward = (float)0.0;
		for (Task task : this.getTasks( ))
		{
			NativeTask nativeTask = (NativeTask)task;
			maxReward = maxReward + nativeTask.getMaxReward( );
		}
		return maxReward;
	}

	private int getTotalQuantityOfTasks ( )
	{
		return this.getTasks( ).size( );
	}

	private int getQuantityOfAnsweredTasks ( )
	{
		int q = 0;
		for (Task task : this.getTasks( ))
		{
			if (task.isAnswerGiven( ))
			{
				q++;
			}
		}
		return q;
	}

	@Override
	public TemplateModel get (String key)
	throws TemplateModelException
	{
		try
		{
			return super.get(key);
		} catch (TemplateModelException e) {
			if (key.equals("currentTask"))
			{
				if (this.currentTaskIndex < this.tasks.size( ))
				{
					return (TemplateModel)this.tasks.get(this.currentTaskIndex);
				} else {
					throw new RuntimeException ("No current task.");
				}
			}
			if (key.equals("tasks"))
			{
				return new CollectionWrapper(this.getTasks( ));
			}
			if (key.equals("totalQuantityOfTasks"))
			{
				return new NumberWrapper(this.getTotalQuantityOfTasks( ));
			}
			if (key.equals("quantityOfTasksCompleted"))
			{
				return new NumberWrapper(this.getQuantityOfAnsweredTasks( ));
			}
		}
		throw new TemplateModelException ("Unknown field: " + key);
	}
}