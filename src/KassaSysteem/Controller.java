package KassaSysteem;

// Date last modified : 09-05-2019
// Author : Sietze Min

import org.json.simple.JSONObject;

import javax.sound.sampled.*;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Controller {
    ViewRegister view; // local reference to the view obj
    Model model;
    Register register;
    private int product_index = 0;

    // < ======== Login functions ======== >

    public Boolean getPasscodeBool(){ return model.getBoolPasscode(); }

    public void checkPasscode(){
        String passcode = "";
        for(int i = 0; i < model.getUserInpSize(); i++){ passcode += model.getUserInp(i); }

        if(passcode.equals(model.getPassCode())){
            System.out.println("match!");
            model.setBoolPasscode(true); // if match, then set boolean to true.
        }
    }

    public String getUserInput(){

      if(model.getClickedCounter() == 1){
          return "*";
      }
      if(model.getClickedCounter() == 2){
            return "* *";
      }
      if(model.getClickedCounter() == 3){
            return "* * *";
      }
      if(model.getClickedCounter() == 4){
            return "* * * *";
      }

      if(model.getClickedCounter() > 4){
          return "te lang";
      }
      return "";
    }

    public void setRegister(Register register){this.register = register;}

    public void setModel(Model model){ this.model = model; }

    public void setView(ViewRegister view){ this.view = view; }

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

    public int getRegisterId(){ return register.getRegisterID(); }

    public String getModelDate(){ return model.getDate(); }

    public String getModelTime(){ return model.getTime(); }

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

    public String GetModelDateTime(){
        String date = model.getDate();
        String time = model.getTime();

        return date + " / " + time;
    }

    // Gets the data from the parser and stores it in the model.
    public void GetDataFromParserToModel(LinkedList<Product> collection){
        System.out.println("Inside GetDataFromParser");
        model.setDataCollection(collection);
    }

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

    public void playLoginSuccessFullSound(){
        Thread thread = new Thread(new Runnable(){
            public void run(){
                try{
                    String soundName = "/Users/sietzemin/IdeaProjects/fxtest2/src/KassaSysteem/logins.wav";
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
    }


    //<======== BUTTON HANDLERS ======== >
    public void btnSearchProd(){
        System.out.println("Search button pressed");
    }

    public void btnClr(){ model.clrLastUserInp(); }

    public void btnOk(){
        User c_user = new User("Sietze");
        register.setCurrentUser(c_user);
        System.out.println("Huidige gebruiker is ingelogd");

//        model.checkPasscode();
        playLoginSuccessFullSound();
    }

    public void btnUserInput(String inp){ model.addUserInput(inp); }

    public void btnLogout(){
        System.out.println("U bent succesvol uitgelogd");
        model.resetUserInp(); // clean the password entry field
        ViewLogin login_view = new ViewLogin();
        model.setBoolPasscode(false);
    }

    public void btnViewDailyRevenue(){
        ViewDailyRevenue view_daily_revenue = new ViewDailyRevenue(KassaMain.getControllerMain());
    }

    public void btnSettings(){
        System.out.println("Settings button clicked");
        ViewSettings view_settings = new ViewSettings(KassaMain.getControllerMain()); // show settings screen
    }

    public void btnPrintRegisterHistory(){
        String os_type;

        // Write the history of the register to a text file on the desktop.
        String filename = "register-" + String.valueOf(getRegisterId()) + "log_" + model.getDate();
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

        writeJson();

        // Try to determine the os version for path specification
        os_type = System.getProperty("os.name");
        System.out.println(os_type);
    }

    public void writeJson(){
        JSONObject obj = new JSONObject();
        ArrayList<JSONObject> jsonArray = new ArrayList<>();

        for(int i = 0; i < model.getScannedHistory().size(); i++) {
            String s = model.getScannedHistory().get(i);
            String[] splited = s.split(" ");

            for (int j = 0; j < splited.length; j++) {
                System.out.println(splited[j]);
            }

            obj.put("date_scanned", splited[0]);
            obj.put("product_barcode", splited[1]);
            obj.put("product_price", splited[splited.length - 1]);

            String product_n = "";
            for (int k = 2; k < splited.length - 2; k++) {
                System.out.println(splited[k]);
                product_n = product_n + " " + splited[k];
            }
            obj.put("product_name ", product_n);
            jsonArray.add(obj);
        }

        try (FileWriter file = new FileWriter("/Users/sietzemin/Desktop/test.json")){
            for(JSONObject o : jsonArray){
                file.write(obj.toJSONString() + "\n");
            }
            file.close();
        } catch( IOException ex){
            ex.printStackTrace();
        }
        System.out.println(obj);
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

}
