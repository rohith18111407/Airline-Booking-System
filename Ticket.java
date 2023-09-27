package src;

import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;




interface Bookable
{
  void cancelTicket();
  void applyDiscount();
  void bookTicket();
}

public class Ticket implements Bookable 
{
  int ticketId;
  String passengerName;
  String flightId;
  String flightName;
  String   fromCode;
  String   toCode;
  String boardingDate;
  String arrivalDate;
  double ticketPrice;
  double discountedPrice;
  String type;
  String purchasedDate;
  boolean bookingStatus;

  Flight flight;
  Passenger passenger;
  String order[] = null;



  Scanner scan=new Scanner(System.in);
  
  //Constructor:
  
  public Ticket(Passenger p, Flight f, int c, String[] order)
  {
  
    this.order = order;
    this.flight = f;
    this.passenger = p;
    this.passengerName = p.getName();
    this.ticketId = gen();
    this.flightId = f.getFlightID();
    this.flightName = f.getFlightName();
    this.fromCode = f.getFromCity();
    this.toCode = f.getToCity();
    this.boardingDate = f.getBoardingDate();
    this.arrivalDate = f.getArrivalDate();
   
    if(c == 0) 
    { this.ticketPrice = f.getEconomyPrice();
      this.type = "Economy";
    }

    if(c == 1) 
    { this.ticketPrice = f.getBusinessPrice();
      this.type = "Business";
    }

    if(c == 2) 
    { this.ticketPrice = f.getPremiumEconomyPrice();
      this.type = "Premium Economy";
    }

    this.discountedPrice = this.ticketPrice;

    FoodCourt foCourt = new FoodCourt("data/food.csv");
    foCourt.placeOrder(order);
    

    String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
    this.purchasedDate = timeStamp;

  }

  int getTicketID()
  {
    return this.ticketId;
  }

  String getPassengerName()
  {
    return passenger.getName();
  }

  public Ticket(Passenger p, Flight f, int c)
  {
  
    this.flight = f;
    this.passenger = p;
    this.passengerName = p.getName();
    this.ticketId = gen();
    this.flightId = f.getFlightID();
    this.flightName = f.getFlightName();
    this.fromCode = f.getFromCity();
    this.toCode = f.getToCity();
    this.boardingDate = f.getBoardingDate();
    this.arrivalDate = f.getArrivalDate();
   
    if(c == 0) 
    { this.ticketPrice = f.getEconomyPrice();
      this.type = "Economy";
    }

    if(c == 1) 
    { this.ticketPrice = f.getBusinessPrice();
      this.type = "Business";
    }

    if(c == 2) 
    { this.ticketPrice = f.getPremiumEconomyPrice();
      this.type = "Premium Economy";
    }


    this.discountedPrice = this.ticketPrice;
    String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
    this.purchasedDate = timeStamp;

  }

  public void cancelTicket()
  {
    this.bookingStatus = false;
    flight.increaseTicketAvai();
  }


  public void saveChanges(String filename, LinkedList<Ticket> tickets)
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
        fWriter.write("ticket_id,passenger_name,passenger_phno,pan"+ System.getProperty( "line.separator"));
        fWriter.close();
    } catch (IOException e) {

        e.printStackTrace();
    }

    for(Ticket f : tickets){
      

        String text = f.getTicketID() + ","+ f.getPassengerName() + "," + f.passenger.getPhno() + "," + f.passenger.getPan() + System.getProperty( "line.separator");
        
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

  public void applyDiscount()
  {
    if(passenger.getOccupation().equals("Armed Forces"))
    {
        System.out.println("Discount applied : -" + 0.1*this.ticketPrice);
        this.discountedPrice = this.ticketPrice - 0.1*this.ticketPrice;
    }

    else if(passenger.getOccupation().equals("Student"))
    {
      System.out.println("Discount applied : -" + 0.3*this.ticketPrice);
      this.discountedPrice = this.ticketPrice - 0.3*this.ticketPrice;
    }

    else
    {
      this.discountedPrice = this.ticketPrice;
    }
  }


  public void displayTicket()
  {
    FoodCourt f = new FoodCourt("data/food.csv");


    System.out.println("Ticket ID :" + this.ticketId);
    System.out.println("Passenger's Name :" + this.passengerName);
    System.out.println("Passenger's Age :" + passenger.getAge());
    System.out.println("Flight ID :" + flight.getFlightID());
    System.out.println("Flight Name :" + flight.getFlightName());
    System.out.println("Journey: "+ flight.getFromCity()+" - "+flight.getToCity());
    System.out.println("Boarding Details: "+flight.getBoardingDate());
    System.out.println("Arrival Details: "+flight.getArrivalDate());
    System.out.println("Class  :" + this.type);
    System.out.println("Original Ticket Price  :" + this.ticketPrice);
    if(order != null)
    {
      f.placeOrder(order);
      f.showOrder();
    }
    
    System.out.println("Ticket Price after Discount :" + (this.discountedPrice + f.OrderTotal()));
    System.out.println("Purchase Date: "+this.purchasedDate);

    

  }

  public static void JourneyDuration(String departureDate,String boardingDate)
    {   
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        try
        {
            Date d1=sdf.parse(departureDate);
            Date d2=sdf.parse(boardingDate);
            long difference_in_time= d1.getTime()- d2.getTime();      
            long difference_in_minutes=(difference_in_time/(1000*60))%60;
            long difference_in_hours=(difference_in_time/(1000*60*60))%24;

            System.out.println("Journey Duration is: "+difference_in_hours+" hours "+difference_in_minutes+" minutes ");
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }
    }

  public int gen() 
  {
     Random r = new Random(System.currentTimeMillis() );
    return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
  }

  public void generatePDF()
  {
    Document doc = new Document();

  

  BaseFont headingFont = null, textFont = null;
  try {
    headingFont = BaseFont.createFont("lib/consolas/CONSOLAB.TTF", BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);
    textFont = BaseFont.createFont("lib/consolas/CONSOLA.TTF", BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);
  } catch (DocumentException e1) {
    e1.printStackTrace();
  } catch (IOException e1) {
    e1.printStackTrace();
  }

  com.itextpdf.text.Font heading = new com.itextpdf.text.Font(headingFont,16);
  com.itextpdf.text.Font text = new com.itextpdf.text.Font(textFont);
  int cost=0;

    try{
      PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("tickets_pdf/ticket_" + passengerName + ".pdf"));

      doc.open();
      doc.add(new Paragraph ("---------------------------"));
      doc.add(new Paragraph ("TICKET", heading));
      doc.add(new Paragraph ("Ticket ID :" + this.ticketId, text));
      doc.add(new Paragraph ("Passenger's Name :" + this.passengerName, text));
      doc.add(new Paragraph ("Passenger's Age :" + passenger.getAge(),text));
      doc.add(new Paragraph ("Flight ID :" + flight.getFlightID(),text));
      doc.add(new Paragraph ("Flight Name :" + flight.getFlightName(),text));
      doc.add(new Paragraph ("Journey: "+ flight.getFromCity()+" - "+flight.getToCity(),text));
      doc.add(new Paragraph ("Boarding Details: "+flight.getBoardingDate(),text));
      doc.add(new Paragraph ("Arrival Details: "+flight.getArrivalDate(),text));
      doc.add(new Paragraph ("Class  :" + this.type,text));
      doc.add(new Paragraph ("Original Ticket Price  : Rs." + this.ticketPrice,text));
      if(order!=null)
      {
        FoodCourt obj = new FoodCourt("data/food.csv");

        doc.add(new Paragraph ("**Order Placed in FoodCourt**",text));
        for(String s : order)
        {
        doc.add(new Paragraph (s + "-" + obj.getPrice(s), text));
        }
        obj.placeOrder(order);

        doc.add(new Paragraph ("Total : " + obj.OrderTotal(), text));
        doc.add(new Paragraph ("**End of Order**", text));
        cost = obj.OrderTotal();

      }

      doc.add(new Paragraph ("Ticket Price after Discount :Rs." + (this.discountedPrice + cost),text));
      doc.add(new Paragraph ("Purchase Date: "+this.purchasedDate,text));
      doc.add(new Paragraph ("Have a safe journey !", heading));
      doc.add(new Paragraph ("---------------------------"));
      doc.close();
      writer.close();

      
    }

    catch (DocumentException e)
      {
          e.printStackTrace();
      }
    catch (FileNotFoundException e)
      {
          e.printStackTrace();
      }
  }


  @Override
  public void bookTicket() {
    this.bookingStatus = true;
    flight.decreaseTicketAvai();
    
  }
}