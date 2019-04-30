package KassaSysteem;

public class PerishableProduct extends Product {
    String product_valid_thru = "";

    public PerishableProduct(String name, String brand, String barcode, double price, String valid_tru){
        super(name, brand, barcode,price);
        this.product_valid_thru = valid_tru;
    }

    public String toString(){
        return "Product : " + product_name + "\n Price : " + product_price + "\n Merk : " + product_brand + "\n barcode : " + product_barcode
                + "\n houdtbaar tot : " + product_valid_thru +"\n";
    }

}