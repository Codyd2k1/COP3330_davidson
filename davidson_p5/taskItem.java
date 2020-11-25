import java.util.InvalidPropertiesFormatException;
import java.util.prefs.InvalidPreferencesFormatException;
import java.util.zip.DataFormatException;

public class taskItem {
    String title;
    String description;
    String dueDate;

    public taskItem(String TaskTitle, String taskDescription, String DueDate)
    {
        String[] Date = DueDate.split("-");
        int DateYear = Integer.parseInt(Date[0]);
        int DateMonth = Integer.parseInt(Date[1]);
        int DateDay = Integer.parseInt(Date[2]);
        if(DateYear < 2020 || DateMonth > 12||DateMonth < 1 || DateDay < 1||DateDay > 31|| TaskTitle.equals(""))
        {
            throw new IllegalArgumentException();
        }
        else if(DateYear == 2020 && DateMonth < 12 && DateDay < 3)
        {
            throw new IllegalArgumentException();
        }
        else {
            title = TaskTitle;
            description = taskDescription;
            dueDate = DueDate;
        }
    }
    public void editTitle(String newTitle)
    {
        if(newTitle.equals(""))
        {
            throw new IllegalArgumentException();
        }
        else{
            title = newTitle;
        }
    }
    public void editDescription(String newDescription)
    {
        description = newDescription;
    }
    public void editDueDate(String newDueDate)
    {
        if(!newDueDate.matches("\\d\\d\\d\\d-\\d\\d-\\d\\d"))
        {
           throw new IllegalArgumentException();
        }
        else
        {
            String[] Date = newDueDate.split("-");
            int DateYear = Integer.parseInt(Date[0]);
            int DateMonth = Integer.parseInt(Date[1]);
            int DateDay = Integer.parseInt(Date[2]);
            if(DateYear < 2020 || DateMonth > 12||DateMonth < 1 || DateDay < 1||DateDay > 31)
            {
                throw new IllegalArgumentException();
            }
            else if(DateYear == 2020 && DateMonth < 12 && DateDay < 3)
            {
                throw new IllegalArgumentException();
            }
            else {
                dueDate = newDueDate;
            }
        }
    }

    public String toString()
    {
        String taskString = "["+dueDate+"] "+ title+": " + description;
        return taskString;
    }
    public void markCompleted()
    {
        description = description + "     *TASK COMPLETED*";
        return;
    }
    public void unMarkCompleted()
    {
        description = description.replace("     *TASK COMPLETED*", "");
        return;
    }
    public String getTitle()
    {
        return title;
    }
    public String getDescription()
    {
        return description;
    }
    public String getDueDate()
    {
        return dueDate;
    }
    public String getDueDateYear()
    {
        String[] Date = dueDate.split("-");
        String dueDateYear = Date[0];
        return dueDateYear;
    }
    public String getDueDateMonth()
    {
        String[] Date = dueDate.split("-");
        String dueDateMonth= Date[1];
        return dueDateMonth;
    }
    public String getDueDateDay()
    {
        String[] Date = dueDate.split("-");
        String dueDateDay = Date[2];
        return dueDateDay;
    }
}
