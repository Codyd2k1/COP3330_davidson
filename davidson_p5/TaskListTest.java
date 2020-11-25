import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.File;
import java.nio.InvalidMarkException;
import java.util.zip.DataFormatException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void addingItemsIncreasesSize()
    {
        taskList t = new taskList();
        t.addItem("2025-07-01", "Task 1", "Task 1 test Size");
        assertEquals(1, t.getSize());
    }
    @Test
    public void completingTaskItemChangesStatus()
    {
        taskList t = new taskList();
        t.addItem("2025-07-01", "Task 1", "Task 1 test completion status");
        t.markItemCompleted(0);
        String completedDescription = t.taskList.get(0).getDescription();
        assertTrue(completedDescription.contains("     *TASK COMPLETED*"));
    }
    @Test
    public void completingTaskItemFailsWithInvalidIndex()
    {
        assertThrows(IndexOutOfBoundsException.class, ()->new taskList().markItemCompleted(10));
    }
    @Test
    public void editingItemDescriptionFailsWithInvalidIndex()
    {
        assertThrows(IndexOutOfBoundsException.class,()->new taskList().editTaskDescription(3,"newDate"));
    }
    @Test
    public void editingItemDescriptionSucceedsWithExpectedValue()
    {
        //testing for both exceptions not thrown and equality to be thorough
        assertAll(()->{
            taskList t=  new taskList();
            t.addItem("2025-06-07", "task 1", "Task 1");
            t.editTaskDescription(0,"edited description");
        });
        taskList t=  new taskList();
        t.addItem("2025-06-07", "task 1", "Task 1");
        t.editTaskDescription(0,"edited description");
        assertEquals("edited description", t.taskList.get(0).getDescription());
    }
    @Test
    public void editingItemDueDateSucceedsWithExpectedValue()
    {
        //testing for both exceptions not thrown and equality to be thorough
        assertAll(()->{
            taskList t=  new taskList();
            t.addItem("2025-06-07", "task 1", "Task 1");
            t.editItemDueDate(0,"2025-08-08");
        });
        taskList t=  new taskList();
        t.addItem("2025-06-07", "task 1", "Task 1");
        t.editItemDueDate(0,"2025-08-08");
        assertEquals("2025-08-08",t.taskList.get(0).getDueDate());
    }
    @Test
    public void editingItemTitleFailsWithEmptyString()
    {
        assertThrows(IllegalArgumentException.class,()->{
            taskList t = new taskList();
            t.addItem("2025-06-07", "task 1", "Task 1");
            t.editItemTitle(0,"");
        });
    }
    @Test
    public void editingItemTitleFailsWithInvalidIndex()
    {
        assertThrows(IndexOutOfBoundsException.class, ()->new taskList().editItemTitle(10,"fail test"));
    }
    @Test
    public void editingItemTitleSucceedsWithExpectedValue()
    {
        assertAll(()->{
            taskList t=  new taskList();
            t.addItem("2025-06-07", "task 1", "Task 1");
            t.editItemTitle(0,"task 1 edited test");
        });
        //testing for both exceptions not thrown and equality to be thorough
        taskList t=  new taskList();
        t.addItem("2025-06-07", "task 1", "Task 1");
        t.editItemTitle(0,"task 1 edited test");
        assertEquals("task 1 edited test", t.taskList.get(0).getTitle());
    }
    @Test
    public void editingTaskItemDueDateFailsWithInvalidDateFormat()
    {
        assertThrows(IllegalArgumentException.class, ()->{
           taskList t = new taskList();
           t.addItem("2022-06-07", "task 1", "Task 1");
           t.editItemDueDate(0,"20011112"); //hey, that's my birthday :)
        });
    }
    @Test
    public void editingTaskItemDueDateFailsWithInvalidIndex()
    {
        assertThrows(IndexOutOfBoundsException.class, ()->new taskList().editItemDueDate(10,"2025-06-07"));
    }
    @Test
    public void editingTaskItemDueDateFailsWithInvalidYYYMMDD()
    {
        assertThrows(IllegalArgumentException.class, ()->{
            taskList t = new taskList();
            t.addItem("2022-06-07", "task 1", "Task 1");
            t.editItemDueDate(0,"2001-11-12");
        });
    }
    @Test
    public void gettingItemDescriptionFailsWithInvalidIndex()
    {
        assertThrows(IndexOutOfBoundsException.class, ()->{
           taskList t = new taskList();
           t.addItem("2022-06-07", "task 1", "Task 1");
           t.getItemDescription(100);
        });
    }
    @Test
    public void gettingItemDescriptionSucceedsWithValidIndex()
    {
        assertAll(()->{
            taskList t = new taskList();
            t.addItem("2022-06-07", "task 1", "Task 1");
            String getDescription = t.getItemDescription(0);
            assertEquals("Task 1", getDescription);
        });
    }
    @Test
    public void gettingItemDueDateFailsWithInvalidIndex()
    {
        assertThrows(IndexOutOfBoundsException.class, ()->{
            taskList t = new taskList();
            t.addItem("2022-06-07", "task 1", "Task 1");
            t.getItemDueDate(100);
        });
    }
    @Test
    public void gettingItemDueDateSucceedsWithValidIndex()
    {
        assertAll(()->{
            taskList t = new taskList();
            t.addItem("2022-06-07", "task 1", "Task 1");
            String getDueDate = t.getItemDueDate(0);
            assertEquals("2022-06-07",getDueDate);
        });
    }
    @Test
    public void gettingItemTitleFailsWithInvalidIndex()
    {
        assertThrows(IndexOutOfBoundsException.class, ()->{
            taskList t = new taskList();
            t.addItem("2022-06-07", "task 1", "Task 1");
            t.getItemTitle(100);
        });
    }
    @Test
    public void gettingItemTitleSucceedsWithValidIndex()
    {
        assertAll(()->{
            taskList t = new taskList();
            t.addItem("2022-06-07", "task 1", "Task 1");
            String getTitle = t.getItemTitle(0);
            assertEquals("task 1",getTitle);
        });
    }
    @Test
    public void newListIsEmpty()
    {
        taskList t = new taskList();
        assertEquals(0,t.getSize());
    }
    @Test
    public void removingItemsDecreasesSize()
    {
        taskList t = new taskList();
        t.addItem("2022-06-07", "task 1", "Task 1");
        assertEquals(1,t.getSize());
        t.removeItem(0);
        assertEquals(0,t.getSize());
    }
    @Test
    public void removingItemsFailsWithInvalidIndex()
    {
        assertThrows(IndexOutOfBoundsException.class, ()->{
            taskList t = new taskList();
            t.addItem("2022-06-07", "task 1", "Task 1");
            t.removeItem(100);
        });
    }
    @Test
    public void savedTaskListCanBeLoaded()
    {
        //testing for exceptions and for equality to be thorough
        assertAll(()->{
            taskList t = new taskList();
            t.addItem("2022-06-07", "task 1", "Task 1");
            t.saveTaskList("testFile.txt");
            assertTrue((new File("testFile.txt")).exists());
            t.loadTaskList("testFile.txt");
            assertEquals("2022-06-07",t.getItemDueDate(0));
            assertEquals("task 1", t.getItemTitle(0));
            assertEquals("Task 1",t.getItemDescription(0));
        });
    }
    @Test
    public void uncompletingTaskItemChangesStatus()
    {
        taskList t = new taskList();
        t.addItem("2022-06-07", "task 1", "Task 1");
        t.markItemCompleted(0);
        assertTrue(t.taskList.get(0).getDescription().contains("     *TASK COMPLETED*"));
    }
    @Test
    public void uncompletingTaskItemFailsWithInvalidIndex()
    {
        //invalid index overall test:
        assertThrows(IndexOutOfBoundsException.class, ()->{
            taskList t = new taskList();
            t.addItem("2022-06-07", "task 1", "Task 1");
            t.unMarkItemComplete(10);
        });
        //invalid index inside of valid arraylist index -- item is not marked complete:
        assertThrows(InvalidMarkException.class, ()->{
            taskList t = new taskList();
            t.addItem("2022-06-07", "task 1", "Task 1");
            t.markItemCompleted(0);
            t.addItem("2022-06-04", "task 2", "Task 2");
            t.unMarkItemComplete(1);
        });
    }


}
