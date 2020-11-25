import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.File;
import java.nio.InvalidMarkException;
import java.util.zip.DataFormatException;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class contactListTest {

    @Test
    public void addingItemsIncreasesSize()
    {
        contactList c = new contactList();
        assertEquals(0, c.getSize());
        c.addItem("firstname","lastname","386-214-3329","gmail.com");
        assertEquals(1,c.getSize());
    }
    @Test
    public void editingItemsFailsWithAllBlankValues()
    {
        assertThrows(IllegalArgumentException.class, ()->{
            contactList c = new contactList();
            c.addItem("","","","");
        });
    }
    @Test
    public void editingItemsFailsWithInvalidIndex()
    {
        assertThrows(IndexOutOfBoundsException.class, ()->{
            contactList c = new contactList();
            c.addItem("firstname","lastname","386-214-3329","gmail.com");
            c.editItem(100,"Cody","Davidson","386-453-8799","email.com");
        });
    }
    @Test
    public void editingSucceedsWithBlankFirstName()
    {
        assertAll(()->{
            contactList c = new contactList();
            c.addItem("firstname","lastname","386-214-3329","gmail.com");
            c.editItem(0,"","Davidson","386-453-8799","email.com");
            assertEquals("",c.contactList.get(0).getFirstName());
        });
    }
    @Test
    public void editingSucceedsWithBlankLastName()
    {
        assertAll(()->{
            contactList c = new contactList();
            c.addItem("firstname","lastname","386-214-3329","gmail.com");
            c.editItem(0,"firstname","","386-453-8799","email.com");
            assertEquals("",c.contactList.get(0).getLastName());
        });
    }
    @Test
    public void editingSucceedsWithBlankPhone()
    {
        assertAll(()->{
            contactList c = new contactList();
            c.addItem("firstname","lastname","386-214-3329","gmail.com");
            c.editItem(0,"firstname","","","email.com");
            assertEquals("",c.contactList.get(0).getPhoneNumber());
        });
    }
    @Test
    public void editingSucceedsWithNonBlankValues()
    {
        assertAll(()->{
            contactList c = new contactList();
            c.addItem("firstname","lastname","386-214-3329","gmail.com");
            c.editItem(0,"firstname","davidson","386-453-8799","email.com");
            assertEquals("firstname",c.contactList.get(0).getFirstName());
            assertEquals("davidson",c.contactList.get(0).getLastName());
            assertEquals("386-453-8799",c.contactList.get(0).getPhoneNumber());
            assertEquals("email.com",c.contactList.get(0).getEmail());

        });
    }
    @Test
    public void newListIsEmpty()
    {
        contactList c = new contactList();
        assertEquals(0,c.getSize());
    }
    @Test
    public void removingItemsDecreasesSize()
    {
        contactList c = new contactList();
        c.addItem("firstname","lastname","386-214-3329","gmail.com");
        assertEquals(1,c.getSize());
        c.removeItem(0);
        assertEquals(0,c.getSize());
    }
    @Test
    public void removingItemsFailsWithInvalidIndex()
    {
        assertThrows(IndexOutOfBoundsException.class,()->{
            contactList c = new contactList();
            c.addItem("firstname","lastname","386-214-3329","gmail.com");
            c.removeItem(100);
        });
    }
    @Test
    public void savedContactListCanBeLoaded()
    {
        assertAll(()->{
            contactList c = new contactList();
            c.addItem("firstname","lastname","386-214-3329","gmail.com");
            c.editItem(0,"firstname","davidson","386-453-8799","email.com");
            c.saveContactList("saveCONTACTFileTest.txt");
            assertTrue((new File("saveCONTACTFileTest.txt").exists()));
            c.loadContactList("saveCONTACTFileTest.txt");
            assertEquals("firstname",c.contactList.get(0).getFirstName());
            assertEquals("davidson",c.contactList.get(0).getLastName());
            assertEquals("386-453-8799",c.contactList.get(0).getPhoneNumber());
            assertEquals("email.com",c.contactList.get(0).getEmail());

        });
    }
}
