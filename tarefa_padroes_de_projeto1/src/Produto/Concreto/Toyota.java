package Produto.Concreto;

import Produto.Abstrato.Carro;

public class Toyota implements Carro {

    @Override
    public void liga(){
        System.out.println("Ligando o Toyota.");
    }

}
