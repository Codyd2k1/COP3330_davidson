import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class appJUNITTests {

    private static final InputStream STDIN = System.in;
    @AfterEach
    public void resetStdInput()
    {
        System.setIn(STDIN);
    }

    //THESE FIRST FEW TESTS ARE TESTS ON HELPER FUNCTIONS I USE THROUGHOUT THE PROGRAM :)

    @Test
    public void testGetUserInputMain()
    {
        app testApp = new app();
        InputStream MenuOption1 = new ByteArrayInputStream("1".getBytes());
        System.setIn(MenuOption1);
        assertEquals(1,testApp.getUserInputMAIN());
    }

    @Test
    public void testCreateNewList()
    {
        app testApp = new app();
        assertEquals(1,app.CreateNewList(0));
    }

    @Test
    public void testGetListOperationInput1()
    {
        app testApp = new app();
        InputStream LOption1 = new ByteArrayInputStream("1".getBytes());
        System.setIn(LOption1);
        //Scanner s = new Scanner(LOption1);
        assertEquals(1,testApp.getListOperationMenuInput());
    }

    @Test
    public void testGetUserInputFileName()
    {
        app testApp = new app();
        InputStream uInputFile = new ByteArrayInputStream(("TestFile.txt".getBytes()));
        System.setIn(uInputFile);
        assertEquals("TestFile.txt",testApp.getUserInputFILE());
    }

    @Test
    public void CountTasksInList()
    {
        app testApp = new app();
        assertEquals(2,app.CountTasksInList("TestCountTasksInList.txt"));
    }

    @Test
    public void testCountNumCompletedTasksInList()
    {
        app testApp = new app();
        assertEquals(1,app.countNumCompletedTasksInTaskList("TaskListItemMarkedCompleteTest.txt"));
    }
    //LIST OPERATION MENU TESTS
    //OPERATION 1 VIEWS LIST, STARTING AT OPERATION 2: TASK LIST ADD ITEM

    @Test
    public void testTaskListAddItem()
    {
        //InputStream uInputFile = new ByteArrayInputStream((("2"+System.lineSeparator()+"TaskListAddItemTest.txt"+System.lineSeparator()+"2"+System.lineSeparator()+"Added Task"+System.lineSeparator()+"2021-05-06"+System.lineSeparator()+"Added Task Test").getBytes()));
        //System.setIn(uInputFile);
        //app.mainMenu(0,0);
        String OutputofTaskListAddedItemRun = readFileIntoStringForAssertEqualsTesting("TaskListAddItemTest.txt");


        assertEquals("0) [2020-11-17] Task 1: Task 1 For Testing Add Item" +System.lineSeparator()+
                "1) [2020-11-19] Task 2: Task 2 For Testing Add item" + System.lineSeparator()+
                "2) [2021-05-06]: Added Task Test"+System.lineSeparator(),OutputofTaskListAddedItemRun);
    }




    //LMENU OPTION 3 EDIT TASK LIST ITEM

    @Test
    public void testEditTaskList()
    {
        String EditedTaskListOutput = readFileIntoStringForAssertEqualsTesting("TaskListEditItemTest.txt");

        assertEquals("0) [2020-11-17] Task 1: Task 1 For Testing Add Item" +System.lineSeparator()+
                "1) [2020-11-19] Task 2: Task 2 For Testing Add item" +System.lineSeparator()+
                "2) [2021-08-08]: Edited Task Test"+System.lineSeparator(),EditedTaskListOutput);
    }

    //LMENU OPTION 4 REMOVE TASK LIST ITEM
    @Test
    public void testRemoveTaskListItem()
    {
        String RemovedTaskListOutput = readFileIntoStringForAssertEqualsTesting("TaskListRemovedItem.txt");
        assertEquals("0) [2020-11-17] Task 1: Task 1 For Testing Add Item"+ System.lineSeparator() +
                "1) [2020-11-19] Task 2: Task 2 For Testing Add item"+System.lineSeparator(), RemovedTaskListOutput);
    }

    //LMENU OPTION 5 MARK ITEM AS COMPLETE
    @Test
    public void testMarkTaskComplete()
    {
        String TaskMarkedCompleteOutput = readFileIntoStringForAssertEqualsTesting("TaskListItemMarkedCompleteTest.txt");
        assertEquals("0) [2020-11-17] Task 1: Task 1 For Testing Add Item"+System.lineSeparator() +
                "1) [2020-11-19] Task 2: Task 2 For Testing Add item"+System.lineSeparator() +
                "2) [2021-08-08]: Edited Task Test" + "  *COMPLETED*" +System.lineSeparator(), TaskMarkedCompleteOutput);
    }

    //LMENU OPTION 6 UNMARK ITEM AS COMPLETE
    @Test
    public void testUnMarkItemComplete()
    {
        String unMarkedTaskCompleteOutput = readFileIntoStringForAssertEqualsTesting("TaskListUnMarkedItemCompleteTest.txt");
        assertEquals("0) [2020-11-17] Task 1: Task 1 For Testing Add Item"+System.lineSeparator() +
                "1) [2020-11-19] Task 2: Task 2 For Testing Add item"+System.lineSeparator() +
                "2) [2021-08-08]: Edited Task Test"+System.lineSeparator(),unMarkedTaskCompleteOutput);
    }

    //LMENU OPTION 7 SAVE LIST, TESTS IF SAVED LIST HAS BEEN PROPERLY SAVED AND EXISTS.
    @Test
    public void testTaskListSaveList()
    {
        File taskListSavedList = new File("TaskListSavedList.txt");
        assertEquals(true,taskListSavedList.exists());
    }
    //NO NEED TO TEST LIST OPTION 8, RETURN TO MAIN MENU, as it just Calls MainMenu Function.

    public static String readFileIntoStringForAssertEqualsTesting(String Filename)
    {
        String OutputofTaskListAddedItemRun = "";
        try {
            FileReader f = new FileReader(Filename);
            BufferedReader buffer = new BufferedReader(f);

            String LineRead = null;

            while( (LineRead = buffer.readLine()) != null)
            {
                if(LineRead.length()!= 0)
                    OutputofTaskListAddedItemRun = OutputofTaskListAddedItemRun + LineRead+System.lineSeparator();
            }
            buffer.close();
            f.close();
        } catch (FileNotFoundException e) {
            System.out.println("Make sure " + Filename + " is in the file directory. NOTE: NOT IN THE SRC FOLDER.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return OutputofTaskListAddedItemRun;
    }
}

