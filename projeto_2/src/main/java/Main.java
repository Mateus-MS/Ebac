package main.java;

public class Main {
    public static void main(String[] args) {

        App app = new App();

        while(true) {

            //Se escolher uma função inválida repete
            if(!app.selecionaServico(app.mostraPergunta("Escolha um servico:", "1 - Clientes, 2 - Produtos, 3 - Funcionarios"))){
                continue;
            }

            //Se escolher uma função inválida repete
            if(!app.selecionaAcction(app.mostraPergunta("Escolha uma ação:", "1 - Cadastrar, 2 - Pesquisar, 3 - Excluir, 4 - Atualizar"))){
                continue;
            }

            switch (app.acctionSelected){
                case "1" -> app.tentaRegistrar();
                case "2" -> app.tentaPesquisar();
                case "3" -> app.tentaExcluir();
                case "4" -> app.tentaAtualizar();
            }

        }

    }

}