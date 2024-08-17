import cafe.Cafe;
import cafeteiras.Cafeteira2000;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Cafeteira2000 cafeteira = new Cafeteira2000();

        cafeteira.selecionaMarca("Santa Mônica");
        cafeteira.selecionaTipo("Latte");
        cafeteira.reservatorioDeAgua.adicionaAgua(70);
        cafeteira.filtro.remove();
        cafeteira.filtro.adiciona();
        cafeteira.reservatorioDeCafe.adicionaCafe(50, cafeteira.filtro.verOEstado());
        System.out.println("Seu café começou a ser preparado, leva 60 segundos...");
        Cafe xicara = cafeteira.liga();

        cafeteira.descreve();

    }
}