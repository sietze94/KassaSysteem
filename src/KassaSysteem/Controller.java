package KassaSysteem;

// Date last modified : 04-05-2019
// Author : Sietze Min

import javax.sound.sampled.*;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Controller {
    View view; // local reference to the view obj
    Model model;
    String testString = "Test string controller 1";
    private int product_index = 0;

    public String getModelDate(){ return model.getDate(); }

    public String getModelTime(){ return model.getTime(); }

    public void setModel(Model model){ this.model = model; }

    public void setView(View view){ this.view = view; }

    public ArrayList<String> getModelCustomerReceipt(){ return model.getWholeReceipt(); }

    public void addProductToReceipt(String name, double price){
        String receipt_text = name + " $" + price +"\n";
        model.addToCustomerReceipt(receipt_text);
    }

    public ArrayList<String> ModelUpdateReceipt() { return model.getWholeReceipt(); }

    public String getCurrentProductName(){
        if(product_index == 0){
            return model.getProduct(0).product_name;
        }
        return model.getProduct(product_index).product_name;
    }

    public String getModelNextProductName (){ return model.getProduct(product_index + 1).product_name; }

    public String[] getModelPrevProductDetails(){
        String[] details = new String[4];
        if(product_index == 0 ){
            details[0] = "-";
            details[1] = "-";
            details[2] = "-";
            details[3] = "-";
            return details;
        }

        String name = model.getProduct(product_index - 1).product_name;
        String brand = model.getProduct(product_index - 1).product_brand;
        String barcode = model.getProduct(product_index - 1).product_barcode;

        if(model.getProduct(product_index -1) instanceof PerishableProduct){
            String valid_thru = ((PerishableProduct) model.getProduct(product_index - 1)).product_valid_thru;
            details[3] = valid_thru;

        } else {
            details[3] = "-";
        }

        details[0] = name;
        details[1] = brand;
        details[2] = barcode;
        return details;
    }

    public void setCurrentProductDetails(){
        increaseProductIndex();
        int index = product_index - 1;

        String current_product_name = getModelProduct(product_index).product_name;
        Double current_product_price = getModelProduct(product_index).product_price;
        addProductToReceipt(model.getProduct(index).product_name, model.getProduct(index).product_price);
        model.setTotalPrice(model.getProduct(index).product_price);

        // add product to scanned history
        String product_history =  model.getTime() + " " + model.getProduct(index).product_barcode + " " + model.getProduct(index).product_name + " " + model.getProduct(index).product_price;
        model.addProductToScannedHistory(product_history);
    }

    public void addToReceipt(String name, double price){
        String receipt_text = name + " $" + price +"\n";
        model.setReceipt(receipt_text);
    }

    public double getModelTotalPrice(){
        if(product_index == 0 ){
            return 0.00;
        }
        return model.getTotalPrice();
    }

    public Product getModelProduct(int index){ return model.getProduct(index); }

    public int increaseProductIndex(){
        product_index++;
        return product_index;
    }


    // BUTTON HANDLERS
    public void btnSettings(){
        System.out.println("Settings button clicked");
        ViewSettings view_settings = new ViewSettings(KassaMain.getControllerMain()); // show settings screen
    }

    public void btnPrintRegisterHistory(){

        // Write the history of the register to a text file on the desktop.
        String filename = "register-" + String.valueOf(model.getRegisterNumber()) + "log_" + model.getDate();
        try {
          FileWriter fw = new FileWriter("/Users/sietzemin/Desktop/"  + filename+ ".txt");
          PrintWriter pw = new PrintWriter(fw);
          for(int i = 0; i < model.getScannedHistory().size(); i++){
              String product_info = model.getScannedHistory().get(i);
              pw.append(product_info +"\n");
          }
          pw.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void btnNextCustomer(){
        model.clearReceipt();
        model.clearTotalPrice();
        model.setHasPaid(false);
        System.out.println(model.getTotalPrice());
    }

    public void btnScan(){
        setCurrentProductDetails();
        playSound();
    }

    public void btnModelPrintReceipt(){
        System.out.println(model.getHasPaid());

        // Wanneer de prijs niet 0 is, en er is betaald, pas dan laat de bon zien.
        if(model.getTotalPrice() != 0 && model.getHasPaid()){
            ViewReceipt view_receipt = new ViewReceipt(model.getTotalPrice());
            // voeg de producten van de bon toe aan de geschiedenis.

            // Wanneer de prijs 0 is, dan kan er geen bon worden geprint.
        } else if(model.getTotalPrice() == 0) {
            ViewNoReceipt view_no_receipt = new ViewNoReceipt();
            System.out.println("Geen producten, kan geen bon printen");
        }
        // Prijs is ongelijk aan nul, maar de klant heeft nog niet betaald
        else {
            ViewNoReceiptNotPaid view_no_receipt_not_paid = new ViewNoReceiptNotPaid();
            System.out.println("Klant moet eerst betalen");

        }

    }

    public String GetModelDateTime(){
        String date = model.getDate();
        String time = model.getTime();

        return date + " / " + time;
    }

    public void btnPaymentAccepted(String value){
        String payment_method = "";
        model.setHasPaid(true);

        if(value.equals("Pin")){
            ViewPaymentAccepted view_payment_accepted = new ViewPaymentAccepted("Pin");

        }
        if(value.equals("Cash")){
            ViewPaymentAccepted view_payment_accepted = new ViewPaymentAccepted("Cash");

        }
    }

    // Gets the data from the parser and stores it in the model.
    public void GetDataFromParserToModel(LinkedList<Product> collection){
        System.out.println("Inside GetDataFromParser");
        model.setDataCollection(collection);
    }

    public String getTestString(){ return "Hello from controller"; }

    public void playSound(){
        Thread thread = new Thread(new Runnable(){
            public void run(){
                try{
                    String soundName = "/Users/sietzemin/IdeaProjects/fxtest2/src/media.io_beep.wav";
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();
//                    clip.drain();
//                    clip.close();
                    Thread.sleep(2000);
                } catch(IOException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException ex)
                {
                    ex.printStackTrace();
                }
            }
        });
        thread.start();
    } // end of play sound method
}
