package services.product;

import annotations.Serviceidentifier;

@Serviceidentifier("getCode")
public class Product {
    private final String  name;
    private final double  code;
    private final float   price;
    private final boolean available;

    public Product(String _name, double _code, float _price, boolean _available){
        this.name      = _name;
        this.code      = _code;
        this.price     = _price;
        this.available = _available;
    }

    //The way to uniquely identify a Product
    public double getCode(){
        return this.code;
    }

    @Override
    public String toString(){
        String isAvailable = this.available ? "Sim" : "Não";

        return  "Name       : " + this.name + System.lineSeparator() +
                "Code       : " + this.code + System.lineSeparator() +
                "Price      : " + this.price + System.lineSeparator() +
                "Has stock  : " + isAvailable + System.lineSeparator();
    }

    //Getters
    public String getName(){
        return this.name;
    }

    public float getPrice(){
        return this.price;
    }

    public boolean getIsAvailable(){
        return this.available;
    }

}
