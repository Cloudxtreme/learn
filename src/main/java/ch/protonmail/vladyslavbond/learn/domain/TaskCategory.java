package ch.protonmail.vladyslavbond.learn.domain;

public enum TaskCategory
{
      TaskSelectionMultiple (new Identificator<TaskCategory> (1), "Multiple selection task")
    , TaskSelectionSingle (new Identificator<TaskCategory> (2), "Single selection task")
    , TaskProvideText (new Identificator<TaskCategory> (3), "Provide text task")
    ;

    public static TaskCategory getTaskCategory (Identificator<TaskCategory> id)
    {
        for (TaskCategory taskCategory : TaskCategory.values( ))
        {
            if (taskCategory.getId( ).equals(id))
            {
                return taskCategory;
            }
        }
        return null;
    }

    private Identificator<TaskCategory> id;
    private String title;

    private TaskCategory (Identificator<TaskCategory> id, String title)
    {
        this.id = id;
        this.title = title;
    }

    public Identificator<TaskCategory> getId ( )
    {
    	return this.id;
    }

    public String getTitle ( )
    {
        return this.title;
    }
}