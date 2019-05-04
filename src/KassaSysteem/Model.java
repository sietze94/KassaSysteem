package KassaSysteem;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

public class Model {
    private static ArrayList<String> customerReceipt = new ArrayList<>();
    private LinkedList<Product> model_data_collection;
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    private String dateString = format.format(new Date());

//    String current_product_name;
    private int minute;
    private int hour;
    private int second;

    // customer variables
    boolean hasPaid = false;

    // Data
    private double total_price = 0;

    //setters and other methods
    public Product getProduct(int index){ return model_data_collection.get(index); }

    public double getTotalPrice(){ return total_price; }

    public void setTotalPrice(double price){ total_price += price; }

    public void addToCustomerReceipt(String s){
        customerReceipt.add(s);
    }

    public void setDataCollection(LinkedList<Product> collection){
        model_data_collection = collection;
    }

    public void setHasPaid(boolean value){
        hasPaid = value;
    }

    public boolean getHasPaid(){
        return hasPaid;
    }

    // For other classes to get the data from the model when needed
    public LinkedList<Product> getDataCollection() {
        return model_data_collection;
    }

    // always returns the first element
    public String getFromCustomerReceipt(){
        return customerReceipt.get(0);
    }

    public ArrayList<String> getWholeReceipt(){ return customerReceipt; }

    public void setReceipt(String s){ customerReceipt.add(s); }

    public void clearReceipt(){ customerReceipt.clear(); }

    public void clearTotalPrice(){total_price = 0.00;}
    public String getDate(){
        return dateString;
    }

    public String getTime(){
        second = LocalDateTime.now().getSecond();
        minute = LocalDateTime.now().getMinute();
        hour = LocalDateTime.now().getHour();
        String s_second ="";
        String s_minute ="";
        String s_hour ="";

        s_second = s_second.valueOf(second);
        s_minute = s_minute.valueOf(minute);
        s_hour = s_hour.valueOf(hour);

        if(second < 10 && minute < 10){
            return s_hour + ":0" + s_minute + ":0" + s_second;
        }
        if(minute < 10){
            return s_hour + ":0" + s_minute + ":" + s_second;
        }
        if(second < 10){
            return s_hour + ":" + s_minute +":0" + s_second;
        }
        return s_hour + ":" + s_minute + ":" + s_second;
    }
}
