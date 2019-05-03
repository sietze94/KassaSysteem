package KassaSysteem;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;

public class Controller {
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    View view; // local reference to the view obj
    Model model;
    String testString = "Test string controller 1";

    // Constructor
    public Controller(Model model, View view){
        this.model = model;
        this.view = view;
    }


    public void addProductToReceipt(String name, double price){
        String receipt_text = name + " $" + price +"\n";
        model.addToCustomerReceipt(receipt_text);
    }

    public ArrayList<String> ModelUpdateReceipt(){
        return model.getWholeReceipt();
    }

    public void BtnModelPrintReceipt(double price){
        boolean paid = model.getHasPaid();
        System.out.println(paid);
        if(price != 0 && paid == true){
            double total_price = price;
            String date = model.getDate();
            String time = model.getTime();
            // GUI
            GridPane receiptPane = new GridPane();
            receiptPane.setPadding(new Insets(20));
            receiptPane.getColumnConstraints().add(new ColumnConstraints(260)); // column 0 is 100 wide
            receiptPane.getRowConstraints().add(new RowConstraints(35)); // row 0 is
            receiptPane.getRowConstraints().add(new RowConstraints(35)); // row 1 is
            receiptPane.getRowConstraints().add(new RowConstraints(35)); // row 2 is
            receiptPane.getRowConstraints().add(new RowConstraints(50)); // row 3 is
            receiptPane.getRowConstraints().add(new RowConstraints(35)); // row 4 is
//        receiptPane.getRowConstraints().add(new RowConstraints(150)); // row 5 is
//        receiptPane.getRowConstraints().add(new RowConstraints(35)); // row 6 is
//        receiptPane.getRowConstraints().add(new RowConstraints(35)); // row 7 is

            // Rij 0
            Label LTitleReceipt = new Label("SietzeSupermarkt");
            LTitleReceipt.setFont(Font.font("Cambria", 20));
            LTitleReceipt.setStyle("-fx-text-fill: #a4b1fc");
            receiptPane.setValignment(LTitleReceipt, VPos.TOP);
            receiptPane.setHalignment(LTitleReceipt, HPos.CENTER);

            receiptPane.add(LTitleReceipt,0,0);

            // Rij 1
            Label LDateVisited = new Label("Datum: " + date);
            receiptPane.setValignment(LDateVisited, VPos.TOP);
            receiptPane.setHalignment(LDateVisited, HPos.CENTER);
            receiptPane.add(LDateVisited,0,1);

            // Rij 2
            Label LTimeOfVisted = new Label("Tijdstip bezoek: " + time);
            receiptPane.setValignment(LTimeOfVisted, VPos.TOP);
            receiptPane.setHalignment(LTimeOfVisted, HPos.CENTER);
            receiptPane.add(LTimeOfVisted,0,2);

            // Rij 3
            Label LReceiptSubTitle = new Label("Kassa bon");
            receiptPane.setValignment(LReceiptSubTitle, VPos.TOP);
            receiptPane.setHalignment(LReceiptSubTitle, HPos.CENTER);
            receiptPane.add(LReceiptSubTitle,0,3);

            // Rij 4
            Label LProducts = new Label("Producten : ");
            receiptPane.setValignment(LProducts, VPos.TOP);
            receiptPane.setHalignment(LProducts, HPos.LEFT);
            receiptPane.add(LProducts,0,4);

            // Rij 5
            TextArea TAReceipt = new TextArea();
            TAReceipt.setDisable(true);
            TAReceipt.setMinHeight(300);
//        TAReceipt.setMinWidth(300);
            for(int i = 0; i < model.getWholeReceipt().size(); i++){
                String product;
                TAReceipt.appendText(product = model.getWholeReceipt().get(i));
            }
            receiptPane.add(TAReceipt,0,5);

            // Rij 6
            String total_price_formatted = df2.format(total_price);
            Label LTtotalPrice = new Label("Totaal  $ : " + total_price_formatted);
            receiptPane.setValignment(LTtotalPrice, VPos.CENTER);
            receiptPane.setHalignment(LTtotalPrice, HPos.LEFT);
            receiptPane.setPadding(new Insets(20));
            receiptPane.add(LTtotalPrice,0,6);

            // Rij 7
            Label LEndText = new Label("Bedankt en tot ziens");
            receiptPane.setValignment(LEndText, VPos.BOTTOM);
            receiptPane.setHalignment(LEndText, HPos.CENTER);
            receiptPane.add(LEndText,0,7);

            Scene secondScene = new Scene(receiptPane,300,600);
            Stage newWindow = new Stage();
            newWindow.setTitle("Klant bon");
            newWindow.setScene(secondScene);
            newWindow.show();
        } else if(price == 0) {
            System.out.println("Geen producten, kan geen bon printen");
            GridPane receiptPane = new GridPane();
            receiptPane.setPadding(new Insets(20));


            Label lNoReceipt = new Label("Geen producten \n kan geen bon uitprinten.");
            receiptPane.add(lNoReceipt,0,0);
            receiptPane.setValignment(lNoReceipt, VPos.CENTER);
            receiptPane.setHalignment(lNoReceipt, HPos.CENTER);

            Scene secondScene = new Scene(receiptPane,300,75);
            Stage newWindow = new Stage();
            newWindow.setTitle("Geen producten");
            newWindow.setScene(secondScene);
            newWindow.show();
        }
        else {
            System.out.println("Klant moet eerst betalen");
            GridPane receiptPane = new GridPane();

            Label lNoReceipt = new Label("Geen betaling geregistreerd");
            receiptPane.add(lNoReceipt,0,0);
            receiptPane.setValignment(lNoReceipt, VPos.CENTER);
            receiptPane.setHalignment(lNoReceipt, HPos.CENTER);

            Button btnOK = new Button("OK");
            btnOK.setMinHeight(40);
            btnOK.setMinWidth(300);
            receiptPane.add(btnOK,0,1);


            Scene secondScene = new Scene(receiptPane,300,75);
            Stage newWindow = new Stage();
            btnOK.setOnAction(e -> {newWindow.close();} );

            newWindow.setTitle("Niet betaald");
            newWindow.setScene(secondScene);
            newWindow.show();
        }

    }

    public void BtnNextCustomer(){
        System.out.println("BtnNextCustomer pressed");
        model.setHasPaid(false);
    }
    public String ModelGetDateTime(){
        String date = model.getDate();
        String time = model.getTime();

        return date + " / " + time;
    }

    public void PaymentAccepted(String value){
        String payment_method = "";
        model.setHasPaid(true);

        if(value.equals("Pin")){
            payment_method = "Pin";
        }
        if(value.equals("Cash")){
            payment_method = "contant";
        }
        GridPane paymentDebitPane = new GridPane();
        Label LPaymentDebit = new Label(payment_method);
        paymentDebitPane.add(LPaymentDebit,0,0);

        Button btnContinue = new Button("Ga verder");
        paymentDebitPane.add(btnContinue,0,1);
        btnContinue.setMinHeight(40);
        btnContinue.setMinWidth(300);
        Scene secondScene = new Scene(paymentDebitPane,300,75);
        Stage newWindow = new Stage();
        btnContinue.setOnAction(e -> {newWindow.close();});

        newWindow.setTitle("Klant heeft betaald");
        newWindow.setScene(secondScene);
        newWindow.show();

    }

    public void PaymentWithCash(){
        model.setHasPaid(true);
    }

    // Gets the data from the parser and stores it in the model.
    public void GetDataFromParserToModel(LinkedList<Product> collection){
        System.out.println("Inside GetDataFromParser");
        model.setDataCollection(collection);
    }

    public String setInitialProductName(){
        return model.getDataCollection().get(0).product_name;
    }

    // View calls this method to acces the data (once initially)
    public void sendDataToView(){
        view.setDataCollection(model.getDataCollection());
    }


     static public void playSound(){
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
