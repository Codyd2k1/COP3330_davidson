import java.io.*;
import java.nio.InvalidMarkException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class taskList implements genericMethodsInterface{
    List<taskItem> taskList = new ArrayList<taskItem>();
    private int numItemsMarkedComplete = 0;


    public void removeAllExternal()
    {
        taskList.removeAll((ArrayList)taskList);
    }
    public String viewList()
    {
        String printItems = viewList((ArrayList) taskList);
        return printItems;
    }


    public void addItem(String Date, String Title, String Description)
    {
        taskItem t = new taskItem(Title, Description, Date);
        taskList.add(t);
    }

    public int getSize()
    {
        return taskList.size();
    }


    //task GET functions
    public String getItemDescription(int itemNum)
    {
        checkIndex((ArrayList)taskList,itemNum);
        return taskList.get(itemNum).getDescription();
    }
    public String getItemDueDate(int itemNum)
    {
        checkIndex((ArrayList)taskList,itemNum);
        return taskList.get(itemNum).getDueDate();
    }
    public String getItemTitle(int itemNum)
    {
        checkIndex((ArrayList)taskList,itemNum);
        return taskList.get(itemNum).getTitle();
    }


    public void editItem(int itemNum, String Date, String Title, String Description)
    {
        taskItem t = new taskItem(Title, Description, Date);
        taskList.set(itemNum,t);
    }
    public void editItemTitle(int itemNum, String newTitle)
    {
        checkIndex((ArrayList)taskList,itemNum);;
        if(newTitle.equals(""))
        {
            throw new IllegalArgumentException();
        }
        else
        {
            taskList.get(itemNum).editTitle(newTitle);
        }
    }

    public void editItemDueDate(int itemNum, String newDueDate) {
            checkIndex((ArrayList)taskList,itemNum);

            if (!newDueDate.matches("\\d\\d\\d\\d-\\d\\d-\\d\\d")) {
                throw new IllegalArgumentException();
            } else {
                String[] Date = newDueDate.split("-");
                int DateYear = Integer.parseInt(Date[0]);
                int DateMonth = Integer.parseInt(Date[1]);
                int DateDay = Integer.parseInt(Date[2]);
                if (DateYear < 2020 || DateMonth > 12 || DateMonth < 1 || DateDay < 1 || DateDay > 31) {
                    throw new IllegalArgumentException();
                } else if (DateYear == 2020 && DateMonth < 12 && DateDay < 3) {
                    throw new IllegalArgumentException();
                } else {
                    taskList.get(itemNum).editDueDate(newDueDate);
                }
            }

    }
    public void editTaskDescription(int itemNum, String newDescription)
    {
        checkIndex((ArrayList)taskList,itemNum);
        taskList.get(itemNum).editDescription(newDescription);
    }

    public void removeItem(int itemNum)
    {
        checkIndex((ArrayList)taskList,itemNum);
        taskList.remove(itemNum);

    }


    public String markItemCompleted(int itemNum)
    {
        checkIndex((ArrayList)taskList,itemNum);
        if(taskList.get(itemNum).getDescription().contains("     *TASK COMPLETED*"))
        {
            String returnStatement = ("Sorry, Item is already Marked Complete.");
            return returnStatement;
        }
        else{
            taskList.get(itemNum).markCompleted();
            String returnStatement = ("Item Marked Completed.");
            return returnStatement;
        }
    }

    public int getNumItemsMarkedComplete()
    {
        int numCompleted = 0;
        for(int i = 0; i < taskList.size(); i++)
        {
            String description = taskList.get(i).getDescription();
            if(description.contains("     *TASK COMPLETED*"))
            {
                numCompleted++;
            }
        }
        return numCompleted;
    }

    public String unMarkItemComplete(int itemNum)
    {
        checkIndex((ArrayList)taskList,itemNum);
        if(getNumItemsMarkedComplete() == 0)
        {
            String returnStatement =("Sorry, no items are marked Completed Currently.");
            return returnStatement;
        }
        else if(!(taskList.get(itemNum).getDescription().contains("     *TASK COMPLETED*")))
        {
            String returnStatement  = ("This task is not currently marked completed, sorry.");
            throw new InvalidMarkException();
        }
        else
        {
            taskList.get(itemNum).unMarkCompleted();
            String returnStatement =("Item unmarked Completed.");
            return returnStatement;
        }

    }

    public void loadTaskList(String taskListName)
    {
        if(taskList.size() != 0)
        {
            removeAll((ArrayList) taskList);
        }
        Scanner s;
        File f;


        try {
            f = new File(taskListName);
            s = new Scanner(f);
            int numLinesInFile = countNumLinesInLoadFile(f);
            for(int i = 0; i < numLinesInFile/5; i++)
            {
                String DateYear = s.nextLine();
                String DateMonth = s.nextLine();
                String DateDay = s.nextLine();
                String TaskTitle = s.nextLine();
                String TaskDescription = s.nextLine();
                String Date = DateYear + "-" + DateMonth + "-" + DateDay;
                taskItem t = new taskItem(TaskTitle,TaskDescription,Date);
                taskList.add(t);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }


    }

    public void saveTaskList(String fileName)
    {
            File savedList = new File(fileName);
            FileWriter f = null;
            try {
                savedList.createNewFile();
                f = new FileWriter(savedList);
            } catch (IOException e) {
                e.printStackTrace();
            }
            for(int i = 0; i < taskList.size(); i++)
            {
                try {
                    f.write(taskList.get(i).getDueDateYear()+"\r\n");
                    f.write(taskList.get(i).getDueDateMonth()+"\r\n");
                    f.write(taskList.get(i).getDueDateDay()+"\r\n");
                    f.write(taskList.get(i).getTitle()+"\r\n");
                    f.write(taskList.get(i).getDescription()+"\r\n");
                    f.flush();
                }catch (IOException e)
                {
                    return;
                }
            }


    }


}
