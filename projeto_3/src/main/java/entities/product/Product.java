package main.java.entities.product;

import main.java.entities.z_generics.IGenericEntity;

public class Product implements IGenericEntity {

    private final String  name;
    private final int     price;
    private final boolean available;
    private final int     ammount;

    public Product(String name, int price, boolean available, int ammount){
        this.name      = name;
        this.price     = price;
        this.available = available;
        this.ammount   = ammount;
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

    public int getAmmount() { return this.ammount; }

}
