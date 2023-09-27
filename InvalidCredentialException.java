package src;

public class InvalidCredentialException extends Exception
{
 
  public InvalidCredentialException() 
  {
    super("\nInvalidCredentialException has Occured : Check UserID and Password\n");
  }
}