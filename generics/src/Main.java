import Carros.Bmw;
import Carros.Tesla;
import Motos.Suzuki;
import generics.Carro;
import generics.Moto;
import generics.Veiculo;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Carro> garagemDeCarros = new ArrayList<>();
        garagemDeCarros.add(new Bmw());
        garagemDeCarros.add(new Tesla());

        ligaCarros(garagemDeCarros);

        List<Moto> garagemDeMotos = new ArrayList<>();
        garagemDeMotos.add(new Suzuki());

        //ligaCarros(garagemDeMotos); //ERRO
    }

    public static <T extends Carro> void ligaCarros(List<T> garagem){
        for(T carro : garagem){
            carro.liga();
        }
    }

}