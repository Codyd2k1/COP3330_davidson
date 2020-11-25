import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class contactList implements genericMethodsInterface{
    List<contactItem> contactList = new ArrayList<contactItem>();


    public void removeAllExternal()
    {
        contactList.removeAll((ArrayList)contactList);
    }
    public String viewList()
    {
        String printItems = viewList((ArrayList) contactList);
        return printItems;
    }
    public void addItem(String FirstName, String LastName, String PhoneNumber, String Email)
    {

        contactItem c = new contactItem(FirstName,LastName,PhoneNumber,Email);
        contactList.add(c);
    }
    public void editItem(int itemNum, String FirstName, String LastName, String PhoneNumber, String Email)
    {
        checkIndex((ArrayList)contactList, itemNum);
        if(FirstName.equals("") && LastName.equals("") && PhoneNumber.equals("")&& Email.equals(""))
        {
            throw new IllegalArgumentException();
        }
        else
        {
            contactItem c = new contactItem(FirstName,LastName,PhoneNumber,Email);
            contactList.set(itemNum,c);
        }
    }
    public void removeItem(int itemNum)
    {
        checkIndex((ArrayList)contactList,itemNum);
        contactList.remove(itemNum);
    }
    public int getSize()
    {
        return contactList.size();
    }
    public void loadContactList(String FileName)
    {
        if(contactList.size() != 0)
        {
            removeAll((ArrayList)contactList);
        }
        Scanner S;
        File f;
        try
        {
            f = new File(FileName);
            S = new Scanner(f);
            int numLines = countNumLinesInLoadFile(f);

            for(int i = 0; i < numLines/4; i++)
            {

                String FirstName = S.nextLine();
                String LastName = S.nextLine();
                String PhoneNumber = S.nextLine();
                String Email = S.nextLine();
                if(FirstName.equals("") && LastName.equals("") && PhoneNumber.equals("")&& Email.equals(""))
                {
                    continue;
                }
                else{
                    contactItem c = new contactItem(FirstName,LastName,PhoneNumber,Email);
                    contactList.add(c);
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
    }
    public void saveContactList(String fileName)
    {
        File savedList = new File(fileName);
        FileWriter f = null;
        try {
            savedList.createNewFile();
            f = new FileWriter(savedList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < contactList.size(); i++)
        {
            try {
                f.write(contactList.get(i).getFirstName() + "\r\n");
                f.write(contactList.get(i).getLastName() + "\r\n");
                f.write(contactList.get(i).getPhoneNumber() + "\r\n");
                f.write(contactList.get(i).getEmail() + "\r\n");
                f.flush();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

        }
    }

}
