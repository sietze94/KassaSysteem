// Last modified : 12-05-2019
// Author : Sietze Min

package KassaSysteem;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

public class Model {
    private static ArrayList<String> customerReceipt = new ArrayList<>();
    private ArrayList<String> scanned_history = new ArrayList<>();
    private LinkedList<Product> model_data_collection;

    // register variables
    private double daily_total = 0; // dag omzet (per kassa, alle kassas )
    private double total_price = 0;

    // Login variables
    private ArrayList<String> user_input = new ArrayList<>(); // holds the input the user inputs during login.
    private int clicked_counter = 0; // keeps track of how many times user has entered input during login
    private String passCode = "1234"; // you do not want to store a passcode like this obviously.
    private boolean boolPassCode = false; // for a user to successfully login this needs to be set to true
//    private char[] passcode = new char[4];

    // Date/Time variables
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    private String dateString = format.format(new Date());
    private int minute;
    private int hour;
    private int second;

    // customer variables
    boolean hasPaid = false;

    // <======== Setters and other methods ================================>

    // <======== Login / logout functions ========>
    public void resetUserInp(){
        clicked_counter = 0;
        user_input.clear();
    }

    public void clrLastUserInp(){
        // Operatie mag alleen worden uitgevoerd wanneer de counter groter of gelijk aan 0 is.
        if(clicked_counter - 1 >= 0){
            user_input.remove(clicked_counter - 1); // remove last added input.
//            System.out.println("Clicked counter : " + clicked_counter + " Removed input : " + user_input.remove(clicked_counter - 1)); // remove last added input.
            clicked_counter--; // anders wel
        } else {
            System.out.println("kan niet meer weghalen");
        }
    }

    public void addUserInput(String s_inp){
        user_input.add(s_inp); // voeg de user input toe aan de data structuur.
        System.out.println("Input toegevoegd : " + s_inp + " controle : " + user_input.get(clicked_counter) + " counter :" + clicked_counter);
        clicked_counter++;
    }

    public void setBoolPasscode(boolean b){ boolPassCode = b; }

    public boolean getBoolPasscode(){ return boolPassCode; }

    public String getPassCode(){ return passCode; }

    public String getUserInp(int index){ return user_input.get(index); }

    public int getUserInpSize(){ return user_input.size(); }

    public int getClickedCounter(){ return clicked_counter; }

// <==== End of login functions =============================================>

    public void addProductToScannedHistory(String s){ scanned_history.add(s); }

    public ArrayList<String> getScannedHistory(){ return scanned_history; }

    public Product getProduct(int index){ return model_data_collection.get(index); }

    public double getTotalPrice(){ return total_price; }

    public void setTotalPrice(double price){ total_price += price; }

    public void addToCustomerReceipt(String s){ customerReceipt.add(s); }

    public void setDataCollection(LinkedList<Product> collection){ model_data_collection = collection; }

    public void setHasPaid(boolean value){ hasPaid = value; }

    public boolean getHasPaid(){ return hasPaid; }

    // For other classes to get the data from the model when needed
    public LinkedList<Product> getDataCollection() { return model_data_collection; }

    // always returns the first element
    public String getFromCustomerReceipt(){ return customerReceipt.get(0); }

    public ArrayList<String> getWholeReceipt(){ return customerReceipt; }

    public void setReceipt(String s){ customerReceipt.add(s); }

    public void clearReceipt(){ customerReceipt.clear(); }

    public void clearTotalPrice(){total_price = 0.00;}

    public String getDate(){ return dateString; }

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