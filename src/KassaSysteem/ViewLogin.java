package KassaSysteem;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ViewLogin {
    Controller controller = KassaMain.getControllerMain();

    // constructor
    public ViewLogin(){ showViewLogin(); }

    public void showViewLogin(){
        GridPane viewLoginPane = new GridPane();

        Label lNoReceipt = new Label("Inloggen");

        viewLoginPane.add(lNoReceipt,0,0);
        viewLoginPane.setValignment(lNoReceipt, VPos.CENTER);
        viewLoginPane.setHalignment(lNoReceipt, HPos.CENTER);

        Button btnLogin = new Button("OK");
        btnLogin.setMinHeight(50);
        btnLogin.setMinWidth(300);
        viewLoginPane.add(btnLogin,0,1);

//        Scene loginScene = new Scene(viewLoginPane,300,75);
        Stage loginStage = new Stage();

        btnLogin.setOnAction(e -> {
            controller.btnLogin();
            loginStage.close();
        });

        loginStage.initStyle(StageStyle.UNDECORATED);
        loginStage.setTitle("Login");
        loginStage.setScene(new Scene(viewLoginPane,300,170));
        loginStage.setResizable(false);
        loginStage.setAlwaysOnTop(true);
        loginStage.initModality(Modality.APPLICATION_MODAL); // blocks all other windows and functionalities
        loginStage.show();
    }
}
