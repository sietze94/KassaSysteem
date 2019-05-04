package KassaSysteem;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ViewPaymentAccepted {
    private String payment_method;

    // Constructor
    public ViewPaymentAccepted(String method){
        payment_method = method;
        showView();
    }

    // View Creator
    public void showView(){
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

}
