package KassaSysteem;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.text.DecimalFormat;

public class ViewReceipt {
    Controller controller = KassaMain.getControllerMain();
    private String date;
    private String time;
    private double total_price;
    private static DecimalFormat df2 = new DecimalFormat("#.##");

    public ViewReceipt(double price){
         date = controller.getModelDate();
         time = controller.getModelTime();
         total_price = price;
         showView();
     }

     public void showView(){
         GridPane receiptPane = new GridPane();
         receiptPane.setPadding(new Insets(20));
         receiptPane.getColumnConstraints().add(new ColumnConstraints(260)); // column 0 is 100 wide
         receiptPane.getRowConstraints().add(new RowConstraints(35)); // row 0 is
         receiptPane.getRowConstraints().add(new RowConstraints(35)); // row 1 is
         receiptPane.getRowConstraints().add(new RowConstraints(35)); // row 2 is
         receiptPane.getRowConstraints().add(new RowConstraints(50)); // row 3 is
         receiptPane.getRowConstraints().add(new RowConstraints(35)); // row 4 is

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
         for(int i = 0; i < controller.getModelCustomerReceipt().size(); i++){
             String product;
             TAReceipt.appendText(product = controller.getModelCustomerReceipt().get(i));
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
     }
}
