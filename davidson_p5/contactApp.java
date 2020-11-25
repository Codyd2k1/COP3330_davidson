import java.util.InputMismatchException;
import java.util.Scanner;

public class contactApp extends taskApp{
    public static contactList contactList= new contactList();
    public static Scanner s = new Scanner(System.in);


    public static void mainMenu()
    {
        printMainMenu();
        int mainMenuInput = getMainMenuInput();
        interpretMainMenuInput(mainMenuInput);
    }
    public static void printMainMenu()
    {
        System.out.println("Main Menu\n" +
                "---------\n" +
                "\n" +
                "1) create a new list\n" +
                "2) load an existing list\n" +
                "3) quit\n");
    }
    public static void interpretMainMenuInput(int mainMenuInput)
    {
        if(mainMenuInput == 1)
        {
            if(contactList.getSize() != 0)
            {
                contactList.removeAllExternal();
            }
            System.out.println("Contact list Created, heading to List Operation Menu");
            listOperationMenu();
        }
        if(mainMenuInput == 2)
        {

            viewFileOptions();
            String fileName = getUserInputFileName();
            contactList.loadContactList(fileName);
            System.out.println("Contact List Loaded");
            listOperationMenu();
        }
        if(mainMenuInput == 3)
        {
            return;
        }
    }


    public static void listOperationMenu()
    {
        printListOperationMenu();
        int listOperationInput = getListOperationMenuInputCL();
        s.nextLine();
        interpretListOperationMenuInput(listOperationInput);
    }

    public static void interpretListOperationMenuInput(int listOperationInput)
    {
        if(listOperationInput == 1)
        {
            String printItems = contactList.viewList();
            System.out.println(printItems);
            listOperationMenu();
        }
        if(listOperationInput == 2)
        {
            String Fname = getFName();
            String Lname = getLName();
            String PhoneNum = getPhoneNum();
            String email = getEmail();
            try {
                contactList.addItem(Fname, Lname, PhoneNum, email);
            }catch (IllegalArgumentException e)
            {
                System.out.println("Sorry, you entered all blank info, try again!");
                interpretListOperationMenuInput(2);
            }
            listOperationMenu();
        }
        if(listOperationInput == 3)
        {
            String printItems = contactList.viewList();
            System.out.println(printItems);
            int itemNum = getUserInputEditTaskItem();
            while(itemNum > (contactList.getSize()-1) || itemNum < 0)
            {
                System.out.println("Invalid input, try again.");

                itemNum = getUserInputEditTaskItem();
            }
            System.out.println("Enter new info for item #" + itemNum + ".");
            String Fname = getFName();
            String Lname = getLName();
            String PhoneNum = getPhoneNum();
            String email = getEmail();
            try {
                contactList.editItem(itemNum,Fname,Lname,PhoneNum,email);
            }catch (IllegalArgumentException e)
            {
                System.out.println("Sorry, you entered all blank info, try again!");
                interpretListOperationMenuInput(3);
            }

            listOperationMenu();
        }
        if(listOperationInput == 4)
        {
            String printItems = contactList.viewList();
            System.out.println(printItems);
            int itemNum = getUserInputRemoveTaskNum();
            while(itemNum > contactList.getSize()-1 || itemNum < 0)
            {
                System.out.println("Invalid input, try again.");
                itemNum = getUserInputRemoveTaskNum();
            }
            contactList.removeItem(itemNum);
            System.out.println("Item " + itemNum+" successfully removed.");
            listOperationMenu();
        }
        if(listOperationInput == 5)
        {
            String saveFileName = getUserInputSaveFileName();
            contactList.saveContactList(saveFileName);
            System.out.println("Contact list successfully saved as "+saveFileName+"");
            listOperationMenu();
        }
        if(listOperationInput == 6)
        {
            mainMenu();
        }
    }

    public static void printListOperationMenu()
    {
        System.out.println("List Operation Menu\n" +
                "---------\n" +
                "\n" +
                "1) view the list\n" +
                "2) add an item\n" +
                "3) edit an item\n" +
                "4) remove an item\n" +
                "5) save the current list\n" +
                "6) quit to the main menu");
    }
    public static String getFName()
    {
        System.out.println("Enter Contact First Name, if blank press enter:");
        String firstName = s.nextLine();
        return firstName;
    }
    public static String getLName()
    {
        System.out.println("Enter Contact Last name, if blank press enter:");
        String lastname = s.nextLine();
        return lastname;
    }
    public static String getPhoneNum()
    {
        System.out.println("Enter Phone number, format xxx-xxx-xxxx, if blank press enter:");
        String phoneNum = s.nextLine();
        if(phoneNum.equals(""))
        {
            return phoneNum;
        }
        else {
            while (!phoneNum.matches("\\d\\d\\d-\\d\\d\\d-\\d\\d\\d\\d")) {
                if(phoneNum.equals(""))
                {
                    break;
                }
                System.out.println("Invalid format, try again!");
                phoneNum = getPhoneNum();

            }
        }
        return phoneNum;
    }
    public static String getEmail()
    {
        System.out.println("Enter Email, if blank, press enter:");
        String email = s.nextLine();
        return email;
    }
    public static int getListOperationMenuInputCL()
    {
        int goodEntry = 0;
        int ListMenuInput = 3;
        while(goodEntry == 0) {
            try {
                ListMenuInput = s.nextInt();
                if(ListMenuInput > 8 || ListMenuInput < 1)
                {
                    System.out.println("Invalid Entry, Try again:");
                    goodEntry = 0;
                }
                else if (ListMenuInput < 7 && ListMenuInput > 0){
                    goodEntry = 1;
                }
            }catch (InputMismatchException e)
            {
                System.out.println("Invalid Entry, Try again:");
                s.nextLine();
                goodEntry = 0;
            }

        }

        return ListMenuInput;
    }
}
