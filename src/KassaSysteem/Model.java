package KassaSysteem;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

public class Model {
    private static ArrayList<String> customerReceipt = new ArrayList<>();
    private LinkedList<Product> model_data_collection;
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    String dateString = format.format(new Date());

    public int minute;
    public int hour;
    public int second;

    // customer variables
    boolean hasPaid = false;

    public Model(){

    }

    //setters
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

    public ArrayList<String> getWholeReceipt(){
        return customerReceipt;
    }

    public String getDate(){
        return dateString;
    }

    public String getTime(){
//        Dateformat dateFormat = new SimpleDateFormat("hh:mm a");
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
            return s_hour + ": 0" + s_minute + ": 0" + s_second;
        }
        if(minute < 10){
            return s_hour + ": 0" + s_minute + ":" + s_second;
        }
        return s_hour + ":" + s_minute + ":" + s_second;
    }
}
