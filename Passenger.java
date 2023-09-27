package src;

import java.util.LinkedList;
import java.util.Scanner;


public class Passenger 
{
  private String name;
  private String pan;
  private int age;
  private String phno;
  private String emailId;
  private String nationality;
  private String occupation;
  
  Scanner scan=new Scanner(System.in);
  
  //Constructor:
  public Passenger()
  {
    System.out.print("\nEnter Passenger's Name : ");
    name=scan.nextLine();
    System.out.print("\nEnter Passenger's PAN : ");
    pan=scan.next();
    try
    {
      if(panValidity(pan) == false)
          throw new InvalidPanException();
    }

    catch(InvalidPanException e)
    {
      System.out.println(e);
      System.out.print("\nEnter Passenger's PAN : ");
      pan=scan.next();
    }

    System.out.print("\nEnter Passenger's Age : ");
    age=scan.nextInt();
    
    System.out.print("\nEnter Passenger's Phone number : ");
    phno=scan.next();
    System.out.print("\nEnter Passenger's Email Id : ");
    emailId=scan.next();
    System.out.print("\nEnter Passenger's nationality : ");
    nationality=scan.next();
    System.out.print("\nEnter Passenger's occupation : ");
    occupation=scan.next();
  }
  
  
  public String getName() {return name;}
  public String getPan() {return pan;}
  public int getAge() {return age;}
  public String getPhno() {return phno;}
  public String getEmailId() {return emailId;}
  public String getNationality() {return nationality;}
  public String getOccupation() {return occupation;}


  public void setName(String name) {this.name=name;}
  public void setPan(String pan) {this.pan=pan;}
  public void setAge(int age) {this.age=age;}
  public void setPhno(String phno) {this.phno=phno;}
  public void setEmailId(String emailId) {this.emailId=emailId;}
  public void setNationality(String nationality) {this.nationality=nationality;}
  public void setOccupation(String occupation) {this.occupation=occupation;}

  

  public Ticket bookTicket(String id, LinkedList<Flight> arr)
  {
     // AllFLights array is passed here from main
     Flight dummy = new Flight();
     Flight flight = dummy.searchFlight(arr, id);

     Ticket ticket = new Ticket(this, flight, 0);
     
     ticket.bookTicket();

     return ticket;
  }


  public LinkedList<Ticket> cancelTicket(int id, LinkedList<Ticket> tickets)
  {
    for(Ticket o : tickets)
    {
      if(o.getTicketID() == id)
      {
          o.cancelTicket();
          tickets.remove(o);
          
      }
    }

    return  tickets;
    
  }

  public boolean panValidity(String pan)
	{
		boolean t = true;
		if(pan.length() == 10)
		{
			for(int i=0; i<5; ++i)
			{
				char ch=pan.charAt(i);
				if(!(Character.isLetter(ch)))
					return false;
				else t=true;
			}
			for(int i=5; i<9; ++i)
			{
				char ch=pan.charAt(i);
				if(!(Character.isDigit(ch)))
					return false;
				else t = true;
			}
		    char ch=pan.charAt(9);
			if(!(Character.isLetter(ch))) return false;
			else t= true;
			
			return t;
		}
		
		else return false;
		
	}
  
  
}