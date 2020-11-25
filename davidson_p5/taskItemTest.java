import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.zip.DataFormatException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class taskItemTest {
    @Test
    public void constructorFailsWithInvalidDueDate()
    {
        assertThrows(IllegalArgumentException.class, ()->new taskItem("Title", "Description", "2018-05-04"));
    }
    @Test
    public void constructorFailsWithInvalidTitle()
    {
        assertThrows(IllegalArgumentException.class, ()->new taskItem("", "Description", "2021-05-04"));
    }
    @Test
    public void constructorSucceedsWithValidDueDate()
    {
        assertAll(()->new taskItem("Task Title", "Description", "2021-05-04"));
    }
    @Test
    public void constructorSucceedsWithValidTitle()
    {
        assertAll(()->new taskItem("Task Title", "Description", "2021-05-04"));
    }
    @Test
    public void editingDescriptionSucceedsWithExpectedValue()
    {
        taskItem t = new taskItem("Title", "Description", "2021-05-07");
        t.editDescription("Edited Description");
        assertEquals("Edited Description", t.getDescription());
    }
    @Test
    public void editingDueDateFailsWithInvalidDateFormat()
    {
        assertThrows(IllegalArgumentException.class, ()->new taskItem("Title","Description","2025-06-07").editDueDate("20300607"));
    }
    @Test
    public void editingDueDateFailsWithInvalidYYYMMDD()
    {
        assertThrows(IllegalArgumentException.class, ()->new taskItem("Title","Description","2025-06-07").editDueDate("2019-05-04"));
    }
    @Test
    public void editingDueDateSucceedsWithExpectedValue()
    {
        assertAll(()->new taskItem("Title","Description","2025-06-07").editDueDate("2040-05-06"));
    }
    @Test
    public void editingTitleFailsWithEmptyString()
    {
        assertThrows(IllegalArgumentException.class, ()->new taskItem("Title","Description","2025-06-07").editTitle(""));
    }
    @Test
    public void editingTitleSucceedsWithExpectedValue()
    {
        taskItem t = new taskItem("Title","Description","2025-06-07");
        t.editTitle("New Title");
        assertEquals("New Title", t.getTitle());
        assertAll(()->new taskItem("Title","Description","2025-06-07").editTitle("New Title"));
    }
}
