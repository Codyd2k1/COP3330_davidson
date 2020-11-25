import java.util.Scanner;

public class mainApp {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args)
    {
        mainMainMenu();
    }
    public static void mainMainMenu()
    {
        PrintMainMenu();
        int mainMenuInput = getMainMenuInput();
        interpretMainMenuInput(mainMenuInput);
    }
    public static void interpretMainMenuInput(int mainMenuInput)
    {
        if(mainMenuInput == 1)
        {
            taskApp t = new taskApp();
            t.mainMenu();
            mainMainMenu();
        }
        else if(mainMenuInput == 2)
        {
            contactApp c = new contactApp();
            c.mainMenu();
            mainMainMenu();
        }
        else if (mainMenuInput == 3)
        {
            System.exit(0);
        }
    }
    public static void PrintMainMenu()
    {
        System.out.println("Select Your Application\n" +
                "-----------------------\n" +
                "\n" +
                "1) task list\n" +
                "2) contact list\n" +
                "3) quit\n");
    }
    public static int getMainMenuInput()
    {
        System.out.println("Enter option #:");
        int mainMenuInput = scanner.nextInt();
        while(mainMenuInput < 1 || mainMenuInput > 3)
        {
            System.out.println("Sorry, invalid input, try again.");
            scanner.nextLine();
            mainMenuInput = getMainMenuInput();
        }
        return mainMenuInput;
    }
}
