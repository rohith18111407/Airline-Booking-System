package src;

class InvalidPanException extends Exception
{
  
    public InvalidPanException() {
    super("\nInvalidPanException has Occured : PAN format doesn't match\n");
  }
}