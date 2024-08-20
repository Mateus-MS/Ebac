import Fabrica.Abstrato.FabricaCarro;
import Fabrica.Concreto.FabricaTesla;
import Fabrica.Concreto.FabricaToyota;
import Produto.Abstrato.Carro;

public class Main {
    public static void main(String[] args) {
        FabricaCarro fabricaTesla = new FabricaTesla();
        FabricaCarro fabricaToyota = new FabricaToyota();

        Carro tesla = fabricaTesla.createCarro();
        Carro toyota = fabricaToyota.createCarro();

        System.out.println("*********  Cars exemple  *********");
        System.out.println();
        tesla.liga();
        toyota.liga();
        System.out.println();
        System.out.println("----------------------------------");
    }
}