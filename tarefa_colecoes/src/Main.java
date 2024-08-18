import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Exemplo: Fernando - M . Clara - F");
        System.out.println();
        System.out.print("Insira os nomes seguindo o exemplo acima: ");
        String entradaCrua = scan.nextLine();
        String[] arrayDePessoas = entradaCrua.split("\\.");

        List<String> homens   = new ArrayList<>();
        List<String> mulheres = new ArrayList<>();
        List<String> erros    = new ArrayList<>();

        for( String pessoa : arrayDePessoas){
            // 0 = Nome
            // 1 = Sexo
            String[] dados = pessoa.split("-");

            //Remove todos espaços em branco
            dados[1] = dados[1].replaceAll(" ", "");

            if(dados[1].equals("M")){
                homens.add(dados[0]);
                continue;
            }

            if(dados[1].equals("F")){
                mulheres.add(dados[0]);
                continue;
            }
            erros.add(dados[0].replaceAll(" ", "") + " - " + dados[1]);
        }

        Tabela tab = new Tabela(homens, mulheres);

        tab.mostra();

        if(!erros.isEmpty()){
            System.out.println("\u001B[31mERROS\u001B[37m:");
            for(String erro : erros){
                System.out.println(erro);
            }
        }

    }

    private static class Tabela{

        private List<String> homens;
        private List<String> mulheres;

        private short maiorNome;
        private short maiorListaTamanho;

        public Tabela(List<String> _homens, List<String> _mulheres){
            this.homens   = _homens;
            this.mulheres = _mulheres;

            this.initMaiorLista();
            this.initMaiorNome();
        }

        /**
         * Encapsula a inicialização
         */
        private void initMaiorNome(){
            for(String name : this.homens){
                if(name.length() > this.maiorNome){
                    this.maiorNome = (short) name.length();
                }
            }

            for(String name : this.mulheres){
                if(name.length() > this.maiorNome){
                    this.maiorNome = (short) name.length();
                }
            }

            this.maiorNome += 6;
        }

        /**
         * Encapsula a inicialização
         */
        private void initMaiorLista(){
            if(this.homens.size() > this.mulheres.size()){
                this.maiorListaTamanho = (short) this.homens.size();
            } else {
                this.maiorListaTamanho = (short) this.mulheres.size();
            }
        }

        public void mostra(){

            System.out.println();

            if(!this.homens.isEmpty()){
                separadorDeCima();
            }
            if(!this.homens.isEmpty()){
                printaNomesMasculinos();
            }
            separadorDoMeio();
            if(!this.mulheres.isEmpty()){
                printaNomesFemininos();
            }
            if(!this.mulheres.isEmpty()){
                separadorDeBaixo();
            }
        }

        /**
         * Encapsula logica de impressão dos nomes masculinos
         */
        private void printaNomesMasculinos(){
            System.out.print("│   Homens   ");
            for(String nome : this.homens){
                printaNome(nome);
            }
            System.out.println("│");
        }

        /**
         * Encapsula logica de impressão dos nomes femininos
         */
        private void printaNomesFemininos(){
            System.out.print("│  Mulheres  ");
            for(String nome : this.mulheres){
                printaNome(nome);
            }
            System.out.println("│");
        }

        /**
         * Printa o nome passado como parametro adicionando os espaços necessários para que o mesmo fique centralizado em relação ao maior nome recebido
         */
        private void printaNome(String nome){
            int metadeDaDiferenca = (this.maiorNome - nome.length()) / 2;
            System.out.print("│" + " ".repeat(metadeDaDiferenca) + nome + " ".repeat(metadeDaDiferenca));

            boolean maiorNomePar = this.maiorNome % 2 == 0;
            boolean nomeAtualPar = nome.length()  % 2 == 0;
            if(maiorNomePar != nomeAtualPar){
                System.out.print(" ");
            }
        }

        /**
         * Seu tamanho depende do tamanho da lista de nomes masculinos
         */
        private void separadorDeCima(){
            System.out.print("┌────────────");

            if(this.homens.isEmpty()){
                System.out.print("┐");
            } else {
                System.out.print("┬");
            }

            for(int i = 0; i < this.homens.size(); i++){
                System.out.print("─".repeat(this.maiorNome));

                //Quando for o ultimo
                if(i == this.homens.size() - 1){
                    System.out.print("┐");
                } else {
                    System.out.print("┬");
                }
            }
            System.out.println();
        }

        /**
         * Seu tamanho depende do tamanho da maior lista
         */
        private void separadorDoMeio(){

            if(this.homens.isEmpty()){
                System.out.print("┌");
            } else {
                if(this.mulheres.isEmpty()){
                    System.out.print("└");
                } else {
                    System.out.print("├");
                }
            }

            System.out.print("────────────");

            if(this.homens.isEmpty()){
                System.out.print("┬");
            } else {
                if(this.mulheres.isEmpty()){
                    System.out.print("┘");
                } else {
                    System.out.print("┼");
                }
            }

            short maiorLista;
            short menorLista;

            if(this.maiorListaTamanho == this.homens.size()){
                maiorLista = (short) this.homens.size();
                menorLista = (short) this.mulheres.size();
            } else {
                maiorLista = (short) this.mulheres.size();
                menorLista = (short) this.homens.size();
            }

            for(int i = 0; i < maiorLista; i++){
                System.out.print("─".repeat(this.maiorNome));

                if(maiorLista == menorLista){
                    System.out.print("┤");
                } else {
                    if(i == this.maiorListaTamanho - 1){
                        if(this.maiorListaTamanho == this.homens.size()){
                            System.out.print("┘");
                        } else {
                            System.out.print("┐");
                        }
                    } else {
                        if(i > menorLista - 1){
                            if(maiorLista == this.homens.size()){
                                System.out.print("┴");
                            } else {
                                System.out.print("┬");
                            }
                        } else {
                            System.out.print("┼");
                        }
                    }
                }

            }
            System.out.println();
        }

        /**
         * Seu tamanho depende do tamanho da lista de nomes femininos
         */
        private void separadorDeBaixo(){
            System.out.print("└────────────┴");

            for(int i = 0; i < this.mulheres.size(); i++){
                System.out.print("─".repeat(this.maiorNome));

                //Quando for o ultimo
                if(i == this.mulheres.size() - 1){
                    System.out.print("┘");
                } else {
                    System.out.print("┴");
                }
            }
            System.out.println();
        }

    }

}