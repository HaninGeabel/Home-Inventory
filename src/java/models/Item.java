/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;

/**
 *
 * @author Hanin
 */
public class Item implements Serializable{
    int itemId; 
    Category category; 
    String itemName; 
    double price; 
    User user; 

    public Item() {
    }

    public Item(int itemId,Category category, String itemName, double price, User user) {
       this.itemId = itemId;
        this.category = category;
        this.itemName = itemName;
        this.price = price;
        this.user = user;
    }

    public int getItemId() {
        return itemId;
    }

    public Category getCategory() {
        return category;
    }

    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }

    public User getUser() {
        return user;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}
