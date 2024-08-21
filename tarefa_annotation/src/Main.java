import annotations.TabelaAnnotation;

public class Main {
    public static void main(String[] args) {

        @TabelaAnnotation(nome = "Clientes")
        Tabela tabela = new Tabela();

    }

    public static class Tabela{

    }

}