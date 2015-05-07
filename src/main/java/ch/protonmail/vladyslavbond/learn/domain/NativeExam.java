package ch.protonmail.vladyslavbond.learn.domain;

import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

class NativeExam extends BusinessObject<Exam> implements Exam
{
	private String title;

	public NativeExam (Identificator<Exam> id, String title)
	{
		super(id);
		this.title = title;
	}

	public NativeExam (ExamDescriptor examDescriptor)
	{
		this(examDescriptor.getId( ), examDescriptor.getTitle( ));
	}

	@Override
	public String getTitle ( )
	{
		return this.title;
	}

	@Override
	public TemplateModel get (String key)
	throws TemplateModelException
	{
		try
		{
			return super.get(key);
		} catch (TemplateModelException e) {
			if (key.equals("title"))
			{
				return new StringWrapper(title);
			}
		}
		throw new TemplateModelException ("Unknown field: " + key);
	}
}