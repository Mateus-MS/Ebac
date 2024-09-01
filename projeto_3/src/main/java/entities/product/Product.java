package main.java.entities.product;

import main.java.entities.z_generics.IGenericEntity;

public class Product implements IGenericEntity {

    private final String  name;
    private final int     price;
    private final boolean available;

    public Product(String name, int price, boolean available){
        this.name      = name;
        this.price     = price;
        this.available = available;
    }

    public String getName(){
        return this.name;
    }

    public int getPrice(){
        return this.price;
    }

    public boolean getAvailable(){
        return this.available;
    }

}
