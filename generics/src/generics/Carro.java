package generics;

public abstract class Carro implements Veiculo{

    public void liga(){
        System.out.println("Ligando o carro");
    }

    public void pinta(String color){
        System.out.printf("Mudando a cor para %s", color);
    }

}
