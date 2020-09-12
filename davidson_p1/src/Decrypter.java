import java.util.Scanner;

public class Decrypter {

    public static char[] splitString(String numbertodecrypt)
    {

        char[] entryarray = numbertodecrypt.toCharArray();

        return entryarray;
    }

    public static int charToInt(char entryChar)
    {
        return ((int)entryChar - '0');

    }

    public static int decryptionrules(int userentryint)
    {
        int decriptedEntry;
        if (userentryint < 7)
        {
            decriptedEntry = (10 + userentryint) - 7;
        }
        else
        {
            decriptedEntry = userentryint - 7;
        }
        return decriptedEntry;

    }


    public static String decrypt(String numbertodecrypt)
    {
        char[] entryarray = splitString(numbertodecrypt);
        char a = entryarray[0];
        char b = entryarray[1];
        char c = entryarray[2];
        char d = entryarray[3];

        //int first = (int)a - '0';
        int first = decryptionrules(charToInt(a));
        int second = decryptionrules(charToInt(b));
        int third = decryptionrules(charToInt(c));
        int fourth = decryptionrules(charToInt(d));

        String firststring = Integer.toString(first);
        String secondstring = Integer.toString(second);
        String thirdstring = Integer.toString(third);
        String fourthstring = Integer.toString(fourth);
        String answer = thirdstring + fourthstring + firststring + secondstring;


        return answer;

    }

}
