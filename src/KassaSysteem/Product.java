package KassaSysteem;
public class Product {
    String product_name;
    String product_brand;
    String product_barcode;
    String product_valid_thru = "";
    double product_price;

    // constructor
    public Product(String name, String brand, String barcode, double price){
        product_name = name;
        product_brand = brand;
        product_barcode = barcode;
        product_price = price;
//        product_valid_thru = valid_thru;
    }

    public String toString(){
        return "Product : " + product_name + "\n Price : " + product_price + "\n Merk : " + product_brand + "\n barcode : " + product_barcode +
                "\n houdbaar tot :" + product_valid_thru;
    }
}