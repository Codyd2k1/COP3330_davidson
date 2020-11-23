import java.text.SimpleDateFormat;
import java.util.Date;


public class taskItem{

        String title;
        String description;
        String dueDate;

        //constructor for task item:
        public taskItem(String TaskTitle, String taskDescription, String DueDate)
        {
            title = TaskTitle;
            description = taskDescription;
            dueDate = DueDate;
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
