package KassaSysteem;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
        Button btnPrintRegisterLogs = new Button("print geschiedenis");
        btnPrintRegisterLogs.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                PrintLogs();
                BorderPane msgPrintedHistoryPane = new BorderPane();
                Label LmsgPrintedHistory = new Label("Uitdraai op bureaublad gemaakt");
                msgPrintedHistoryPane.setTop(LmsgPrintedHistory);
                msgPrintedHistoryPane.setAlignment(LmsgPrintedHistory, Pos.TOP_CENTER);
                msgPrintedHistoryPane.setMargin(LmsgPrintedHistory, new Insets(10,10,0,0));

                Button btnCloseWindow = new Button("Sluiten");
                btnCloseWindow.setMinWidth(300);
                btnCloseWindow.setMinHeight(50);
                msgPrintedHistoryPane.setBottom(btnCloseWindow);
                msgPrintedHistoryPane.setAlignment(btnCloseWindow, Pos.BOTTOM_CENTER);

                Stage msgPrintedHistoryStage = new Stage();
                btnCloseWindow.setOnAction(e -> msgPrintedHistoryStage.close());

                msgPrintedHistoryStage.initStyle(StageStyle.UNDECORATED);
                msgPrintedHistoryStage.setTitle("popup");
                msgPrintedHistoryStage.setScene(new Scene(msgPrintedHistoryPane,300,170));
                msgPrintedHistoryStage.setResizable(false);
                msgPrintedHistoryStage.setAlwaysOnTop(true);
                msgPrintedHistoryStage.initModality(Modality.APPLICATION_MODAL); // blocks all other windows and functionalities
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
//        btnLogout.setOnAction(e -> controller.btnLogout());
        btnLogout.setMinWidth(300);
        btnLogout.setMinHeight(50);

        VBox settingsBox = new VBox(btnPrintRegisterLogs,btnViewDailyRevenue,btnLogout);

        Scene settingsScene = new Scene(settingsBox,300,150);
        Stage settingsStage = new Stage();
        settingsStage.initModality(Modality.APPLICATION_MODAL);
        settingsStage.setResizable(false);
        settingsStage.setTitle("Instellingen");
        settingsStage.setScene(settingsScene);
        settingsStage.show();

        btnLogout.setOnAction(e -> {
            controller.btnLogout();
            settingsStage.close();
        });
    }
}
