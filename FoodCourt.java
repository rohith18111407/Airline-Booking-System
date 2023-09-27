package src;
import java.util.*;
import java.io.*;


public class FoodCourt {

    Map<String,Integer> menu;
    private HashMap<String,Integer> order;
    private ArrayList<Integer> ordertotal;
    String filename;
    

    public FoodCourt(String filename)
    {
        FileReader file;
        this.filename = filename;
        

        this.menu = new HashMap<String,Integer>();
        this.order = new HashMap<String,Integer>();
        this.ordertotal = new ArrayList<Integer>();

        try {

             file = new FileReader(filename);
           
             Scanner Reader = new Scanner(file);
             Reader.nextLine(); // skipping title
             while (Reader.hasNextLine()) 
             {
                    String data = Reader.nextLine();
                    String[] data_array = data.split(",", 5);
                    this.addFood(data_array[0], Integer.parseInt(data_array[1]));
                    
             }
                Reader.close();
        } 
        
        catch (FileNotFoundException e) {

            e.printStackTrace();
        }

    }

    public void saveChanges()
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
            fWriter.write("food,cost"+ System.getProperty( "line.separator"));
            fWriter.close();
        } catch (IOException e) {
    
            e.printStackTrace();
        }

        for(Map.Entry<String,Integer> entry : menu.entrySet()){
            String text = entry.getKey() + ","+ entry.getValue() + System.getProperty( "line.separator");
            
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

    public void addFood(String food, int cost)
    {
        this.menu.put(food, cost);

    }

    public int getPrice(String food)
    {
        for(Map.Entry<String,Integer> entry : menu.entrySet())
        {
            if(entry.getKey().equalsIgnoreCase(food))
                    return entry.getValue();
            
        }

        return 0;
    }

    public void removeFood(String food)
    {
        this.menu.remove(food);

    }

    public void displayMenu()
    {
        for(Map.Entry<String,Integer> entry : menu.entrySet())
        {
            System.out.println(entry.getKey() + "- Rs. " +entry.getValue());
        }
    }

    public void placeOrder(String[] orderlist)
    {
       
        for(String food : orderlist)
        {   
            //System.out.println(food);
            //System.out.println(menu);
        
           int cost =  menu.get(food);
           // System.out.println(cost);
           ordertotal.add(cost);

           this.order.put(food,cost);

        }
        

    }

    public void showOrder()
    {
        System.out.println("Order Placed in FoodCourt");
        System.out.println("-----------------------");
        for(Map.Entry<String,Integer> entry : order.entrySet())
        {
            System.out.println(entry.getKey() + "-" + entry.getValue());

        }

        System.out.println("Total :" +OrderTotal());
        System.out.println("-----------------------");

    }
    public int OrderTotal()
    {
        int amount = 0;
        //System.out.println(ordertotal);
        for(int c : ordertotal )
        {
            //System.out.println(c);
            
            amount +=  c;
            //System.out.println(amount);
            
        }

        return amount;
    } 
}
