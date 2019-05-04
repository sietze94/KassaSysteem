package KassaSysteem;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ViewNoReceipt {

    public ViewNoReceipt(){ showView(); }

    public void showView(){
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
}
