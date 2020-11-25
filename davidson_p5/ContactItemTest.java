import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ContactItemTest {




    @Test
    public void creationFailsWithAllBlankValues()
    {
        assertThrows(IllegalArgumentException.class, () -> new contactItem("","","",""));
    }
    @Test
    public void creationSucceedsWithBlankEmail()
    {
        contactItem t = new contactItem("FirstName","LastName","123-123-1234", "");
        assertEquals("FirstName", t.getFirstName());
        assertEquals("LastName",t.getLastName());
        assertEquals("123-123-1234",t.getPhoneNumber());
        assertEquals("",t.getEmail());
        assertAll(()->new contactItem("FirstName","LastName","123-123-1234", ""));
    }
    @Test
    public void creationSucceedsWithBlankFirstName()
    {
        contactItem t = new contactItem("","LastName","123-123-1234", "testemail@email.com");
        assertEquals("", t.getFirstName());
        assertEquals("LastName",t.getLastName());
        assertEquals("123-123-1234",t.getPhoneNumber());
        assertEquals("testemail@email.com",t.getEmail());
        assertAll(()->new contactItem("","LastName","123-123-1234", "testemail@email.com"));

    }
    @Test
    public void creationSucceedsWithBlankLastName()
    {
        contactItem t = new contactItem("FirstName","","123-123-1234", "testemail@email.com");
        assertEquals("FirstName", t.getFirstName());
        assertEquals("",t.getLastName());
        assertEquals("123-123-1234",t.getPhoneNumber());
        assertEquals("testemail@email.com",t.getEmail());
        assertAll(()->new contactItem("FirstName","","123-123-1234", "testemail@email.com"));

    }
    @Test
    public void creationSucceedsWithBlankPhone()
    {
        contactItem t = new contactItem("FirstName","LastName","", "testemail@email.com");
        assertEquals("FirstName", t.getFirstName());
        assertEquals("LastName",t.getLastName());
        assertEquals("",t.getPhoneNumber());
        assertEquals("testemail@email.com",t.getEmail());
        assertAll(()->new contactItem("FirstName","LastName","", "testemail@email.com"));

    }
    @Test
    public void creationSucceedsWithNonBlankValues()
    {
        contactItem t = new contactItem("FirstName","LastName","123-123-1234", "testemail@email.com");
        assertEquals("FirstName", t.getFirstName());
        assertEquals("LastName",t.getLastName());
        assertEquals("123-123-1234",t.getPhoneNumber());
        assertEquals("testemail@email.com",t.getEmail());
        assertAll(()->new contactItem("FirstName","LastName","123-123-1234", "testemail@email.com"));

    }
    @Test
    public void editingFailsWithAllBlankValues()
    {
        assertThrows(IllegalArgumentException.class, ()->new contactItem("FirstName","LastName","PhoneNumber","Email").editTask("","","",""));
    }
    @Test
    public void editingSucceedsWithBlankEmail()
    {
        assertAll(()->new contactItem("FirstName","LastName","PhoneNumber","Email").editTask("FirstName","LastName","PhoneNumber",""));
    }
    @Test
    public void editingSucceedsWithBlankFirstName()
    {
        assertAll(()->new contactItem("FirstName","LastName","PhoneNumber","Email").editTask("","LastName","PhoneNumber","testemail@email.com"));
    }
    @Test
    public void editingSucceedsWithBlankLastName()
    {
        assertAll(()->new contactItem("FirstName","LastName","PhoneNumber","Email").editTask("FirstName","","PhoneNumber","testemail@email.com"));
    }
    @Test
    public void editingSucceedsWithBlankPhone()
    {
        assertAll(()->new contactItem("FirstName","LastName","PhoneNumber","Email").editTask("FirstName","LastName","","testemail@email.com"));
    }
    @Test
    public void editingSucceedsWithNonBlankValues()
    {
        assertAll(()->new contactItem("FirstName","LastName","PhoneNumber","Email").editTask("FirstName","LastName","PhoneNumber","testemail@email.com"));
    }
    @Test
    public void testToString()
    {
        contactItem t = new contactItem("FirstName","LastName","PhoneNumber","Email");
        assertEquals("Name: FirstName LastName\nPhone number (xxx-xxx-xxxx): PhoneNumber\nEmail: Email", t.toString());
    }
}
