// Date 28-04-2019, last modified : 01-05-2019
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

import java.io.IOException;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

public class ViewRegister extends Application {
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    private static ArrayList<String> customerReceipt = new ArrayList<>();
    private double total_price;

//    private ImageView img_settings = new ImageView("/Users/sietzemin/IdeaProjects/fxtest2/src/KassaSysteem/images/settings_icon.png");
    Controller controller = KassaMain.getControllerMain(); // assign controller to local reference
    String s_current_user = "Sietze Min";

    @Override
    public void start(Stage primaryStage){


        GridPane root = new GridPane();
        String current_product_name = controller.getModelProduct(0).product_name;
        root.getColumnConstraints().add(new ColumnConstraints(400)); // column 0 is 100 wide
        root.getColumnConstraints().add(new ColumnConstraints(400)); // column 1 is 100 wide
        root.getColumnConstraints().add(new ColumnConstraints(400)); // column 2 is 100 wide
        root.setStyle("-fx-background-color: #8ec2e5");
        root.getRowConstraints().add(new RowConstraints(200)); // row 0 is 200 high

        root.setPadding(new Insets(20));

//        img_settings.setPickOnBounds(true); // make transparent regions clickable too.
//        img_settings.setOnMouseClicked((MouseEvent e) -> { controller.btnSettings(); });
        Image img_settings = new Image(getClass().getResourceAsStream("settings_icon.png"));

        // Rij 0
        Label LproductToCome = new Label("Eerstvolgende product op lopendeband : ");
        root.setHalignment(LproductToCome, HPos.LEFT);
        root.setValignment(LproductToCome, VPos.TOP);

        Label LRegisterNumber = new Label("Kassa " + controller.getRegisterId());
        LRegisterNumber.setFont(Font.font("Cambria", 24));
        root.setHalignment(LRegisterNumber, HPos.CENTER);
        root.setValignment(LRegisterNumber, VPos.TOP);


        Label LCurrentDateTime = new Label("Datum : ");
        root.setHalignment(LCurrentDateTime, HPos.CENTER);
        root.setValignment(LCurrentDateTime, VPos.TOP);

        // Rij 1
        Button btnSearchProd = new Button("Zoeken");
        btnSearchProd.setMinWidth(200);
        btnSearchProd.setMinHeight(50);
        root.setHalignment(btnSearchProd, HPos.CENTER);
        btnSearchProd.setOnAction(e -> controller.btnSearchProd());

        // Rij 2
        Label LpreviouslyScannedProd = new Label("Laatst gescanned : ");
        root.setHalignment(LpreviouslyScannedProd,HPos.LEFT);

        Label LCurrentProduct = new Label("Huidig product");
        root.setHalignment(LCurrentProduct, HPos.CENTER);

        Button BtnPrintReceipt = new Button("Print bon");
        BtnPrintReceipt.setMinWidth(200);
        BtnPrintReceipt.setMinHeight(50);
        root.setHalignment(BtnPrintReceipt, HPos.CENTER);
        BtnPrintReceipt.setOnAction(e -> controller.btnModelPrintReceipt());

        // Rij 3
        Label LdynamicCurrentProduct = new Label(current_product_name);
        root.setHalignment(LdynamicCurrentProduct,HPos.CENTER);

        // Rij 4
        Label LprevScannedBrand = new Label("Merk : ");
        root.setHalignment(LprevScannedBrand, HPos.LEFT);

        Button BtnPaymentMethodCredit = new Button("Pin");
        BtnPaymentMethodCredit.setMinWidth(200);
        BtnPaymentMethodCredit.setMinHeight(50);
        root.setHalignment(BtnPaymentMethodCredit, HPos.CENTER);
        BtnPaymentMethodCredit.setOnAction(e -> controller.btnPaymentAccepted("Pin"));

        // Rij 5
        Label LprevScannedBarcode = new Label("Barcode : ");
        root.setHalignment(LprevScannedBrand, HPos.LEFT);

        Button BtnPaymentMethodCash = new Button("Contant");
        BtnPaymentMethodCash.setMinWidth(200);
        BtnPaymentMethodCash.setMinHeight(50);
        root.setHalignment(BtnPaymentMethodCash, HPos.CENTER);
        BtnPaymentMethodCash.setOnAction(e -> controller.btnPaymentAccepted("Cash"));


        Button BtnScanProduct = new Button("SCAN");
        BtnScanProduct.setMinWidth(200);
        BtnScanProduct.setMinHeight(50);
        root.setHalignment(BtnScanProduct, HPos.CENTER);
        BtnScanProduct.setOnAction(e -> controller.btnScan());

        // Rij 6
        Label LprevValidThru = new Label("Houdbaar tot : ");
        root.setHalignment(LprevValidThru, HPos.LEFT);

        Button BtnNextCustomer = new Button("Volgende klant");
        BtnNextCustomer.setStyle("-fx-background-color: #00ff00");
        BtnNextCustomer.setMinWidth(200);
        BtnNextCustomer.setMinHeight(50);
        root.setHalignment(BtnNextCustomer, HPos.CENTER);
        BtnNextCustomer.setOnAction(e -> controller.btnNextCustomer());

        // Rij 7
        Label LcurrentReceiptTF = new Label("Huidige klant bon");
        root.setHalignment(LcurrentReceiptTF, HPos.LEFT);

        Label LTotalAmount = new Label("Total bedrag E");
        root.setHalignment(LTotalAmount, HPos.CENTER);

        // Rij 8
        TextArea TfcurrentReceipt = new TextArea();
//        TfcurrentReceipt.setDisable(true);
        TfcurrentReceipt.setMinWidth(100);
        TfcurrentReceipt.setMinHeight(200);
        TfcurrentReceipt.setText("Klant bon");

        Label LDyanmicTotalAmount = new Label("$ 0.00");
        root.setHalignment(LDyanmicTotalAmount, HPos.CENTER);
        root.setValignment(LDyanmicTotalAmount, VPos.TOP);

        Label LcurrentUser = new Label("Medewerker :" + s_current_user);
        root.setHalignment(LcurrentUser, HPos.CENTER);

        // Rij 9
        Label LDetailProdName = new Label("Product naam : ");
        root.setHalignment(LDetailProdName, HPos.RIGHT);
        root.setValignment(LDetailProdName, VPos.TOP);

        // Rij 10
        Label LDetailProdBrand = new Label("Product merk : ");
        root.setHalignment(LDetailProdBrand, HPos.RIGHT);
        root.setValignment(LDetailProdBrand, VPos.TOP);

        // Rij 11
        Label LsettingsView = new Label("Instellingen");
        LsettingsView.setGraphic(new ImageView(img_settings));
        LsettingsView.setOnMouseClicked((MouseEvent e) ->{controller.btnSettings();});
        root.setHalignment(LsettingsView, HPos.CENTER);
        root.setValignment(LsettingsView, VPos.TOP);
        LsettingsView.setPadding(new Insets(-50,0,0,0));

        // Toevoegen aan de root scene
        // Rij 0
        root.add(LproductToCome, 0,0);
        root.add(LRegisterNumber, 1,0);
        root.add(LCurrentDateTime, 2,0);

        // Rij 1
        root.add(btnSearchProd,2,1);

        // Rij 2
        root.add(LpreviouslyScannedProd,0,2);
        root.add(LCurrentProduct,1,2);
        root.add(BtnPrintReceipt,2,2);

        // Rij 3
        root.add(LprevScannedBrand,0,3);
        root.add(LdynamicCurrentProduct,1,3);
        root.add(BtnPaymentMethodCredit,2,3);

        // Rij 4
        root.add(LprevScannedBarcode,0,4);
        root.add(BtnScanProduct,1,4);
        root.add(BtnPaymentMethodCash,2,4);

        // Rij 5
        root.add(LprevValidThru,0,5);
        root.add(BtnNextCustomer,2,5);

        // Rij 6
        root.add(LcurrentReceiptTF,0,6);
        root.add(LTotalAmount,1,6);

        // Rij 7
        root.add(TfcurrentReceipt,0,7);
        root.add(LDyanmicTotalAmount,1,7);
        root.add(LcurrentUser,2,7);

        // Rij 7
        root.add(LsettingsView,2,8);

        new Thread(new Runnable(){
            @Override
            public void run(){
                try{
                    while (true) {
                        Platform.runLater(new Runnable(){
                            @Override
                            public void run(){
                                LdynamicCurrentProduct.setText(controller.getCurrentProductName());
                                LprevScannedBrand.setText("Product merk : " + controller.getModelPrevProductDetails()[1]);
                                LprevScannedBarcode.setText("Product barcode :" + controller.getModelPrevProductDetails()[2]);
                                LpreviouslyScannedProd.setText("Laatst gescanned : " + controller.getModelPrevProductDetails()[0]);
                                LprevValidThru.setText("Product houdbaar tot :" + controller.getModelPrevProductDetails()[3]);

                                LCurrentDateTime.setText(controller.GetModelDateTime());
                                LproductToCome.setText("Volgende product : " + controller.getModelNextProductName());
                                String s_price = df2.format(controller.getModelTotalPrice());
                                TfcurrentReceipt.setText(controller.getModelCustomerReceipt().toString() + "\n");
                                LDyanmicTotalAmount.setText(s_price);
                            }
                        });
                        Thread.sleep(200);
                    }
                }
                catch(InterruptedException ex){
                }
//                LdynamicCurrentProduct.setText("Hello");

            }
        }).start();

        Scene scene = new Scene(root, 1200,700);

        primaryStage.setTitle("KassaSysteem SietzeKassa");
        primaryStage.setScene(scene);
        primaryStage.show();
        ViewLogin login_view = new ViewLogin();
    } // end of start method

    public void showView(){
        ViewRegister.launch();
    }


}