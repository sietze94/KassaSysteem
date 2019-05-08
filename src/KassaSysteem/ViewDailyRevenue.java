package KassaSysteem;

public class ViewDailyRevenue {
    Controller controller;

    // Constructor
    public ViewDailyRevenue(Controller c){
        controller = c; // assign reference to local controller variable
        showDailyRevenue();
    }

    public void showDailyRevenue(){
        System.out.println("scherm dag totaal");
    }
}
