/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author Quan
 */
public class Cart {
    private int productID;
    private String name;
    private int ammount;
    private double price;
    
    public Cart() {
    }

    public Cart(int productID, String name, int ammount, double price) {
        this.productID = productID;
        this.name = name;
        this.ammount = ammount;
        this.price = price;
    }
    
    

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmmount() {
        return ammount;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

   
    
}
