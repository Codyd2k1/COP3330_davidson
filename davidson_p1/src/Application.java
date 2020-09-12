public class Application {


    public static void assertEquals(String origin, String output)
    {
        if (origin.equals(output))
        {
            System.out.println("Good");
        }
        else
        {
            System.out.println("Bad");
        }

    }

    public static void main(String[] args)
    {
        /*Encrypter Encryption = new Encrypter();
        Encryption.encrypt("1234");
        Decrypter Decryption = new Decrypter();
        Decryption.decrypt("0189");
        */
        Encrypter e = new Encrypter();
        assertEquals("0189", e.encrypt("1234"));
        Decrypter d = new Decrypter();
        assertEquals("1234", d.decrypt("0189"));
    }




//hes only going to call our encrypt method and decrypt method
    //make sure to have 0 user input in these two methods
    //must return a string, so program takes a string, goes to int, then back to string.
}
