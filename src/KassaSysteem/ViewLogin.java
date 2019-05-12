// Date : Last modified : 09-05-2019
// Author : Sietze Min

/*
    Used sources :
    1. https://stackoverflow.com/questions/30680570/javafx-button-border-and-hover
    2. https://docs.oracle.com/javafx/2/ui_controls/label.htm
*/

package KassaSysteem;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ViewLogin {
    Controller controller = KassaMain.getControllerMain();
    Stage loginStage = new Stage();
    String user_input = "";

    // logo
    Image img_logo = new Image(getClass().getResourceAsStream("images/sietzekassalogo.png"));

    // Button styles
    final String IDLE_BTN_OK_STYLE = "-fx-background-color: #80d6b1";
    final String HOVER_BTN_OK_STYLE = "-fx-background-color: #4ea07d";

    // constructor
    public ViewLogin(){
        showViewLoginBackground();
        showViewLogin();
    }

    public void showViewLoginBackground(){
        GridPane viewLoginPane = new GridPane();
        viewLoginPane.getColumnConstraints().add(new ColumnConstraints(117));
        viewLoginPane.getColumnConstraints().add(new ColumnConstraints(116));
        viewLoginPane.getColumnConstraints().add(new ColumnConstraints(117));

        Label lbl = new Label("Label");
        lbl.setStyle("-fx-background-color: rgba(43,60,99,1)");

        Scene loginScene = new Scene(viewLoginPane,1200,725);
        loginStage.setScene(loginScene);

        viewLoginPane.setStyle("-fx-background-color: rgba(15, 32, 58, 0.9);");

        loginScene.setFill(null);
        loginStage.initStyle(StageStyle.TRANSPARENT);

        loginStage.setTitle("Login");
        loginStage.setResizable(false);
//        loginStage.setAlwaysOnTop(true);
        loginStage.initModality(Modality.APPLICATION_MODAL); // blocks all other windows and functionalities

        loginStage.show();
    }

    public void showViewLogin(){
        Stage loginViewStage = new Stage();
        GridPane viewLoginGridPane = new GridPane();
        viewLoginGridPane.setStyle("-fx-background-color: #0f203a");

        // Rij 0
        Label lblLogo = new Label();
        lblLogo.setGraphic(new ImageView(img_logo));
        lblLogo.setMinWidth(116);
        viewLoginGridPane.setHalignment(lblLogo,HPos.CENTER);
        lblLogo.setPadding(new Insets(25,0,10,5));
        viewLoginGridPane.add(lblLogo,1,0);

//        // Rij 1
       Label lblTitle = new Label("SietzeKassa");
       lblTitle.setFont(Font.font("Cambria", 22));
       lblTitle.setTextFill(Color.web("#ffffff"));
        lblTitle.setPadding(new Insets(0,0,25,-10));
       lblTitle.setMinWidth(116);
       viewLoginGridPane.add(lblTitle,1,1,2,1);

        // Rij 2
        Label lblTime = new Label("");
        lblTime.setFont(Font.font("Cambria", 20));
        lblTime.setTextFill(Color.web("#8d9cb2"));
        viewLoginGridPane.setHalignment(lblTime, HPos.CENTER);
        lblTime.setPadding(new Insets(0,0,10,0));
        viewLoginGridPane.add(lblTime,1,2);
//
        // Rij 3
        Label lblDate = new Label(controller.getModelDate());
        lblDate.setFont(Font.font("Cambria", 12));
        lblDate.setTextFill(Color.web("#8d9cb2"));
        viewLoginGridPane.setHalignment(lblDate, HPos.CENTER);
        lblDate.setPadding(new Insets(0,0,10,0));
        viewLoginGridPane.add(lblDate,1,3);

        // Rij 3
        Label lblPasscode = new Label();
        lblPasscode.setFont(Font.font("Cambria", 24));
        lblPasscode.setTextFill(Color.web("#ffffff"));
        viewLoginGridPane.setHalignment(lblPasscode, HPos.CENTER);
        lblPasscode.setPadding(new Insets(10,0,10,0));
        viewLoginGridPane.add(lblPasscode,1,4);
//
//        // Rij 5
        Button btnNum1 = new Button("1");
        btnNum1.setMinHeight(50);
        btnNum1.setMinWidth(117);
        btnNum1.setOnAction(e -> controller.btnUserInput("1"));
        viewLoginGridPane.add(btnNum1,0,5);

        Button btnNum2 = new Button("2");
        btnNum2.setMinHeight(50);
        btnNum2.setMinWidth(116);
        btnNum2.setOnAction(e -> controller.btnUserInput("2"));
        viewLoginGridPane.add(btnNum2,1,5);

        Button btnNum3 = new Button("3");
        btnNum3.setMinHeight(50);
        btnNum3.setMinWidth(117);
        btnNum3.setOnAction(e -> controller.btnUserInput("3"));
        viewLoginGridPane.add(btnNum3,2,5);

        // Rij 6
        Button btnNum4 = new Button("4");
        btnNum4.setMinHeight(50);
        btnNum4.setMinWidth(117);
        btnNum4.setOnAction(e -> controller.btnUserInput("4"));
        viewLoginGridPane.add(btnNum4,0,6);

        Button btnNum5 = new Button("5");
        btnNum5.setMinHeight(50);
        btnNum5.setMinWidth(116);
        btnNum5.setOnAction(e -> controller.btnUserInput("5"));
        viewLoginGridPane.add(btnNum5,1,6);

        Button btnNum6 = new Button("6");
        btnNum6.setMinHeight(50);
        btnNum6.setMinWidth(117);
        btnNum6.setOnAction(e -> controller.btnUserInput("6"));
        viewLoginGridPane.add(btnNum6,2,6);

        // Rij 7
        Button btnNum7 = new Button("7");
        btnNum7.setMinHeight(50);
        btnNum7.setMinWidth(117);
        btnNum7.setOnAction(e -> controller.btnUserInput("7"));
        viewLoginGridPane.add(btnNum7,0,7);

        Button btnNum8 = new Button("8");
        btnNum8.setMinHeight(50);
        btnNum8.setMinWidth(116);
        btnNum8.setOnAction(e -> controller.btnUserInput("8"));
        viewLoginGridPane.add(btnNum8,1,7);

        Button btnNum9 = new Button("9");
        btnNum9.setMinHeight(50);
        btnNum9.setMinWidth(117);
        btnNum9.setOnAction(e -> controller.btnUserInput("9"));
        viewLoginGridPane.add(btnNum9,2,7);

        // Rij 8
        Button btnOk = new Button("OK");
        btnOk.setMinHeight(50);
        btnOk.setMinWidth(117);
        btnOk.setStyle(IDLE_BTN_OK_STYLE);
        viewLoginGridPane.add(btnOk,0,8);

        Button btnClr = new Button("C");
        btnClr.setMinHeight(50);
        btnClr.setMinWidth(116);
        btnClr.setOnAction(e -> controller.btnClr());
        viewLoginGridPane.add(btnClr,1,8);

        Button btnNum0 = new Button("0");
        btnNum0.setMinHeight(50);
        btnNum0.setMinWidth(117);
        btnNum0.setOnAction(e -> controller.btnUserInput("0"));
        viewLoginGridPane.add(btnNum0,2,8);

        new Thread(new Runnable(){
            @Override
            public void run(){
                    while(true){
                        try {
                            Platform.runLater(new Runnable(){
                                @Override
                                public void run(){
                                    lblTime.setText(controller.getModelTime());
                                    lblPasscode.setText(controller.getUserInput());
                                }
                            });
                            Thread.sleep(100);
                        } catch(InterruptedException ex){
                            ex.printStackTrace();
                        }
                    }
                }
        }).start();

        btnOk.setOnAction(e -> {
            // First thing, current user input needs to be checked against the password.
            controller.checkPasscode();
            lblPasscode.setTextFill(Color.web("#ef3251"));

            if(controller.getPasscodeBool()){
//                lblPasscode.setTextFill(Color.web("#77ce87"));
                loginViewStage.close();
                controller.btnOk();
                loginStage.close();
            }
        });

        // Set border
        viewLoginGridPane.setBorder(new Border(new BorderStroke(Color.rgb(39,66,107), BorderStrokeStyle.SOLID, null, null)));
        Scene loginViewScene = new Scene(viewLoginGridPane,350,465);

        // Button handler, change color and mouse pointer
        btnOk.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                btnOk.setStyle(HOVER_BTN_OK_STYLE);
                loginViewScene.setCursor(Cursor.HAND);
            }
        });

        btnOk.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                btnOk.setStyle(IDLE_BTN_OK_STYLE);
                loginViewScene.setCursor(Cursor.DEFAULT);
            }
        });

        loginViewStage.setScene(loginViewScene);
        loginViewStage.setTitle("Login");
        loginViewStage.setAlwaysOnTop(true);
        loginViewStage.setResizable(false);
        loginViewStage.initModality(Modality.APPLICATION_MODAL);
        loginViewStage.initStyle(StageStyle.UNDECORATED);
        loginViewStage.show();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        loginViewStage.setX((primScreenBounds.getWidth() - loginViewStage.getWidth()) / 2);
        loginViewStage.setY((primScreenBounds.getHeight() - loginViewStage.getHeight()) / 2);

    }
}
