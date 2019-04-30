// Date 28-04-2019
// Author : Sietze Min
// Description :
/*
Sources  :
1.    https://o7planning.org/en/10641/javafx-gridpane-layout-tutorial
2.    https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/GridPane.html
3.    https://www.tutorialspoint.com/javafx/layout_gridpane.htm
4.    http://tutorials.jenkov.com/javafx/button.html
5.    https://riptutorial.com/javafx/example/7291/updating-the-ui-using-platform-runlater

 */
package KassaSysteem;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.File;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.*;
import javafx.scene.media.Media;

import javax.sound.sampled.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;

public class View extends Application {
    private static LinkedList<Product> data_collection = new LinkedList<>();
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    private static ArrayList<String> customerReceipt = new ArrayList<>();
    private MediaPlayer player;
    private static final String media_url ="beep.mp3";

    String current_product_name = "-";
    String current_product_brand = "-";
    String current_product_barcode = "-";
    String current_product_valid_thru = "-";
    String receipt_text = "lorem";

    String next_product_name = "Volgende product op de loopband :";
    String previous_product_name = "";

    Product current_product;
    Product previous_product;
    Product next_product;

//    private int counter2 = 0;
    private int counter = 0;
    private double total_price;

    @Override
    public void start(Stage primaryStage){
        GridPane root = new GridPane();
        root.getColumnConstraints().add(new ColumnConstraints(400)); // column 0 is 100 wide
        root.getColumnConstraints().add(new ColumnConstraints(400)); // column 1 is 100 wide
        root.getColumnConstraints().add(new ColumnConstraints(400)); // column 2 is 100 wide
        root.setStyle("-fx-background-color: #8ec2e5");
        root.getRowConstraints().add(new RowConstraints(200)); // row 0 is 200 high

        root.setPadding(new Insets(20));

        // Rij 0
        Label LproductToCome = new Label("Eerstvolgende product op lopendeband : ");
        root.setHalignment(LproductToCome, HPos.LEFT);
        root.setValignment(LproductToCome, VPos.TOP);

        Label LRegisterNumber = new Label("Kassa 3");
        LRegisterNumber.setFont(Font.font("Cambria", 24));
        root.setHalignment(LRegisterNumber, HPos.CENTER);
        root.setValignment(LRegisterNumber, VPos.TOP);


        Label Larea3 = new Label("Tijd : 16:04:22 / 01-02-2019");
        root.setHalignment(Larea3, HPos.CENTER);
        root.setValignment(Larea3, VPos.TOP);

        // Rij 1
        Label LpreviouslyScannedProd = new Label("Laatst gescanned : ");
        root.setHalignment(LpreviouslyScannedProd,HPos.LEFT);

        Label LCurrentProduct = new Label("Huidig product");
        root.setHalignment(LCurrentProduct, HPos.CENTER);

        Button BtnPrintReceipt = new Button("Print bon");
        BtnPrintReceipt.setMinWidth(200);
        BtnPrintReceipt.setMinHeight(50);

        root.setHalignment(BtnPrintReceipt, HPos.CENTER);

        // Rij 2
        Label LdynamicCurrentProduct = new Label("Huidig product 1");
        root.setHalignment(LdynamicCurrentProduct,HPos.CENTER);

        // Rij 3
        Label LprevScannedBrand = new Label("Merk : ");
        root.setHalignment(LprevScannedBrand, HPos.LEFT);

        Button BtnPaymentMethodCredit = new Button("Pin");
        BtnPaymentMethodCredit.setMinWidth(200);
        BtnPaymentMethodCredit.setMinHeight(50);
        root.setHalignment(BtnPaymentMethodCredit, HPos.CENTER);

        // Rij 4
        Label LprevScannedBarcode = new Label("Barcode : ");
        root.setHalignment(LprevScannedBrand, HPos.LEFT);

        Button BtnPaymentMethodCash = new Button("Contant");
        BtnPaymentMethodCash.setMinWidth(200);
        BtnPaymentMethodCash.setMinHeight(50);
        root.setHalignment(BtnPaymentMethodCash, HPos.CENTER);

        Button BtnScanProduct = new Button("SCAN");
        BtnScanProduct.setMinWidth(200);
        BtnScanProduct.setMinHeight(50);
        root.setHalignment(BtnScanProduct, HPos.CENTER);
        BtnScanProduct.setOnAction(e -> ActScanProduct());

        // Rij 5
        Label LprevValidThru = new Label("Houdbaar tot : ");
        root.setHalignment(LprevValidThru, HPos.LEFT);

        Button BtnNextCustomer = new Button("Volgende klant");
        BtnNextCustomer.setStyle("-fx-background-color: #00ff00");
        BtnNextCustomer.setMinWidth(200);
        BtnNextCustomer.setMinHeight(50);
        root.setHalignment(BtnNextCustomer, HPos.CENTER);

        // Rij 6
        Label LcurrentReceiptTF = new Label("Huidige klant bon");
        root.setHalignment(LcurrentReceiptTF, HPos.LEFT);

        Label LTotalAmount = new Label("Total bedrag E");
        root.setHalignment(LTotalAmount, HPos.CENTER);

        // Rij 7
        TextArea TfcurrentReceipt = new TextArea();
//        TfcurrentReceipt.setDisable(true);
        TfcurrentReceipt.setMinWidth(100);
        TfcurrentReceipt.setMinHeight(200);
        TfcurrentReceipt.setText("Klant bon");

        Label LDyanmicTotalAmount = new Label("$ 0.00");
        root.setHalignment(LDyanmicTotalAmount, HPos.CENTER);
        root.setValignment(LDyanmicTotalAmount, VPos.TOP);

        Label LcurrentUser = new Label("Medewerker : Sietze Min");
        root.setHalignment(LcurrentUser, HPos.CENTER);

        // Rij 8
        Label LDetailProdName = new Label("Product naam : ");
        root.setHalignment(LDetailProdName, HPos.RIGHT);
        root.setValignment(LDetailProdName, VPos.TOP);

        // Rij 9
        Label LDetailProdBrand = new Label("Product merk : ");
        root.setHalignment(LDetailProdBrand, HPos.RIGHT);
        root.setValignment(LDetailProdBrand, VPos.TOP);

        // Rij 10
        Label LDetailProdBarcode = new Label("Product barcode : ");
        root.setHalignment(LDetailProdBarcode, HPos.RIGHT);
        root.setValignment(LDetailProdBarcode, VPos.TOP);

        // Toevoegen aan de root scene
        // Rij 0
        root.add(LproductToCome, 0,0);
        root.add(LRegisterNumber, 1,0);
        root.add(Larea3, 2,0);

        // Rij 2
        root.add(LpreviouslyScannedProd,0,1);
        root.add(LCurrentProduct,1,1);
        root.add(BtnPrintReceipt,2,1);

        // Rij 3
        root.add(LprevScannedBrand,0,2);
        root.add(LdynamicCurrentProduct,1,2);
        root.add(BtnPaymentMethodCredit,2,2);

        // Rij 4
        root.add(LprevScannedBarcode,0,3);
        root.add(BtnScanProduct,1,3);
        root.add(BtnPaymentMethodCash,2,3);

        // Rij 5
        root.add(LprevValidThru,0,4);
        root.add(BtnNextCustomer,2,4);

        // Rij 6
        root.add(LcurrentReceiptTF,0,5);
        root.add(LTotalAmount,1,5);

        // Rij 7
        root.add(TfcurrentReceipt,0,6);
        root.add(LDyanmicTotalAmount,1,6);
        root.add(LcurrentUser,2,6);

        new Thread(new Runnable(){
            @Override
            public void run(){
                try{
                    while (true) {
                        Platform.runLater(new Runnable(){
                            @Override
                            public void run(){
                                LdynamicCurrentProduct.setText(current_product_name);
                                LprevScannedBrand.setText("Product merk : " + current_product_brand);
                                LprevScannedBarcode.setText("Product barcode :" + current_product_barcode);
                                LprevValidThru.setText("Product houdbaar tot :" + current_product_valid_thru);

                                LproductToCome.setText("Volgende product : " + next_product_name);
                                LpreviouslyScannedProd.setText("Laatst gescanned : " +previous_product_name);
                                String s_price = df2.format(total_price);

                                TfcurrentReceipt.setText(customerReceipt.toString() + "\n");

                                // pas de prijs aan.
                                LDyanmicTotalAmount.setText(s_price);

                            }
                        });
                        Thread.sleep(200);
                    }
                }
                catch(InterruptedException ex){
                }
            }
        }).start();

        String first_product = data_collection.get(counter).product_name;
        double first_product_price = data_collection.get(counter).product_price;
        next_product_name = data_collection.get(counter + 1).product_name;
        current_product_name = first_product;
//        String first_entry_on_receipt = first_product + " $ " +
        customerReceipt.add(first_product + " $ " + first_product_price + "\n");

        Scene scene = new Scene(root, 1200,700);
        primaryStage.setTitle("KassaSysteem SietzeKassa");
        primaryStage.setScene(scene);
        primaryStage.show();
    } // end of start method

    public void showView(){
        View.launch();
    }

    public void getDataFromMain(LinkedList<Product> collection){
        View.data_collection = collection;
        System.out.println("data is nu gezet" + data_collection.get(0).product_name);
    }

    public int showNum(){
        counter++;
        return counter;
    }

    // Button handler methods
    private synchronized void ActScanProduct(){
        System.out.println("SCAN button is pressed ");
        int product_index = showNum();

        current_product = data_collection.get(product_index);

        if(current_product instanceof PerishableProduct){
            current_product_valid_thru = ((PerishableProduct) current_product).product_valid_thru;
        } else {
            current_product_valid_thru = "";
        }
        next_product = data_collection.get(product_index + 1);

        previous_product = data_collection.get(product_index - 1);
        previous_product_name = previous_product.product_name;

        current_product_name = current_product.product_name;
        current_product_brand = current_product.product_brand;
        current_product_barcode = current_product.product_barcode;

        next_product_name = next_product.product_name;
        total_price += current_product.product_price;
        System.out.println(total_price);

        // voeg het huidige gescande product toe aan de bon.
        addToReceipt(current_product_name, current_product.product_price);
        Controller.playSound(media_url);
    }

    public void addToReceipt(String name, double price){
        String receipt_text = name + " $" + price +"\n";
        customerReceipt.add(receipt_text);
    }
}