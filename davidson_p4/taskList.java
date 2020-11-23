import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class taskList {
    List<taskItem> taskList = new ArrayList<taskItem>();
    private int numItemsMarkedComplete = 0;



    public void viewList()
    {
        //formatter f = new formatter();
        if(taskList.size() == 0)
        {
            System.out.println("Sorry, No Tasks to show.");
        }
        else {
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(i + ")" + taskList.get(i).toString());
            }
        }
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

    public void editItem(int itemNum, String Date, String Title, String Description)
    {
        taskItem t = new taskItem(Title, Description, Date);
        taskList.set(itemNum,t);
    }

    public void removeItem(int itemNum)
    {
        taskList.remove(itemNum);
        System.out.println("Item Successfully Removed.");
    }

    public void markItemCompleted(int itemNum)
    {
        if(itemNum > taskList.size()-1||itemNum < 0)
        {
            System.out.println("Sorry, this item does not exist.");
            return;
        }
        else if(taskList.get(itemNum).getDescription().contains("     *TASK COMPLETED*"))
        {
            System.out.println("Sorry, Item is already Marked Complete.");
            return;
        }
        else{
            taskList.get(itemNum).markCompleted();
            System.out.println("Item Marked Completed.");
            return;
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

    public void unMarkItemComplete(int itemNum)
    {
        if(itemNum > taskList.size()-1||itemNum < 0)
        {
            System.out.println("Sorry, this item does not exist.");
            return;
        }
        else if(getNumItemsMarkedComplete() == 0)
        {
            System.out.println("Sorry, no items are marked Completed Currently.");
        }
        else
        {
            taskList.get(itemNum).unMarkCompleted();
            System.out.println("Item unmarked Completed.");
        }

    }

    public int countNumLinesInLoadFile(File loadFile)
    {
        try {
            FileInputStream f = new FileInputStream(loadFile);
            byte[] byteA = new byte[(int) loadFile.length()];
            f.read(byteA);
            String info = new String(byteA);
            String[] infoArray = info.split("\r\n");
            return infoArray.length;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public void loadTaskList(String taskListName)
    {
        Scanner s;
        File f;
        BufferedReader reader;

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
            System.out.println("Sorry, File could not be found in project directory.");
            e.printStackTrace();
            return;
        }

        System.out.println("Task List Loaded");
    }

    public void saveTaskList(String fileName)
    {
        if((new File(fileName).exists()))
        {
            System.out.println("Sorry, a file named " + fileName + " already exists in the project directory.");
        }
        else
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
                    f.write(taskList.get(i).getDueDateYear()+"\n");
                    f.write(taskList.get(i).getDueDateMonth()+"\n");
                    f.write(taskList.get(i).getDueDateDay()+"\n");
                }catch (IOException e)
                {
                    System.out.println("Error saving File, sorry.");
                    return;
                }
            }
            System.out.println("Task List Successfully Saved as " + fileName);
        }
    }


}
