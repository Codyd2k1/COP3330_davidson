/*
       Dear Grader, Unfortunately I was ignorant when I started programming this assignment
    and as you can see, made it all in the app class. I've spoken with Professor H about
    this on discord and we've agreed I must lose 3 Points immediately for failing to adhere
    to rubric item "p4) solution contains public classes for App, TaskItem, and TaskList".

       On top of those 3 points, I must lose partial points for
    "p4) solution makes use of functional and object decomposition and clean code techniques (SOLID)"
    at your discretion, as I've done my best to decompose within app.

       Aside from that, I've followed the rubric to the best of my abilities.
    Thank you for your hard work this semester, you are a blessing to this class.
    Have a lovely day.


 */
import javax.sound.sampled.Line;
import java.awt.*;
import java.io.*;
import java.io.FileOutputStream;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Formatter;
import java.nio.file.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class app {


    public static void main(String[] args)
    {
        int taskListItems = 0;
        int ListsMade = 0;
        mainMenu(taskListItems, ListsMade);

    }

    public static void mainMenu(int taskListItems,int ListsMade)
    {
        //function to print main menu
        printMainMenu();
        //function now to get user input of mainmenu:
        int userInputMain = getUserInputMAIN();
        //function to interpret and call different options depending on userInputMain
        //interpretUserInputMain(userInputMain);
        if(userInputMain == 3)
        {
            System.exit(0);
        }
        if(userInputMain == 2)
        {
            //call function to view / load existing lists *
            viewFileOptions();
            //call function to get what list user wants to load:
            String FileName;
            FileName = getUserInputFILE();
            //call function to attempt to load user inputted filename.txt:
            int FileExists = loadFileInput(FileName, taskListItems,ListsMade);
            if(FileExists == 1)
            {
                ListOperationMenu(taskListItems,ListsMade,0,1, FileName);
            }
        }
        if(userInputMain == 1)
        {
            //call function to create a new list.
            ListsMade = CreateNewList(ListsMade);
            ListOperationMenu(taskListItems, ListsMade,1,0, "Doesn't Matter");

        }

    }
    public static void ListOperationMenu(int taskListItems,int ListsMade, int useCreatedFile, int useLoadedFile, String loadFileName)
    {
        //call function to view list operation menu
        printListOperationMenu();
        //call function to get list operation menu input
        int listOperationInput = getListOperationMenuInput();
        //call function to interpret L O Input
        interpretListOperationInput(listOperationInput,taskListItems, ListsMade,useCreatedFile,useLoadedFile, loadFileName);
    }

    public static void printMainMenu()
    {
        System.out.println("Main Menu");
        System.out.println("---------");
        System.out.println("1) Create a new list");
        System.out.println("2) Load an existing list");
        System.out.println("3) Quit");
        System.out.println("Enter one of the above options:");

    }

    public static int getUserInputMAIN()
    {
        Scanner s = new Scanner(System.in);
        int goodInput = 0;
        int userInput = 0;
        //while(goodInput != 1)
        try {
            userInput = s.nextInt();

        }
        catch(InputMismatchException e)
        {
            System.out.println("You entered an invalid number, try again: ");
            getUserInputMAIN();
        }
        try {
            if (userInput > 3) {
                System.out.println("You entered an invalid number, try again: ");
                getUserInputMAIN();
            }
        }
        catch (InputMismatchException e)
        {
            System.out.println("You entered an invalid number, try again: ");
            getUserInputMAIN();
        }
        //return userInput;
        //it's never actually going to reach down here
        //return 0;
        return userInput;
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

    public static String getUserInputFILE()
    {
        Scanner userInputFile = new Scanner(System.in);
        String Filename;
        System.out.println("Enter the name.txt of the file you'd like to load: ");
        Filename = userInputFile.nextLine();
        while(!Filename.endsWith(".txt"))
        {
            System.out.println("Sorry, your file name entry does not include .txt, try again!");
            //serInputFile.nextLine();
            Filename = getUserInputFILE();
        }
        return Filename;
    }

    public static int loadFileInput(String FileName, int TaskListItems, int ListsMade)
    {
        System.out.println("Attempting to load Task File: " + FileName +"...");
        File loadFile = new File(FileName);
        int fileExists=0;
        if(loadFile.exists())
        {
            System.out.println("Task File Load Successful, continuing to List Menu!");
            fileExists = 1;
        }
        else{
            System.out.println("Sorry, File not found, Returning to main menu.");
            mainMenu(TaskListItems,ListsMade);
        }
        return fileExists;
    }

    public static int CreateNewList(int ListsMade){

        String FileName = "Task_List[" + ListsMade+"].txt";
        System.out.println("Creating New Task List");
        File f = new File(FileName);

        try {
            // if file has contents deleting first to override
            if(f.exists())
            {
                f.delete();
            }
            f.createNewFile();

        }
        catch(IOException  e)
        {
            System.out.println("Error in Creating TaskList");
        }
        System.out.println("Task List Created\n");
        ListsMade++;
        return ListsMade;
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
                "8) quit to the main menu");

    }

    public static int getListOperationMenuInput()
    {
        Scanner input = new Scanner(System.in);
        int userInputOMenu = 8;
        try{
            userInputOMenu = Integer.parseInt(input.nextLine());
        }
        catch (InputMismatchException e)
        {
            System.out.println("Invalid input, Try again: ");
            getListOperationMenuInput();
        }
        if(userInputOMenu > 8)
        {
            System.out.println("Invalid input, Try again");
            getListOperationMenuInput();
        }
        return userInputOMenu;
    }



    public static void interpretListOperationInput(int listOperationInput,int taskListItems, int ListsMade, int useCreatedFile, int useLoadedFile, String loadFileName)
    {
        if(listOperationInput == 1)
        {
            //call function to view the current task list;
            viewTaskList(ListsMade, taskListItems, useCreatedFile, useLoadedFile, loadFileName);
            ListOperationMenu(taskListItems, ListsMade,useCreatedFile,useLoadedFile, loadFileName);
        }
        else if(listOperationInput == 2)
        {

            //call function to add item to task list;
            taskListItems = taskListAddItem(ListsMade,taskListItems,useCreatedFile,useLoadedFile,loadFileName);
            ListOperationMenu(taskListItems, ListsMade,useCreatedFile,useLoadedFile, loadFileName);
        }
        else if(listOperationInput == 3)
        {
            //call function to edit item in task list;
            editTaskList(ListsMade,taskListItems,useCreatedFile,useLoadedFile,loadFileName);
            ListOperationMenu(taskListItems, ListsMade,useCreatedFile,useLoadedFile, loadFileName);
        }
        else if(listOperationInput == 4)
        {
            //call function to remove item from task list;
            taskListRemoveItem(taskListItems, ListsMade,useCreatedFile,useLoadedFile,loadFileName);
            ListOperationMenu(taskListItems,ListsMade,useCreatedFile,useLoadedFile,loadFileName);
        }
        else if(listOperationInput == 5)
        {
            // call function to mark an item as completed;
            taskListMarkItemComplete(ListsMade,taskListItems,useCreatedFile,useLoadedFile,loadFileName);
            ListOperationMenu(taskListItems,ListsMade,useCreatedFile,useLoadedFile,loadFileName);
        }
        else if(listOperationInput == 6)
        {
            //call function to unmark an item as completed;
            unmarkTaskCompleted(ListsMade,taskListItems,useCreatedFile,useLoadedFile,loadFileName);
            ListOperationMenu(taskListItems,ListsMade,useCreatedFile,useLoadedFile,loadFileName);
        }
        else if(listOperationInput == 7)
        {
            //call function to save the current list;
            saveCurrentTaskList(ListsMade,useCreatedFile,useLoadedFile,loadFileName);
            mainMenu(taskListItems, ListsMade);
        }
        else if(listOperationInput == 8)
        {
            //return to main menu;
            mainMenu(taskListItems, ListsMade);
        }

    }
    //LMENU OPTION 1 VIEW
    public static void viewTaskList(int ListsMade, int taskListItems, int useCreatedFile, int useLoadedFile, String loadFileName)
    {

        if(useCreatedFile == 1) {
            removeNullLines("Task_List[" + (ListsMade - 1) + "].txt");
            String FileName = "Task_list["+(ListsMade-1)+"].txt";
            String LineRead;
            try {
                FileReader reader = new FileReader(FileName);
                BufferedReader readTaskList = new BufferedReader(reader);
                while ((LineRead = readTaskList.readLine()) != null) {
                    System.out.println(LineRead);
                }
                readTaskList.close();
                reader.close();
            } catch (FileNotFoundException e) {
                System.out.println("File Not Found, Sorry.");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("");
            }
        }
        if(useLoadedFile == 1)
        {
            removeNullLines(loadFileName);
            String FileName = loadFileName;
            String LineRead;
            try {
                FileReader reader = new FileReader(FileName);
                BufferedReader readTaskList = new BufferedReader(reader);
                while ((LineRead = readTaskList.readLine()) != null) {
                    System.out.println(LineRead);
                }
                readTaskList.close();
                reader.close();

            } catch (FileNotFoundException e) {
                System.out.println("File Not Found, Sorry.");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("");
            }

        }

    }


    //LMENU OPTION 2 ADD TASK ITEM
    public static int taskListAddItem(int ListsMade, int taskListItems, int useCreatedFile, int useLoadedFile, String loadedFileName)
    {
        if(useCreatedFile == 1) {
            removeNullLines("Task_List[" + (ListsMade - 1) + "].txt");
            Scanner taskInputTaskName = new Scanner(System.in);
            Scanner taskInputTaskDueDate = new Scanner(System.in);
            Scanner taskInputTaskDescription = new Scanner(System.in);
            String TaskName;
            String TaskDueDate = null;
            String TaskDescription;
            System.out.println("Enter Task Title:");
            TaskName = taskInputTaskName.nextLine();
            int goodDateEntry = 0;
            while(goodDateEntry == 0)
            {
                System.out.println("Enter Task Due Date (yyyy-mm-dd): ");
                TaskDueDate = taskInputTaskDueDate.nextLine();
                while (TaskDueDate.length() > 10 || !TaskDueDate.matches("\\d\\d\\d\\d-\\d\\d-\\d\\d")) {
                    System.out.println("Invalid Entry, Use Format YYYY-MM-DD including the -'s.");
                    System.out.println("Enter Task Due Date: ");
                    TaskDueDate = taskInputTaskDueDate.nextLine();
                }
                String year = "";
                String month = "";
                String day = "";
                String Result[] = TaskDueDate.split("-");
                year = Result[0];
                month = Result[1];
                day = Result[2];
                int yearInt = Integer.parseInt(year);
                int monthInt = Integer.parseInt(month);
                int dayInt = Integer.parseInt(day);
                if(yearInt < 2020 || monthInt <= 0 || monthInt > 12 || dayInt < 0 || dayInt > 31)
                {
                    System.out.println("Sorry, Invalid Date Entered, Date must be at or beyond Nov 16, 2020, when this project is due. Try Again.");
                    goodDateEntry = 0;
                }
                else if(yearInt == 2020 && monthInt > 10 && monthInt == 11 && dayInt > 15 && dayInt <= 31 )
                {
                    goodDateEntry = 1;
                }
                else if(yearInt == 2020 && monthInt == 12 && dayInt > 0  && dayInt <= 31)
                {
                    goodDateEntry = 1;
                }
                else if(yearInt >= 2021 && monthInt > 0 && monthInt <= 12 && dayInt > 00 && dayInt <= 31)
                {
                    goodDateEntry = 1;
                }
                else
                {
                    System.out.println("Sorry, Invalid Date Entered, Date must be at or beyond Nov 16, 2020, when this project is due. Try Again.");
                    goodDateEntry = 0;
                }

            }
            System.out.println("Enter Task Description");
            TaskDescription = taskInputTaskDescription.nextLine();

            String createdFileName = "Task_List["+ (ListsMade-1)+"].txt";
            int numTasksInList = CountTasksInList(createdFileName);
            try (FileWriter append = new FileWriter(createdFileName, true)) {
                Formatter taskFile = new Formatter(append);
                taskFile.format("%d) [%s] %s: %s\n", numTasksInList, TaskDueDate, TaskName, TaskDescription);
                taskFile.close();
                append.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not Found, Sorry.");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("File not Found, Sorry.");
                e.printStackTrace();
            }
            taskListItems = taskListItems + 1;
            return taskListItems;
        }
        if(useLoadedFile == 1)
        {
            removeNullLines(loadedFileName);
            Scanner taskInputTaskName = new Scanner(System.in);
            Scanner taskInputTaskDueDate = new Scanner(System.in);
            Scanner taskInputTaskDescription = new Scanner(System.in);
            String TaskName;
            String TaskDueDate = null;
            String TaskDescription;
            System.out.println("Enter Task Title:");
            TaskName = taskInputTaskName.nextLine();
            int goodDateEntry = 0;
            while(goodDateEntry == 0)
            {
                System.out.println("Enter Task Due Date (yyyy-mm-dd): ");
                TaskDueDate = taskInputTaskDueDate.nextLine();
                while (TaskDueDate.length() > 10 || !TaskDueDate.matches("\\d\\d\\d\\d-\\d\\d-\\d\\d")) {
                    System.out.println("Invalid Entry, Use Format YYYY-MM-DD including the -'s.");
                    System.out.println("Enter Task Due Date: ");
                    TaskDueDate = taskInputTaskDueDate.nextLine();
                }
                String year = "";
                String month = "";
                String day = "";
                String Result[] = TaskDueDate.split("-");
                year = Result[0];
                month = Result[1];
                day = Result[2];
                int yearInt = Integer.parseInt(year);
                int monthInt = Integer.parseInt(month);
                int dayInt = Integer.parseInt(day);
                if(yearInt < 2020 || monthInt <= 0 || monthInt > 12 || dayInt < 0 || dayInt > 31)
                {
                    System.out.println("Sorry, Invalid Date Entered, Date must be at or beyond Nov 16, 2020, when this project is due. Try Again.");
                    goodDateEntry = 0;
                }
                else if(yearInt == 2020 && monthInt > 10 && monthInt == 11 && dayInt > 15 && dayInt <= 31 )
                {
                    goodDateEntry = 1;
                }
                else if(yearInt == 2020 && monthInt == 12 && dayInt > 0  && dayInt <= 31)
                {
                    goodDateEntry = 1;
                }
                else if(yearInt >= 2021 && monthInt > 0 && monthInt <= 12 && dayInt > 0 && dayInt <= 31)
                {
                    goodDateEntry = 1;
                }
                else
                {
                    System.out.println("Sorry, Invalid Date Entered, Date must be at or beyond Nov 16, 2020, when this project is due. Try Again.");
                    goodDateEntry = 0;
                }

            }
            System.out.println("Enter Task Description");
            TaskDescription = taskInputTaskDescription.nextLine();

            int numTasksInList = CountTasksInList(loadedFileName);

            try (FileWriter append = new FileWriter(loadedFileName, true)) {
                Formatter taskFile = new Formatter(append);
                taskFile.format("\n%d) [%s] %s: %s", numTasksInList, TaskDueDate, TaskName, TaskDescription);
                taskFile.close();
                append.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not Found, Sorry.");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("File not Found, Sorry.");
                e.printStackTrace();
            }
            taskListItems = taskListItems + 1;
            return taskListItems;
        }
        return taskListItems;
    }





    public static int CountTasksInList(String FileName)
    {
        int numTasksInList = 0;
        try(FileReader reader = new FileReader(FileName)) {
            BufferedReader readTaskList = new BufferedReader(reader);
            String LineRead;
            while ((LineRead = readTaskList.readLine()) != null) {
                numTasksInList++;
            }
            reader.close();
            readTaskList.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error counting amount of tasks in list.. Sorry.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numTasksInList;
    }

    //LMENU OPTION 3 Edit Item in Task List.
    public static void editTaskList(int ListsMade,int taskListItems, int useCreatedList, int useLoadedList, String loadedListName)
    {
        if(useCreatedList == 1) {
            removeNullLines("Task_List[" + (ListsMade - 1) + "].txt");
            System.out.println("Current Task list is Task_List[" + (ListsMade-1)+"].txt, contents of this list are: ");
            viewTaskList(ListsMade,taskListItems,useCreatedList,useLoadedList,loadedListName);
            int validInput = 0;
            int taskNumToEdit = 0;
            int numTasksinList = CountTasksInList("Task_List["+(ListsMade-1)+"].txt");
            if(numTasksinList == 0)
            {
                System.out.println("No tasks to edit, sorry.");
                return;
            }
            while(validInput == 0)
            {
                try {
                    System.out.println("Which Task # (far left) would you like to edit?");
                    Scanner taskToEdit = new Scanner(System.in);
                    taskNumToEdit = taskToEdit.nextInt();
                    validInput = 1;

                } catch (InputMismatchException e) {
                    System.out.println("Invalid Input, enter an integer.");
                    validInput = 0;
                }
                if(taskNumToEdit > numTasksinList || taskNumToEdit < 0)
                {
                    System.out.println("Sorry, task # entered is invalid, try again");
                    validInput = 0;
                }
            }

            //scan through file until charAt(oldline) ==  # inputted by user
            //store entire line in oldLine
            //replaceAllMethod on oldline to newLine inputted by user.
            String LineRead = null;
            try(FileReader reader = new FileReader("Task_List[" + (ListsMade-1)+"].txt")) {
                BufferedReader readTaskList = new BufferedReader(reader);
                
                int lineNum;
                char lineNoChar;
                while ((LineRead = readTaskList.readLine()) != null) {
                    String firstInt = LineRead;
                    if(firstInt.contains(")"))
                    {
                        firstInt = firstInt.substring(0,firstInt.indexOf(")"));
                    }
                    //lineNoChar = firstInt.charAt(0);
                    //System.out.println("First int for this line is: " + firstInt);
                    lineNum = Integer.parseInt(String.valueOf(firstInt));
                    if(lineNum == taskNumToEdit)
                    {
                        break;
                    }
                    
                }
                readTaskList.close();
                reader.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found, sorry.");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("File exception caught, sorry.");
                e.printStackTrace();
            }
            //now lineRead is storing all of our old line words, lets test it.
            System.out.println("Task to be replaced is: " + LineRead);
            //now ask user what they want to put in to replace this line
            Scanner taskInputReplaceTaskName = new Scanner(System.in);
            Scanner taskInputReplaceTaskDueDate = new Scanner(System.in);
            Scanner taskInputReplaceTaskDescription = new Scanner(System.in);
            String TaskName;
            String TaskDueDate = null;
            String TaskDescription;
            System.out.println("Enter Edited Task Title:");
            TaskName = taskInputReplaceTaskName.nextLine();
            int goodDateEntry = 0;
            while(goodDateEntry == 0)
            {
                System.out.println("Enter Edited Task Due Date (yyyy-mm-dd): ");
                TaskDueDate = taskInputReplaceTaskDueDate.nextLine();
                while (TaskDueDate.length() > 10 || !TaskDueDate.matches("\\d\\d\\d\\d-\\d\\d-\\d\\d")) {
                    System.out.println("Invalid Entry, Use Format YYYY-MM-DD including the -'s.");
                    System.out.println("Enter Task Due Date: ");
                    TaskDueDate = taskInputReplaceTaskDueDate.nextLine();
                }
                String year = "";
                String month = "";
                String day = "";
                String Result[] = TaskDueDate.split("-");
                year = Result[0];
                month = Result[1];
                day = Result[2];
                int yearInt = Integer.parseInt(year);
                int monthInt = Integer.parseInt(month);
                int dayInt = Integer.parseInt(day);
                if(yearInt < 2020 || monthInt <= 0 || monthInt > 12 || dayInt < 0 || dayInt > 31)
                {
                    System.out.println("Sorry, Invalid Date Entered, Date must be at or beyond Nov 16, 2020, when this project is due. Try Again.");
                    goodDateEntry = 0;
                }
                else if(yearInt == 2020 && monthInt > 10 && monthInt == 11 && dayInt > 15 && dayInt <= 31 )
                {
                    goodDateEntry = 1;
                }
                else if(yearInt == 2020 && monthInt == 12 && dayInt > 0  && dayInt <= 31)
                {
                    goodDateEntry = 1;
                }
                else if(yearInt >= 2021 && monthInt > 0 && monthInt <= 12 && dayInt > 0 && dayInt <= 31)
                {
                    goodDateEntry = 1;
                }
                else
                {
                    System.out.println("Sorry, Invalid Date Entered, Date must be at or beyond Nov 16, 2020, when this project is due. Try Again.");
                    goodDateEntry = 0;
                }

            }


            System.out.println("Enter Edited Task Description");
            TaskDescription = taskInputReplaceTaskDescription.nextLine();
            String NewTaskLine = taskNumToEdit + ") [" + TaskDueDate + "] " + TaskName +": " + TaskDescription;
            String OldLine = LineRead;

            try{
                File oldFile = new File("Task_List[" + (ListsMade-1)+"].txt");
                BufferedReader reader = new BufferedReader(new FileReader(oldFile));
                String oldText = "";
                String textLines = reader.readLine();
                while(textLines != null)
                {
                    //line separator creates a newline after each line is read.
                    oldText = oldText + textLines + System.lineSeparator();
                    textLines = reader.readLine();
                }
                String newTextLines = oldText.replace(OldLine, NewTaskLine);
                FileWriter f = new FileWriter("Task_List[" + (ListsMade-1)+"].txt");
                f.write(newTextLines);
                reader.close();
                f.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        if(useLoadedList == 1)
        {

                removeNullLines(loadedListName);
                System.out.println("Current Task list is " + (loadedListName) +", contents of this list are: ");
                viewTaskList(ListsMade,taskListItems,useCreatedList,useLoadedList,loadedListName);
                int validInput = 0;
                int taskNumToEdit = 0;
                int numTasksinList = CountTasksInList(loadedListName);
                if(numTasksinList == 0)
                {
                    System.out.println("No tasks to edit, sorry.");
                    return;
                }
                while(validInput == 0)
                {
                    try {
                        System.out.println("Which Task # (far left) would you like to edit?");
                        Scanner taskToEdit = new Scanner(System.in);
                        taskNumToEdit = taskToEdit.nextInt();
                        validInput = 1;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid Input, enter an integer.");
                        validInput = 0;
                    }
                    if(taskNumToEdit > numTasksinList || taskNumToEdit < 0)
                    {
                        System.out.println("Sorry, task # entered is invalid, try again");
                        validInput = 0;
                    }
                }

                //scan through file until charAt(oldline) ==  # inputted by user
                //store entire line in oldLine
                //replaceAllMethod on oldline to newLine inputted by user.
                String LineRead = null;
                try(FileReader reader = new FileReader(loadedListName)) {
                    BufferedReader readTaskList = new BufferedReader(reader);

                    int lineNum;
                    char lineNoChar;
                    while ((LineRead = readTaskList.readLine()) != null) {
                        String firstInt = LineRead;
                        if(firstInt.contains(")"))
                        {
                            firstInt = firstInt.substring(0,firstInt.indexOf(")"));
                        }
                        //lineNoChar = firstInt.charAt(0);
                        //System.out.println("First int for this line is: " + firstInt);
                        lineNum = Integer.parseInt(String.valueOf(firstInt));
                        if(lineNum == taskNumToEdit)
                        {
                            break;
                        }

                    }
                    readTaskList.close();
                    reader.close();
                } catch (FileNotFoundException e) {
                    System.out.println("File not found, sorry.");
                    e.printStackTrace();
                } catch (IOException e) {
                    System.out.println("File exception caught, sorry.");
                    e.printStackTrace();
                }
                //now lineRead is storing all of our old line words, lets test it.
                System.out.println("Task to be replaced is: " + LineRead);
                //now ask user what they want to put in to replace this line
                Scanner taskInputReplaceTaskName = new Scanner(System.in);
                Scanner taskInputReplaceTaskDueDate = new Scanner(System.in);
                Scanner taskInputReplaceTaskDescription = new Scanner(System.in);
                String TaskName;
                String TaskDueDate = null;
                String TaskDescription;
                System.out.println("Enter Edited Task Title:");
                TaskName = taskInputReplaceTaskName.nextLine();
                int goodDateEntry = 0;
                while(goodDateEntry == 0)
                {
                    System.out.println("Enter Edited Task Due Date (yyyy-mm-dd): ");
                    TaskDueDate = taskInputReplaceTaskDueDate.nextLine();
                    while (TaskDueDate.length() > 10 || !TaskDueDate.matches("\\d\\d\\d\\d-\\d\\d-\\d\\d")) {
                        System.out.println("Invalid Entry, Use Format YYYY-MM-DD including the -'s.");
                        System.out.println("Enter Task Due Date: ");
                        TaskDueDate = taskInputReplaceTaskDueDate.nextLine();
                    }
                    String year = "";
                    String month = "";
                    String day = "";
                    String Result[] = TaskDueDate.split("-");
                    year = Result[0];
                    month = Result[1];
                    day = Result[2];
                    int yearInt = Integer.parseInt(year);
                    int monthInt = Integer.parseInt(month);
                    int dayInt = Integer.parseInt(day);
                    if(yearInt < 2020 || monthInt <= 0 || monthInt > 12 || dayInt < 0 || dayInt > 31)
                    {
                        System.out.println("Sorry, Invalid Date Entered, Date must be at or beyond Nov 16, 2020, when this project is due. Try Again.");
                        goodDateEntry = 0;
                    }
                    else if(yearInt == 2020 && monthInt > 10 && monthInt == 11 && dayInt > 15 && dayInt <= 31 )
                    {
                        goodDateEntry = 1;
                    }
                    else if(yearInt == 2020 && monthInt == 12 && dayInt > 0  && dayInt <= 31)
                    {
                        goodDateEntry = 1;
                    }
                    else if(yearInt >= 2021 && monthInt > 0 && monthInt <= 12 && dayInt > 0 && dayInt <= 31)
                    {
                        goodDateEntry = 1;
                    }
                    else
                    {
                        System.out.println("Sorry, Invalid Date Entered, Date must be at or beyond Nov 16, 2020, when this project is due. Try Again.");
                        goodDateEntry = 0;
                    }

                }
                System.out.println("Enter Edited Task Description");
                TaskDescription = taskInputReplaceTaskDescription.nextLine();
                String NewTaskLine = taskNumToEdit + ") [" + TaskDueDate + "] " + TaskName +": " + TaskDescription;
                String OldLine = LineRead;

                try{
                    File oldFile = new File(loadedListName);
                    BufferedReader reader = new BufferedReader(new FileReader(oldFile));
                    String oldText = "";
                    String textLines = reader.readLine();
                    while(textLines != null)
                    {
                        //line separator creates a newline after each line is read.
                        oldText = oldText + textLines + System.lineSeparator();
                        textLines = reader.readLine();
                    }
                    String newTextLines = oldText.replace(OldLine, NewTaskLine);
                    FileWriter f = new FileWriter(loadedListName);
                    f.write(newTextLines);
                    reader.close();
                    f.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        System.out.println("Task list Successfully Edited, Returning to List Options Menu.");
    }

    //LMENU OPTION 4, REMOVE ITEM FROM TASK LIST:
    public static void taskListRemoveItem(int taskListItems, int ListsMade, int useCreatedList, int useLoadedList, String loadedFileName) {
        if (useCreatedList == 1) {
            removeNullLines("Task_List[" + (ListsMade - 1) + "].txt");
            System.out.println("Current Task list is Task_List[" + (ListsMade - 1) + "].txt");
            int numTasks = CountTasksInList("Task_List[" + (ListsMade - 1) + "].txt");
            if (numTasks == 0) {
                System.out.println("Sorry, No Tasks in List to Remove!");
                ListOperationMenu(taskListItems, ListsMade, useCreatedList, useLoadedList, loadedFileName);
            }
            System.out.println("Contents of this Task List are:");
            viewTaskList(ListsMade, taskListItems, useCreatedList, useLoadedList, loadedFileName);
            //System.out.println("Which task #(far left) would you like to remove?");

            //Scanner s = new Scanner(System.in);
            //int numTaskToDelete = s.nextInt();
            int numTaskToDelete = -1;
            int goodEntry = 0;
            while (goodEntry == 0)
            {
                try {
                    System.out.println("Which task #(far left) would you like to remove?");
                    Scanner s = new Scanner(System.in);
                    numTaskToDelete = s.nextInt();
                    goodEntry = 1;

                } catch (InputMismatchException e) {
                    System.out.println("Invalid task #, try again.");
                    goodEntry = 0;
                }
                if (numTaskToDelete > numTasks)
                {
                    System.out.println("Entry too high, try again.");
                    goodEntry = 0;
                }
             }
            //Copying over find line process from above function editTaskList
            String LineRead = null;
            try (FileReader reader = new FileReader("Task_List[" + (ListsMade - 1) + "].txt")) {
                BufferedReader readTaskList = new BufferedReader(reader);

                int lineNum;
                char lineNoChar;

                while ((LineRead = readTaskList.readLine()) != null) {
                    String firstInt = LineRead;

                    if (firstInt.contains(")")) {
                        firstInt = firstInt.substring(0, firstInt.indexOf(")"));
                    }
                    //lineNoChar = firstInt.charAt(0);
                    //System.out.println("First int for this line is: " + firstInt);
                    lineNum = Integer.parseInt(String.valueOf(firstInt));
                    if (lineNum == numTaskToDelete) {
                        break;
                    }

                }
                readTaskList.close();
                reader.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found, sorry.");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("File exception caught, sorry.");
                e.printStackTrace();
            }

            //new loop to read through file and copy contents to output.txt excluding the line == LineRead;
            String output = "";
            String Buffer = null;
            try (FileReader readerOne = new FileReader("Task_List[" + (ListsMade - 1) + "].txt")) {
                BufferedReader readTaskListOne = new BufferedReader(readerOne);
                int a = 1;
                String firstInt = null;
                int lineNum = 0;
                while (a != 0) {
                    Buffer = readTaskListOne.readLine();

                    if (Buffer == null) {
                        a = 0;
                        break;
                    }
                    else if (!(Buffer.equals(LineRead))) {
                        firstInt = Buffer;
                        if(firstInt.contains(")"))
                        {
                            //System.out.println("reached inside of CONTAINS");
                            firstInt = firstInt.substring(0,firstInt.indexOf(")"));
                            lineNum = Integer.parseInt(String.valueOf(firstInt));
                        }
                        if(lineNum > numTaskToDelete)
                        {
                            //System.out.println("reached inside of lineNum>Numtasktodelete");
                            lineNum = lineNum - 1;
                            String lineNumChar = Integer.toString(lineNum) + ")";
                            //System.out.println("replacing " + firstInt + " with "+lineNumChar);
                            Buffer = Buffer.replace(firstInt+")",lineNumChar);
                        }
                        output = output + Buffer + System.lineSeparator();
                    } else {
                        continue;
                    }

                }
                FileWriter outputWriter = new FileWriter("Task_List[" + (ListsMade - 1) + "].txt");
                readTaskListOne.close();
                readerOne.close();
                outputWriter.write(output);
                outputWriter.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        if (useLoadedList == 1) {
            removeNullLines(loadedFileName);
            System.out.println("Current Task list is" + loadedFileName);
            int numTasks = CountTasksInList(loadedFileName);
            if (numTasks == 0) {
                System.out.println("Sorry, No Tasks in List to Remove!");
                ListOperationMenu(taskListItems,ListsMade,useCreatedList,useLoadedList,loadedFileName);
            }
            System.out.println("Contents of this Task List are:");
            viewTaskList(ListsMade,taskListItems,useCreatedList,useLoadedList,loadedFileName);
            int goodEntry = 0;
            int numTaskToDelete = -1;
            while(goodEntry == 0) {
                try {
                    System.out.println("Which task #(far left) would you like to remove?");
                    Scanner s = new Scanner(System.in);
                    numTaskToDelete = s.nextInt();
                    goodEntry = 1;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid task #, try again.");
                    goodEntry = 0;
                }
                if (numTaskToDelete > numTasks)
                {
                    System.out.println("Entry too high, try again.");
                    goodEntry = 0;
                }
            }
            //Copying over find line process from above function editTaskList
            String LineRead = null;
            try (FileReader reader = new FileReader(loadedFileName)) {
                BufferedReader readTaskList = new BufferedReader(reader);

                int lineNum;
                char lineNoChar;

                while ((LineRead = readTaskList.readLine()) != null) {
                    String firstInt = LineRead;

                    if (firstInt.contains(")")) {
                        firstInt = firstInt.substring(0, firstInt.indexOf(")"));
                    }
                    else if(firstInt.length() == 0||firstInt == null)
                    {
                        LineRead = readTaskList.readLine();
                        firstInt = LineRead;
                        if (firstInt.contains(")")) {
                            firstInt = firstInt.substring(0, firstInt.indexOf(")"));
                        }
                    }
                    //lineNoChar = firstInt.charAt(0);
                    //System.out.println("First int for this line is: " + firstInt);
                    lineNum = Integer.parseInt(String.valueOf(firstInt));
                    if (lineNum == numTaskToDelete) {
                        break;

                    }
                }
                readTaskList.close();
                reader.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found, sorry.");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("File exception caught, sorry.");
                e.printStackTrace();
            }

            //new loop to read through file and copy contents to output.txt excluding the line == LineRead;
            String output = "";
            String Buffer = null;
            try (FileReader readerOne = new FileReader(loadedFileName)) {
                BufferedReader readTaskListOne = new BufferedReader(readerOne);
                int a = 1;
                String firstInt = null;
                int lineNum = 0;
                while (a != 0) {
                    Buffer = readTaskListOne.readLine();

                    if (Buffer == null) {
                        a = 0;
                        break;
                    }
                    else if (!(Buffer.equals(LineRead))) {
                        firstInt = Buffer;
                        if(firstInt.contains(")"))
                        {
                            //System.out.println("reached inside of CONTAINS");
                            firstInt = firstInt.substring(0,firstInt.indexOf(")"));
                            lineNum = Integer.parseInt(String.valueOf(firstInt));
                        }
                        if(lineNum > numTaskToDelete)
                        {
                            //System.out.println("reached inside of lineNum>Numtasktodelete");
                            lineNum = lineNum - 1;
                            String lineNumChar = Integer.toString(lineNum) + ")";
                            //System.out.println("replacing " + firstInt + " with "+lineNumChar);
                            Buffer = Buffer.replace(firstInt+")",lineNumChar);
                        }
                        output = output + Buffer + System.lineSeparator();
                    } else {
                        continue;
                    }

                }

                FileWriter outputWriter = new FileWriter(loadedFileName);
                outputWriter.write(output);
                readTaskListOne.close();
                readerOne.close();
                outputWriter.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        System.out.println("Task Removal Completed! Returning to List Operation Menu.");
    }

    //LMENU OPTION 5 MARK ITEM AS COMPLETE
    public static void taskListMarkItemComplete(int ListsMade,int taskListItems, int useCreatedFile, int useLoadedFile, String loadedFileName)
    {
        if(useCreatedFile == 1) {
            removeNullLines("Task_List[" + (ListsMade - 1) + "].txt");
            System.out.println("Current Task list is Task_List[" + (ListsMade - 1) + "].txt");
            System.out.println("Contents of list are: ");
            viewTaskList(ListsMade, taskListItems, useCreatedFile, useLoadedFile, loadedFileName);
            int numTasksInList = CountTasksInList("Task_List[" + (ListsMade - 1) + "].txt");
            //System.out.println("Numtasks in list is" + numTasksInList);
            int taskNumComplete = -1;
            int validEntry = 0;
            while (validEntry == 0) {
                try {
                    System.out.println("Which task # (Far left) would you like to mark complete?");
                    Scanner complete = new Scanner(System.in);
                    taskNumComplete = complete.nextInt();
                    validEntry = 1;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input, enter an integer.");
                    validEntry = 0;
                }
                if (taskNumComplete >= numTasksInList) {
                    System.out.println("Input too high, try again.");
                    validEntry = 0;
                }
            }

            //copy the code from edit task list function to find proper line number.
            String LineRead = null;
            String ReplaceWith = null;
            try(FileReader reader = new FileReader("Task_List[" + (ListsMade-1)+"].txt")) {
                BufferedReader readTaskList = new BufferedReader(reader);

                int lineNum;
                char lineNoChar;
                while ((LineRead = readTaskList.readLine()) != null) {
                    String firstInt = LineRead;
                    if(firstInt.contains(")"))
                    {
                        firstInt = firstInt.substring(0,firstInt.indexOf(")"));
                    }
                    //lineNoChar = firstInt.charAt(0);
                    //System.out.println("First int for this line is: " + firstInt);
                    lineNum = Integer.parseInt(String.valueOf(firstInt));
                    if(lineNum == taskNumComplete)
                    {
                        ReplaceWith = LineRead + "  *COMPLETED*";
                        break;
                    }

                }
                readTaskList.close();
                reader.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found, sorry.");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("File exception caught, sorry.");
                e.printStackTrace();
            }
            //copy code from edit task list to read all lines into output string
            try{
                File oldFile = new File("Task_List[" + (ListsMade-1)+"].txt");
                BufferedReader reader = new BufferedReader(new FileReader(oldFile));
                String oldText = "";
                String textLines = reader.readLine();
                while(textLines != null)
                {
                    //line separator creates a newline after each line is read.
                    oldText = oldText + textLines + System.lineSeparator();
                    textLines = reader.readLine();
                }
                String newTextLines = oldText.replace(LineRead, ReplaceWith);
                FileWriter f = new FileWriter("Task_List[" + (ListsMade-1)+"].txt");
                f.write(newTextLines);
                reader.close();
                f.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if(useLoadedFile == 1) {
            removeNullLines(loadedFileName);
            System.out.println("Current Task list is " + loadedFileName);
            System.out.println("Contents of list are: ");
            viewTaskList(ListsMade, taskListItems, useCreatedFile, useLoadedFile, loadedFileName);

            int numTasksInList = CountTasksInList(loadedFileName);
            //System.out.println("Numtasks in list is" + numTasksInList);
            int taskNumComplete = -1;
            int validEntry = 0;
            while (validEntry == 0) {
                try {
                    System.out.println("Which task # (Far left) would you like to mark complete?");
                    Scanner complete = new Scanner(System.in);
                    taskNumComplete = complete.nextInt();
                    validEntry = 1;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input, enter an integer.");
                    validEntry = 0;
                }
                if (taskNumComplete >= numTasksInList) {
                    System.out.println("Input too high, try again.");
                    validEntry = 0;
                }
            }

            //copy the code from edit task list function to find proper line number.
            String LineRead = null;
            String ReplaceWith = null;
            try(FileReader reader = new FileReader(loadedFileName)) {
                BufferedReader readTaskList = new BufferedReader(reader);

                int lineNum;
                char lineNoChar;
                while ((LineRead = readTaskList.readLine()) != null) {
                    String firstInt = LineRead;
                    if(firstInt.contains(")"))
                    {
                        firstInt = firstInt.substring(0,firstInt.indexOf(")"));
                    }
                    //lineNoChar = firstInt.charAt(0);
                    //System.out.println("First int for this line is: " + firstInt);
                    lineNum = Integer.parseInt(String.valueOf(firstInt));
                    if(lineNum == taskNumComplete)
                    {
                        ReplaceWith = LineRead + "  *COMPLETED*";
                        break;
                    }

                }
                readTaskList.close();
                reader.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found, sorry.");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("File exception caught, sorry.");
                e.printStackTrace();
            }
            //copy code from edit task list to read all lines into output string
            try{
                File oldFile = new File(loadedFileName);
                BufferedReader reader = new BufferedReader(new FileReader(oldFile));
                String oldText = "";
                String textLines = reader.readLine();
                while(textLines != null)
                {
                    //line separator creates a newline after each line is read.
                    oldText = oldText + textLines + System.lineSeparator();
                    textLines = reader.readLine();
                }
                String newTextLines = oldText.replace(LineRead, ReplaceWith);
                FileWriter f = new FileWriter(loadedFileName);
                f.write(newTextLines);
                reader.close();
                f.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        System.out.println("Task Item Marked Completed! Returning to List Operation Menu.");
    }

    //LMENU OPTION 6, UNMARK ITEM AS COMPLETED;

    public static void unmarkTaskCompleted(int ListsMade, int taskListItems, int useCreatedFile, int useLoadedFile, String loadedFileName)
    {
        if(useCreatedFile == 1)
        {
            removeNullLines("Task_List[" + (ListsMade - 1) + "].txt");
            System.out.println("Current Task list is Task_List[" + (ListsMade - 1) + "].txt");
            int numCompletedTasksInList = countNumCompletedTasksInTaskList("Task_List["+(ListsMade-1)+"].txt");
            //System.out.println("Num Completed Tasks in list: "+numCompletedTasksInList);
            if(numCompletedTasksInList == 0)
            {
                System.out.println("Sorry, There are no Completed Tasks to UnMark Completed currently in this list.");
                return;
            }
            System.out.println("Contents of list are: ");
            viewTaskList(ListsMade, taskListItems, useCreatedFile, useLoadedFile, loadedFileName);
            int numTasksInList = CountTasksInList("Task_List[" + (ListsMade - 1) + "].txt");

            //System.out.println("Number of Tasks in List" + numTasksInList);
            int taskNumUnMarkComplete = -1;
            int validEntry = 0;
            while (validEntry == 0) {
                try {
                    System.out.println("Which task # (Far left) would you like to un-mark complete?");
                    Scanner complete = new Scanner(System.in);
                    taskNumUnMarkComplete = complete.nextInt();
                    validEntry = 1;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input, enter an integer.");
                    validEntry = 0;
                }
                if (taskNumUnMarkComplete >= numTasksInList) {
                    System.out.println("Input too high, try again.");
                    validEntry = 0;
                }
            }

            String LineRead = null;
            try(FileReader reader = new FileReader("Task_List[" + (ListsMade-1)+"].txt")) {
                BufferedReader readTaskList = new BufferedReader(reader);

                int lineNum;
                char lineNoChar;
                while ((LineRead = readTaskList.readLine()) != null) {
                    String firstInt = LineRead;
                    if(firstInt.contains(")"))
                    {
                        firstInt = firstInt.substring(0,firstInt.indexOf(")"));
                    }
                    //lineNoChar = firstInt.charAt(0);
                    //System.out.println("First int for this line is: " + firstInt);
                    lineNum = Integer.parseInt(String.valueOf(firstInt));
                    if(lineNum == taskNumUnMarkComplete)
                    {
                        break;
                    }

                }
                readTaskList.close();
                reader.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found, sorry.");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("File exception caught, sorry.");
                e.printStackTrace();
            }
            //System.out.println("Lineread is storing line" + LineRead);
            //String stringToRemove = "*COMPLETED*";

            //LineRead.replace(stringToRemove, "");
            String anchorText = LineRead;
            anchorText = anchorText.replace("*COMPLETED*"," ");
            //System.out.println(anchorText);
            try{
                File oldFile = new File("Task_List[" + (ListsMade-1)+"].txt");
                BufferedReader reader = new BufferedReader(new FileReader(oldFile));
                String oldText = "";
                String textLines = reader.readLine();
                while(textLines != null)
                {
                    //line separator creates a newline after each line is read.
                    oldText = oldText + textLines + System.lineSeparator();
                    textLines = reader.readLine();
                }
                oldText = oldText.replace(LineRead,anchorText);
                //System.out.println
                FileWriter f = new FileWriter("Task_List[" + (ListsMade-1)+"].txt");
                f.write(oldText);
                reader.close();
                f.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        if(useLoadedFile == 1)
        {
            removeNullLines(loadedFileName);
            System.out.println("Current Task list is"+loadedFileName);
            System.out.println("Contents of list are: ");
            viewTaskList(ListsMade, taskListItems, useCreatedFile, useLoadedFile, loadedFileName);
            int numTasksInList = CountTasksInList(loadedFileName);
            int numCompletedTasksInList = countNumCompletedTasksInTaskList(loadedFileName);
            if(numCompletedTasksInList == 0)
            {
                System.out.println("Sorry, there are no completed tasks in this list to un-Mark");
                return;
            }
            //System.out.println("Number of Tasks in List" + numTasksInList);
            int taskNumUnMarkComplete = -1;
            int validEntry = 0;
            while (validEntry == 0) {
                try {
                    System.out.println("Which task # (Far left) would you like to un-mark complete?");
                    Scanner complete = new Scanner(System.in);
                    taskNumUnMarkComplete = complete.nextInt();
                    validEntry = 1;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input, enter an integer.");
                    validEntry = 0;
                }
                if (taskNumUnMarkComplete >= numTasksInList) {
                    System.out.println("Input too high, try again.");
                    validEntry = 0;
                }
            }

            String LineRead = null;
            try(FileReader reader = new FileReader(loadedFileName)) {
                BufferedReader readTaskList = new BufferedReader(reader);

                int lineNum;
                char lineNoChar;
                while ((LineRead = readTaskList.readLine()) != null) {
                    String firstInt = LineRead;
                    if(firstInt.contains(")"))
                    {
                        firstInt = firstInt.substring(0,firstInt.indexOf(")"));
                    }
                    //lineNoChar = firstInt.charAt(0);
                    //System.out.println("First int for this line is: " + firstInt);
                    lineNum = Integer.parseInt(String.valueOf(firstInt));
                    if(lineNum == taskNumUnMarkComplete)
                    {
                        break;
                    }

                }
                readTaskList.close();
                reader.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found, sorry.");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("File exception caught, sorry.");
                e.printStackTrace();
            }
            //System.out.println("Lineread is storing line" + LineRead);
            //String stringToRemove = "*COMPLETED*";

            //LineRead.replace(stringToRemove, "");
            String anchorText = LineRead;
            anchorText = anchorText.replace("*COMPLETED*"," ");
            //System.out.println(anchorText);
            try{
                File oldFile = new File(loadedFileName);
                BufferedReader reader = new BufferedReader(new FileReader(oldFile));
                String oldText = "";
                String textLines = reader.readLine();
                while(textLines != null)
                {
                    //line separator creates a newline after each line is read.
                    oldText = oldText + textLines + System.lineSeparator();
                    textLines = reader.readLine();
                }
                oldText = oldText.replace(LineRead,anchorText);
                //System.out.println
                FileWriter f = new FileWriter(loadedFileName);
                f.write(oldText);
                reader.close();
                f.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        System.out.println("Task Successfully Un-Marked Completed! Returning to List Operation Menu.");
    }

    public static int countNumCompletedTasksInTaskList(String taskListName)
    {
        String oldText = null;
        try{
            File oldFile = new File(taskListName);
            BufferedReader reader = new BufferedReader(new FileReader(oldFile));
            oldText = "";
            String textLines = reader.readLine();
            while(textLines != null)
            {
                //line separator creates a newline after each line is read.
                oldText = oldText + textLines + System.lineSeparator();
                textLines = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int numTasksComplete = 0;
        Pattern completedTask = Pattern.compile("\\*COMPLETED\\*");
        Matcher matchCompleted = completedTask.matcher(oldText);
        while(matchCompleted.find())
        {
            numTasksComplete++;
        }
        return numTasksComplete;
    }


    //LMENU OPTION 7, SAVE CURRENT TASK LIST:
    public static void saveCurrentTaskList(int ListsMade, int useCreatedList, int useLoadedList, String loadedListName)
    {
        if(useCreatedList == 1) {
            System.out.println("Current Task list is Task_List[" + (ListsMade - 1) + "].txt");
            String saveFileAs = getTaskListSaveName(ListsMade, useCreatedList, useLoadedList, loadedListName);
            String currentDirectory = System.getProperty("user.dir");
            String oldName = currentDirectory + "Task_List["+(ListsMade-1)+"].txt";
            saveFileAs = saveFileAs;
            //Path source = Paths.get()
            File prevName = new File(oldName);
            File newName = new File(saveFileAs);


            Path currentFile = Paths.get("Task_List["+(ListsMade-1)+"].txt");
            try {
                Files.move(currentFile,currentFile.resolveSibling(saveFileAs),StandardCopyOption.REPLACE_EXISTING);
            }
            catch (FileAlreadyExistsException e) {
                e.printStackTrace();


            } catch (IOException e) {
                e.printStackTrace();
            }

            /*
            boolean success = prevName.renameTo(newName);
            if(success) {
                System.out.println("Success: File Saved as " + saveFileAs + ".");
            }
            else{
                System.out.println("Error Renaming file, try again!");
                saveCurrentTaskList(ListsMade,useCreatedList,useLoadedList,loadedListName);
            }

             */
        }
        if(useLoadedList == 1) {
            System.out.println("Current Task list is " + loadedListName);
            String saveFileAs = getTaskListSaveName(ListsMade, useCreatedList, useLoadedList, loadedListName);
            File oldName = new File(loadedListName);
            File newName = new File(saveFileAs);

            Path currentFile = Paths.get(loadedListName);
            try {
                Files.move(currentFile,currentFile.resolveSibling(saveFileAs),StandardCopyOption.REPLACE_EXISTING);
            }
            catch (FileAlreadyExistsException e) {
                e.printStackTrace();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Task List Saved! Returning to Main Menu");
    }
    public static String getTaskListSaveName(int ListsMade, int useCreatedList, int useLoadedList, String loadedListName)
    {
        String saveAs;
        if(useCreatedList == 1) {
            System.out.println("What name would you like to save Task_List[" + (ListsMade - 1) + "].txt as? (end with .txt)");
            Scanner saveFileAs = new Scanner(System.in);
            saveAs = saveFileAs.nextLine();
            while (!(saveAs.endsWith(".txt"))) {
                System.out.println("Invalid file name, try again with .txt");
                saveAs = getTaskListSaveName(ListsMade, useCreatedList, useLoadedList,loadedListName);
            }
            return saveAs;
        }
        if(useLoadedList == 1)
        {
            System.out.println("What name would you like to save " + loadedListName +" as?");
            Scanner saveFileAs = new Scanner(System.in);
            saveAs = saveFileAs.nextLine();
            while (!(saveAs.endsWith(".txt"))) {
                System.out.println("Invalid file name, try again with .txt");
                saveAs = getTaskListSaveName(ListsMade, useCreatedList, useLoadedList, loadedListName);
            }
            return saveAs;
        }
        return "never gonna make it here";
    }

    public static void removeNullLines(String FileName)
    {
        try(FileReader reader = new FileReader(FileName)) {

            String output = "";
            BufferedReader readList = new BufferedReader(reader);
            String lineRead = null;
            while((lineRead = readList.readLine())!= null)
            {
                if(!(lineRead.isEmpty()))
                {
                    output = output + lineRead + System.lineSeparator();
                }
            }
            Formatter f = new Formatter(FileName);
            f.format(output);
            f.close();
            readList.close();
            reader.close();


        }
        catch (FileNotFoundException e) {
            System.out.println("File Not Found, Sorry.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File IO Exception, Sorry.");
            e.printStackTrace();
        }
    }


}
