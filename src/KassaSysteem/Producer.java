package KassaSysteem;
import java.util.*;

public class Producer {
    LinkedList<Product> products_coll = new LinkedList<>();
    

    public Producer(LinkedList<Product> collection){
        products_coll = collection;
    }

    public void produce(){
        // Thread
        Thread thread1 = new Thread(new Runnable(){
            @Override
            public void run(){
                for(int i = 0; i < 3; i++){
                    try {
                        System.out.println("producer thread");
                        System.out.println(products_coll.get(i).product_name);
                        Thread.sleep(3000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread1.start();

        // try {
        //     thread1.join();
        // } catch (Exception ex){
        //     ex.printStackTrace();
        // }
    }
}
