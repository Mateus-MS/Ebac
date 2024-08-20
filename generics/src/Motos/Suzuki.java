package Motos;

import generics.Moto;

public class Suzuki extends Moto {

    public Suzuki(){
        super();
    }

    public void liga(){
        System.out.println("Ligando a suzuki");
    }

    public void pinta(String color){
        System.out.println("Pitando a suzuki de " + color);
    }

}
