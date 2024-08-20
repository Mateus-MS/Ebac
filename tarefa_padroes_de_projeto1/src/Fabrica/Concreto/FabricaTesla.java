package Fabrica.Concreto;

import Fabrica.Abstrato.FabricaCarro;
import Produto.Abstrato.Carro;
import Produto.Concreto.Tesla;

public class FabricaTesla implements FabricaCarro {

    @Override
    public Carro createCarro(){
        return new Tesla();
    }

}
