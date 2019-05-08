// Date : 28-04-2019
// Author : Sietze Min
// Description :
/*
Used sources : https://stackoverflow.com/questions/25873769/launch-javafx-application-from-another-class
 */

package KassaSysteem;
import javafx.application.Application;

import java.util.LinkedList;

public class KassaMain {
    Model model = new Model();
    Register register;
    private static Controller controller = new Controller(); // local referrence to the controller obj

    public static void main(String[] args){
        // // kickstart the program
        System.out.println("## Booting KassaySysteem 1.0 ");
        KassaMain kassa_systeem = new KassaMain();

        System.out.println("### Executing Main function (kassa_systeem.Main())");
        kassa_systeem.Main();
        System.out.println("End of line");
    }

    // for other classes to acces the controller (should only be 1 controller, singleton ?)
    public static Controller getControllerMain(){
        return controller;
    }

    // Creates the view object, and starts a thread to execute the view.
    public void Main(){
        System.out.println("## Init : Booting : JsonParser");
        JsonParser parser = new JsonParser();
        parser.readFile("data.json"); // lees de data in (en verplaatst deze in een LinkedList van product objecten)

        System.out.println("### 01 Main boots Register");
        register = new Register(1,250, controller); // add a new register with some cash

        controller.setRegister(register);

        System.out.println("### 02 Main creates controller object with model as arg");
        controller.setModel(model);

        System.out.println("### 03 Data uit parser wordt via controller naar de model gestuurt");
        controller.GetDataFromParserToModel(parser.ParserGetDataCollection());
//        controller.sendDataToView(); // View retrieves a local copy from the data to work with, (should not be needed)
        Thread mainThread = new Thread(new Runnable(){
            @Override
            public void run(){
                System.out.println("Starting consumer thread");
                register.showRegisterView();
            }
        });
        mainThread.start();


    } // end of main
}
