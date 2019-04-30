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

    public static void main(String[] args){
        System.out.println("## Init : Booting KassaySysteem 1.0 ");

        KassaMain kassa_systeem = new KassaMain();

        // // kickstart the program
        System.out.println("## Booting : JsonParser");
        JsonParser parser = new JsonParser();
        parser.readFile("data.json"); // lees de data in (en verplaatst deze in een LinkedList van product objecten)
        kassa_systeem.data_collection = parser.getDataCollection(); // verkrijg de lijst van producten uit de parser.

        System.out.println("## Booting consumer");
        kassa_systeem.consumer();

        System.out.println("## Booting producer");
//        Producer producer = new Producer(kassa_systeem.data_collection);
//        producer.produce();

//        kassa_systeem.testShowData();
        System.out.println("End of line");
    }

    public void consumer(){
        View view = new View();
        Thread consumerThread = new Thread(new Runnable(){
            @Override
            public void run(){
                System.out.println("Starting consumer thread");
                view.showView();
            }
        });

        Thread setInitialDataThread = new Thread(new Runnable(){
            @Override
            public void run(){
                view.getDataFromMain(data_collection);
                view.current_product_name = data_collection.get(1).product_name;

            }
        });
        setInitialDataThread.start();
        consumerThread.start();


    }

    public LinkedList<Product> getData(){
        return data_collection;
    }
}
