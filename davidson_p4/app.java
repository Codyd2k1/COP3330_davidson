import java.io.File;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Scanner;








public class app {
    public static taskList taskList = new taskList();

    public static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu()
    {
        printMainMenu();
        int mainMenuInput = getMainMenuInput();
        interpretMainMenuInput(mainMenuInput);
    }

    public static void interpretMainMenuInput(int mainMenuInput)
    {
        if(mainMenuInput == 1)
        {
            System.out.println("Task list Created, heading to List Operation Menu");
            listOperationMenu();
        }
        if(mainMenuInput == 2)
        {
            viewFileOptions();
            s.nextLine();
            String fileName = getUserInputFileName();

            taskList.loadTaskList(fileName);
            listOperationMenu();
        }
    }
    public static void viewFileOptions()
    {
        //part 1. find current working directory:
        String currentDirectory = System.getProperty("user.dir");
        //part 2. Print File Names in the directory.
        File dirPath = new File(currentDirectory);
        File pathList[] = dirPath.listFiles();
        System.out.println("Task Lists in Current Directory: ");
        for(int i = 0; i < pathList.length; i++)
        {
            String FileName = pathList[i].getName();
            if(FileName.endsWith(".txt"))
            {
                System.out.println(pathList[i]);
            }
        }

    }
    public static String getUserInputFileName()
    {
        //s.nextLine();
        String Filename = "";
        System.out.println("Enter the name.txt of the file you'd like to load: ");
        Filename = s.nextLine();
        while(!Filename.endsWith(".txt"))
        {
            System.out.println("Sorry, your file name entry does not include .txt, try again!");
            //s.nextLine();
            Filename = getUserInputFileName();
        }
        while(!(new File(Filename).exists()))
        {
            System.out.println("Sorry, File does not exist, try again.");
            //s.nextLine();
            Filename =  getUserInputFileName();
        }
        return Filename;

    }

    public static void listOperationMenu()
    {
        printListOperationMenu();
        int listOperationInput = getListOperationMenuInput();
        s.nextLine();
        interpretListOperationMenuInput(listOperationInput);
    }


    public static void interpretListOperationMenuInput(int listOperationInput)
    {
        if(listOperationInput == 1)
        {
            taskList.viewList();
            listOperationMenu();
        }
        if(listOperationInput == 2)
        {
            String Date = getDate();
            String TaskTitle = getUserInputTaskTitle();
            String taskDescription = getDescription();
            taskList.addItem(Date, TaskTitle, taskDescription);
            listOperationMenu();
        }
        if(listOperationInput == 3)
        {
            taskList.viewList();

            int itemNum = getUserInputEditTaskItem();
            int sizeList = taskList.getSize();
            while(itemNum > sizeList-1 || itemNum < 0)
            {
                System.out.println("Invalid Input. Try again.");
                itemNum = getUserInputEditTaskItem();
            }
            System.out.println("Enter New Info For Item "+itemNum+".");
            String Date = getDate();
            String TaskTitle = getUserInputTaskTitle();
            String taskDescription = getDescription();
            taskList.editItem(itemNum, Date, TaskTitle, taskDescription);
            listOperationMenu();
        }
        if(listOperationInput == 4)
        {
            taskList.viewList();
            int sizeList = taskList.getSize();
            int itemNum = getUserInputRemoveTaskNum();
            while(itemNum > sizeList-1 || itemNum < 0)
            {
                System.out.println("Invalid Input. Try again.");
                itemNum = getUserInputRemoveTaskNum();
            }
            taskList.removeItem(itemNum);
            listOperationMenu();
        }
        if(listOperationInput == 5)
        {
            taskList.viewList();
            int sizeList = taskList.getSize();
            if(sizeList == 0)
            {
                System.out.println("No tasks to mark Complete.");
                listOperationMenu();
            }
            int itemNum = getUserInputMarkCompleted();
            while(itemNum > sizeList-1 || itemNum < 0)
            {
                System.out.println("Invalid Input. Try again.");
                itemNum = getUserInputMarkCompleted();
            }
            taskList.markItemCompleted(itemNum);
            listOperationMenu();
        }
        if(listOperationInput == 6)
        {
            taskList.viewList();
            int sizeList = taskList.getSize();
            if(sizeList == 0 || taskList.getNumItemsMarkedComplete() == 0)
            {
                System.out.println("No tasks to un-mark Complete.");
                listOperationMenu();
            }
            int itemNum = getUserInputUnMarkCompleted();
            while(itemNum > sizeList-1 || itemNum < 0)
            {
                System.out.println("Invalid Input. Try again.");
                itemNum = getUserInputUnMarkCompleted();
            }
            taskList.unMarkItemComplete(itemNum);
            listOperationMenu();
        }
        if(listOperationInput == 7)
        {
            //s.nextLine();
            String fileName = getUserInputSaveFileName();
            taskList.saveTaskList(fileName);
            listOperationMenu();
        }
        if(listOperationInput == 8)
        {
            mainMenu();
        }
    }

    public static String getUserInputSaveFileName()
    {
        String Filename = "";
        System.out.println("Enter the name.txt of the file you'd like to Save TaskList as: ");
        Filename = s.nextLine();
        while(!Filename.endsWith(".txt"))
        {
            System.out.println("Sorry, your file name entry does not include .txt, try again!");
            //s.nextLine();
            Filename = getUserInputSaveFileName();
        }
        while((new File(Filename).exists()))
        {
            System.out.println("Sorry, File already Exists, try again.");
            //s.nextLine();
            Filename =  getUserInputSaveFileName();
        }
        return Filename;
    }


    public static int getUserInputUnMarkCompleted()
    {
        int taskToUnComplete = 0;
        try {
            System.out.println("Which Task will you mark Uncompleted? (far left)");
            taskToUnComplete = s.nextInt();
            s.nextLine();

        }catch (InputMismatchException e)
        {
            System.out.println("Invalid Input, try again.");
            s.nextLine();
            getUserInputUnMarkCompleted();
        }
        return taskToUnComplete;
    }
    public static int getUserInputMarkCompleted()
    {
        int taskToComplete = 0;
        try {
            System.out.println("Which Task will you Mark Completed? (far left)");
            taskToComplete = s.nextInt();
            s.nextLine();

        }catch (InputMismatchException e)
        {
            System.out.println("Invalid Input, try again.");
            s.nextLine();
            getUserInputMarkCompleted();
        }
        return taskToComplete;
    }

    public static int getUserInputRemoveTaskNum()
    {
        int taskToRemove = 0;
        try {
            System.out.println("Which Task will you Remove? (far left)");
            taskToRemove = s.nextInt();
            s.nextLine();

        }catch (InputMismatchException e)
        {
            System.out.println("Invalid Input, try again.");
            s.nextLine();
            getUserInputRemoveTaskNum();
        }
        return taskToRemove;
    }

    public static int getUserInputEditTaskItem()
    {
        int taskToEdit = 0;
        try {
            System.out.println("Which Task will you edit? (far left)");
            taskToEdit = s.nextInt();
            s.nextLine();

        }catch (InputMismatchException e)
        {
            System.out.println("Invalid Input, try again.");
            s.nextLine();
            getUserInputEditTaskItem();
        }
        return taskToEdit;
    }

    public static void printMainMenu()
    {
        System.out.println("Main Menu");
        System.out.println("---------");
        System.out.println();
        System.out.println("1) create a new list");
        System.out.println("2) load an existing list");
        System.out.println("3) quit");
    }
    public static int getMainMenuInput()
    {
        int goodEntry = 0;
        int mainMenuInput = 3;
        while(goodEntry == 0) {
            try {
                mainMenuInput = s.nextInt();
            }catch (InputMismatchException e)
            {
                System.out.println("Invalid Entry, Try again:");
                goodEntry = 0;
            }
            if(mainMenuInput > 3 || mainMenuInput < 1)
            {
                System.out.println("Invalid Entry, Try again:");
                goodEntry = 0;
            }
            else{
                goodEntry = 1;
            }
        }

        return mainMenuInput;
    }
    public static void printListOperationMenu()
    {
        System.out.println("List Operation Menu");
        System.out.println("---------");
        System.out.println();
        System.out.println("1) view the list\n" +
                "2) add an item\n" +
                "3) edit an item\n" +
                "4) remove an item\n" +
                "5) mark an item as completed\n" +
                "6) unmark an item as completed\n" +
                "7) save the current list\n" +
                "8) quit to the main menu\n");
    }
    public static int getListOperationMenuInput()
    {
        int goodEntry = 0;
        int ListMenuInput = 3;
        while(goodEntry == 0) {
            try {
                ListMenuInput = s.nextInt();
            }catch (InputMismatchException e)
            {
                System.out.println("Invalid Entry, Try again:");
                goodEntry = 0;
            }
            if(ListMenuInput > 8 || ListMenuInput < 1)
            {
                System.out.println("Invalid Entry, Try again:");
                goodEntry = 0;
            }
            else{
                goodEntry = 1;
            }
        }

        return ListMenuInput;
    }
    public static String getUserInputTaskTitle()
    {
        int goodentry = 0;
        String TaskTitle = "";
        while(goodentry == 0) {
            System.out.println("Enter Task Title:");
            TaskTitle = s.nextLine();
            goodentry = 1;
        }
        return TaskTitle;
    }
    public static String getDate()
    {
        int goodEntry = 0;
        String TaskDate = "";
        while(goodEntry == 0) {
            try {


                System.out.println("Enter Task DueDate Year:");
                int year = s.nextInt();
                while (year < 2021) {
                    System.out.println("Enter Valid Year:");
                    year = s.nextInt();

                }

                System.out.println("Enter Task DueDate Month:");
                int month = s.nextInt();
                while (month < 1 || month > 12) {
                    System.out.println("Enter Valid Month:");
                    month = s.nextInt();
                }

                System.out.println("Enter Task DueDate Day:");
                int day = s.nextInt();
                while (day < 1 || day > 31) {
                    System.out.println("Enter Valid Day:");
                    day = s.nextInt();
                }
                TaskDate = "" + year + "-"+ month + "-"+day;
                goodEntry = 1;
            }catch (InputMismatchException e)
            {
                System.out.println("Invalid Entry, enter an int. Try again.");
                s.nextLine();
                goodEntry = 0;
            }

        }
        s.nextLine();
        return TaskDate;
    }
    public static String getDescription()
    {
        System.out.println("Enter Task Description:");
        String TaskDescription = s.nextLine();
        return TaskDescription;
    }
}



