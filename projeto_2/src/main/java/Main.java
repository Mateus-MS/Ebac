package main.java;

import main.java.client.Client;
import main.java.client.DAO.ClientDAOMock;
import main.java.client.service.ClientService;
import main.java.exceptions.DataAlreadyInDBException;
import main.java.exceptions.DataNotPresentInDBException;
import main.java.funcionario.DAO.FuncionarioDAOMock;
import main.java.funcionario.Funcionario;
import main.java.funcionario.service.FuncionarioService;
import main.java.generics.DAO.IGenericDAO;
import main.java.generics.IGenericEntity;
import main.java.generics.service.IGenericService;
import main.java.product.DAO.ProductDAOMock;
import main.java.product.Product;
import main.java.product.service.ProductService;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        IGenericDAO dao;
        IGenericService service;

        final List<String> fieldsNewClient = Arrays.asList(
                "Insira seu nome:",
                "Insira sua idade:",
                "Insira seu endereço:",
                "Insira seu sexo: ( M | F )",
                "Insira seu cpf:");

        final List<String> fieldsNewProduct = Arrays.asList(
                "Insira o nome:",
                "Insira o código:",
                "Insira o preço:",
                "Insira há estoque: (Sim | Não)");

        final List<String> fieldsNewFuncionario = Arrays.asList(
                "Insira o nome:",
                "Insira o registro:",
                "Insira a idade:",
                "Insira o cargo:",
                "Insira o salário:");

        List<String> fieldsNewSelected;

        while(true) {

            String serviceSelected = JOptionPane.showInputDialog(
                    null,
                    "1 - Clientes, 2 - Produtos, 3 - Funcionarios",
                    "Escolha um servico:",
                    JOptionPane.INFORMATION_MESSAGE
            );

            String message = "1 - Cadastrar, 2 - Pesquisar, 3 - Excluir, 4 - Atualizar";
            String title = "Erro";

            switch (serviceSelected) {
                case "1": {
                    title = "Serviços de cliente";
                    dao = ClientDAOMock.getInstance();
                    service = new ClientService(dao);
                    fieldsNewSelected = fieldsNewClient;
                    break;
                }
                case "2": {
                    title = "Serviços de produto";
                    dao = ProductDAOMock.getInstance();
                    service = new ProductService(dao);
                    fieldsNewSelected = fieldsNewProduct;
                    break;
                }
                case "3": {
                    title = "Serviços de funcionarios";
                    dao = FuncionarioDAOMock.getInstance();
                    service = new FuncionarioService(dao);
                    fieldsNewSelected = fieldsNewFuncionario;
                    break;
                }
                default: {
                    message = "Opção selecionada inválida!";
                    continue;
                }

            }

            String acction = JOptionPane.showInputDialog(
                    null,
                    message,
                    title,
                    JOptionPane.INFORMATION_MESSAGE
            );

            //tentando registrar
            if (acction.equals("1")) {

                //Pega a entrada do usuário
                List<String> fieldsValue = fieldsNewSelected.stream().map(field -> JOptionPane.showInputDialog(null, field, "Cadastro", JOptionPane.INFORMATION_MESSAGE)).toList();

                IGenericEntity newEntity;

                //Cria uma novo cliente
                if(serviceSelected.equals("1")){
                    newEntity = createEntity(fieldsValue, Client.class);
                } else
                //Cria um novo produto
                if (serviceSelected.equals("2")){
                    newEntity = createEntity(fieldsValue, Product.class);
                } else
                //Cria um novo funcionario
                if (serviceSelected.equals("3")){
                    newEntity = createEntity(fieldsValue, Funcionario.class);
                } else {
                    //TODO criar uma exepction
                    //Em teoria não é preciso já que
                    //nessa parte do código chega
                    //apenas um desses 3 valores
                    //Já que já está sendo testado lá em cima
                    throw new RuntimeException();
                }

                try {
                    if(service.register(newEntity)){
                        JOptionPane.showConfirmDialog(
                                null,
                                "Registrado com sucesso",
                                "Sucesso",
                                JOptionPane.DEFAULT_OPTION
                        );
                    }
                } catch (DataAlreadyInDBException e) {
                    JOptionPane.showConfirmDialog(
                            null,
                            "Já registrado no sistema",
                            "Erro ao cadastrar",
                            JOptionPane.DEFAULT_OPTION
                    );
                }

            }

            //Tentando pesquisar
            if (acction.equals("2")){

                String messageSearch = "";

                if(serviceSelected.equals("1")){
                    messageSearch = "Insira o cpf que quer procurar:";
                } else if(serviceSelected.equals("2")) {
                    messageSearch = "Insira o código que quer procurar: ";
                } else {
                    messageSearch = "Insira o registro que quer procurar: ";
                }

                Double code =  Double.parseDouble(JOptionPane.showInputDialog(
                        null,
                        messageSearch,
                        "Buscando",
                        JOptionPane.INFORMATION_MESSAGE
                ));

                Object entity = service.pesquisa(code);

                if(entity != null){
                    JOptionPane.showConfirmDialog(
                            null,
                            entity.toString(),
                            "Encontrado",
                            JOptionPane.DEFAULT_OPTION
                    );
                } else {
                    JOptionPane.showConfirmDialog(
                            null,
                            "Não registrado no Banco de dados",
                            "Error",
                            JOptionPane.DEFAULT_OPTION
                    );
                }

            }

            //Tentando excluir
            if (acction.equals("3")){
                String messageSearch = "";
                String titleSearch = "";
                String erroMessage = "";

                if(serviceSelected.equals("1")){
                    messageSearch = "Insira o cpf que quer excluir:";
                    titleSearch = "Excluindo cliente";
                    erroMessage = "Não existe um cliente com esse cpf";
                } else if(serviceSelected.equals("2")) {
                    messageSearch = "Insira o código que quer excluir: ";
                    titleSearch = "Excluindo produto";
                    erroMessage = "Não existe um produto com esse código";
                } else {
                    messageSearch = "Insira o registro que quer excluir: ";
                    titleSearch = "Excluindo funcionario";
                    erroMessage = "Não existe um funcionario com esse registro";
                }

                Double code =  Double.parseDouble(JOptionPane.showInputDialog(
                        null,
                        messageSearch,
                        titleSearch,
                        JOptionPane.INFORMATION_MESSAGE
                ));

                try {
                    service.excluir(code);
                    JOptionPane.showConfirmDialog(
                            null,
                            "Excluido com sucesso!",
                            "Excluido.",
                            JOptionPane.DEFAULT_OPTION
                    );
                } catch (DataNotPresentInDBException e){
                    JOptionPane.showConfirmDialog(
                            null,
                            erroMessage,
                            "Erro",
                            JOptionPane.DEFAULT_OPTION
                    );
                }

            }

            //Tentando atualizar
            if(acction.equals("4")){

                String messageSearch = "";
                String titleSearch = "";
                String erroMessage = "";

                if(serviceSelected.equals("1")){
                    messageSearch = "Insira o cpf que quer atualizar:";
                    titleSearch = "Atualizar cliente";
                    erroMessage = "Não existe um cliente com esse cpf";
                } else if(serviceSelected.equals("2")) {
                    messageSearch = "Insira o código que quer atualizar: ";
                    titleSearch = "Atualizando produto";
                    erroMessage = "Não existe um produto com esse código";
                } else {
                    messageSearch = "Insira o registro que quer atualizar: ";
                    titleSearch = "Atualizando funcionario";
                    erroMessage = "Não existe um funcionario com esse registro";
                }

                Double code =  Double.parseDouble(JOptionPane.showInputDialog(
                        null,
                        messageSearch,
                        titleSearch,
                        JOptionPane.INFORMATION_MESSAGE
                ));

                List<String> fieldsValue;
                if(service.pesquisa(code) != null){
                    //Pega a entrada do usuário
                    fieldsValue = fieldsNewSelected.stream().map(field -> JOptionPane.showInputDialog(null, field, "Atualizando", JOptionPane.INFORMATION_MESSAGE)).toList();
                } else {
                    JOptionPane.showConfirmDialog(
                            null,
                            erroMessage,
                            "Erro",
                            JOptionPane.DEFAULT_OPTION
                    );
                    continue;
                }

                IGenericEntity newEntity = switch (serviceSelected) {
                    case "1" -> createEntity(fieldsValue, Client.class);
                    //Cria um novo produto
                    case "2" -> createEntity(fieldsValue, Product.class);
                    //Cria um novo funcionario
                    case "3" -> createEntity(fieldsValue, Funcionario.class);
                    default ->
                        //TODO criar uma exepction
                        //Em teoria não é preciso já que
                        //nessa parte do código chega
                        //apenas um desses 3 valores
                        //Já que já está sendo testado lá em cima
                        throw new RuntimeException();
                };

                service.atualiza(newEntity);

            }

        }

    }

    public static <T extends IGenericEntity> T createEntity(List<String> data, Class<T> reference){

        if(reference.equals(Client.class)){
            return reference.cast(new Client(
                    data.get(0),
                    Integer.parseInt(data.get(1)),
                    data.get(2),
                    data.get(3).charAt(0),
                    Double.parseDouble(data.get(4))
            ));
        }

        if(reference.equals(Product.class)){
            boolean state = data.get(3).toLowerCase().charAt(0) == 's';
            return reference.cast(new Product(
                    data.get(0),
                    Double.parseDouble(data.get(1)),
                    Float.parseFloat(data.get(2)),
                    state
            ));
        }

        if(reference.equals(Funcionario.class)){
            return reference.cast(new Funcionario(
                    data.get(0),
                    Double.parseDouble(data.get(1)),
                    Integer.parseInt(data.get(2)),
                    data.get(3),
                    Integer.parseInt(data.get(4))
            ));
        }

        //TODO criar um custom excpetion
        throw new RuntimeException();

    }

}