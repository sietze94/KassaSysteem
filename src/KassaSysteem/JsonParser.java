// Date : 29-04-2019
// Sietze Min

package KassaSysteem;

import java.io.*;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.*;

public class JsonParser {
    JSONParser parser = new JSONParser();
    LinkedList<Product> placeholder_colll = new LinkedList<>();


    public void readFile(String filename){
        try {
            // Try and read the data file
            Object obj = parser.parse(new FileReader("/Users/sietzemin/IdeaProjects/fxtest2/src/KassaSysteem/data.json"));

            // Store data into a collection
            storeDataInCollection(obj);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

     public void storeDataInCollection(Object  obj){
        JSONArray jsonArray = (JSONArray) obj;

        for(int i = 0; i < jsonArray.size(); i++){
            JSONObject json_obj = (JSONObject)jsonArray.get(i);

            // Create variables to use for the object Product creation
            String p_name = (String) json_obj.get("product_name");
            String p_brand = (String) json_obj.get("product_brand");
            String p_barcode = (String) json_obj.get("product_barcode");
            Double p_price = (Double) json_obj.get("product_price");
            String p_valid_thru = (String) json_obj.get("product_valid_thru");

            // Maak nieuw Product object aan met variabelen uit de data
            if(p_valid_thru == null){
                // er is geen houdbaarheids datum voor dit product
                Product product = new Product(p_name, p_brand,p_barcode,p_price);

                // voeg het product toe aan de collectie
                placeholder_colll.add(product);

            } else {
                // er is wel een houdbaarheids datum voor dit product
                Product perishable_prod = new PerishableProduct(p_name,p_brand,p_barcode,p_price,p_valid_thru);

                // voeg het product toe aan de collectie.
                placeholder_colll.add(perishable_prod);

            }
        }
        System.out.println(" ## Notification : JsonParer : data successfully loaded and transformed into a collection");
     } // end of storeDataInCollectiokn

    public LinkedList<Product> getDataCollection(){
        return placeholder_colll;
    }
}