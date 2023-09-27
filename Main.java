import java.io.Console;
import java.util.LinkedList;
import java.util.Scanner;


import src.*;

public class Main
{
    public static void main(String[] args) {
        Admin admin = new Admin(1105,"admin");
        FoodCourt fCourt = new FoodCourt("data/food.csv");
        Flight obj = new Flight();
        String s,fid = null;
        String order[] = null;
        Ticket ticket = null;
        Passenger person = null;
        LinkedList<Ticket> AllTickets = new LinkedList<>();
        LinkedList<Flight> AllFlights = obj.createFlightList("data/flight.csv");
        Scanner scan = new Scanner(System.in);


        Console console = System.console();

        System.out.println("****WELCOME TO AIRLINE RESERVATION SYSTEM***");
        System.out.println("1. Enter as Admin");
        System.out.println("2. Enter as User");
        int choice = scan.nextInt();

        switch(choice)
        {
            case 1:
            System.out.print("Enter User ID :");
            int ID = scan.nextInt();
            //int ID = 1105;
            //String password = "admin";
            System.out.println("Enter Password :");
            char[] pw = console.readPassword();
            String password = new String(pw);

            admin.logIn(ID, password);
            if(!admin.getLoggedInStatus()) 
            {
                System.out.println("Incorrect Credentials");
                System.exit(-1);
            }

            else
            {
                System.out.println("Logged in");
                System.out.print("1.Add new flight\n" + 
                                 "2.Delete existing flight\n" +
                                 "3.View existing flights\n"+
                                 "4.View menu in FoodCourt\n" +
                                 "5.Add new items in FoodCourt\n"
                                  );
                 System.out.print("Select Option :");

                choice = scan.nextInt();

                if(choice == 1)
                {
                    System.out.println("ADD NEW FLIGHT DETAILS");
                    AllFlights = obj.addNewFlight(AllFlights);
                    obj.saveChanges(AllFlights);
                }
            
                else if(choice == 2)
                {
                    System.out.println("Enter flight ID to delete");
                    String id = scan.next();
                    Flight f = obj.searchFlight(AllFlights, id);
                    AllFlights.remove(f);
                    obj.saveChanges(AllFlights);

                }

                else if(choice == 3)
                {
                    System.out.print("1.Sort Flights by Economy Price \n" + 
                                 "2.Sort Flights by Premium Economy Price\n" +
                                 "3.Sort Flights by Business Price\n"+
                                 "4.Sort Flights by Rating\n");

                    System.out.print("Select Option :");
                    choice = scan.nextInt();

                    if(choice == 1 )
                    {
                        AllFlights = obj.sortbyEcoPrice(AllFlights);
                        for(Flight f : AllFlights) f.display();
                        
                    }

                    else if(choice == 2 )
                    {
                        AllFlights = obj.sortbyPremiumEco(AllFlights);
                        for(Flight f : AllFlights) f.display();
                        
                    }

                    if(choice == 3 )
                    {
                        AllFlights = obj.sortbyBusinessPrice(AllFlights);
                        for(Flight f : AllFlights) f.display();
                        
                    }

                    if(choice == 4 )
                    {
                        AllFlights = obj.sortbyRating(AllFlights);
                        for(Flight f : AllFlights) f.display();
                    }
                
                
                }

                else if(choice == 4)
                {
                    fCourt.displayMenu();
                }

                else if(choice == 5)
                {
                    System.out.print("Enter food name :");
                    String food = scan.nextLine();
                    System.out.print("Enter food price :");
                    int price = scan.nextInt();

                    fCourt.addFood(food, price);
                    fCourt.saveChanges();

                }

                System.exit(0);
            }

            case 2:
            while(true)
               {
                 System.out.print("1. Enter Passenger Details\n" +
                            "2. Place Order for Food\n" +
                            "3. Sort Flights by Economy Price \n" + 
                            "4. Sort Flights by Premium Economy Price\n" +
                            "5. Sort Flights by Business Price\n"+
                            "6. Sort Flights by Rating\n"   +
                            "7. Book Flight\n" +
                            "8. Cancel Ticket\n"+
                             "9. Exit Application\n" );
                System.out.print("Select Option :");
                choice = scan.nextInt();

                if(choice == 1)
                {
                    System.out.println("---Passenger Details---");
                    person = new Passenger();
                    System.out.println("-----------------------");
                }

                else if(choice == 2)
                {
                    System.out.println("---Food Court---");
                    fCourt.displayMenu();
                    System.out.print("Enter Order (separate order by comma) :");
                    scan.nextLine();
                    s = scan.nextLine();
                    order = s.split(",");
                    fCourt.placeOrder(order);
                    fCourt.showOrder();
                    System.out.println("-----------------------"); 
                }

               else if(choice == 3 )
                {
                        AllFlights = obj.sortbyEcoPrice(AllFlights);
                        for(Flight f : AllFlights) f.display();
                        obj.saveChanges(AllFlights);
                        
                }

                else if(choice == 4 )
                 {
                        AllFlights = obj.sortbyPremiumEco(AllFlights);
                        for(Flight f : AllFlights) f.display();
                        obj.saveChanges(AllFlights);
                        
                 }

                else if(choice == 5 )
                 {
                        AllFlights = obj.sortbyBusinessPrice(AllFlights);
                        for(Flight f : AllFlights) f.display();
                        obj.saveChanges(AllFlights);
                        
                 }

                else if(choice == 6)
                 {
                        AllFlights = obj.sortbyRating(AllFlights);
                        for(Flight f : AllFlights) f.display();
                        obj.saveChanges(AllFlights);
                 }

                else if(choice == 7)
                 {

                    System.out.println("----Book Ticket----");
                    System.out.print("Enter flight ID to book : ");
                    fid = scan.next();
                    System.out.println("Choose Class- ");
                    System.out.println("0: Economy ; 1: Business ; 2: Premium Economy");
                    int c = scan.nextInt();

                    Flight flight = obj.searchFlight(AllFlights, fid);
                    try{
                        if(person==null)
                            throw new NoPassengerException();
                    }
                    catch(NoPassengerException e)
                    {
                        System.out.println(e);
                        System.out.println("\nEnter Passenger Details : ");
                        person = new Passenger();
                    }
                    finally
                    {   if(order!= null)
                        {
                              ticket = new Ticket(person, flight, c, order);
                        }
                         else
                         {
                              ticket = new Ticket(person, flight, c);

                         }
                    }
                    AllTickets.add(ticket);
                    ticket.bookTicket();
                    ticket.applyDiscount();
                    ticket.displayTicket();
                    obj.saveChanges(AllFlights);
                    ticket.generatePDF();
                    ticket.saveChanges("data/ticket.csv", AllTickets);
                    System.out.println("\nTicket Successfully Booked and Printed !\n");

                        // System.out.println("----Book Ticket----");
                        // System.out.print("Enter flight ID to book : ");
                        // fid = scan.next();
                        // Flight flight = obj.searchFlight(AllFlights, fid);
                        // if(order!= null)
                        //     ticket = new Ticket(person, flight, 0, order);
                        // else
                        //     ticket = new Ticket(person, flight, 0);

                        // ticket.bookTicket();
                        // ticket.applyDiscount();
                        // ticket.displayTicket();
                        // obj.saveChanges(AllFlights);
                        // ticket.generatePDF();
                        // System.out.println("Ticket Successfully Booked and Printed !");
                 }

                else if(choice == 8)
                {
                        System.out.print("Enter ticket ID to Cancel: ");
                        int id = scan.nextInt();
                        AllTickets =  person.cancelTicket(id, AllTickets);
                        ticket.saveChanges("data/ticket.csv", AllTickets);
                        System.out.println("\nTicket Cancelled Successfully !\n");

                }

                else if(choice == 9)
                {
                    System.exit(0);
                }
            }
        }
        scan.close();
    }
}