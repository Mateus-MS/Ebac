package Fabrica.Concreto;

import Fabrica.Abstrato.FabricaCarro;
import Produto.Abstrato.Carro;
import Produto.Concreto.Toyota;

public class FabricaToyota implements FabricaCarro {

    @Override
    public Carro createCarro(){
        return new Toyota();
    }

}
