package src;
public class Admin {

    private int UserID;
    private String Password;
    private boolean loggedinStatus;

    public Admin (int UserID, String Password)
    {
        this.UserID = UserID;
        this.Password = Password;
    }

    public void logIn(int UserID, String Password)
    {
        try{
        if(this.UserID == UserID && this.Password.equals(Password)) loggedinStatus = true;
        else
        {
            loggedinStatus = false;
            throw new InvalidCredentialException();
        }
        }
        catch(InvalidCredentialException e)
        {
            System.out.println(e);
        }

    }

    public boolean getLoggedInStatus()
    {
        return this.loggedinStatus;
    }

}
