package src;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Flight
{
    private String FlightID;
    private String FlightName;
    private String FromCity;
    private String ToCity;
    private String BoardingDate;
    private String ArrivalDate;
    private int capacity;
    private int TicketsAvailable;
    private double EconomyPrice;
    private double BusinessPrice;
    private double PremiumEconomyPrice;
    private double rating;


    public String filename;


    public Flight() { }


    public Flight (String FlightID, String FlightName, String FromCity, String ToCity, String BoardingDate, String ArrivalDate, int capacity, double EconomyPrice, double BusinessPrice, double PremiumEconomyPrice, double rating, int TicketsAvailable)
    {
         this.FlightID = FlightID;
         this.FlightName = FlightName;
         this.FromCity = FromCity;
         this.ToCity = ToCity;
         this.BoardingDate = BoardingDate;
         this.ArrivalDate = ArrivalDate;
         this.capacity = capacity; 
         this.EconomyPrice = EconomyPrice;
         this.BusinessPrice = BusinessPrice;
         this.PremiumEconomyPrice = PremiumEconomyPrice;
         this.rating = rating;
         this.TicketsAvailable = TicketsAvailable;
    }

    public LinkedList<Flight> createFlightList(String filename)
    {
        FileReader file;
        this.filename = filename;
        LinkedList<Flight> AllFlights = new LinkedList<>();

        try {

             file = new FileReader(filename);
           
             Scanner Reader = new Scanner(file);
             Reader.nextLine(); // skipping title
    
            while(Reader.hasNextLine())
              {
                    String data = Reader.nextLine();

                    String[] data_array = data.split(",", 14);

                    AllFlights.add(new Flight(data_array[0], data_array[1], data_array[2], data_array[3], data_array[4]+ "  " +data_array[5], data_array[6]+ "  " +data_array[7], Integer.parseInt(data_array[8]), Double.parseDouble(data_array[9]), Double.parseDouble(data_array[10]), Double.parseDouble(data_array[11]), Double.parseDouble(data_array[12]), Integer.parseInt(data_array[13])));
                    
                    /*
                    AllFlights[j].setFlightID(data_array[0]);
                    AllFlights[j].setFlightName(data_array[1]);
                    AllFlights[j].setFromCity(data_array[2]);
                    AllFlights[j].setToCity(data_array[3]);
                    AllFlights[j].setArrivalDate(data_array[4]+ "  " +data_array[5]);
                    AllFlights[j].setBoardingDate(data_array[6]+ "  " +data_array[7]);
                    AllFlights[j].setCapacity(Integer.parseInt(data_array[8]));
                    AllFlights[j].setEconomyPrice(Double.parseDouble(data_array[9]));
                    AllFlights[j].setBusinessPrice(Double.parseDouble(data_array[10]));
                    AllFlights[j].setPremiumEconomyPrice(Double.parseDouble(data_array[11]));
                    AllFlights[j].setRating(Double.parseDouble(data_array[12]));
                    AllFlights[j].setTicketsAvailable((Integer.parseInt(data_array[13])));

                    j++; */
                    
              }
            Reader.close();
        } 
        
        catch (FileNotFoundException e) {

            e.printStackTrace();
        }

        return AllFlights;
    }

    public LinkedList<Flight> deleteFlight(LinkedList<Flight> flights, String id)
    {
        for(Flight f : flights)
        {
            if(f.getFlightID().equalsIgnoreCase(id))
            {
                flights.remove(f);
            }
        }
        
        
        
        return flights;
    }


    public LinkedList<Flight> addNewFlight(LinkedList<Flight> flights)
    {
        Scanner scanner = new Scanner(System.in);
        Flight flight = new Flight();
        System.out.print("Enter Flight Name: ");
            
            String name=scanner.nextLine();
            flight.setFlightName(name);

            System.out.print("Enter Flight ID: ");
            String id=scanner.next();
            flight.setFlightID(id);

            System.out.print("Enter Boarding Location: ");
            scanner.nextLine();
            String origin=scanner.nextLine();
            flight.setFromCity(origin);

            System.out.print("Enter Arrival Location: ");
            String destination=scanner.nextLine();
            flight.setToCity(destination);

            System.out.print("Enter Boarding Date (DD-MM-YYYY) :");
            String d=scanner.nextLine();
            System.out.print("Enter Boarding Time (hh:mm:ss) :");
            String t=scanner.nextLine();
            flight.setBoardingDate(d+"  "+t);

            System.out.print("Enter Arrival Date (DD-MM-YYYY) :");
            d=scanner.nextLine();
            System.out.print("Enter Arrival Time (hh:mm:ss) :");
            t=scanner.nextLine();
            flight.setArrivalDate(d+"  "+t);

            System.out.print("Enter Economy price of flight :");
            double Economyprice=scanner.nextDouble();
            flight.setEconomyPrice(Economyprice);

            System.out.print("Enter Business Class Price :");
            double Businessprice=scanner.nextDouble();
            flight.setBusinessPrice(Businessprice);

            System.out.print("Enter Premium Economy price :");
            double PremiumEconomyprice=scanner.nextDouble();
            flight.setPremiumEconomyPrice(PremiumEconomyprice);

            System.out.print("Enter capacity :");
            capacity=scanner.nextInt();
            flight.setCapacity(capacity);
            flight.setTicketsAvailable(capacity);    
            
            System.out.print("Enter rating (out of 5): ");
            rating=scanner.nextInt();
            flight.setRating(rating);
        scanner.close();
        flights.add(flight);
        return flights;
    }

    public String getFlightName()
    {
        return FlightName;
    }

    public void setFlightName(String flight)
    {
        this.FlightName=flight;
    }

     public  String getFlightID()
    {
        return FlightID;
    }

    public void setFlightID(String id)
    {
        this.FlightID=id;
    }

    public String getFromCity()
    {
        return FromCity;
    }

    public void setFromCity(String city)
    {
        this.FromCity=city;
    }

    public String getToCity()
    {
        return ToCity;
    }

    public void setToCity(String city)
    {
        this.ToCity=city;
    }

    public void setBoardingDate(String d)
    {
        this.BoardingDate = d;
    }

    public String getBoardingDate()
    {
        return BoardingDate;
    }

    public void setArrivalDate(String d)
    {
        this.ArrivalDate = d;
    }

    public String getArrivalDate()
    {
        return ArrivalDate;
    }

    public int getTicketsAvailable()
    {
        return TicketsAvailable;
    }

    public void setTicketsAvailable(int t)
    {
        this.TicketsAvailable = t;
    }

    public void decreaseTicketAvai()
    {
        this.TicketsAvailable--;
    }

    public void increaseTicketAvai()
    {
        this.TicketsAvailable++;
    }


    public void setCapacity(int capacity)
    {
        this.capacity=capacity;
    }

    public int getCapacity()
    {
        return this.capacity;
    }

    public void setEconomyPrice(double price)
    {
        this.EconomyPrice=price;
    }

    public double getEconomyPrice()
    {
        return EconomyPrice;
    }

    public void setPremiumEconomyPrice(double price)
    {
        this.PremiumEconomyPrice=price;
    }

    public double getPremiumEconomyPrice()
    {
        return PremiumEconomyPrice;
    }

    public void setBusinessPrice(double price)
    {
        this.BusinessPrice=price;
    }

    public double getBusinessPrice()
    {
        return BusinessPrice;
    }

    public void setRating(double rating)
    {
        this.rating=rating;
    }

    public double getRating()
    {
        return rating;
    }

    public Flight searchFlight(LinkedList<Flight> flights, String ID)
    {
        for(Flight f : flights)
        {
            if(f.getFlightID().equals(ID))
            {
                return f;
            }
        }
        try
        {
            throw new FlightNotFoundException();
        }
        catch(FlightNotFoundException e)
        {
            System.out.println(e);
        }

        return null;
    }


    public LinkedList<Flight> sortbyEcoPrice(LinkedList<Flight> flights)
    {
        Collections.sort(flights, new SortbyEconomyPrice());
       // for(Flight f : flights) f.display();

        return flights;
    }

    public LinkedList<Flight> sortbyBusinessPrice(LinkedList<Flight> flights)
    {
        Collections.sort(flights, new SortbyBusinessPrice());
        //for(Flight f : flights) f.display();

        return flights;
    }
    
    public LinkedList<Flight> sortbyPremiumEco(LinkedList<Flight> flights)
    {
        Collections.sort(flights, new SortbyPremiumEcoPrice());
       // for(Flight f : flights) f.display();

        return flights;
    }

    public LinkedList<Flight> sortbyRating(LinkedList<Flight> flights)
    {
        Collections.sort(flights, new SortbyRating());
        //for(Flight f : flights) f.display();

        return flights;
    }

    public void display()
    {
        System.out.println("Flight Name: "+this.FlightName);
        System.out.println("Flight ID: "+this.FlightID);
        System.out.println("Journey: "+ this.FromCity+" - "+this.ToCity);
        System.out.println("Boarding Details: "+this.BoardingDate);
        System.out.println("Departure Details: "+this.ArrivalDate);
        System.out.println("Total capacity: "+this.capacity);
        System.out.println("Tickets Available: "+this.TicketsAvailable);
        System.out.println("Business Class : Rs. "+this.BusinessPrice+"\nEconomy Class : Rs. "+this.EconomyPrice+"\nPremium Economy Class : Rs. "+this.PremiumEconomyPrice);
        JourneyDuration(BoardingDate, ArrivalDate);
        System.out.println("Rating : "+rating);
        System.out.println();
    }


    public void saveChanges(LinkedList<Flight> AllFlights)
    {
        FileWriter fWriter;
        File temp = new File(filename);
        
        if (temp.exists()) {
        RandomAccessFile raf;
        try
             {
            raf = new RandomAccessFile(temp, "rw");    // Emptying the file
            raf.setLength(0);
             } 
        catch (FileNotFoundException e) 
             {
            e.printStackTrace();
             }

        catch (IOException e)
             {
            e.printStackTrace();
             }
        }

        try {
            fWriter = new FileWriter(filename, true);
            fWriter.write("flight id ,flight name,boarding code,arrival code,boarding date,boarding time,arrival date,arrival time,capacity,eco price,business price,prem eco price,rating,available tickets"+ System.getProperty( "line.separator"));
            fWriter.close();
        } catch (IOException e) {
    
            e.printStackTrace();
        }

        for(Flight f : AllFlights){
            String[] dateNtimeA = f.getBoardingDate().split("  ");
            String[] dateNtimeD = f.getArrivalDate().split("  ");
            //System.out.println(dateNtimeA[0] + " " + dateNtimeA[1]);
            String text = f.getFlightID() + ","+ f.getFlightName() + "," + f.getFromCity() + "," + f.getToCity() +"," + dateNtimeD[0] + "," + dateNtimeD[1] + "," + dateNtimeA[0] + "," + dateNtimeA[1] + "," + f.getCapacity() + "," + f.getEconomyPrice() + "," + f.getBusinessPrice() + "," + f.getPremiumEconomyPrice() + "," + f.getRating() + "," + f.getTicketsAvailable() + System.getProperty( "line.separator");
            
            try {
                fWriter = new FileWriter(filename, true);
                fWriter.write(text);
                
                fWriter.close();
            } catch (IOException e) {
        
                e.printStackTrace();
            }
            
            
            
            //Files.writeString(filename, text);
        }
    }
  
    public void JourneyDuration(String departureDate,String arrivalDate)
    {   
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        try
        {
            Date d1=sdf.parse(departureDate);
            Date d2=sdf.parse(arrivalDate);
            long difference_in_time=d2.getTime()-d1.getTime();      
            long difference_in_minutes=(difference_in_time/(1000*60))%60;
            long difference_in_hours=(difference_in_time/(1000*60*60))%24;

            System.out.println("Journey Duration is: "+difference_in_hours+" hours "+difference_in_minutes+" minutes ");
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }
    }
}

class SortbyEconomyPrice implements Comparator <Flight>
{

    public int compare(Flight f1, Flight f2) {

        Double ob1 = (Double) f1.getEconomyPrice() ;
        Double ob2 = (Double) f2.getEconomyPrice() ;
        if (ob1.compareTo(ob2) > 0)  return 1;

        else if (ob1.compareTo(ob2) < 0) return -1;

        else return 0;
    }
    
}

class SortbyBusinessPrice implements Comparator <Flight>
{

    public int compare(Flight f1, Flight f2) {

        Double ob1 = (Double) f1.getBusinessPrice() ;
        Double ob2 = (Double) f2.getBusinessPrice() ;
        if (ob1.compareTo(ob2) > 0)  return 1;

        else if (ob1.compareTo(ob2) < 0) return -1;

        else return 0;
    }
    
}


class SortbyPremiumEcoPrice implements Comparator <Flight>
{

    public int compare(Flight f1, Flight f2) {

        Double ob1 = (Double) f1.getPremiumEconomyPrice() ;
        Double ob2 = (Double) f2.getPremiumEconomyPrice() ;

        if (ob1.compareTo(ob2) > 0)  return 1;

        else if (ob1.compareTo(ob2) < 0) return -1;

        else return 0;
    }
    
}


class SortbyRating implements Comparator <Flight>
{
    public int compare(Flight f1, Flight f2) {

        Double ob1 = (Double) f1.getRating() ;
        Double ob2 = (Double) f2.getRating() ;

        if (ob1.compareTo(ob2) > 0)  return 1;

        else if (ob1.compareTo(ob2) < 0) return -1;

        else return 0;
    }
}






