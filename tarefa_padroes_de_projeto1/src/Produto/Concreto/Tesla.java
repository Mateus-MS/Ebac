package Produto.Concreto;

import Produto.Abstrato.Carro;

public class Tesla implements Carro {

    @Override
    public void liga(){
        System.out.println("Ligando o Tesla.");
    }

}
