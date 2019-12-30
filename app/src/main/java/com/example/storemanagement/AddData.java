package com.example.storemanagement;

public class AddData {

    private String product_name;
    private String Product_quantity;


    public AddData(){
        //empty constructor

    }

    public AddData(String product_name, String Product_quantity) {
        this.product_name = product_name;
        this.Product_quantity = Product_quantity;

    }

    public String getProduct_name() { return product_name;}

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_quantity() {
        return Product_quantity;
    }

    public void setProduct_quantity(String Product_quantity) { this.Product_quantity = Product_quantity; }

}


