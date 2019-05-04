package KassaSysteem;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ViewNoReceiptNotPaid {

    public ViewNoReceiptNotPaid(){
        showView();
    }

    public void showView(){
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
