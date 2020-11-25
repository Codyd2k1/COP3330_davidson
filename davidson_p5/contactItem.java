public class contactItem {
    String sFirstName;
    String sLastName;
    String sPhoneNumber;
    String sEmail;

    public contactItem(String firstName, String LastName, String phoneNumber,  String email)
    {
        if(firstName.equals("") && LastName.equals("") && phoneNumber.equals("")&& email.equals(""))
        {
            throw new IllegalArgumentException();
        }
        else{
            sFirstName = firstName;
            sLastName = LastName;
            sPhoneNumber = phoneNumber;
            sEmail = email;
        }
    }
    public String toString()
    {
        String returnString = "";
        if(!sFirstName.equals(""))
        {
            returnString = returnString + "Name: " + sFirstName+" ";
        }
        if(!sLastName.equals("") && !sFirstName.equals(""))
        {
            returnString = returnString +  sLastName;
        }
        if(!sLastName.equals("")&& sFirstName.equals(""))
        {
            returnString = returnString + "Last Name: "+sLastName;
        }
        if(!sPhoneNumber.equals(""))
        {
            returnString = returnString + "\nPhone number (xxx-xxx-xxxx): "+sPhoneNumber;
        }
        if(!sEmail.equals(""))
        {
            returnString = returnString + "\nEmail: "+sEmail;
        }
        return returnString;
    }


    public void editTask(String fName, String lName, String PhoneNumber, String Email)
    {
        if(fName.equals("") && lName.equals("") && PhoneNumber.equals("") && Email.equals(""))
        {
            throw new IllegalArgumentException();
        }
        else
        {
            sFirstName = fName;
            sLastName = lName;
            sPhoneNumber = PhoneNumber;
            sEmail = Email;
        }
    }

    public String getFirstName()
    {
        return sFirstName;
    }
    public String getLastName()
    {
        return sLastName;
    }
    public String getPhoneNumber()
    {
        return sPhoneNumber;
    }
    public String getEmail()
    {
        return sEmail;
    }

}
