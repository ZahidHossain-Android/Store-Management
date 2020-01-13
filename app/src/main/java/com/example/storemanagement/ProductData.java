package com.example.storemanagement;

public class ProductData {

    private String product_name;
    private String product_quantity;


    public ProductData(){
        //require empty constructor
    }

    public ProductData(String product_name, String product_quantity) {
        this.product_name = product_name;
        this.product_quantity = product_quantity;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_quantity() {
        return product_quantity;
    }
}
