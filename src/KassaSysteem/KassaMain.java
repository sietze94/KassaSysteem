// Date : 28-04-2019
// Author : Sietze Min
// Description :
/*
Used sources : https://stackoverflow.com/questions/25873769/launch-javafx-application-from-another-class
 */

package KassaSysteem;

//import javafx.application.Application;

import javafx.application.Application;

import java.util.LinkedList;

public class KassaMain {
    LinkedList<Product> data_collection;
    View view; // local referrence to the view obj
    Model model;
    Controller controller; // local referrence to the controller obj

    public static void main(String[] args){
        // // kickstart the program
        System.out.println("## Booting KassaySysteem 1.0 ");
        KassaMain kassa_systeem = new KassaMain();
//        kassa_systeem.getDataCollection(parser.placeholder_colll);// verkrijg de lijst van producten uit de parser.

        System.out.println("### Executing Main function (kassa_systeem.Main())");
        kassa_systeem.Main();
        System.out.println("End of line");
    }

    /*
    Creates the view object, and starts a thread to execute the view.
    Start another thread to initially pass the data to the view object. (this needs to be in the controller)
     */
    public void Main(){
        System.out.println("## Init : Booting : JsonParser");
        JsonParser parser = new JsonParser();
        parser.readFile("data.json"); // lees de data in (en verplaatst deze in een LinkedList van product objecten)

        System.out.println("### 01 Main boots View");
        this.view = new View();
        this.model = new Model();

        System.out.println("### 02 Main creates controller object with model as arg");
        this.controller = new Controller(model,view);

        System.out.println("### 03 Data uit parser wordt via controller naar de model gestuurt");
        controller.GetDataFromParserToModel(parser.ParserGetDataCollection());
        controller.sendDataToView(); // View haalt de data rechtstreeks uit de controller.
//        view.setController(controller);

        Thread mainThread = new Thread(new Runnable(){
            @Override
            public void run(){
                System.out.println("Starting consumer thread");
//                System.out.println(view.controller1.testString);
                view.showView();

            }
        });

        mainThread.start();

    }
}
