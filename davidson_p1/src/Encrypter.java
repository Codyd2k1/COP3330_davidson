import java.util.Scanner;


public class Encrypter {
    //splits strings into arrays
    public static char[] splitString(String numbertoencrypt)
    {

        char[] entryarray = numbertoencrypt.toCharArray();

        return entryarray;
    }


    public static int charToInt(char entryChar)
    {
        return ((int)entryChar - '0');

    }

    public static int algorithm(int numToEncrypt)
    {

        return (numToEncrypt + 7)%10;

    }

    public static String encrypt(String numbertoencrypt)
    {


        char entryarray[] = splitString(numbertoencrypt);
        char a = entryarray[0];
        char b = entryarray[1];
        char c = entryarray[2];
        char d = entryarray[3];

        int first;
        int second;
        int third;
        int fourth;
        //these integers make it much easier to read rather than calling functions over and over.
        first = algorithm(charToInt(a));
        second = algorithm(charToInt(b));
        third = algorithm(charToInt(c));
        fourth = algorithm(charToInt(d));


        String firststring = Integer.toString(first);
        String secondstring = Integer.toString(second);
        String thirdstring = Integer.toString(third);
        String fourthstring = Integer.toString(fourth);

        String answer = thirdstring + fourthstring + firststring + secondstring;


        return answer;

    }








}
