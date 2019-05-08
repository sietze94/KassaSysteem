package KassaSysteem;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.awt.*;

public class ViewSettings {
    Controller controller;

    // Constructor
    public ViewSettings(Controller c){
        controller = c; // assign reference to local controller variable
        showSettings();
    }

    public void PrintLogs(){
        controller.btnPrintRegisterHistory();
    }

    public void showSettings(){
        Label LpopupMessage = new Label("Uitdraai op bureaublad gemaakt.");
        Button btnPrintRegisterLogs = new Button("print geschiedenis");
        btnPrintRegisterLogs.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                PrintLogs();
                Label LmsgPrintedHistory = new Label("Uitdraai op bureaublad gemaakt");
                VBox msgPrintedHistoryVBox = new VBox(LmsgPrintedHistory);
                System.out.println("button pressed");
                Stage msgPrintedHistoryStage = new Stage();
                msgPrintedHistoryStage.setTitle("popup");
                msgPrintedHistoryStage.setScene(new Scene(msgPrintedHistoryVBox,300,20));
                msgPrintedHistoryStage.show();
            }
        });

        btnPrintRegisterLogs.setMinWidth(300);
        btnPrintRegisterLogs.setMinHeight(50);

        Button btnViewDailyRevenue = new Button("Bekijk dag totaal");
        btnViewDailyRevenue.setOnAction(e -> controller.btnViewDailyRevenue());
        btnViewDailyRevenue.setMinWidth(300);
        btnViewDailyRevenue.setMinHeight(50);

        Button btnLogout = new Button("Uitloggen");
        btnLogout.setOnAction(e -> controller.btnLogout());
        btnLogout.setMinWidth(300);
        btnLogout.setMinHeight(50);

        VBox settingsBox = new VBox(btnPrintRegisterLogs,btnViewDailyRevenue,btnLogout);

        Scene settingsScene = new Scene(settingsBox,300,150);
        Stage settingsStage = new Stage();
        settingsStage.setResizable(false);
        settingsStage.setTitle("Instellingen");
        settingsStage.setScene(settingsScene);
        settingsStage.show();
    }
}
